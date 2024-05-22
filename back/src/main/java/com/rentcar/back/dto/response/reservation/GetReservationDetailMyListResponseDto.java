package com.rentcar.back.dto.response.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.repository.resultSet.GetUserDetatilReservationResultSet;

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

    private GetReservationDetailMyListResponseDto (GetUserDetatilReservationResultSet resultSet) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.carImageUrl = resultSet.getCarImageUrl();
        this.nickName = resultSet.getNickName();
        this.insuranceType = resultSet.getinsuranceType();
        this.reservationStart = resultSet.getreservationStart();
        this.reservationEnd = resultSet.getreservationEnd();
        this.carOil = resultSet.getcarOil();
        this.grade = resultSet.getgrade();
        this.carNumber = resultSet.getcarNumber();
        this.rentCompany = resultSet.getrentCompany();
        this.companyTelnumber = resultSet.getcompanyTelnumber();
        this.address = resultSet.getaddress();
    }

    public static ResponseEntity<GetReservationDetailMyListResponseDto> success (GetUserDetatilReservationResultSet resultSet) throws Exception {
        GetReservationDetailMyListResponseDto responseBody = new GetReservationDetailMyListResponseDto(resultSet);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
