package com.rentcar.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentcar.back.dto.response.reservation.GetReservationMyListResponseDto;
import com.rentcar.back.service.ReservationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor     // 의존성 주입 어노테이션
public class ReservationController {

    private final ReservationService reservationService;

        @GetMapping("/MyList")
    public ResponseEntity<? super GetReservationMyListResponseDto> getReservationList (
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetReservationMyListResponseDto> response = reservationService.getReservationList(userId);
        return response;
    }

}
