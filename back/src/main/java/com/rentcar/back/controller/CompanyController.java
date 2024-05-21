package com.rentcar.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentcar.back.dto.request.company.PostCompanyRequestDto;
import com.rentcar.back.dto.request.company.PutCompanyRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.company.GetCompanyListResponseDto;
import com.rentcar.back.dto.response.company.GetSearchCompanyListResponseDto;
import com.rentcar.back.service.CompanyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        @RequestParam("word") String searchWord
    ) {
        ResponseEntity<? super GetSearchCompanyListResponseDto> response = companyService.getSearchCompanyList(searchWord);
        return response;
    }

    // company 등록하기
    @PostMapping("/regist")
    ResponseEntity<ResponseDto> postCompany (
        @RequestBody @Valid PostCompanyRequestDto requestBody,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = companyService.postCompany(requestBody, userId);
        return response;
    }

    // company 수정하기
    @PutMapping("/{companyCode}")
    ResponseEntity<ResponseDto> putCompany (
        @RequestBody @Valid PutCompanyRequestDto requestBody,
        @PathVariable("companyCode") int companyCode,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = companyService.putCompany(requestBody, companyCode, userId);
        return response;
    }
    
    // company 삭제하기
    @DeleteMapping("/{companyCode}")
    public ResponseEntity<ResponseDto> deleteCompany(
        @PathVariable("companyCode") int companyCode,
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<ResponseDto> response = companyService.deleteCompany(companyCode, userId);
        return response;
    }
}
