package com.rentcar.back.dto.request.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PutCompanyRequestDto {
    
    @NotNull
    private Integer companyCode;
    @NotBlank
    private String rentCompany;
    @NotBlank
    private String address;
    @NotBlank
    private String owner;
    @NotBlank
    private String companyTelnumber;
    private String companyRule;
}
