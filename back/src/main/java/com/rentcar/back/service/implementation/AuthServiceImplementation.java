package com.rentcar.back.service.implementation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rentcar.back.common.util.EmailAuthNumberUtil;
import com.rentcar.back.dto.request.auth.EmailAuthCheckRequestDto;
import com.rentcar.back.dto.request.auth.EmailAuthRequestDto;
import com.rentcar.back.dto.request.auth.FindIdRequestDto;
import com.rentcar.back.dto.request.auth.FindPasswordRequestDto;
import com.rentcar.back.dto.request.auth.FindPasswordResetRequestDto;
import com.rentcar.back.dto.request.auth.IdCheckRequestDto;
import com.rentcar.back.dto.request.auth.NickNameCheckRequestDto;
import com.rentcar.back.dto.request.auth.SignInRequestDto;
import com.rentcar.back.dto.request.auth.SignUpRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.auth.FindIdResponseDto;
import com.rentcar.back.dto.response.auth.SignInResponseDto;
import com.rentcar.back.entity.EmailAuthNumberEntity;
import com.rentcar.back.entity.UserEntity;
import com.rentcar.back.provider.JwtProvider;
import com.rentcar.back.provider.MailProvider;
import com.rentcar.back.repository.EmailAuthNumberRepository;
import com.rentcar.back.repository.UserRepository;
import com.rentcar.back.service.AuthService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AuthServiceImplementation implements AuthService {

    private final UserRepository userRepository;
    private final EmailAuthNumberRepository emailAuthNumberRepository;
    private final MailProvider mailProvider;
    private final JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto) {

        try {
            String userId = dto.getUserId();
            boolean existedUser = userRepository.existsById(userId);
            if (existedUser)
            return ResponseDto.duplicatedId();

        } catch (Exception exception) {
            exception.printStackTrace();
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
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        String accessToken = null;

        try {
            String userId = dto.getUserId();
            String userPassword = dto.getUserPassword();
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null)
                return ResponseDto.signInFailed();

            String encodedPassword = userEntity.getUserPassword();
            boolean isMatched = passwordEncoder.matches(userPassword, encodedPassword);
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

            String userEmail = dto.getUserEmail();
            boolean existedEmail = userRepository.existsByUserEmail(userEmail);
            if (existedEmail)
                return ResponseDto.duplicatedEmail();

            String authNumber = EmailAuthNumberUtil.createCodeNumber();

            EmailAuthNumberEntity emailAuthNumberEntity = new EmailAuthNumberEntity(userEmail, authNumber);

            emailAuthNumberRepository.save(emailAuthNumberEntity);

            mailProvider.mailAuthSend(userEmail, authNumber);

        } catch (MessagingException exception) {
            exception.printStackTrace();
            return ResponseDto.mailSendFailed(); 

        }

        catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success(); 
    }

    @Override
    public ResponseEntity<ResponseDto> emailAuthCheck(EmailAuthCheckRequestDto dto) {

        try {

            String userEmail = dto.getUserEmail();
            String userNumber = dto.getAuthNumber();

            boolean isMatched = emailAuthNumberRepository.existsByEmailAndAuthNumber(userEmail, userNumber);
            if (!isMatched)
                return ResponseDto.authenticationFailed();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> SignUp(SignUpRequestDto dto) {

        try {
            String userId = dto.getUserId();
            String nickName = dto.getNickName();
            String userPassword = dto.getUserPassword();
            String userEmail = dto.getUserEmail();
            String authNumber = dto.getAuthNumber();

            boolean existedUser = userRepository.existsByUserId(userId);
            if (existedUser)
                return ResponseDto.duplicatedId();

            boolean existedNickName = userRepository.existsByNickName(nickName);
            if (existedNickName)
                return ResponseDto.duplicatedNickName();

            boolean existedEmail = userRepository.existsByUserEmail(userEmail);
            if (existedEmail)
                return ResponseDto.duplicatedEmail();

            boolean isMatched = emailAuthNumberRepository.existsByEmailAndAuthNumber(userEmail, authNumber);    
            if (!isMatched)
                return ResponseDto.authenticationFailed();

            String encodedPassword = passwordEncoder.encode(userPassword);

            dto.setUserPassword((encodedPassword));

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
    }

    @Override
    public ResponseEntity<ResponseDto> findPassword(FindPasswordRequestDto dto) {

        try {

            String userId = dto.getUserId();
            String userEmail = dto.getUserEmail();

            boolean isMatched = userRepository.existsByUserIdAndUserEmail(userId, userEmail);
            if (!isMatched) return ResponseDto.authenticationFailed();
            
            return ResponseDto.success();

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    }

    @Override
    public ResponseEntity<ResponseDto> findPasswordReset(FindPasswordResetRequestDto dto, String userId) {

        try {

            String userPassword = dto.getUserPassword();

            UserEntity userEntity = userRepository.findByUserId(userId);
            System.out.println(userId);
            if (userEntity == null) return ResponseDto.noExistUser();

            boolean isMatched = userRepository.existsById(userId);
            if (!isMatched) return ResponseDto.authenticationFailed();

            String encodedPassword = passwordEncoder.encode(userPassword);

            dto.setUserPassword(encodedPassword);

            userEntity.findPassword(dto);

            userRepository.save(userEntity);

            return ResponseDto.success();

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

}