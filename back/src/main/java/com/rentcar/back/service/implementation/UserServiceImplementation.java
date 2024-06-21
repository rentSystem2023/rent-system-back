package com.rentcar.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rentcar.back.common.util.EmailAuthNumberUtil;
import com.rentcar.back.dto.request.auth.EmailAuthRequestDto;
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
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final MailProvider mailProvider;
    private final EmailAuthNumberRepository emailAuthNumberRepository;

    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String userId) {

        UserEntity userEntity = null;

        try{

            userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ResponseDto.authenticationFailed();

            return GetSignInUserResponseDto.success(userEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
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

            String userEmail = dto.getUserEmail();
            boolean existedEmail = userRepository.existsByUserEmail(userEmail);

            if (existedEmail) return ResponseDto.duplicatedEmail();

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
    public ResponseEntity<ResponseDto> putEmailModify(PutEmailModifyRequestDto dto, String userId) {

        try {

            String userEmail = dto.getUserEmail();
            String userNumber = dto.getAuthNumber();

            boolean isMatched = emailAuthNumberRepository.existsByEmailAndAuthNumber(userEmail, userNumber);
            
            if (!isMatched) return ResponseDto.authenticationFailed();

            UserEntity userEntity = userRepository.findByUserId(userId);

            userEntity.emailModify(dto);
            userRepository.save(userEntity);

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

            return ResponseDto.success();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
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
            return ResponseDto.success();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
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