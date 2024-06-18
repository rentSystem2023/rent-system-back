package com.rentcar.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentcar.back.entity.QnaBoardEntity;


// estate 데이터베이스의 board 테이블 작업을 위한 리포지토리
@Repository
public interface QnaBoardRepository extends JpaRepository <QnaBoardEntity, Integer> {
    
    List<QnaBoardEntity> findByOrderByReceptionNumberDesc();

    List<QnaBoardEntity> findByWriterIdOrderByReceptionNumberDesc(String writerId);

    // Contains / Containing / IsContaining = LIKE '%word%'
    // StartingWith => LIKE 'word%'
    // EndingWith => LIKE '%word'
    List<QnaBoardEntity> findByTitleContainsOrderByReceptionNumberDesc(String title);
    QnaBoardEntity findByReceptionNumber(Integer receptionNumber);



}
