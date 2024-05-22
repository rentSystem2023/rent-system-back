package com.rentcar.back.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import com.rentcar.back.dto.request.reservation.PostReservationRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="reservation")
@Table(name="reservation") // 매핑되는 테이블의 이름은 user , 클래스명하고 테이블명이 다르기 때문에 지정
@Getter // DB에서 받아오기 위한 작업
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ReservationEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer reservationCode;
    private String userId;
    private String insuranceType;
    private String reservationDate;
    private String reservationState;
    private String reservationStart;
    private String reservationEnd;
    private Integer companyCarCode;


public ReservationEntity(PostReservationRequestDto dto, String userId) {
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String reservationDate = simpleDateFormat.format(now);
        this.insuranceType = dto.getInsuranceType();
        this.reservationDate = reservationDate;
        this.reservationStart = dto.getReservationStart();
        this.reservationEnd = dto.getReservationEnd();
        this.userId = userId;
        this.companyCarCode = dto.getCompanyCarCode();
    }

}