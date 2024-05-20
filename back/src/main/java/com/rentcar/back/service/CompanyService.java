package com.rentcar.back.service;

import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.response.company.GetCompanyListResponseDto;

public interface CompanyService {
    
    ResponseEntity<? super GetCompanyListResponseDto> getCompanyList(Integer companyCode);

}
