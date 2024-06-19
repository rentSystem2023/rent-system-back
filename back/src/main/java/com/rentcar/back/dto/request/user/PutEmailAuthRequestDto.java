package com.rentcar.back.dto.request.user;

import com.rentcar.back.common.util.PatternUtil;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PutEmailAuthRequestDto {
    
    @NotNull
    @Pattern(regexp = PatternUtil.EMAIL_PATTERN)
    private String userEmail;
}
