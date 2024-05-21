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
public class GetSearchCompanyListResponseDto extends ResponseDto{
    
    private List<CompanyListItem> companyList;

    private GetSearchCompanyListResponseDto (List<CompanyEntity> CompanyEntities) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.companyList = CompanyListItem.getList(CompanyEntities);
    }

    public static ResponseEntity<GetSearchCompanyListResponseDto> success (List<CompanyEntity> CompanyEntities) throws Exception {
        GetSearchCompanyListResponseDto responseBody = new GetSearchCompanyListResponseDto(CompanyEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
