package com.rentcar.back.service;

import org.springframework.http.ResponseEntity;


import com.rentcar.back.dto.response.reservation.GetReservationMyListResponseDto;


public interface ReservationService {
        ResponseEntity<? super GetReservationMyListResponseDto>getReservationList(String userId);
}
