package com.rentcar.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.rentcar.back.entity.CarEntity;

public interface CarRepository extends JpaRepository<CarEntity, Integer> {
    
    List<CarEntity> findTop3ByOrderByReservationCountDesc();
}
