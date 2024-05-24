package com.rentcar.back.dto.response.reservation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.object.PopularCarListItem;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.entity.CarEntity;

import lombok.Getter;

@Getter
public class GetReservationPopularListResponseDto extends ResponseDto {

    private List<PopularCarListItem> popularList;

    private GetReservationPopularListResponseDto(List<CarEntity> carEntities) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.popularList = PopularCarListItem.getList(carEntities);
    }

    public static ResponseEntity<GetReservationPopularListResponseDto> success (List<CarEntity> carEntities) throws Exception {
        GetReservationPopularListResponseDto responseBody = new GetReservationPopularListResponseDto(carEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
