package com.rentcar.back.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rentcar.back.dto.response.user.GetSignInUserResponseDto;
import com.rentcar.back.dto.response.user.GetUserDetailListResponseDto;
import com.rentcar.back.dto.request.user.PatchUserRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.user.GetFindIdResponseDto;
import com.rentcar.back.dto.response.user.GetMyInfoResponseDto;
import com.rentcar.back.dto.response.user.GetSearchUserListResponseDto;
import com.rentcar.back.dto.response.user.GetUserListResponseDto;

@Service
public interface UserService {

    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser (String userId); 

    ResponseEntity<? super GetMyInfoResponseDto> getMyInfo (String userId);

    ResponseEntity<ResponseDto> myInfoModify(PatchUserRequestDto dto, String userId);

    ResponseEntity<ResponseDto> deleteMyInfo(String userId);

    ResponseEntity<? super GetUserListResponseDto> getUserList (String userId);

    ResponseEntity<ResponseDto> deleteUserList(String userId);

    ResponseEntity<? super GetSearchUserListResponseDto> getSearchUserList(String searchWord);

    ResponseEntity<? super GetUserDetailListResponseDto> getUserDetailList (String userId);

    ResponseEntity<ResponseDto> FindId(String userEmail);

    ResponseEntity<? super GetFindIdResponseDto> getFindId(String userEmail);

}

