package com.rentcar.back.dto.response.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.repository.resultSet.GetSearchReservationDetailResultSet;

import lombok.Getter;

@Getter
public class GetSearchReservationDetailListResponseDto extends ResponseDto {
    private String carName;
    private String carImageUrl;
    private String carYear;
    private String brand;
    private String grade;
    private Integer carOil;
    private String fuelType;
    private Integer capacity;
    private Integer normalPrice;
    private Integer luxuryPrice;
    private Integer superPrice;
    private String rentCompany;
    private String address;
    private String companyTelnumber;
    private String companyRule;
    private String reservationStart;
    private String reservationEnd;
    private Double companyLat;
    private Double companyLng;

    private GetSearchReservationDetailListResponseDto (GetSearchReservationDetailResultSet resultSet) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.carName = resultSet.getCarName();
        this.carImageUrl = resultSet.getCarImageUrl();
        this.carYear = resultSet.getCarYear();
        this.brand = resultSet.getBrand();
        this.grade = resultSet.getGrade();
        this.carOil = resultSet.getCarOil();
        this.fuelType = resultSet.getFuelType();
        this.capacity = resultSet.getCapacity();
        this.normalPrice = resultSet.getNormalPrice();
        this.luxuryPrice = resultSet.getLuxuryPrice();
        this.superPrice = resultSet.getSuperPrice();
        this.rentCompany = resultSet.getRentCompany();
        this.address = resultSet.getAddress();
        this.companyTelnumber = resultSet.getCompanyTelnumber();
        this.companyRule = resultSet.getCompanyRule();
        this.reservationStart = resultSet.getReservationStart();
        this.reservationEnd = resultSet.getReservationEnd();
        this.companyLat = resultSet.getCompanyLat();
        this.companyLng = resultSet.getCompanyLng();
    }

    public static ResponseEntity<GetSearchReservationDetailListResponseDto> success (GetSearchReservationDetailResultSet resultSet) throws Exception {
        GetSearchReservationDetailListResponseDto responseBody = new GetSearchReservationDetailListResponseDto(resultSet);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
