package com.rentcar.back.dto.response.reservation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.object.ReservationCarListItem;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.repository.resultSet.GetSearchReservationResultSet;

import lombok.Getter;

@Getter
public class GetSearchReservationCarListResponseDto extends ResponseDto {
    
    private List<ReservationCarListItem> reservationCarList;

    private GetSearchReservationCarListResponseDto(List<GetSearchReservationResultSet> resultSets) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reservationCarList = ReservationCarListItem.getList(resultSets);
    }
    public static ResponseEntity<GetSearchReservationCarListResponseDto> success (List<GetSearchReservationResultSet> resultSets) throws Exception {
        GetSearchReservationCarListResponseDto responseBody = new GetSearchReservationCarListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
