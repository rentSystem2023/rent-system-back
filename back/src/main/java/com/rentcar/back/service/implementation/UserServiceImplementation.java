package com.rentcar.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rentcar.back.dto.request.user.PatchUserRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.user.GetFindIdResponseDto;
import com.rentcar.back.dto.response.user.GetMyInfoResponseDto;
import com.rentcar.back.dto.response.user.GetSearchUserListResponseDto;
import com.rentcar.back.dto.response.user.GetSignInUserResponseDto;
import com.rentcar.back.dto.response.user.GetUserDetailListResponseDto;
import com.rentcar.back.dto.response.user.GetUserListResponseDto;
import com.rentcar.back.entity.UserEntity;
import com.rentcar.back.repository.UserRepository;
import com.rentcar.back.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService{

    private final UserRepository userRepository;

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
    public ResponseEntity<ResponseDto> myInfoModify(PatchUserRequestDto dto, String userId) {
        
        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.noExistInfo();

            userEntity.update(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
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

    // 내 아이디 찾기
    @Override
    public ResponseEntity<? super GetFindIdResponseDto> getFindId(String userEmail) {
        try {

            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            return GetFindIdResponseDto.success(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
}