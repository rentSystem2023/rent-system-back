package com.rentcar.back.dto.response.board.qnaboard;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.object.QnaMyListItem;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.entity.QnaBoardEntity;
import java.util.List;
import lombok.Getter;

@Getter
public class GetQnaBoardMyListResponseDto extends ResponseDto{
        private List<QnaMyListItem> qnaList;

    private GetQnaBoardMyListResponseDto (List<QnaBoardEntity> qnaBoardEntities) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        
        this.qnaList = QnaMyListItem.getList(qnaBoardEntities);
    }

    public static ResponseEntity<GetQnaBoardMyListResponseDto> success (List<QnaBoardEntity> qnaBoardEntities) throws Exception{
        GetQnaBoardMyListResponseDto responseBody = new GetQnaBoardMyListResponseDto(qnaBoardEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
