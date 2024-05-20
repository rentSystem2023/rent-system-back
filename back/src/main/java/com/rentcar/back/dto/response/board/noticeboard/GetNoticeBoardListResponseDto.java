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

// 데이터베이스에서 전체 리스트 조회 -> List<BoardEntity> -> List<BoardListItem> 바꾸는작업

@Getter
public class GetNoticeBoardListResponseDto extends ResponseDto {
    
    private List<NoticeListItem> noticeList;

    private GetNoticeBoardListResponseDto (List<NoticeBoardEntity> NoticeBoardEntities) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.noticeList = NoticeListItem.getList(NoticeBoardEntities);
    }

    public static ResponseEntity<GetNoticeBoardListResponseDto> success (List<NoticeBoardEntity> NoticeBoardEntities) throws Exception{
        GetNoticeBoardListResponseDto responseBody = new GetNoticeBoardListResponseDto(NoticeBoardEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}

