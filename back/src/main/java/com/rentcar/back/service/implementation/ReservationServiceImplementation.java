package com.rentcar.back.service.implementation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationMyListResponseDto;
import com.rentcar.back.dto.response.reservation.PostReservationResponseDto;
import com.rentcar.back.entity.ReservationEntity;
import com.rentcar.back.repository.ReservationRepository;
import com.rentcar.back.repository.UserRepository;
import com.rentcar.back.service.ReservationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImplementation implements ReservationService {

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public ResponseEntity<? super GetReservationMyListResponseDto> getReservationList(String userId) {

        ReservationEntity reservationEntity;
        try {
            boolean isExistUser = userRepository.existsById(userId);
            if (!isExistUser)
                return ResponseDto.authenticationFailed();
            // 예약 내역 확인
            reservationEntity = reservationRepository.findByUserId(userId);

            // 성공 작성
            return GetReservationMyListResponseDto.success(reservationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();                           
        }

    }



}