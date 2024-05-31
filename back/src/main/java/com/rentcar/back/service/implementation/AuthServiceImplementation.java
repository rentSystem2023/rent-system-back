package com.rentcar.back.service.implementation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rentcar.back.common.util.EmailAuthNumberUtil;
import com.rentcar.back.dto.request.auth.EmailAuthCheckRequestDto;
import com.rentcar.back.dto.request.auth.EmailAuthRequestDto;
import com.rentcar.back.dto.request.auth.FindIdRequestDto;
import com.rentcar.back.dto.request.auth.IdCheckRequestDto;
import com.rentcar.back.dto.request.auth.NickNameCheckRequestDto;
import com.rentcar.back.dto.request.auth.SignInRequestDto;
import com.rentcar.back.dto.request.auth.SignUpRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.auth.FindIdResponseDto;
import com.rentcar.back.dto.response.auth.SignInResponseDto;
import com.rentcar.back.entity.EmailAuthNumberEntity;
import com.rentcar.back.entity.NoticeBoardEntity;
import com.rentcar.back.entity.UserEntity;
import com.rentcar.back.provider.JwtProvider;
import com.rentcar.back.provider.MailProvider;
import com.rentcar.back.repository.EmailAuthNumberRepository;
import com.rentcar.back.repository.UserRepository;
import com.rentcar.back.service.AuthService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;


@Service // 이 클래스를 서비스 클래스로 사용하기 위함
@RequiredArgsConstructor //의존성 주입을 위해 사용
public class AuthServiceImplementation implements AuthService {

    private final UserRepository userRepository;
    private final EmailAuthNumberRepository emailAuthNumberRepository;
    private final MailProvider mailProvider;

    // 암호화된 비밀번호
    private final JwtProvider jwtProvider;

