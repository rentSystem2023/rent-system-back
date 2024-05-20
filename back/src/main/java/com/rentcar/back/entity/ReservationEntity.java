package com.rentcar.back.entity;

import com.rentcar.back.dto.request.reservation.PatchReservationRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private String reservationCode;
    @NotBlank
    private String nickName;
    @NotBlank
    private String carImageUrl;
    @NotBlank
    private String reservationDate;
    @NotBlank
    private String rentCompany;
}
