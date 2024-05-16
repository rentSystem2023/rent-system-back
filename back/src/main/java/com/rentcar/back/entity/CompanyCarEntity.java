package com.rentcar.back.entity;

import jakarta.persistence.Id;

public class CompanyCarEntity {
    @Id
    private String companyCarCode;
    private String carNumber;
    private Integer normalPrice;
    private Integer luxuryPrice;
    private Integer superPrice;
    private Integer companyCode;
    private Integer carCode;
}
