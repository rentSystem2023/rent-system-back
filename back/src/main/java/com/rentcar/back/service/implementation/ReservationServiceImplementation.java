package com.rentcar.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rentcar.back.dto.request.reservation.PatchReservationRequestDto;
import com.rentcar.back.dto.request.reservation.PostReservationRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationCancelListResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationDetailMyListResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationMyListResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationPopularListResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationUserListResponseDto;
import com.rentcar.back.dto.response.reservation.GetSearchReservationCarListResponseDto;
import com.rentcar.back.entity.CarEntity;
import com.rentcar.back.entity.ReservationEntity;
import com.rentcar.back.repository.CarRepository;
import com.rentcar.back.repository.CompanyCarRepository;
import com.rentcar.back.repository.ReservationRepository;
import com.rentcar.back.repository.UserRepository;
import com.rentcar.back.repository.resultSet.GetAllUserReservationResultSet;
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
    private final CarRepository carRepository;

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

            reservationRepository.save(reservationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    // 취소 신청 예약 리스트 불러오기
    @Override
    public ResponseEntity<? super GetReservationCancelListResponseDto> getReservationCancelList(String userId, String reservationState) {

        try {

            List<ReservationEntity> reservationEntities = reservationRepository.findByReservationState("cancel");
            return GetReservationCancelListResponseDto.success(reservationEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 예약 취소 신청 승인하기
    @Override
    public ResponseEntity<ResponseDto> deleteReservation(int reservationCode, String userId) {

        try {
            
            ReservationEntity reservationEntity = reservationRepository.findByReservationCode(reservationCode);
            if (reservationEntity == null) return ResponseDto.noExistReservation();

            String reservationState = reservationEntity.getReservationState();
            boolean isCancel = "cancel".equals(reservationState);
            if (!isCancel) return ResponseDto.noCancelState();

            reservationRepository.delete(reservationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();;
            return ResponseDto.databaseError();
        }
        
        return ResponseDto.success();
    }

    // 전체 예약 목록 리스트 불러오기
    @Override
    public ResponseEntity<? super GetReservationUserListResponseDto> getReservationUserList(String userId) {

        try {

            boolean isExistUser = userRepository.existsById(userId);
            if (!isExistUser) return ResponseDto.authenticationFailed();

            List<GetAllUserReservationResultSet> reservationEntity = reservationRepository.getAllUserReservationList();

            return GetReservationUserListResponseDto.success(reservationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 인기 차량 리스트 불러오기
    @Override
    public ResponseEntity<? super GetReservationPopularListResponseDto> getReservationPopularList() {
        
        try {

            List<CarEntity> carEntity = carRepository.findTop3ByOrderByReservationCountDesc();

            return GetReservationPopularListResponseDto.success(carEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // // 차량 검색 결과 불러오기
    // @Override
    // public ResponseEntity<? super GetSearchReservationCarListResponseDto> getSearchReservationCarList(String address, String reservationStart, String reservationEnd) {

    //     try {

    //         boolean existedAddress = reservationRepository.existsByAddress(address);
    //         if (!existedAddress) return ResponseDto.noExistAddress();

    //         List<GetAllUserReservationResultSet> reservationEntity = reservationRepository.getSearchReservationList(address, reservationStart, reservationEnd);

    //         return GetSearchReservationCarListResponseDto.success(null);

    //     } catch (Exception exception) {
    //         exception.printStackTrace();
    //         return ResponseDto.databaseError();
    //     }
    // }


}