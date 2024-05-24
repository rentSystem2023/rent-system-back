package com.rentcar.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rentcar.back.dto.request.reservation.PatchReservationRequestDto;
import com.rentcar.back.dto.request.reservation.PostReservationRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationCancleListResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationDetailMyListResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationMyListResponseDto;
import com.rentcar.back.entity.ReservationEntity;
import com.rentcar.back.repository.CompanyCarRepository;
import com.rentcar.back.repository.ReservationRepository;
import com.rentcar.back.repository.UserRepository;
import com.rentcar.back.repository.resultSet.GetUserDetatilReservationResultSet;
import com.rentcar.back.repository.resultSet.GetUserReservationResultSet;
import com.rentcar.back.service.ReservationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImplementation implements ReservationService {

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final CompanyCarRepository companyCarRepository;

        // 예약하기
        @Override
        public ResponseEntity<ResponseDto> postReservationBoard(PostReservationRequestDto dto, String userId) {
        
            try {
                boolean isExistUser = userRepository.existsById(userId);
                if (!isExistUser) return ResponseDto.authenticationFailed();
        
                Integer companyCarCode = dto.getCompanyCarCode();
                boolean isExistCompanyCar = companyCarRepository.existsByCompanyCarCode(companyCarCode);
    
                if (!isExistCompanyCar) return ResponseDto.noExistVehicle();
    
                ReservationEntity reservationEntity = new ReservationEntity(dto, userId);
                reservationRepository.save(reservationEntity);
    
                return ResponseDto.success();
        
            } catch (Exception exception) {
                exception.printStackTrace();
                return ResponseDto.databaseError();
            }
        }

    // 내 예약 내역 보기
    @Override
    public ResponseEntity<? super GetReservationMyListResponseDto> getReservationMyList(String userId) {
        try {
            boolean isExistUser = userRepository.existsById(userId);
            if (!isExistUser) return ResponseDto.authenticationFailed();

            List<GetUserReservationResultSet> reservationEntity = reservationRepository.getUserReservationList(userId);

            return GetReservationMyListResponseDto.success(reservationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();                           
        }
    }


    // 내 예약 내역 상세보기
    @Override
    public ResponseEntity<? super GetReservationDetailMyListResponseDto> getReservationDetailMyList(int reservationCode, String userId) {

        try {

            // reservationCode 로 존재하는 예약내역인지 확인
            ReservationEntity reservationEntity = reservationRepository.findByReservationCode(reservationCode);
            if (reservationEntity == null) return ResponseDto.noExistReservation();

            // 해당 예약이 사용자의 예약인지 확인
            String reservationId = reservationEntity.getUserId();
            boolean isUser = userId.equals(reservationId);
            if (!isUser) return ResponseDto.authorizationFailed();

            GetUserDetatilReservationResultSet reservationDetail = reservationRepository.getUserDetailReservationList(userId);
            return GetReservationDetailMyListResponseDto.success(reservationDetail);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 예약 취소하기
    @Override
    public ResponseEntity<ResponseDto> patchReservation(PatchReservationRequestDto dto, int reservationCode, String userId) {

        try {

            // reservationCode 로 존재하는 예약내역인지 확인
            ReservationEntity reservationEntity = reservationRepository.findByReservationCode(reservationCode);
            if (reservationEntity == null) return ResponseDto.noExistReservation();

            // 해당 예약이 사용자의 예약인지 확인
            String reservationId = reservationEntity.getUserId();
            boolean isUser = userId.equals(reservationId);
            if (!isUser) return ResponseDto.authorizationFailed();

            reservationEntity.update(dto);

            return ResponseDto.success();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 취소 신청 예약 리스트 불러오기
    @Override
    public ResponseEntity<? super GetReservationCancleListResponseDto> getReservationCancleList(String userId) {

        try {

            List<ReservationEntity> reservationEntities = reservationRepository.findByUserId(userId);
            return GetReservationCancleListResponseDto.success(reservationEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }




}