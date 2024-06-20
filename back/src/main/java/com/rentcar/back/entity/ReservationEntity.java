package com.rentcar.back.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import com.rentcar.back.dto.request.reservation.PatchReservationStateRequestDto;
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
@Table(name="reservation")
@Getter 
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
        this.reservationState = "reservationComplete";
        this.reservationStart = dto.getReservationStart();
        this.reservationEnd = dto.getReservationEnd();
        this.userId = userId;
        this.companyCarCode = dto.getCompanyCarCode();
    }

    // 예약상태 변경(예약취소, 취소승인)
    public void update(PatchReservationStateRequestDto dto) {         
        this.reservationState = dto.getReservationState();
    }


}