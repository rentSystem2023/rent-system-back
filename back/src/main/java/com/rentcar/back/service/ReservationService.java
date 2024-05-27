package com.rentcar.back.service;

import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.request.reservation.PatchReservationRequestDto;
import com.rentcar.back.dto.request.reservation.PostReservationRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationCancelListResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationDetailMyListResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationMyListResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationPopularListResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationUserListResponseDto;
import com.rentcar.back.dto.response.reservation.GetSearchReservationCarListResponseDto;



public interface ReservationService {

        // 나의 예약 리스트 보기
        ResponseEntity<? super GetReservationMyListResponseDto> getReservationMyList(String userId);
        
        // 예약 하기
        ResponseEntity<ResponseDto> postReservationBoard(PostReservationRequestDto dto, String userId);

        // 나의 예약 상세 보기
        ResponseEntity<? super GetReservationDetailMyListResponseDto> getReservationDetailMyList(int reservationCode, String userId);

        // 예약 취소하기
        ResponseEntity<ResponseDto> patchReservation(PatchReservationRequestDto dto, int reservationCode, String userId);

        // 취소 신청 예약 리스트 불러오기
        ResponseEntity<? super GetReservationCancelListResponseDto> getReservationCancelList(String reservationState, String userId);

        // 예약 취소 신청 승인 하기
        ResponseEntity<ResponseDto> deleteReservation (int reservationCode, String userId);

        // 전체 예약 목록 리스트 불러오기
        ResponseEntity<? super GetReservationUserListResponseDto> getReservationUserList(String userId);

        // 인기 차량 리스트 불러오기
        ResponseEntity<? super GetReservationPopularListResponseDto> getReservationPopularList();

        // 차량 검색 결과 불러오기
        // ResponseEntity<? super GetSearchReservationCarListResponseDto> getSearchReservationCarList(String address, String reservationStart, String reservationEnd);
}
