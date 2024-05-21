package com.rentcar.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rentcar.back.entity.CompanyEntity;

import com.rentcar.back.entity.ReservationEntity;
import com.rentcar.back.entity.UserEntity;

import jakarta.persistence.Tuple;
@Repository

public interface ReservationRepository extends JpaRepository<ReservationEntity, String> {
    ReservationEntity findByUserIdOrderByRegervationDateDesc(String userId);


    // @Query("SELECT r.user, r.company FROM ReservationEntity r WHERE r.userId = :userId")
    // Tuple findUserAndCompanyByUserId(@Param("userId") String userId);

    // @Query("SELECT c FROM CompanyEntity c WHERE c.companyCode = :companyCode")
    // CompanyEntity findByCompanyCode(@Param("companyCode") Integer companyCode);
    }

    