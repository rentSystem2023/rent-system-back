package com.rentcar.back.dto.response.board.qnaboard;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.object.BoardListItem;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.entity.QnaBoardEntity;

import lombok.Getter;

@Getter
public class GetSearchQnaBoardListResponseDto extends ResponseDto{
    
    private List<BoardListItem> boardList;

    private GetSearchQnaBoardListResponseDto (List<QnaBoardEntity> qnaBoardEntities) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardList = BoardListItem.getList(qnaBoardEntities);
    }

    public static ResponseEntity<GetSearchQnaBoardListResponseDto> success (List<QnaBoardEntity> qnaBoardEntities) throws Exception{
        GetSearchQnaBoardListResponseDto responseBody = new GetSearchQnaBoardListResponseDto(qnaBoardEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
