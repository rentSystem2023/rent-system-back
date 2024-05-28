package com.rentcar.back.service;

import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.request.auth.EmailAuthCheckRequestDto;
import com.rentcar.back.dto.request.auth.EmailAuthRequestDto;
import com.rentcar.back.dto.request.auth.IdCheckRequestDto;
import com.rentcar.back.dto.request.auth.NickNameCheckRequestDto;
import com.rentcar.back.dto.request.auth.SignInRequestDto;
import com.rentcar.back.dto.request.auth.SignUpRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.auth.SignInResponseDto;

public interface AuthService {
    
    ResponseEntity<? super SignInResponseDto>signIn (SignInRequestDto dto);
    //리스폰스객체를 반환
    ResponseEntity<ResponseDto> idCheck (IdCheckRequestDto dto); // 여기까지 작성후 implementation 구현
    ResponseEntity<ResponseDto> nickNameCheck (NickNameCheckRequestDto dto);
    ResponseEntity<ResponseDto> emailAuth (EmailAuthRequestDto dto);
    ResponseEntity<ResponseDto> emailAuthCheck (EmailAuthCheckRequestDto dto);
    ResponseEntity<ResponseDto> SignUp (SignUpRequestDto dto);
}
