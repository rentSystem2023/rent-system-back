package com.rentcar.back.entity;

import jakarta.persistence.Id;

public class CarEntity {
    @Id
    private Integer carCode;
    private String carNumber;
    private String carRentCompany;
    private String brand;
    private String grade;
    private String carName;
    private String carOil;
    private String fuelType;
    private String carYear;
    private Integer capacity;
    private Integer normalPrice;
    private Integer luxuryPrice;
    private Integer superPrice;
    private String insuranceType;
    private String carImageUrl;
}
