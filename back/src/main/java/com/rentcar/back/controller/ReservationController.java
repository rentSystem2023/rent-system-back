package com.rentcar.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentcar.back.dto.request.reservation.PatchReservationRequestDto;
import com.rentcar.back.dto.request.reservation.PostReservationRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationCancleListResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationDetailMyListResponseDto;
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

    // 내 예약 내역 보기
    @GetMapping("/mylist")
    public ResponseEntity<? super GetReservationMyListResponseDto> getReservationList (
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetReservationMyListResponseDto> response = reservationService.getReservationMyList(userId);
        return response;
    }

    // 내 예약 내역 상세보기
    @GetMapping("/mylist/{reservationCode}")
    public ResponseEntity<? super GetReservationDetailMyListResponseDto> getReservaitonDetailList (
        @AuthenticationPrincipal String userId,
        @PathVariable int reservationCode
    ) {
        ResponseEntity<? super GetReservationDetailMyListResponseDto> response = reservationService.getReservationDetailMyList(reservationCode, userId);
        return response;
    }

    // 예약 취소하기
    @PatchMapping("/mylist/{reservationCode}/cancle")
    public ResponseEntity<ResponseDto> PatchReservationCancle (
        @AuthenticationPrincipal String userId,
        @PathVariable ("reservationCode") int reservationCode,
        @RequestBody @Valid PatchReservationRequestDto requestBody 
    ) {
        ResponseEntity<ResponseDto> response = reservationService.patchReservation(requestBody, reservationCode, userId);
        return response;
    }

    // 취소 신청 예약 리스트 불러오기
    @GetMapping("/cancle")
    public ResponseEntity<? super GetReservationCancleListResponseDto> GetReservationCancleList (
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetReservationCancleListResponseDto> response = reservationService.getReservationCancleList(userId);
        return response;
    }

}