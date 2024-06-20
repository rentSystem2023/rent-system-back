package com.rentcar.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentcar.back.entity.CompanyEntity;


import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {
    
    List<CompanyEntity> findByOrderByRegistDateDesc();

    CompanyEntity findByCompanyCode(Integer companyCode);

    boolean existsByCompanyCode(Integer companyCode);

    List<CompanyEntity> findByRentCompanyContainsOrderByRegistDateDesc(String rentCompany);

    boolean existsByAddress(String address);

    boolean existsByRentCompany(String rentCompany);
} 

