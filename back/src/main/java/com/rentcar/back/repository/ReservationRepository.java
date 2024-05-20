package com.rentcar.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentcar.back.entity.ReservationEntity;
import com.rentcar.back.entity.UserEntity;
@Repository

public interface ReservationRepository extends JpaRepository<UserEntity,String> {

    ReservationEntity findByUserId(String userId);

    }

