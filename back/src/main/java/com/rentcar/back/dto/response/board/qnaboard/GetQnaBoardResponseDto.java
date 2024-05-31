package com.rentcar.back.dto.response.board.qnaboard;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.util.ChangeDateFormatUtil;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.entity.QnaBoardEntity;

import lombok.Getter;

@Getter
public class GetQnaBoardResponseDto extends ResponseDto {
    private Integer receptionNumber;
    private Boolean status;
    private String title;
    private String writerId;
    private String writeDatetime;
    private Integer viewCount;
    private String contents;
    private String comment;
    private String imageUrl;
    private Boolean publicState;

    private GetQnaBoardResponseDto (QnaBoardEntity qnaBoardEntities) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        String writeDatetime = ChangeDateFormatUtil.changeYYYYMMDD(qnaBoardEntities.getWriteDatetime());

        this.receptionNumber = qnaBoardEntities.getReceptionNumber();
        this.status = qnaBoardEntities.getStatus();
        this.title = qnaBoardEntities.getTitle();
        this.writerId = qnaBoardEntities.getWriterId();
        this.writeDatetime = writeDatetime;
        this.viewCount = qnaBoardEntities.getViewCount();
        this.contents = qnaBoardEntities.getContents();
        this.comment = qnaBoardEntities.getComment();
        this.imageUrl = qnaBoardEntities.getImageUrl();
        this.publicState = qnaBoardEntities.getPublicState();
    }

    public static ResponseEntity<GetQnaBoardResponseDto> success(QnaBoardEntity qnaBoardEntities) throws Exception {
        GetQnaBoardResponseDto responseBody = new GetQnaBoardResponseDto(qnaBoardEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
