package com.rentcar.back.service;

import org.springframework.http.ResponseEntity;


import com.rentcar.back.dto.response.reservation.GetReservationMyListResponseDto;



public interface ReservationService {

        // ResponseEntity<ResponseDto>PostReservation (PostReservationResponseDto dto, String userId); //userId 는 JWT를 통해 따로 받아옴
        ResponseEntity<? super GetReservationMyListResponseDto> getReservationMyList(String userId);
        
}
