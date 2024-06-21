package com.rentcar.back.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.entity.UserEntity;

import lombok.Getter;

@Getter
public class FindIdResponseDto extends ResponseDto {
    
    private String userId;

    private FindIdResponseDto(UserEntity userEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userId = userEntity.getUserId();
    }

    public static ResponseEntity<FindIdResponseDto> success(UserEntity userEntity) {
        FindIdResponseDto responseBody = new FindIdResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}