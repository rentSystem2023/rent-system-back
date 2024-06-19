package com.rentcar.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentcar.back.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {

    UserEntity findByUserId(String userId);

    UserEntity findByNickName(String nickName);

    UserEntity findByUserEmail(String userEmail);

    UserEntity findByUserPassword(String userPassword);

    boolean existsByUserEmail(String userEmail);

    boolean existsByUserId(String userId);

    boolean existsByNickName(String nickName);

    boolean existsByUserIdAndUserEmail(String userId, String userEmail);

    String findUserIdByUserEmail(String userEmail);
    
    String findUserPwByUserId(String userId);

    List<UserEntity> findByOrderByJoinDateDesc();

    List<UserEntity> findByUserIdContainsOrderByJoinDateDesc(String userId);

}
