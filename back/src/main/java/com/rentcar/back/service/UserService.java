package com.rentcar.back.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rentcar.back.dto.response.user.GetSignInUserResponseDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.user.GetMyInfoResponseDto;

@Service
public interface UserService {

    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser (String userId); 

    ResponseEntity<? super GetMyInfoResponseDto> getMyInfo (String userId);

    ResponseEntity<ResponseDto> myInfoModify(String userId);
}

