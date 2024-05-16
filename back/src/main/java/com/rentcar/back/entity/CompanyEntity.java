package com.rentcar.back.entity;

import jakarta.persistence.Id;


public class CompanyEntity {
    @Id
    private Integer companyCode;
    private String rentCompany;
    private String address;
    private String companyTelnumber;
    private String owner;
    private String registDate;
    private String companyRule;
}
