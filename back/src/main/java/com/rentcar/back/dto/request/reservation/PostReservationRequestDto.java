package com.rentcar.back.dto.request.reservation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostReservationRequestDto  {
    @NotBlank
    private String insuranceType;
    @NotNull
    private Integer commpanyCarCode;
    @NotBlank
    private String reservationDate;
    @NotBlank
    private String reservationSate;
    @NotBlank
    private String reservationPeriod;
}
