package com.rentcar.back.service;

import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.request.board.noticeboard.PostNoticeBoardRequestDto;
import com.rentcar.back.dto.request.board.noticeboard.PutNoticeBoardRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.board.noticeboard.GetNoticeBoardListResponseDto;
import com.rentcar.back.dto.response.board.noticeboard.GetNoticeBoardResponseDto;
import com.rentcar.back.dto.response.board.noticeboard.GetSearchNoticeBoardListResponseDto;

public interface NoticeBoardService {

    // 공지사항 리스트 보기
    ResponseEntity<? super GetNoticeBoardListResponseDto> getNoticeBoardList();

    // 공지사항 검색하기
    ResponseEntity<? super GetSearchNoticeBoardListResponseDto> getSearchNoticeBoardList(String searchWord);

    // 공지사항 보기
    ResponseEntity<? super GetNoticeBoardResponseDto> getNoticeBoard(int registNumber);

    // 공지사항 작성하기
    ResponseEntity<ResponseDto> postNoticeBoard(PostNoticeBoardRequestDto dto, String userId);

    // 공지사항 수정하기
    ResponseEntity<ResponseDto> putNoticeBoard(PutNoticeBoardRequestDto dto, int registNumber, String userId);

    // 공지사항 조회수 증가
    ResponseEntity<ResponseDto> increaseViewCount(int registNumber);

    // 공지사항 삭제하기
    ResponseEntity<ResponseDto> deleteNoticeBoard(int registNumber, String userId);
}
