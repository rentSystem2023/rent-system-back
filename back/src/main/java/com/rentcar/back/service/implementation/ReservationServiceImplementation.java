package com.rentcar.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rentcar.back.dto.request.reservation.PostReservationRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationDetailMyListResponseDto;
import com.rentcar.back.dto.response.reservation.GetReservationMyListResponseDto;
import com.rentcar.back.entity.ReservationEntity;
import com.rentcar.back.repository.CompanyCarRepository;
import com.rentcar.back.repository.ReservationRepository;
import com.rentcar.back.repository.UserRepository;
import com.rentcar.back.repository.resultSet.GetUserReservationResultSet;
import com.rentcar.back.service.ReservationService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ReservationServiceImplementation implements ReservationService {

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final CompanyCarRepository companyCarRepository;

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

    @Override
    public ResponseEntity<ResponseDto> postReservationBoard(PostReservationRequestDto dto, String userId) {
    
        try {
            boolean isExistUser = userRepository.existsById(userId);
            if (!isExistUser) return ResponseDto.authenticationFailed();
    
            Integer companyCarCode = dto.getCompanyCarCode();
            boolean isExistCompanyCar = companyCarRepository.existsByCompanyCarCode(companyCarCode);
            // TODO: 존재하지 않는 회사 차량 내보내기
            if (!isExistCompanyCar) return ResponseDto.authenticationFailed();

            ReservationEntity reservationEntity = new ReservationEntity(dto, userId);
            reservationRepository.save(reservationEntity);

            return ResponseDto.success();
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<GetReservationDetailMyListResponseDto> getReservationDetailMyList(String userId) {

        try {
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }


}