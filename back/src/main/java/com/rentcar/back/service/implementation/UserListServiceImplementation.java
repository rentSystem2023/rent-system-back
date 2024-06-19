package com.rentcar.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.user.GetSearchUserListResponseDto;
import com.rentcar.back.dto.response.user.GetUserDetailListResponseDto;
import com.rentcar.back.dto.response.user.GetUserListResponseDto;
import com.rentcar.back.entity.UserEntity;
import com.rentcar.back.repository.UserRepository;
import com.rentcar.back.service.UserListService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserListServiceImplementation implements UserListService{
    
    private final UserRepository userRepository;
    
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
