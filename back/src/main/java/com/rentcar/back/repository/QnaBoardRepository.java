package com.rentcar.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentcar.back.entity.QnaBoardEntity;

@Repository
public interface QnaBoardRepository extends JpaRepository <QnaBoardEntity, Integer> {

    QnaBoardEntity findByReceptionNumber(Integer receptionNumber);
    
    List<QnaBoardEntity> findByOrderByReceptionNumberDesc();

    List<QnaBoardEntity> findByWriterIdOrderByReceptionNumberDesc(String writerId);

    List<QnaBoardEntity> findByTitleContainsOrderByReceptionNumberDesc(String title);
}  
