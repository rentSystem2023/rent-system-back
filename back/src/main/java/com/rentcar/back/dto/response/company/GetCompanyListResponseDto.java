package com.rentcar.back.dto.response.company;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.object.CompanyListItem;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.entity.CompanyEntity;

import lombok.Getter;

@Getter
public class GetCompanyListResponseDto extends ResponseDto {
    
    private List<CompanyListItem> companyList;

    private GetCompanyListResponseDto(List<CompanyEntity> companyEntities) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.companyList = CompanyListItem.getList(companyEntities);
    }

    public static ResponseEntity<GetCompanyListResponseDto> success (List<CompanyEntity> companyEntities) throws Exception {
        GetCompanyListResponseDto responseBody = new GetCompanyListResponseDto(companyEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);

    }

}
