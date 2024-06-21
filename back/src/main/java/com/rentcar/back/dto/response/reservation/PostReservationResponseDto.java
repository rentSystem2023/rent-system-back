package com.rentcar.back.dto.response.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.object.KakaoReady;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;

import lombok.Getter;

@Getter
public class PostReservationResponseDto extends ResponseDto {
    
    private String redirectUrl;

    private PostReservationResponseDto (KakaoReady kakaoReady) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        
        this.redirectUrl = kakaoReady.getNext_redirect_pc_url();
    }

    public static ResponseEntity<PostReservationResponseDto> success (KakaoReady kakaoReady) {
        PostReservationResponseDto responseBody = new PostReservationResponseDto(kakaoReady);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
