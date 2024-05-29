package com.rentcar.back.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindIdRequestDto {
    @NotBlank
    private String userEmail;
    // @NotBlank
    // private String authNumber;
    @NotBlank
    private String userId;
}
