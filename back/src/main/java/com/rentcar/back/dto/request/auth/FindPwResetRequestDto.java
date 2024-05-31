package com.rentcar.back.dto.request.auth;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindPwResetRequestDto {
    
    @NotNull
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,13}$")
    private String userPassword;

}
