package com.rentcar.back.service;

import org.springframework.http.ResponseEntity;


import com.rentcar.back.dto.request.reservation.PostReservationRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationMyListResponseDto;



public interface ReservationService {

        // 나의 예약 리스트 보기
        // ResponseEntity<ResponseDto>PostReservation (PostReservationResponseDto dto, String userId); //userId 는 JWT를 통해 따로 받아옴
        ResponseEntity<? super GetReservationMyListResponseDto> getReservationMyList(String userId);
        
        // 예약 하기
        ResponseEntity<ResponseDto> postReservationBoard(PostReservationRequestDto dto, String userId);
}
