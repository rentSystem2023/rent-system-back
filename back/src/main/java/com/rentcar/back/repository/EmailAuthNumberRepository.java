package com.rentcar.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentcar.back.entity.EmailAuthNumberEntity;

// estate 데이터베이스의 email_auth_number 테이블의 작업을 위한 리포지토리
// JPA 리포지토리 확장
@Repository
public interface EmailAuthNumberRepository extends JpaRepository<EmailAuthNumberEntity,String>{

    boolean existsByEmailAndAuthNumber (String email, String authNumber);

}
