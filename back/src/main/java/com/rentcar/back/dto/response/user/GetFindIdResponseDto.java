package com.rentcar.back.dto.response.user;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetFindIdResponseDto extends ResponseDto {
    private String userId;

    private GetFindIdResponseDto(UserEntity userEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userId = userEntity.getUserId();
    }

    public static ResponseEntity<GetFindIdResponseDto> success (UserEntity userEntity) {

        GetFindIdResponseDto responseBody = new GetFindIdResponseDto(userEntity);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
