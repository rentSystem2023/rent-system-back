package com.rentcar.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentcar.back.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    boolean existsByUserId(String userId);

    boolean existsByNickName(String nickName);

    boolean existsByUserEmail(String userEmail);

    boolean existsByUserIdAndUserEmail(String userId, String userEmail);

    UserEntity findByUserId(String userId);

    UserEntity findByNickName(String nickName);

    UserEntity findUserPwByUserId(String userId);

    UserEntity findByUserEmail(String userEmail);
    
    UserEntity findByUserPassword(String userPassword);

    UserEntity findUserIdByUserEmail(String userEmail);

    List<UserEntity> findByOrderByJoinDateDesc();

    List<UserEntity> findByUserIdContainsOrderByJoinDateDesc(String userId);
} 
