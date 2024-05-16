package com.rentcar.back.entity;

import jakarta.persistence.Id;

public class ReservationEntity {
    
    @Id
    private String reservationCode;
    private String userId;
    private String insuranceType;
    private String reservationDate;
    private String reservationState;
    private String reservationPeriod;
    private Integer companyCarCode;
    
}
