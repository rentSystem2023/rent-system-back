package com.rentcar.back.dto.response.reservation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.object.PopularCarListItem;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.repository.resultSet.GetPopularCarResultSet;

import lombok.Getter;

@Getter
public class GetReservationPopularListResponseDto extends ResponseDto {

    private List<PopularCarListItem> popularList;

    private GetReservationPopularListResponseDto(List<GetPopularCarResultSet> resultSets) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.popularList = PopularCarListItem.getList(resultSets);
    }

    public static ResponseEntity<GetReservationPopularListResponseDto> success (List<GetPopularCarResultSet> resultSets) throws Exception {
        GetReservationPopularListResponseDto responseBody = new GetReservationPopularListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
