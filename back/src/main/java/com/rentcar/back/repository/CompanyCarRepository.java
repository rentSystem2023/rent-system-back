package com.rentcar.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentcar.back.entity.CompanyCarEntity;



public interface CompanyCarRepository extends JpaRepository<CompanyCarEntity, Integer> {
    
    boolean existsByCompanyCarCode(Integer companyCarCode);
    
    CompanyCarEntity findByCompanyCarCode(Integer companyCarCode);
}

