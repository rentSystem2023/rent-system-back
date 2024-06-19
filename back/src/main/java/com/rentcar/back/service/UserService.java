package com.rentcar.back.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rentcar.back.dto.response.user.GetSignInUserResponseDto;
import com.rentcar.back.dto.response.user.GetUserDetailListResponseDto;
import com.rentcar.back.dto.request.auth.EmailAuthRequestDto;
import com.rentcar.back.dto.request.user.PutEmailModifyRequestDto;
import com.rentcar.back.dto.request.user.PutPwModifyRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.user.GetMyInfoResponseDto;
import com.rentcar.back.dto.response.user.GetSearchUserListResponseDto;
import com.rentcar.back.dto.response.user.GetUserListResponseDto;

@Service
public interface UserService {

    // 사용자 정보 가져오기
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser (String userId); 

    // 사용자의 세부 정보 가져오기
    ResponseEntity<? super GetMyInfoResponseDto> getMyInfo (String userId);

    // 비밀번호 수정하기
    ResponseEntity<ResponseDto> putPasswordModify (PutPwModifyRequestDto dto, String userId);

    // 이메일 인증하기
    ResponseEntity<ResponseDto> emailAuth (EmailAuthRequestDto dto);

    // 이메일 수정하기
    ResponseEntity<ResponseDto> putEmailModify (PutEmailModifyRequestDto dto, String userId);

    // 회원 탈퇴하기
    ResponseEntity<ResponseDto> deleteMyInfo(String userId);

    
    ResponseEntity<? super GetUserListResponseDto> getUserList (String userId);

    ResponseEntity<ResponseDto> deleteUserList(String userId);

    ResponseEntity<? super GetSearchUserListResponseDto> getSearchUserList(String searchWord);

    ResponseEntity<? super GetUserDetailListResponseDto> getUserDetailList (String userId);


}

