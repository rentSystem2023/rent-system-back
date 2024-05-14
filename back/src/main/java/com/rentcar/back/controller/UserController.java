package com.rentcar.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentcar.back.dto.response.user.GetSignInUserResponseDto;
import com.rentcar.back.service.UserService;

import lombok.RequiredArgsConstructor;

// 컨트롤러의 메서드에서 인증 접근 주체의 정보를 가져옴
@RestController
@RequestMapping("/api/v1/user")
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

}