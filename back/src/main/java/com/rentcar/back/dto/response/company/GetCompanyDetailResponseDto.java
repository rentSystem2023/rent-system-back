package com.rentcar.back.dto.response.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.entity.CompanyEntity;

import lombok.Getter;

@Getter
public class GetCompanyDetailResponseDto extends ResponseDto {
    private Integer companyCode;
    private String rentCompany;
    private String address;
    private String owner;
    private String companyTelnumber;
    private String registDate;
    private String companyRule;

    private GetCompanyDetailResponseDto (CompanyEntity companyEntities) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        
        this.companyCode = companyEntities.getCompanyCode();
        this.rentCompany = companyEntities.getRentCompany();
        this.address = companyEntities.getAddress();
        this.owner = companyEntities.getOwner();
        this.companyTelnumber = companyEntities.getCompanyTelnumber();
        this.registDate = companyEntities.getRegistDate();     
        this.companyRule = companyEntities.getCompanyRule();
    }

    public static ResponseEntity<GetCompanyDetailResponseDto> sucess(CompanyEntity companyEntities) throws Exception {
        GetCompanyDetailResponseDto responseBody = new GetCompanyDetailResponseDto(companyEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
