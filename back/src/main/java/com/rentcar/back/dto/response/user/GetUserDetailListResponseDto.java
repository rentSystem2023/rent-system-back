package com.rentcar.back.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetUserDetailListResponseDto extends ResponseDto {
    
    private String userId;
    private String nickName;
    private String userEmail;
    private String joinDate;

    private GetUserDetailListResponseDto(UserEntity userEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userId = userEntity.getUserId();
        this.nickName = userEntity.getNickName();
        this.userEmail = userEntity.getUserEmail();
        this.joinDate = userEntity.getJoinDate();
    }

    public static ResponseEntity<GetUserDetailListResponseDto> success (UserEntity userEntity) {

        GetUserDetailListResponseDto responseBody = new GetUserDetailListResponseDto(userEntity);
        
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
