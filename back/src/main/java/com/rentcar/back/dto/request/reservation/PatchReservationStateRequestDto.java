package com.rentcar.back.dto.request.reservation;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchReservationStateRequestDto {
    
    @NotBlank
    private String reservationState;
}
