package com.rentcar.back.dto.response.reservation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.object.ReservationUserListItem;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.repository.resultSet.GetAllUserReservationResultSet;

import lombok.Getter;

@Getter
public class GetReservationUserListResponseDto extends ResponseDto {
    
    private List<ReservationUserListItem> reservationUserList;

    private GetReservationUserListResponseDto(List<GetAllUserReservationResultSet> resultSets) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reservationUserList = ReservationUserListItem.getList(resultSets);
    }
        public static ResponseEntity<GetReservationUserListResponseDto> success (List<GetAllUserReservationResultSet> resultSets) throws Exception {
            GetReservationUserListResponseDto responseBody = new GetReservationUserListResponseDto(resultSets);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }
}
