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

    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser (String userId); 

    ResponseEntity<? super GetMyInfoResponseDto> getMyInfo (String userId);

    ResponseEntity<ResponseDto> putPasswordModify (PutPwModifyRequestDto dto, String userId);

    ResponseEntity<ResponseDto> emailAuth (EmailAuthRequestDto dto);

    ResponseEntity<ResponseDto> putEmailModify (PutEmailModifyRequestDto dto, String userId);

    ResponseEntity<ResponseDto> deleteMyInfo(String userId);

    ResponseEntity<? super GetUserListResponseDto> getUserList (String userId);

    ResponseEntity<ResponseDto> deleteUserList(String userId);

    ResponseEntity<? super GetSearchUserListResponseDto> getSearchUserList(String searchWord);

    ResponseEntity<? super GetUserDetailListResponseDto> getUserDetailList (String userId);


}

