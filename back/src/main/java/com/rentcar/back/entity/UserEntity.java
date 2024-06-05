package com.rentcar.back.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import com.rentcar.back.dto.request.auth.FindPwResetRequestDto;
import com.rentcar.back.dto.request.auth.SignUpRequestDto;
import com.rentcar.back.dto.request.user.PatchUserRequestDto;
import com.rentcar.back.dto.request.user.PutEmailModifyRequestDto;
import com.rentcar.back.dto.request.user.PutPwModifyRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="user")
@Table(name="user") // 매핑되는 테이블의 이름은 user , 클래스명하고 테이블명이 다르기 때문에 지정
@Getter // DB에서 받아오기 위한 작업
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserEntity {    
    
    // 데이터베이스에 개의 컬럼이 존재함
    @Id // UserId의 기본키 지정 
    private String userId; 
    private String nickName;        // 추가
    private String userPassword;
    private String userEmail;
    private String userRole;
    private String joinPath;
    private String joinDate;        // 추가

    // 새로운 생성자 작성 (implement 서비스에서 쓰기 위해)
    public UserEntity(SignUpRequestDto dto) {

        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String joinDate = simpleDateFormat.format(now);

        this.userId = dto.getUserId();
        this.nickName = dto.getNickName();      // 추가
        this.userPassword = dto.getUserPassword();
        this.userEmail = dto.getUserEmail();
        this.userRole = "ROLE_USER";
        this.joinPath = "HOME";      // 추가
        this.joinDate = joinDate;
    }
    
    public UserEntity (String userId, String nickName, String userPassword, String userEmail, String userRole, String joinPath) {
        this.userId = userId;
        this.nickName = nickName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.joinPath = joinPath;
    }

    public void update(PatchUserRequestDto dto){
        this.nickName = dto.getNickName();
    }

    public void findPassword(FindPwResetRequestDto dto){
        this.userPassword = dto.getUserPassword();
    }

    public void findModify(PutPwModifyRequestDto dto){
        this.userPassword = dto.getUserPassword();
    }

    public void emailModify(PutEmailModifyRequestDto dto){
        this.userEmail = dto.getUserEmail();
    }


}

