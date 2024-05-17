package com.rentcar.back.service.implementation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.user.GetMyInfoResponseDto;
import com.rentcar.back.dto.response.user.GetSignInUserResponseDto;
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
    public ResponseEntity<ResponseDto> myInfoModify(String userId) {
        
        
        try {

            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ResponseDto.noExistInfo();

            userEntity.myInfoModify(userId);
            userRepository.save(userEntity);

        } catch (Exception exception) {

            exception.printStackTrace();

            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    
    }
    
}