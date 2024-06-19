package com.rentcar.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rentcar.back.dto.request.auth.EmailAuthRequestDto;
import com.rentcar.back.dto.request.user.PutEmailModifyRequestDto;
import com.rentcar.back.dto.request.user.PutPwModifyRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.user.GetMyInfoResponseDto;
import com.rentcar.back.dto.response.user.GetSignInUserResponseDto;
import com.rentcar.back.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rentcar/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 사용자 정보 가져오기
    @GetMapping("/")
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(
            @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super GetSignInUserResponseDto> response = userService.getSignInUser(userId);
        return response;
    }

    // 사용자의 세부 정보 가져오기
    @GetMapping("/information")
    public ResponseEntity<? super GetMyInfoResponseDto> getMyInfo(
            @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super GetMyInfoResponseDto> response = userService.getMyInfo(userId);
        return response;
    }

    // 비밀번호 수정하기
    @PutMapping("/information/password-modify")
    public ResponseEntity<ResponseDto> putPasswordModify(
            @RequestBody @Valid PutPwModifyRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ){
        ResponseEntity<ResponseDto> response = userService.putPasswordModify(requestBody, userId);
        return response;
    }

    // 이메일 인증 확인
    @PostMapping("information/email-auth")
    public ResponseEntity<ResponseDto> emailAuth(
            @RequestBody @Valid EmailAuthRequestDto requestBody
    ){
        ResponseEntity<ResponseDto> response = userService.emailAuth(requestBody);
        return response;
    }

    // 이메일 수정하기
    @PutMapping("/information/email-modify")
    public ResponseEntity<ResponseDto> putEmailModify(
            @RequestBody @Valid PutEmailModifyRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ){
        ResponseEntity<ResponseDto> response = userService.putEmailModify(requestBody, userId);
        return response;
    }

    // 회원 탈퇴하기
    @DeleteMapping("/information/{userId}")
    public ResponseEntity<ResponseDto> deleteMyInfo(
            // @PathVariable("userId") String userId
            @AuthenticationPrincipal String userId
    ){
        ResponseEntity<ResponseDto> response = userService.deleteMyInfo(userId);
        return response;
    }

}