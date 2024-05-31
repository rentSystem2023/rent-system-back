package com.rentcar.back.service;

import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.request.company.PostCompanyRequestDto;
import com.rentcar.back.dto.request.company.PutCompanyRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.company.GetCompanyDetailResponseDto;
import com.rentcar.back.dto.response.company.GetCompanyListResponseDto;
import com.rentcar.back.dto.response.company.GetSearchCompanyListResponseDto;

public interface CompanyService {
    
    // 업체 리스트 불러오기
    ResponseEntity<? super GetCompanyListResponseDto> getCompanyList();

    // 업체 리스트 상세 불러오기
    ResponseEntity<? super GetCompanyDetailResponseDto> getCompanyDetail(int CompanyCode);

    // 업체 리스트 검색 불러오기
    ResponseEntity<? super GetSearchCompanyListResponseDto> getSearchCompanyList(String searchWord);

    // 업체 등록하기
    ResponseEntity<ResponseDto> postCompany(PostCompanyRequestDto dto, String userId);

    // 업체 수정하기
    ResponseEntity<ResponseDto> putCompany(PutCompanyRequestDto dto, int companyCode, String userId);

    // 업체 삭제하기
    ResponseEntity<ResponseDto> deleteCompany(int companyCode, String userId);
}
