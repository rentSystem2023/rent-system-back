package com.rentcar.back.dto.request.reservation;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchReservationCancelRequestDto {
    
    @NotBlank
    private String reservationState;
}
