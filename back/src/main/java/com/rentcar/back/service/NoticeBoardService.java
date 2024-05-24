package com.rentcar.back.service;

import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.request.board.noticeboard.PostNoticeBoardRequestDto;
import com.rentcar.back.dto.request.board.noticeboard.PutNoticeBoardRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.board.noticeboard.GetNoticeBoardListResponseDto;
import com.rentcar.back.dto.response.board.noticeboard.GetNoticeBoardResponseDto;
import com.rentcar.back.dto.response.board.noticeboard.GetSearchNoticeBoardListResponseDto;



public interface NoticeBoardService {
    
    ResponseEntity<ResponseDto> postNoticeBoard(PostNoticeBoardRequestDto dto, String userId);
    ResponseEntity<? super GetNoticeBoardListResponseDto> getNoticeBoardList();
    ResponseEntity<? super GetSearchNoticeBoardListResponseDto> getSearchNoticeBoardList(String searchWord);
    ResponseEntity<? super GetNoticeBoardResponseDto> getNoticeBoard(int registNumber);

    ResponseEntity<ResponseDto> putNoticeBoard(PutNoticeBoardRequestDto dto, int registNumber, String userId);

    ResponseEntity<ResponseDto> increaseViewCount(int registNumber);

    ResponseEntity<ResponseDto> deleteNoticeBoard(int registNumber, String userId);

}
