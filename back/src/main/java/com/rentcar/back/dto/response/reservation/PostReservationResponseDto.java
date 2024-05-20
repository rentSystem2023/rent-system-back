package com.rentcar.back.dto.response.reservation;

import lombok.Getter;

@Getter
public class PostReservationResponseDto {
    private String insuranceType;
    private Integer commpanyCarCode;
    private String reservationDate;
    private String reservationSate;
    private String reservationPeriod;
}
