package com.rentcar.back.dto.request.auth;
import com.rentcar.back.common.util.PatternUtil;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {
    
    @NotBlank
    private String userId;
    @NotBlank
    private String nickName;
    @NotNull
    @Pattern(regexp = PatternUtil.PW_PATTERN)
    private String userPassword;
    @NotNull
    @Pattern(regexp = PatternUtil.EMAIL_PATTERN)
    private String userEmail;
    @NotBlank
    private String authNumber;
}
