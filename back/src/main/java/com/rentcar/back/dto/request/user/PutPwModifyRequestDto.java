package com.rentcar.back.dto.request.user;

import com.rentcar.back.common.util.PatternUtil;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PutPwModifyRequestDto {

    @NotNull
    @Pattern(regexp =PatternUtil.PW_PATTERN)
    private String userPassword;
}