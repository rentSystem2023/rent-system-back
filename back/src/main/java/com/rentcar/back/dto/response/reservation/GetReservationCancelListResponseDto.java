package com.rentcar.back.dto.response.reservation;

import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.object.ReservationCancelListItem;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.entity.ReservationEntity;

import lombok.Getter;

@Getter
public class GetReservationCancelListResponseDto extends ResponseDto {
    
    private List<ReservationCancelListItem> reservationCancelList;

    private GetReservationCancelListResponseDto(List<ReservationEntity> reservationEntities) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        
        this.reservationCancelList = ReservationCancelListItem.getList(reservationEntities);
    }

    public static ResponseEntity<GetReservationCancelListResponseDto> success (List<ReservationEntity> reservationEntities) throws Exception{
        GetReservationCancelListResponseDto responseBody = new GetReservationCancelListResponseDto(reservationEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
