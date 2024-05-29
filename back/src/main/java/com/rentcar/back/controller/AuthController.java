package com.rentcar.back.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentcar.back.dto.request.auth.EmailAuthCheckRequestDto;
import com.rentcar.back.dto.request.auth.EmailAuthRequestDto;
import com.rentcar.back.dto.request.auth.FindIdRequestDto;
import com.rentcar.back.dto.request.auth.IdCheckRequestDto;
import com.rentcar.back.dto.request.auth.NickNameCheckRequestDto;
import com.rentcar.back.dto.request.auth.SignInRequestDto;
import com.rentcar.back.dto.request.auth.SignUpRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.auth.SignInResponseDto;
import com.rentcar.back.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

// Auth 모듈 컨트롤러 : /api/rentcar/auth
@RestController // JSON 형태의 리스폰스를 반환하려고 할 때 사용
@RequestMapping("/api/rentcar/auth")
@RequiredArgsConstructor // AuthService 의존성 주입을 위해 작업
public class AuthController {

    // AuthService 의존성
    private final AuthService authService;

    @PostMapping("/sign-in")
    // responsedto , signinresponsedto 둘다 들어올 수 있음 <>
    public ResponseEntity<? super SignInResponseDto> signIn(
        @RequestBody @Valid SignInRequestDto requestBody
    ){
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }

    @PostMapping("/id-check")
    public ResponseEntity<ResponseDto> idCheck( // 클라이언트에게 응답을 보낼때 사용하는 ResponseEntity<ResponseDto>
            @RequestBody @Valid IdCheckRequestDto requestBody) { // valid 유효성검사하는 기본 패키지
        ResponseEntity<ResponseDto> response = authService.idCheck(requestBody);
        return response;
    }

    @PostMapping("/nickname-check")
    public ResponseEntity<ResponseDto> nickNameCheck(
            @RequestBody @Valid NickNameCheckRequestDto requestBody) {
        ResponseEntity<ResponseDto> response = authService.nickNameCheck(requestBody);
        return response;
    }

    // 이메일 인증
    @PostMapping("/email-auth")
    public ResponseEntity<ResponseDto> emailAuth(
            @RequestBody @Valid EmailAuthRequestDto requestBody) {
        ResponseEntity<ResponseDto> response = authService.emailAuth(requestBody);
        return response;
    }

    // 이메일 인증 확인
    @PostMapping("/email-auth-check")
    // body의 형태가 responseDto
    public ResponseEntity<ResponseDto> emailAuthCheck(
            // @RequestBody로 받기 위해 ,valid = 유효성 검사
            @RequestBody @Valid EmailAuthCheckRequestDto requestBody) {
        ResponseEntity<ResponseDto> response = authService.emailAuthCheck(requestBody);
        return response;
    }

    // 회원가입 (API명세서 보고 매핑 작성)
    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDto> signUp(
            // @RequestBody로 받기 위해 ,valid = 유효성 검사
            @RequestBody @Valid SignUpRequestDto requestBody) {
        ResponseEntity<ResponseDto> response = authService.SignUp(requestBody);
        return response;
    }




}
