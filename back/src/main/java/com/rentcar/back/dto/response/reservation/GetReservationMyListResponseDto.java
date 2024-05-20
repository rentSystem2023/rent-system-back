package com.rentcar.back.dto.response.reservation;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.util.ChangeDateFormatUtil;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.entity.ReservationEntity;

import lombok.Getter;
@Getter
public class GetReservationMyListResponseDto extends ResponseDto {
    private String carImageUrl;
    private String nickName;
    private String reservationDate;
    private String reservationCode;
    private String rentCompany;

    private GetReservationMyListResponseDto(ReservationEntity reservationEntity) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        String reservationDate = ChangeDateFormatUtil.changeYYYYMMDD(reservationEntity.getReservationDate());


        this.carImageUrl = reservationEntity.getCarImageUrl();
        this.nickName = reservationEntity.getNickName();
        this.reservationDate = reservationDate;
        this.reservationCode = reservationEntity.getReservationCode();
        this.rentCompany = reservationEntity.getRentCompany();

    }
       public static ResponseEntity<GetReservationMyListResponseDto> success (ReservationEntity reservationEntity) throws Exception {
        GetReservationMyListResponseDto responseBody = new GetReservationMyListResponseDto(reservationEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
       }
}
