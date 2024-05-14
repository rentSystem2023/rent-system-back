package com.rentcar.back.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//*  로그인 Request Body DTO

@Getter
@Setter
@NoArgsConstructor
public class SignInRequestDto {
    @NotBlank
    private String userId;
    @NotBlank
    private String userPassword;
}

// 위 코드 작업 후
// Response Dto 확장해서 만들기 -> SignInResponseDto