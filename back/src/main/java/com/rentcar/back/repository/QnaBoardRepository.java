package com.rentcar.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentcar.back.entity.QnaBoardEntity;

@Repository
public interface QnaBoardRepository extends JpaRepository <QnaBoardEntity, Integer> {
    
    List<QnaBoardEntity> findByOrderByReceptionNumberDesc();

    List<QnaBoardEntity> findByTitleContainsOrderByReceptionNumberDesc(String title);
    
    QnaBoardEntity findByReceptionNumber(Integer receptionNumber);



}
