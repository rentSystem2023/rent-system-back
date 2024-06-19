package com.rentcar.back.service;

import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.request.auth.EmailAuthCheckRequestDto;
import com.rentcar.back.dto.request.auth.EmailAuthRequestDto;
import com.rentcar.back.dto.request.auth.FindIdRequestDto;
import com.rentcar.back.dto.request.auth.FindPwRequestDto;
import com.rentcar.back.dto.request.auth.FindPwResetRequestDto;
import com.rentcar.back.dto.request.auth.IdCheckRequestDto;
import com.rentcar.back.dto.request.auth.NickNameCheckRequestDto;
import com.rentcar.back.dto.request.auth.SignInRequestDto;
import com.rentcar.back.dto.request.auth.SignUpRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.auth.FindIdResponseDto;
import com.rentcar.back.dto.response.auth.SignInResponseDto;

public interface AuthService {
    
    ResponseEntity<? super SignInResponseDto>signIn (SignInRequestDto dto);
    ResponseEntity<ResponseDto> idCheck (IdCheckRequestDto dto);
    ResponseEntity<ResponseDto> nickNameCheck (NickNameCheckRequestDto dto);
    ResponseEntity<ResponseDto> emailAuth (EmailAuthRequestDto dto);
    ResponseEntity<ResponseDto> emailAuthCheck (EmailAuthCheckRequestDto dto);
    ResponseEntity<ResponseDto> SignUp (SignUpRequestDto dto);

    ResponseEntity<? super FindIdResponseDto> FindId (FindIdRequestDto dto);

    ResponseEntity<ResponseDto> findPassword (FindPwRequestDto dto);
    ResponseEntity<ResponseDto> findPasswordReset (FindPwResetRequestDto dto, String userId);


    
}
