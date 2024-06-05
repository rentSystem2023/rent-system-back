package com.rentcar.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rentcar.back.common.util.EmailAuthNumberUtil;
import com.rentcar.back.dto.request.auth.EmailAuthRequestDto;
import com.rentcar.back.dto.request.user.PatchUserRequestDto;
import com.rentcar.back.dto.request.user.PutEmailModifyRequestDto;
import com.rentcar.back.dto.request.user.PutPwModifyRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.user.GetMyInfoResponseDto;
import com.rentcar.back.dto.response.user.GetSearchUserListResponseDto;
import com.rentcar.back.dto.response.user.GetSignInUserResponseDto;
import com.rentcar.back.dto.response.user.GetUserDetailListResponseDto;
import com.rentcar.back.dto.response.user.GetUserListResponseDto;
import com.rentcar.back.entity.EmailAuthNumberEntity;
import com.rentcar.back.entity.UserEntity;
import com.rentcar.back.provider.MailProvider;
import com.rentcar.back.repository.EmailAuthNumberRepository;
import com.rentcar.back.repository.UserRepository;
import com.rentcar.back.service.UserService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService{

    private final UserRepository userRepository;
    // 패스워드 암호화를 하기 위해 password 인코더
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final MailProvider mailProvider;
    private final EmailAuthNumberRepository emailAuthNumberRepository;


    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String userId) {

        UserEntity userEntity = null;

        try{

            userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.authenticationFailed();

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInUserResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<? super GetMyInfoResponseDto> getMyInfo(String userId) {
        
        UserEntity userEntity = null;

        try {

            userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.authenticationFailed();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetMyInfoResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<ResponseDto> putPasswordModify (PutPwModifyRequestDto dto, String userId) {
        
        try {

            String userPassword = dto.getUserPassword();

            UserEntity userEntity = userRepository.findByUserId(userId);
            System.out.println(userId);
            if (userEntity == null) return ResponseDto.noExistUser();

            boolean isMatched = userRepository.existsById(userId);
            if (!isMatched) return ResponseDto.authenticationFailed();

            String encodedPassword = passwordEncoder.encode(userPassword);

            dto.setUserPassword(encodedPassword);

            userEntity.findModify(dto);

            userRepository.save(userEntity);

            return ResponseDto.success();

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
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
    public ResponseEntity<ResponseDto> putEmailModify(PutEmailModifyRequestDto dto, String userId) {
        try {

            String userEmail = dto.getUserEmail();
            String userNumber = dto.getAuthNumber();

            // 데이터베이스의 email_auth_number 테이블에서 해당하는 userEmail과 authNumber를 모두 가지고 있는 데이터가
            // 있는지 확인
            boolean isMatched = emailAuthNumberRepository.existsByEmailAndAuthNumber(userEmail, userNumber);
            
            // 해당하는 데이터가 없다면 'AF' 응답 처리
            if (!isMatched)
                return ResponseDto.authenticationFailed();

            UserEntity userEntity = userRepository.findByUserId(userId);

            userEntity.emailModify(dto);

            userRepository.save(userEntity);

            // EmailAuthNumberEntity emailAuthNumberEntity = emailAuthNumberRepository.findByEmail(email);

            return ResponseDto.success();

        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> deleteMyInfo(String userId) {

        try {

            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.noExistInfo();

            userRepository.delete(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    

    @Override
    public ResponseEntity<? super GetUserListResponseDto> getUserList(String userId) {
        
        
        try {

            List<UserEntity> userEntities = userRepository.findByOrderByJoinDateDesc();
            return GetUserListResponseDto.success(userEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    
    }
    
    @Override
    public ResponseEntity<ResponseDto> deleteUserList(String userId) {
        
        try {

            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.noExistUser();

            userRepository.delete(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<? super GetSearchUserListResponseDto> getSearchUserList(String searchWord) {
        
        
        try {

            List<UserEntity> userEntities = userRepository.findByUserIdContainsOrderByJoinDateDesc(searchWord);
            return GetSearchUserListResponseDto.success(userEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    
    }

    @Override
    public ResponseEntity<? super GetUserDetailListResponseDto> getUserDetailList(String userId) {
        try{
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.noExistUser();

            return GetUserDetailListResponseDto.success(userEntity);
        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }



}