package com.rentcar.back.dto.response.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.repository.resultSet.GetUserDetailReservationResultSet;

import lombok.Getter;

@Getter
public class GetReservationDetailMyListResponseDto extends ResponseDto {
    
    private String carImageUrl;
    private String nickName;
    private String insuranceType;
    private String reservationStart;
    private String reservationEnd;
    private Integer carOil;
    private String grade;
    private String carNumber;
    private String rentCompany;
    private String companyTelnumber;
    private String address;
    private String reservationState;
    private String carName;
    private Integer insurancePrice;

    private GetReservationDetailMyListResponseDto (GetUserDetailReservationResultSet resultSet) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.carImageUrl = resultSet.getCarImageUrl();
        this.nickName = resultSet.getNickName();
        this.insuranceType = resultSet.getInsuranceType();
        this.reservationStart = resultSet.getReservationStart();
        this.reservationEnd = resultSet.getReservationEnd();
        this.carOil = resultSet.getCarOil();
        this.grade = resultSet.getGrade();
        this.carNumber = resultSet.getCarNumber();
        this.rentCompany = resultSet.getRentCompany();
        this.companyTelnumber = resultSet.getCompanyTelnumber();
        this.address = resultSet.getAddress();
        this.reservationState = resultSet.getReservationState();
        this.carName = resultSet.getCarName();
        this.insurancePrice = resultSet.getInsurancePrice();
    }

    public static ResponseEntity<GetReservationDetailMyListResponseDto> success (GetUserDetailReservationResultSet resultSet) throws Exception {
        GetReservationDetailMyListResponseDto responseBody = new GetReservationDetailMyListResponseDto(resultSet);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
