package com.rentcar.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.rentcar.back.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rentcar/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 로그인
    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
        @RequestBody @Valid SignInRequestDto requestBody
    ){
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }

    // 아이디 중복 확인
    @PostMapping("/id-check")
    public ResponseEntity<ResponseDto> idCheck(
        @RequestBody @Valid IdCheckRequestDto requestBody
    ){
        ResponseEntity<ResponseDto> response = authService.idCheck(requestBody);
        return response;
    }

    // 닉네임 중복 확인
    @PostMapping("/nickname-check")
    public ResponseEntity<ResponseDto> nickNameCheck(
        @RequestBody @Valid NickNameCheckRequestDto requestBody
    ){
        ResponseEntity<ResponseDto> response = authService.nickNameCheck(requestBody);
        return response;
    }

    // 이메일 인증
    @PostMapping("/email-auth")
    public ResponseEntity<ResponseDto> emailAuth(
        @RequestBody @Valid EmailAuthRequestDto requestBody
    ){
        ResponseEntity<ResponseDto> response = authService.emailAuth(requestBody);
        return response;
    }

    // 이메일 인증 확인
    @PostMapping("/email-auth-check")
    public ResponseEntity<ResponseDto> emailAuthCheck(
        @RequestBody @Valid EmailAuthCheckRequestDto requestBody
    ){
        ResponseEntity<ResponseDto> response = authService.emailAuthCheck(requestBody);
        return response;
    }

    // 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDto> signUp(
        // @RequestBody로 받기 위해 ,valid = 유효성 검사
        @RequestBody @Valid SignUpRequestDto requestBody
    ){
        ResponseEntity<ResponseDto> response = authService.SignUp(requestBody);
        return response;
    }

    // 아이디 찾기
    @PostMapping("/find-id")
    public ResponseEntity<? super FindIdResponseDto> findId(
        @RequestBody @Valid FindIdRequestDto requestBody
    ){
        ResponseEntity<? super FindIdResponseDto> response = authService.FindId(requestBody);
        return response;
    }

    // 아이디 찾기
    @PostMapping("/find-password")
    public ResponseEntity<ResponseDto> findPassword(
        @RequestBody @Valid FindPasswordRequestDto requestBody
    ){
        ResponseEntity<ResponseDto> response = authService.findPassword(requestBody);
        return response;
    }

    // 비밀번호 찾기
    @PutMapping("/find-password/{userId}")
    public ResponseEntity<ResponseDto> findPasswordReset(
        @RequestBody @Valid FindPasswordResetRequestDto requestBody,
        @PathVariable("userId") String userId
    ){
        ResponseEntity<ResponseDto> response = authService.findPasswordReset(requestBody, userId);
        return response;
    }
}
