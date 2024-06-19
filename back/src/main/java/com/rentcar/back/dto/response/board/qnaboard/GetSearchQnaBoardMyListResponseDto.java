package com.rentcar.back.dto.response.board.qnaboard;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.object.QnaMyListItem;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.entity.QnaBoardEntity;

import lombok.Getter;

@Getter
public class GetSearchQnaBoardMyListResponseDto extends ResponseDto {
    private List<QnaMyListItem> qnaList;

    private GetSearchQnaBoardMyListResponseDto (List<QnaBoardEntity> qnaBoardEntities) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.qnaList = QnaMyListItem.getList(qnaBoardEntities);
    }

    public static ResponseEntity<GetSearchQnaBoardMyListResponseDto> success (List<QnaBoardEntity> qnaBoardEntities) throws Exception{
        GetSearchQnaBoardMyListResponseDto responseBody = new GetSearchQnaBoardMyListResponseDto(qnaBoardEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}