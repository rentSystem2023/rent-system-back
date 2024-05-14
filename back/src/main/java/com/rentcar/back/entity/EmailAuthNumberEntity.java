package com.rentcar.back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="emailAuthNumber") // 다른 엔터티로 사용하기 위한 지정
@Table(name="email_auth_number") // 매핑되는 테이블의 이름
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailAuthNumberEntity {
    @Id
    private String email;
    private String authNumber;
}
