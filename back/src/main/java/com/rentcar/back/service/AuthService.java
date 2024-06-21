package com.rentcar.back.service;

import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.request.auth.EmailAuthCheckRequestDto;
import com.rentcar.back.dto.request.auth.EmailAuthRequestDto;
import com.rentcar.back.dto.request.auth.FindIdRequestDto;
import com.rentcar.back.dto.request.auth.FindPasswordRequestDto;
import com.rentcar.back.dto.request.auth.FindPasswordResetRequestDto;
import com.rentcar.back.dto.request.auth.IdCheckRequestDto;
import com.rentcar.back.dto.request.auth.NickNameCheckRequestDto;
import com.rentcar.back.dto.request.auth.SignInRequestDto;
import com.rentcar.back.dto.request.auth.SignUpRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.auth.FindIdResponseDto;
import com.rentcar.back.dto.response.auth.SignInResponseDto;

public interface AuthService {
    
    // 로그인
    ResponseEntity<? super SignInResponseDto> signIn (SignInRequestDto dto);

    // 아이디 체크
    ResponseEntity<ResponseDto> idCheck (IdCheckRequestDto dto);

    // 닉네임 체크
    ResponseEntity<ResponseDto> nickNameCheck (NickNameCheckRequestDto dto);

    // 이메일 인증
    ResponseEntity<ResponseDto> emailAuth (EmailAuthRequestDto dto);

    // 이메일 인증 확인
    ResponseEntity<ResponseDto> emailAuthCheck (EmailAuthCheckRequestDto dto);

    // 회원가입
    ResponseEntity<ResponseDto> signUp (SignUpRequestDto dto);

    // 아이디 찾기
    ResponseEntity<? super FindIdResponseDto> findId (FindIdRequestDto dto);

    // 비밀번호 찾기
    ResponseEntity<ResponseDto> findPassword (FindPasswordRequestDto dto);

    // 비밀번호 변경
    ResponseEntity<ResponseDto> findPasswordReset (FindPasswordResetRequestDto dto, String userId);
}
