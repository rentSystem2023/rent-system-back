package com.rentcar.back.service.implementation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.company.GetCompanyListResponseDto;
import com.rentcar.back.entity.CompanyEntity;
import com.rentcar.back.repository.CompanyRepository;
import com.rentcar.back.service.CompanyService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImplementation implements CompanyService {
    
    private final CompanyRepository companyRepository;

    @Override
    public ResponseEntity<? super GetCompanyListResponseDto> getCompanyList(Integer companyCode) {

        try {

            List<CompanyEntity> companyEntities = companyRepository.findByOrderByRegistDateDesc();
            return GetCompanyListResponseDto.success(companyEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    }
    
}
