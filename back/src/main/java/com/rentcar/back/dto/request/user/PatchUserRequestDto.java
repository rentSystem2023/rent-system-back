package com.rentcar.back.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchUserRequestDto {
    
    @NotBlank
    private String userPassword;
    @NotBlank
    private String nickName;
}
