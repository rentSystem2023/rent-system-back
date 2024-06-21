package com.rentcar.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentcar.back.entity.CompanyEntity;


import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {
    
    boolean existsByCompanyCode(Integer companyCode);

    boolean existsByAddress(String address);

    boolean existsByRentCompany(String rentCompany);

    CompanyEntity findByCompanyCode(Integer companyCode);

    List<CompanyEntity> findByOrderByRegistDateDesc();

    List<CompanyEntity> findByRentCompanyContainsOrderByRegistDateDesc(String rentCompany);

} 

