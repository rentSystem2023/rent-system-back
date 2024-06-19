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
    @NotBlank
    private String reservationStart;
    @NotBlank
    private String reservationEnd;
    @NotNull
    private Integer companyCarCode;
}




