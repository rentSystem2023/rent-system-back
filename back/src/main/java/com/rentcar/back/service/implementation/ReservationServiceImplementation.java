package com.rentcar.back.service.implementation;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rentcar.back.common.object.KakaoReady;
import com.rentcar.back.common.util.KaKaopayUtil;
import com.rentcar.back.dto.request.reservation.PatchReservationStateRequestDto;
import com.rentcar.back.dto.request.reservation.PostReservationRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationCancelListResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationDetailMyListResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationDetailResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationMyListResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationPopularListResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationUserListResponseDto; 
import com.rentcar.back.dto.response.reservation.GetSearchReservationCarListResponseDto;
import com.rentcar.back.dto.response.reservation.GetSearchReservationCarPriceListResponseDto;
import com.rentcar.back.dto.response.reservation.GetSearchReservationDetailListResponseDto;
import com.rentcar.back.dto.response.reservation.GetSearchReservationListResponseDto;
import com.rentcar.back.dto.response.reservation.PostReservationResponseDto;
import com.rentcar.back.entity.CarEntity;
import com.rentcar.back.entity.CompanyCarEntity;
import com.rentcar.back.entity.ReservationEntity;
import com.rentcar.back.repository.CarRepository;
import com.rentcar.back.repository.CompanyCarRepository;
import com.rentcar.back.repository.CompanyRepository;
import com.rentcar.back.repository.ReservationRepository;
import com.rentcar.back.repository.UserRepository;
import com.rentcar.back.repository.resultSet.GetAllUserReservationResultSet;
import com.rentcar.back.repository.resultSet.GetPopularCarResultSet;
import com.rentcar.back.repository.resultSet.GetReservationDetailResultSet;
import com.rentcar.back.repository.resultSet.GetSearchReservationDetailResultSet;
import com.rentcar.back.repository.resultSet.GetSearchReservationPriceResultSet;
import com.rentcar.back.repository.resultSet.GetSearchReservationResultSet;
import com.rentcar.back.repository.resultSet.GetUserDetailReservationResultSet;
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
    private final CompanyRepository companyRepository;

    private final KaKaopayUtil kaKaopayUtil;

    // 예약하기
    @Override
    public ResponseEntity<? super PostReservationResponseDto> postReservation(PostReservationRequestDto dto, String userId) {

        try {

            boolean isExistUser = userRepository.existsById(userId);
            if (!isExistUser) return ResponseDto.authenticationFailed();

            Integer companyCarCode = dto.getCompanyCarCode();
            boolean isExistCompanyCar = companyCarRepository.existsByCompanyCarCode(companyCarCode);
            if (!isExistCompanyCar) return ResponseDto.noExistVehicle();

            ReservationEntity reservationEntity = new ReservationEntity(dto, userId);
            reservationRepository.save(reservationEntity);

            CompanyCarEntity companyCarEntity = companyCarRepository.findByCompanyCarCode(companyCarCode);
            if (companyCarEntity != null) {
                CarEntity carEntity = carRepository.findByCarCode(companyCarEntity.getCarCode());
                if (carEntity != null) {
                    carEntity.setReservationCount(carEntity.getReservationCount() + 1);
                    carRepository.save(carEntity);
                }
            }

            Integer reservationCode = reservationEntity.getReservationCode();
            KakaoReady kakaoReady =  kaKaopayUtil.prepareKakaoPayment(dto, reservationCode);

            return PostReservationResponseDto.success(kakaoReady);

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
    public ResponseEntity<? super GetReservationDetailMyListResponseDto> getReservationDetailMyList(
        int reservationCode, String userId) {

        try {

            ReservationEntity reservationEntity = reservationRepository.findByReservationCode(reservationCode);
            if (reservationEntity == null) return ResponseDto.noExistReservation();

            String reservationId = reservationEntity.getUserId();
            boolean isUser = userId.equals(reservationId);
            if (!isUser) return ResponseDto.authorizationFailed();

            GetUserDetailReservationResultSet reservationDetail = reservationRepository.getUserDetailReservationList(userId, reservationCode);
            
            return GetReservationDetailMyListResponseDto.success(reservationDetail);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 예약 취소하기
    @Override
    public ResponseEntity<ResponseDto> patchReservation(PatchReservationStateRequestDto dto, int reservationCode, String userId) {

        try {

            ReservationEntity reservationEntity = reservationRepository.findByReservationCode(reservationCode);
            if (reservationEntity == null) return ResponseDto.noExistReservation();

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
    public ResponseEntity<? super GetReservationCancelListResponseDto> getReservationCancelList(
        String userId, String reservationState) {

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
    public ResponseEntity<ResponseDto> patchReservationCancel(PatchReservationStateRequestDto dto, int reservationCode) {

        try {
            
            ReservationEntity reservationEntity = reservationRepository.findByReservationCode(reservationCode);
            if (reservationEntity == null) return ResponseDto.noExistReservation();

            String reservationState = reservationEntity.getReservationState();
            boolean isCancel = "waitingCancel".equals(reservationState);
            if (!isCancel) return ResponseDto.noCancelState();

            reservationEntity.update(dto);

            reservationRepository.save(reservationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        return ResponseDto.success();
    }


    // 전체 예약 목록 리스트 불러오기(관리자)
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

    // 예약 상세 불러오기
    @Override
    public ResponseEntity<? super GetReservationDetailResponseDto> getReservationDetail(Integer ReservationCode) {

        try {

            ReservationEntity reservationEntity = reservationRepository.findByReservationCode(ReservationCode);
            if (reservationEntity == null) return ResponseDto.noExistReservation();

            GetReservationDetailResultSet reservationDetail = reservationRepository.getReservationDetail(ReservationCode);

            return GetReservationDetailResponseDto.success(reservationDetail);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 예약 검색 리스트 불러오기
    @Override
	public ResponseEntity<? super GetSearchReservationListResponseDto> getSearchReservationList(Integer searchWord) {

        try {
            List<GetAllUserReservationResultSet> reservationEntity = new ArrayList<>();
            if (searchWord == null) reservationEntity = reservationRepository.getAllUserReservationList();
            else reservationEntity = reservationRepository.findByReservationCodeOrderByReservationCode(searchWord);

            return GetSearchReservationListResponseDto.success(reservationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
	}

    // 예약 목록 리스트 삭제하기
    @Override
    public ResponseEntity<ResponseDto> deleteReservationList(
        int reservationCode, String userId) {
        
        try {
            
            ReservationEntity reservationEntity = reservationRepository.findByReservationCode(reservationCode);
            if (reservationEntity == null) return ResponseDto.noExistReservation();

            reservationRepository.delete(reservationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();;
            return ResponseDto.databaseError();
        }
        
        return ResponseDto.success();
    }

    // 인기 차량 리스트 불러오기
    @Override
    public ResponseEntity<? super GetReservationPopularListResponseDto> getReservationPopularList() {

        try {

            List<GetPopularCarResultSet> resultSet = carRepository.findTop12ByTotalReservationCount();

            return GetReservationPopularListResponseDto.success(resultSet);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }


    // 차량 검색 결과 불러오기
    @Override
    public ResponseEntity<? super GetSearchReservationCarListResponseDto> getSearchReservationCarList(
        String reservationStart, String reservationEnd) {

        try {

            List<GetSearchReservationResultSet> reservationEntity = reservationRepository.getSearchReservationList(reservationStart, reservationEnd);

            return GetSearchReservationCarListResponseDto.success(reservationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 보험별(업체) 가격 검색 결과 불러오기
    @Override
    public ResponseEntity<? super GetSearchReservationCarPriceListResponseDto> getSearchReservationCarPriceList(
        String reservationStart, String reservationEnd, String carName) {

        try {

            boolean existedCarName = carRepository.existsByCarName(carName);
            if (!existedCarName) return ResponseDto.noExistVehicle();

            List<GetSearchReservationPriceResultSet> reservationEntity = reservationRepository.getSearchReservationPriceList(reservationStart, reservationEnd, carName);

            return GetSearchReservationCarPriceListResponseDto.success(reservationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 차량 예약 상세 검색 결과 불러오기
    @Override
    public ResponseEntity<? super GetSearchReservationDetailListResponseDto> getSearchReservationDetailList(
        String reservationStart, String reservationEnd, String carName, String rentCompany) {

        try {

            boolean existedCarName = carRepository.existsByCarName(carName);
            if (!existedCarName) return ResponseDto.noExistVehicle();

            boolean existedRentCompany = companyRepository.existsByRentCompany(rentCompany);
            if (!existedRentCompany) return ResponseDto.noExistCompany();

            GetSearchReservationDetailResultSet reservationEntity = reservationRepository.getSearchReservationDetailList(reservationStart, reservationEnd, carName, rentCompany);

            return GetSearchReservationDetailListResponseDto.success(reservationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
}