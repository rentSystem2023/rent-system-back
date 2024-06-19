package com.rentcar.back.dto.response.reservation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.object.PopularCarTopListItem;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.repository.resultSet.GetPopularCarResultSet;

import lombok.Getter;

@Getter
public class GetReservationPopularCarListResponseDto extends ResponseDto {

    private List<PopularCarTopListItem> popularCarTopList;

    private GetReservationPopularCarListResponseDto(List<GetPopularCarResultSet> resultSets) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        
        this.popularCarTopList = PopularCarTopListItem.getList(resultSets);
    }

    public static ResponseEntity<GetReservationPopularCarListResponseDto> success (List<GetPopularCarResultSet> resultSets) throws Exception {
        GetReservationPopularCarListResponseDto responseBody = new GetReservationPopularCarListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}

