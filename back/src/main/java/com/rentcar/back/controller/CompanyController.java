package com.rentcar.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentcar.back.dto.response.company.GetCompanyListResponseDto;
import com.rentcar.back.service.CompanyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rentcar/company")
@RequiredArgsConstructor
public class CompanyController {
    
    private final CompanyService companyService;

    // companyList 불러오기
    @GetMapping("/list")
    public ResponseEntity<? super GetCompanyListResponseDto> getCompanyList () {
        ResponseEntity<? super GetCompanyListResponseDto> response = companyService.getCompanyList();
        return response;
    }
}
