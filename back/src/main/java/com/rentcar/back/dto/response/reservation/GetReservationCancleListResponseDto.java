package com.rentcar.back.dto.response.reservation;

import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.object.ReservationCancleListItem;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.entity.ReservationEntity;

import lombok.Getter;

@Getter
public class GetReservationCancleListResponseDto extends ResponseDto {
    
    private List<ReservationCancleListItem> reservationCancleList;

    private GetReservationCancleListResponseDto(List<ReservationEntity> reservationEntities) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reservationCancleList = ReservationCancleListItem.getList(reservationEntities);
    }

    public static ResponseEntity<GetReservationCancleListResponseDto> success (List<ReservationEntity> reservationEntities) throws Exception{
        GetReservationCancleListResponseDto responseBody = new GetReservationCancleListResponseDto(reservationEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
