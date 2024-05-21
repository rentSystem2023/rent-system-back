package com.rentcar.back.service;

import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.response.company.GetCompanyListResponseDto;
import com.rentcar.back.dto.response.company.GetSearchCompanyListResponseDto;

public interface CompanyService {
    
    ResponseEntity<? super GetCompanyListResponseDto> getCompanyList();
    ResponseEntity<? super GetSearchCompanyListResponseDto> getSearchCompanyList(String searchWord);

}
