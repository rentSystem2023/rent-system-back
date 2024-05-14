package com.rentcar.back.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;

import lombok.Getter;

// 로그인 Response Body DTO

@Getter // 생성자를 직접 만들었기에 getter 사용해줘야함
public class SignInResponseDto extends ResponseDto {

    private String accessToken;
    private int expires;

    // 생성자 만들어 주기
    // ResponseDto가 생성자 없이 ALLargs로 존재하기에 생성자를 호출해줘야함
    // () 안에는 외부에서 받아와서 작업해주는 accessToken 입력
    private SignInResponseDto(String accessToken) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.accessToken = accessToken;
        this.expires = 10 * 60 * 60; // 시간 , 분 , 초 provider jwt에 작성된 기준으로
    }

    public static ResponseEntity<SignInResponseDto> success(String accessToken) {
        SignInResponseDto responseBody = new SignInResponseDto(accessToken);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
