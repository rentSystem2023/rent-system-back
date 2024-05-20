package com.rentcar.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentcar.back.entity.NoticeBoardEntity;


// estate 데이터베이스의 board 테이블 작업을 위한 리포지토리
@Repository
public interface NoticeBoardRepository extends JpaRepository <NoticeBoardEntity, Integer> {
    
    List<NoticeBoardEntity> findByOrderByRegistNumberDesc();
    // Contains / Containing / IsContaining = LIKE '%word%'
    // StartingWith => LIKE 'word%'
    // EndingWith => LIKE '%word'
    List<NoticeBoardEntity> findByTitleContainsOrderByRegistNumberDesc(String title);
    NoticeBoardEntity findByRegistNumber(Integer registNumber);


}
