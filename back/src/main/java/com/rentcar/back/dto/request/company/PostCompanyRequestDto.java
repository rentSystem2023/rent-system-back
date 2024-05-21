package com.rentcar.back.dto.request.company;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostCompanyRequestDto {

    @NotBlank
    private String rentCompany;
    @NotBlank
    private String address;
    @NotBlank
    private String owner;
    @NotBlank
    private String companyTelnumber;
    private String companyRule;
    @NotBlank
    private Integer compnayCode;
}
