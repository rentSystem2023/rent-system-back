package com.rentcar.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name="car")
@Table(name="car")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarEntity {
    @Id
    private Integer carCode;
    private String carRentCompany;
    private String brand;
    private String grade;
    private String carName;
    private String carOil;
    private String fuelType;
    private String carYear;
    private Integer capacity;
    private String carImageUrl;
    private Integer reservationCount;
}
