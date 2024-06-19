package com.rentcar.back.dto.response.board.qnaboard;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.object.QnaListItem;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.entity.QnaBoardEntity;

import lombok.Getter;

@Getter
public class GetQnaBoardListResponseDto extends ResponseDto {
    
    private List<QnaListItem> qnaList;

    private GetQnaBoardListResponseDto (List<QnaBoardEntity> qnaBoardEntities) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        
        this.qnaList = QnaListItem.getList(qnaBoardEntities);
    }

    public static ResponseEntity<GetQnaBoardListResponseDto> success (List<QnaBoardEntity> qnaBoardEntities) throws Exception{
        GetQnaBoardListResponseDto responseBody = new GetQnaBoardListResponseDto(qnaBoardEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}

