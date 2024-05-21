package com.rentcar.back.dto.response.reservation;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.util.ChangeDateFormatUtil;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.entity.CarEntity;
import com.rentcar.back.entity.CompanyEntity;
import com.rentcar.back.entity.ReservationEntity;
import com.rentcar.back.entity.UserEntity;

import lombok.Getter;
@Getter
public class GetReservationMyListResponseDto extends ResponseDto {
    private String carImageUrl;
    private String nickName;
    private String reservationDate;
    private String reservationCode;
    private String rentCompany;

    private GetReservationMyListResponseDto(ReservationEntity reservationEntity, CarEntity carEntity, UserEntity userEntity, CompanyEntity companyEntity) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        String reservationDate = ChangeDateFormatUtil.changeYYYYMMDD(reservationEntity.getReservationDate());

        this.carImageUrl = carEntity.getCarImageUrl();
        this.nickName = userEntity.getNickName();
        this.reservationDate = reservationDate;
        this.reservationCode = reservationEntity.getReservationCode();
        this.rentCompany = companyEntity.getRentCompany();

    }
        public static ResponseEntity<GetReservationMyListResponseDto> success (ReservationEntity reservationEntity, CarEntity carEntity, UserEntity userEntity, CompanyEntity companyEntity) throws Exception {
        GetReservationMyListResponseDto responseBody = new GetReservationMyListResponseDto(reservationEntity, carEntity, userEntity, companyEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
