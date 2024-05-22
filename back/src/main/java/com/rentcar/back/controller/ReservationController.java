package com.rentcar.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentcar.back.dto.request.reservation.PostReservationRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationMyListResponseDto;
import com.rentcar.back.service.ReservationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rentcar/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    // 예약 하기
    @PostMapping("/regist")
    ResponseEntity<ResponseDto> postReservationBoard (
        @RequestBody @Valid PostReservationRequestDto requestBody, 
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = reservationService.postReservationBoard(requestBody, userId);
        return response;
    }

    // 예약 리스트 보기
    @GetMapping("/mylist")
    public ResponseEntity<? super GetReservationMyListResponseDto> getReservationList (
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetReservationMyListResponseDto> response = reservationService.getReservationMyList(userId);
        return response;
    }
}