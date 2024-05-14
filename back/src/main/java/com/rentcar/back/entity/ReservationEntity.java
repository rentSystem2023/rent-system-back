package com.rentcar.back.entity;

import jakarta.persistence.Id;

public class ReservationEntity {
    
    @Id
    private String reservationCode;
    private String userId;
    private Integer companyCode;
    private Integer carCode;
    private String reservationDate;
    private String reservationState;
    private String reservationPeriod;
}
