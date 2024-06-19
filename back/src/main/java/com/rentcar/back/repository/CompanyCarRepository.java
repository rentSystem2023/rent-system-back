package com.rentcar.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentcar.back.entity.CompanyCarEntity;


@Repository
public interface CompanyCarRepository extends JpaRepository<CompanyCarEntity, Integer> {
    
    boolean existsByCompanyCarCode(Integer companyCarCode);

    CompanyCarEntity findByCompanyCarCode(Integer companyCarCode);
}

