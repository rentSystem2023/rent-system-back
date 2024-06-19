package com.rentcar.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentcar.back.entity.NoticeBoardEntity;

@Repository
public interface NoticeBoardRepository extends JpaRepository <NoticeBoardEntity, Integer> {
    
    List<NoticeBoardEntity> findByOrderByRegistNumberDesc();

    List<NoticeBoardEntity> findByTitleContainsOrderByRegistNumberDesc(String title);

    NoticeBoardEntity findByRegistNumber(Integer registNumber);
}
