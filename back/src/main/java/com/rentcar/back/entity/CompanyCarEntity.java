package com.rentcar.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name="companyCar")
@Table(name="company_car")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCarEntity {
    
    @Id
    private Integer companyCarCode;
    private String carNumber;
    private Integer normalPrice;
    private Integer luxuryPrice;
    private Integer superPrice;
    private Integer companyCode;
    private Integer carCode;
}
