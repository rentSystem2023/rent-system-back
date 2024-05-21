package com.rentcar.back.service;

import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.request.company.PostCompanyRequestDto;
import com.rentcar.back.dto.request.company.PutCompanyRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.company.GetCompanyListResponseDto;
import com.rentcar.back.dto.response.company.GetSearchCompanyListResponseDto;

public interface CompanyService {
    
    ResponseEntity<? super GetCompanyListResponseDto> getCompanyList();
    ResponseEntity<? super GetSearchCompanyListResponseDto> getSearchCompanyList(String searchWord);
    ResponseEntity<ResponseDto> postCompany(PostCompanyRequestDto dto, String userId);
    ResponseEntity<ResponseDto> putCompany(PutCompanyRequestDto dto, int companyCode, String userId);
    ResponseEntity<ResponseDto> deleteCompany(int companyCode, String userId);
}
