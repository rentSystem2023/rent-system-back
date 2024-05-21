package com.rentcar.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name="company")
@Table(name="company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
