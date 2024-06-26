package com.rentcar.back.common.object;

import com.rentcar.back.common.util.ChangeDateFormatUtil;
import com.rentcar.back.entity.CompanyEntity;

import lombok.Getter;

import java.util.List;
import java.util.ArrayList;

@Getter
public class CompanyListItem {
    
    private Integer companyCode;
    private String rentCompany;
    private String address;
    private String owner;
    private String companyTelnumber;
    private String registDate;
    private String companyRule;

    private CompanyListItem(CompanyEntity companyEntity) throws Exception {
        String registDate = ChangeDateFormatUtil.registYYMMDD(companyEntity.getRegistDate());

        this.companyCode = companyEntity.getCompanyCode();
        this.rentCompany = companyEntity.getRentCompany();
        this.address = companyEntity.getAddress();
        this.owner = companyEntity.getOwner();
        this.companyTelnumber = companyEntity.getCompanyTelnumber();
        this.registDate = registDate;
        this.companyRule = companyEntity.getCompanyRule();
    }

    public static List<CompanyListItem> getList(List<CompanyEntity> companyEntities) throws Exception {
        List<CompanyListItem> companyList = new ArrayList<>();

        for (CompanyEntity companyEntity : companyEntities) {
            CompanyListItem companyListItem = new CompanyListItem(companyEntity);
            companyList.add(companyListItem);
        }

        return companyList;
    }
}
