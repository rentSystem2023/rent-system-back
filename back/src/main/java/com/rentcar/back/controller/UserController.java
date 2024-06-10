package com.rentcar.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rentcar.back.dto.request.auth.EmailAuthRequestDto;
import com.rentcar.back.dto.request.user.PatchUserRequestDto;
import com.rentcar.back.dto.request.user.PutEmailModifyRequestDto;
import com.rentcar.back.dto.request.user.PutPwModifyRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.company.GetCompanyListResponseDto;
import com.rentcar.back.dto.response.user.GetMyInfoResponseDto;
import com.rentcar.back.dto.response.user.GetSearchUserListResponseDto;
import com.rentcar.back.dto.response.user.GetSignInUserResponseDto;
import com.rentcar.back.dto.response.user.GetUserDetailListResponseDto;
import com.rentcar.back.dto.response.user.GetUserListResponseDto;
import com.rentcar.back.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

// 컨트롤러의 메서드에서 인증 접근 주체의 정보를 가져옴
@RestController
@RequestMapping("/api/rentcar/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser (
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super GetSignInUserResponseDto> response = userService.getSignInUser(userId);
        return response;
    }

    @GetMapping("/information")
    public ResponseEntity<? super GetMyInfoResponseDto> getMyInfo (
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super GetMyInfoResponseDto> response = userService.getMyInfo(userId);
        return response;
    }

    @PutMapping("/information/password-modify")
    public ResponseEntity<ResponseDto> putPasswordModify(
        @RequestBody @Valid PutPwModifyRequestDto requestBody,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = userService.putPasswordModify(requestBody, userId);
        return response;
    }

    // 이메일 인증 확인
    @PostMapping("information/email-auth")
    public ResponseEntity<ResponseDto> emailAuth(
            @RequestBody @Valid EmailAuthRequestDto requestBody) {
        ResponseEntity<ResponseDto> response = userService.emailAuth(requestBody);
        return response;
    }

    @PutMapping("/information/email-modify")
    public ResponseEntity<ResponseDto> putEmailModify(
        @RequestBody @Valid PutEmailModifyRequestDto requestBody,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = userService.putEmailModify(requestBody, userId);
        return response;
    }

    @DeleteMapping("/information/{userId}")
    public ResponseEntity<ResponseDto> deleteMyInfo (
        // @PathVariable("userId") String userId
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = userService.deleteMyInfo(userId);
        return response;
    }

    
    // 

}