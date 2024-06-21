package com.rentcar.back.dto.response.reservation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.object.ReservationCarPriceListItem;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.repository.resultSet.GetSearchReservationPriceResultSet;

import lombok.Getter;

@Getter
public class GetSearchReservationCarPriceListResponseDto extends ResponseDto {
    
    private List<ReservationCarPriceListItem> reservationCarPriceList;

    private GetSearchReservationCarPriceListResponseDto(List<GetSearchReservationPriceResultSet> resultSets) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        
        this.reservationCarPriceList = ReservationCarPriceListItem.getList(resultSets);
    }
    public static ResponseEntity<GetSearchReservationCarPriceListResponseDto> success (List<GetSearchReservationPriceResultSet> resultSets) throws Exception {
        GetSearchReservationCarPriceListResponseDto responseBody = new GetSearchReservationCarPriceListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
