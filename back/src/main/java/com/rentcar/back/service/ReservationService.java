package com.rentcar.back.service;

import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.request.reservation.PatchReservationRequestDto;
import com.rentcar.back.dto.request.reservation.PostReservationRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationCancleListResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationDetailMyListResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationMyListResponseDto;



public interface ReservationService {

        // 나의 예약 리스트 보기
        // ResponseEntity<ResponseDto>PostReservation (PostReservationResponseDto dto, String userId); //userId 는 JWT를 통해 따로 받아옴
        ResponseEntity<? super GetReservationMyListResponseDto> getReservationMyList(String userId);
        
        // 예약 하기
        ResponseEntity<ResponseDto> postReservationBoard(PostReservationRequestDto dto, String userId);

        // 나의 예약 상세 보기
        ResponseEntity<? super GetReservationDetailMyListResponseDto> getReservationDetailMyList(int reservationCode, String userId);

        // 예약 취소하기
        ResponseEntity<ResponseDto> patchReservation(PatchReservationRequestDto dto, int reservationCode, String userId);

        // 취소 신청 예약 리스트 불러오기
        ResponseEntity<? super GetReservationCancleListResponseDto> getReservationCancleList(String userId);
}
