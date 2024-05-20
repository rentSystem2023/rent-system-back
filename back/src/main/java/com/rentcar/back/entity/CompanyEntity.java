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
    @NotBlank
    private String rentCompany;
    @NotBlank
    private String address;
    @NotBlank
    private String companyTelnumber;
    @NotBlank
    private String owner;
    @NotBlank
    private String registDate;
    @NotNull
    private String companyRule;
    
}
