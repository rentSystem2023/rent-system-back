package com.rentcar.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rentcar.back.dto.request.auth.FindIdRequestDto;
import com.rentcar.back.dto.request.user.PatchUserRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.user.GetFindIdResponseDto;
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

    @PatchMapping("/information/modify")
    public ResponseEntity<ResponseDto> myInfoModify (
        @RequestBody @Valid PatchUserRequestDto requestBody,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = userService.myInfoModify(requestBody, userId);
        return response;
    }

    @DeleteMapping("/information/{userId}")
    public ResponseEntity<ResponseDto> deleteMyInfo (
        @PathVariable("userId") String userId
        //, @AuthenticationPrincipal String userId
        // 토큰 없어도 되겠죠?
    ) {
        ResponseEntity<ResponseDto> response = userService.deleteMyInfo(userId);
        return response;
    }

    // 관리자의 회원목록 리스트
    @GetMapping("/list")
    public ResponseEntity<? super GetUserListResponseDto> getUserList (
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super GetUserListResponseDto> response = userService.getUserList(userId);
        return response;
    }

    @DeleteMapping("/list/{userId}")
    public ResponseEntity<ResponseDto> deleteUserList (
        @PathVariable("userId") String userId
        //, @AuthenticationPrincipal String userId
        // 토큰 없어도 되겠죠?
    ) {
        ResponseEntity<ResponseDto> response = userService.deleteUserList(userId);
        return response;
    }


    @GetMapping("/list/search")
    public ResponseEntity<? super GetSearchUserListResponseDto> getSearchUserList (
        @RequestParam("word") String searchWord
    ) {
        ResponseEntity<? super GetSearchUserListResponseDto> response = userService.getSearchUserList(searchWord);
        return response;
    }

    // 회원관리 상세보기
    @GetMapping("/list/{userId}/")
    public ResponseEntity<? super GetUserDetailListResponseDto> getUserDetailList (
        @PathVariable("userId") String userId
    ) {
        ResponseEntity<? super GetUserDetailListResponseDto> response = userService.getUserDetailList(userId);
        return response;
    }

        @PostMapping("/find-id")
    public ResponseEntity<? super GetFindIdResponseDto> findIdcheck(@RequestBody @Valid FindIdRequestDto requestBody) {
        ResponseEntity<? super GetFindIdResponseDto> response = userService.FindId(requestBody.getUserEmail());
        return response;
    }

    @GetMapping("/find-id/{userEmail}")
    public ResponseEntity<? super GetFindIdResponseDto> getfindId (
        @PathVariable("userEmail") String userEmail
    ) {
        ResponseEntity<? super GetFindIdResponseDto> response = userService.getFindId(userEmail);
        return response;
    }
    
    

}