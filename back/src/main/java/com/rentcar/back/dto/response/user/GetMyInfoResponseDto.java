package com.rentcar.back.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetMyInfoResponseDto extends ResponseDto {
    
    private String userId;
    private String userPassword;
    private String nickName;
    private String userEmail;
    private String userRole;
    private String joinPath;
    private String joinDate;

    private GetMyInfoResponseDto(UserEntity userEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.userId = userEntity.getUserId();
        this.userPassword = userEntity.getUserPassword();
        this.nickName = userEntity.getNickName();
        this.userEmail = userEntity.getUserEmail();
        this.userRole = userEntity.getUserRole();
        this.joinPath = userEntity.getJoinPath();
        this.joinDate = userEntity.getJoinDate();
    }

    public static ResponseEntity<GetMyInfoResponseDto> success(UserEntity userEntity) {
        GetMyInfoResponseDto responseBody = new GetMyInfoResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
