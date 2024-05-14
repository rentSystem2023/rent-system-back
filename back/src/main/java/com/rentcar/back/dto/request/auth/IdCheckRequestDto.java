package com.rentcar.back.dto.request.auth;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 아이디 중복 확인 Request Body Dto 

@Getter
@Setter
@NoArgsConstructor

public class IdCheckRequestDto {
    // NOT NULL: NULL 허용 x
    // NOT BLANK: NULL , 빈문자열 , 공백 허용 x
    @NotBlank
    private String userId;

}
