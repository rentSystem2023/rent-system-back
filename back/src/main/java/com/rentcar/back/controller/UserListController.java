package com.rentcar.back.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.user.GetSearchUserListResponseDto;
import com.rentcar.back.dto.response.user.GetUserDetailListResponseDto;
import com.rentcar.back.dto.response.user.GetUserListResponseDto;
import com.rentcar.back.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

// 컨트롤러의 메서드에서 인증 접근 주체의 정보를 가져옴
@RestController
@RequestMapping("/api/rentcar/user/list")
@RequiredArgsConstructor
public class UserListController {

    private final UserService userService;

    // 관리자의 회원목록 리스트
    @GetMapping("/")
    public ResponseEntity<? super GetUserListResponseDto> getUserList (
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super GetUserListResponseDto> response = userService.getUserList(userId);
        return response;
    }

    // 회원관리 상세보기
    @GetMapping("/{userId}")
    public ResponseEntity<? super GetUserDetailListResponseDto> getUserDetailList (
        @PathVariable("userId") String userId
    ) {
        ResponseEntity<? super GetUserDetailListResponseDto> response = userService.getUserDetailList(userId);
        return response;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseDto> deleteUserList (
        @PathVariable("userId") String userId
    ) {
        ResponseEntity<ResponseDto> response = userService.deleteUserList(userId);
        return response;
    }


    @GetMapping("/search")
    public ResponseEntity<? super GetSearchUserListResponseDto> getSearchUserList (
        @RequestParam("word") String searchWord
    ) {
        ResponseEntity<? super GetSearchUserListResponseDto> response = userService.getSearchUserList(searchWord);
        return response;
    }
    
}
