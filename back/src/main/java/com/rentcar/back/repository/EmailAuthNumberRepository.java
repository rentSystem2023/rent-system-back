package com.rentcar.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentcar.back.entity.EmailAuthNumberEntity;

@Repository
public interface EmailAuthNumberRepository extends JpaRepository<EmailAuthNumberEntity, String>{

    boolean existsByEmailAndAuthNumber (String email, String authNumber);

    EmailAuthNumberEntity findByEmail (String email);
} 
