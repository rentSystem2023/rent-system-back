package com.rentcar.back.dto.response.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.repository.resultSet.GetReservationDetailResultSet;

import lombok.Getter;

@Getter
public class GetReservationDetailResponseDto extends ResponseDto {
    private Integer reservationCode;
    private String rentCompany;
    private String carName;
    private String carNumber;
    private String reservationStart;
    private String reservationEnd;
    private String userId;
    private String reservationState;
    private String insuranceType;
    private Integer insurancePrice;

    private GetReservationDetailResponseDto (GetReservationDetailResultSet resultSet) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reservationCode = resultSet.getReservationCode();
        this.rentCompany = resultSet.getRentCompany();
        this.carName = resultSet.getCarName();
        this.carNumber = resultSet.getCarNumber();
        this.reservationStart = resultSet.getReservationStart();
        this.reservationEnd = resultSet.getReservationEnd();
        this.userId = resultSet.getUserId();
        this.reservationState = resultSet.getReservationState();
        this.insuranceType = resultSet.getInsuranceType();
        this.insurancePrice = resultSet.getInsurancePrice();
    }

    public static ResponseEntity<GetReservationDetailResponseDto> success (GetReservationDetailResultSet resultSet) throws Exception {
        GetReservationDetailResponseDto responseBody = new GetReservationDetailResponseDto(resultSet);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
