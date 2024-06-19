package com.rentcar.back.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.user.GetSearchUserListResponseDto;
import com.rentcar.back.dto.response.user.GetUserDetailListResponseDto;
import com.rentcar.back.dto.response.user.GetUserListResponseDto;
import com.rentcar.back.service.UserListService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rentcar/admin")
@RequiredArgsConstructor
public class UserListController {

    private final UserListService userListService;

    // 관리자의 회원목록 리스트
    @GetMapping("/list")
    public ResponseEntity<? super GetUserListResponseDto> getUserList (
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super GetUserListResponseDto> response = userListService.getUserList(userId);
        return response;
    }

    // 회원관리 상세보기
    @GetMapping("/list/{userId}")
    public ResponseEntity<? super GetUserDetailListResponseDto> getUserDetailList (
        @PathVariable("userId") String userId
    ) {
        ResponseEntity<? super GetUserDetailListResponseDto> response = userListService.getUserDetailList(userId);
        return response;
    }

    // 회원 삭제하기
    @DeleteMapping("/list/{userId}")
    public ResponseEntity<ResponseDto> deleteUserList (
        @PathVariable("userId") String userId
    ) {
        ResponseEntity<ResponseDto> response = userListService.deleteUserList(userId);
        return response;
    }

    // 회원 검색하기
    @GetMapping("/list/search")
    public ResponseEntity<? super GetSearchUserListResponseDto> getSearchUserList (
        @RequestParam("word") String searchWord
    ) {
        ResponseEntity<? super GetSearchUserListResponseDto> response = userListService.getSearchUserList(searchWord);
        return response;
    }
}
