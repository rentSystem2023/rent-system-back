package com.rentcar.back.entity;

import com.rentcar.back.dto.request.company.PostCompanyRequestDto;
import com.rentcar.back.dto.request.company.PutCompanyRequestDto;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="company")
@Table(name="company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEntity {
    
    @Id
    private Integer companyCode;
    private String rentCompany;
    private String address;
    private String companyTelnumber;
    private String owner;
    private String registDate;
    private String companyRule;
    private String companyLat;
    private String companyLng;

    public CompanyEntity(PostCompanyRequestDto dto, String userId) {
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String companyDatetime = simpleDateFormat.format(now);

        this.companyCode = dto.getCompanyCode();
        this.rentCompany = dto.getRentCompany();
        this.address = dto.getAddress();
        this.companyTelnumber = dto.getCompanyTelnumber();
        this.owner = dto.getOwner();
        this.registDate = companyDatetime;
        this.companyRule = dto.getCompanyRule();
    }

    public void update(PutCompanyRequestDto dto){
        this.rentCompany = dto.getRentCompany();
        this.address = dto.getAddress();
        this.owner = dto.getOwner();
        this.companyTelnumber = dto.getCompanyTelnumber();
        this.companyRule = dto.getCompanyRule();
    }
}
