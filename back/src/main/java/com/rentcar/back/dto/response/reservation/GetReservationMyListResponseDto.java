package com.rentcar.back.dto.response.reservation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.object.ReservationListItem;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.repository.resultSet.GetUserReservationResultSet;

import lombok.Getter;
@Getter
public class GetReservationMyListResponseDto extends ResponseDto {

    private List<ReservationListItem> reservationList;

    private GetReservationMyListResponseDto(List<GetUserReservationResultSet> resultSets) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reservationList = ReservationListItem.getList(resultSets);
    }
    public static ResponseEntity<GetReservationMyListResponseDto> success (List<GetUserReservationResultSet> resultSets) throws Exception {
        GetReservationMyListResponseDto responseBody = new GetReservationMyListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
