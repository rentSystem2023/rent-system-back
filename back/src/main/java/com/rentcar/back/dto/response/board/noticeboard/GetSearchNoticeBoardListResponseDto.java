package com.rentcar.back.dto.response.board.noticeboard;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.object.NoticeListItem;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.entity.NoticeBoardEntity;

import lombok.Getter;

@Getter
public class GetSearchNoticeBoardListResponseDto extends ResponseDto{
    
    private List<NoticeListItem> boardList;

    private GetSearchNoticeBoardListResponseDto (List<NoticeBoardEntity> NoticeBoardEntities) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardList = NoticeListItem.getList(NoticeBoardEntities);
    }

    public static ResponseEntity<GetSearchNoticeBoardListResponseDto> success (List<NoticeBoardEntity> NoticeBoardEntities) throws Exception{
        GetSearchNoticeBoardListResponseDto responseBody = new GetSearchNoticeBoardListResponseDto(NoticeBoardEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