    // 패스워드 암호화를 하기 위해 password 인코더
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    
    // Auth 모듈의 비즈니스 로직 구현체
    @Override
    public ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto) {

        // user 테이블에서 해당하는 userEmail를 가지고 있는 유저가 있는지 확인 할때는 반드시 예외가 발생할 수 있어 try catch
        // 작업
        try {
            // user Id 를 가져오는 작업
            String userId = dto.getUserId();
            // 확인할 때는 boolean 으로 있다 없다만 작업해주면 된다.
            boolean existedUser = userRepository.existsById(userId);
            if (existedUser)
                return ResponseDto.duplicatedId();

        } catch (Exception exception) {
            exception.printStackTrace();
            // 스태틱으로 지정했기에 databaseError를 바로 사용할 수 있음
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> nickNameCheck(NickNameCheckRequestDto dto) {

        try {
            String nickName = dto.getNickName();
            boolean existedUser = userRepository.existsByNickName(nickName);
            if (existedUser)
                return ResponseDto.duplicatedNickName();

        } catch (Exception exception) {
            exception.printStackTrace();
            // 스태틱으로 지정했기에 databaseError를 바로 사용할 수 있음
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        // 밑에서 사용하기 위해 위에서 잡아주는 작업
        String accessToken = null;

        try {
            // - (userId , userPassword)
            String userId = dto.getUserId();
            String userPassword = dto.getUserPassword();

            UserEntity userEntity = userRepository.findByUserId(userId);
            // 유저엔티티는 null
            if (userEntity == null)
                return ResponseDto.signInFailed();

            // 암호화된 비밀번호는 userEntity에 있음으로 user entity. 으로 꺼내옴
            String encodedPassword = userEntity.getUserPassword();
            boolean isMatched = passwordEncoder.matches(userPassword, encodedPassword); // 평뮨의 비밀번호 , 암호화된 비밀번호 비교
            // 만약 일치하지 않는다면?
            if (!isMatched)
                return ResponseDto.signInFailed();

            accessToken = jwtProvider.create(userId);
            if (accessToken == null)
                return ResponseDto.tokenCreationFailed();

        } catch (Exception exception) {
            exception.printStackTrace();
            
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(accessToken);
    }

    @Override
    public ResponseEntity<ResponseDto> emailAuth(EmailAuthRequestDto dto) {

        try {

            // 레포지토리에서 existByUserEmail 작업 해주고
            String userEmail = dto.getUserEmail();
            boolean existedEmail = userRepository.existsByUserEmail(userEmail);
            // 존재한다면
            if (existedEmail)
                return ResponseDto.duplicatedEmail();

            String authNumber = EmailAuthNumberUtil.createCodeNumber();

            // 바로 위에서 받은 인증번호 저장하려면 엔터티를 만들어 줘야 함
            EmailAuthNumberEntity emailAuthNumberEntity = new EmailAuthNumberEntity(userEmail, authNumber);

            // 그 다음 save 작업 해줘야 함 그러기 위해서는 public 선언해주고 emailAuthNumberEntity 저장
            emailAuthNumberRepository.save(emailAuthNumberEntity);

            mailProvider.mailAuthSend(userEmail, authNumber);

        } catch (MessagingException exception) {
            exception.printStackTrace();
            return ResponseDto.mailSendFailed(); // 예외 에러 반환

        }

        catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError(); // 예외 에러 반환
        }

        return ResponseDto.success(); // 성공 반환
    }

    @Override
    public ResponseEntity<ResponseDto> emailAuthCheck(EmailAuthCheckRequestDto dto) {

        try {

            String userEmail = dto.getUserEmail();
            String userNumber = dto.getAuthNumber();

            // 데이터베이스의 email_auth_number 테이블에서 해당하는 userEmail과 authNumber를 모두 가지고 있는 데이터가
            // 있는지 확인
            boolean isMatched = emailAuthNumberRepository.existsByEmailAndAuthNumber(userEmail, userNumber);
            // 해당하는 데이터가 없다면 'AF' 응답 처리
            if (!isMatched)
                return ResponseDto.authenticationFailed();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        // try를 거치고 문제가 없다면 성공 처리
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> SignUp(SignUpRequestDto dto) {

        try {
            // (userId, userPassword, userEmail, authNumber) 유효성검사가 끝나고 SIGNUP DTO에 있는것들
            // 꺼내오기
            String userId = dto.getUserId();
            String userPassword = dto.getUserPassword();
            String userEmail = dto.getUserEmail();
            String authNumber = dto.getAuthNumber();

            // 리포지토리에서 가져옴
            boolean existedUser = userRepository.existsByUserId(userId);
            // 해당하는 데이터가 없다면 에러 처리
            if (existedUser)
                return ResponseDto.duplicatedId();

            boolean existedEmail = userRepository.existsByUserEmail(userEmail);
            if (existedEmail)
                return ResponseDto.duplicatedEmail();

            // 데이터베이스의 email_auth_number 테이블에서 해당하는 userEmail과 authNumber를 모두 가지고 있는 데이터가
            // 있는지 확인
            boolean isMatched = emailAuthNumberRepository.existsByEmailAndAuthNumber(userEmail, authNumber);    
            // 만약 FALSE 라면
            if (!isMatched)
                return ResponseDto.authenticationFailed();

            // 사용자로부터 입력받은 userPassword를 암호화
            String encodedPassword = passwordEncoder.encode(userPassword);

            // encodedPassword 을 넣어줌
            dto.setUserPassword((encodedPassword));

            // 사용자로부터 입력받은 userId, userEmail과 암호화한 password, 지정된 권한, 가입경로를 사용하여 데이터베이스의 user
            // 테이블에 레코드를 삽입
            // 엔터티 만들기
            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

     @Override
    public ResponseEntity<? super FindIdResponseDto> FindId(FindIdRequestDto dto) {

        try {
            String userEmail = dto.getUserEmail();
            // String userId = dto.getUserId();
            

            boolean isMatched = userRepository.existsByUserEmail(userEmail);
            if (!isMatched) return ResponseDto.authenticationFailed();
            
            UserEntity userEntity = userRepository.findByUserEmail(userEmail);

            userRepository.save(userEntity);
            
            return FindIdResponseDto.success(userEntity);

          } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
          }
        //   return ResponseDto.success(userId);
    }

}