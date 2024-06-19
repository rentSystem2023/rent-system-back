package com.rentcar.back.service;

import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.user.GetSearchUserListResponseDto;
import com.rentcar.back.dto.response.user.GetUserDetailListResponseDto;
import com.rentcar.back.dto.response.user.GetUserListResponseDto;

public interface UserListService {
    
    // 관리자페이지 회원 관리 리스트 가져오기
    ResponseEntity<? super GetUserListResponseDto> getUserList(String userId);

    // 관리자페이지 회원 삭제하기
    ResponseEntity<ResponseDto> deleteUserList(String userId);

    // 관리자페이지 회원 관리 리스트 검색하기
    ResponseEntity<? super GetSearchUserListResponseDto> getSearchUserList(String searchWord);

    // 관리자페이지 회원 관리 회원 상세 보기
    ResponseEntity<? super GetUserDetailListResponseDto> getUserDetailList(String userId);

}
