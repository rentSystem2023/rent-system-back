package com.rentcar.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentcar.back.dto.response.company.GetCompanyListResponseDto;
import com.rentcar.back.dto.response.company.GetSearchCompanyListResponseDto;
import com.rentcar.back.service.CompanyService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


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

    // company 검색 불러오기
    @GetMapping("/list/search")
    public ResponseEntity<? super GetSearchCompanyListResponseDto> getSearchCompanyList (
        @RequestParam("word") String word
    ) {
        ResponseEntity<? super GetSearchCompanyListResponseDto> response = companyService.getSearchCompanyList(word);
        return response;
    }
    
}
