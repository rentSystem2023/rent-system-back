package com.rentcar.back.service;

import org.springframework.http.ResponseEntity;

import com.rentcar.back.dto.request.board.qnaboard.PostQnaBoardRequestDto;
import com.rentcar.back.dto.request.board.qnaboard.PostQnaCommentRequestDto;
import com.rentcar.back.dto.request.board.qnaboard.PutQnaBoardRequsetDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.board.qnaboard.GetQnaBoardListResponseDto;
import com.rentcar.back.dto.response.board.qnaboard.GetQnaBoardMyListResponseDto;
import com.rentcar.back.dto.response.board.qnaboard.GetQnaBoardResponseDto;
import com.rentcar.back.dto.response.board.qnaboard.GetSearchQnaBoardListResponseDto;
import com.rentcar.back.dto.response.board.qnaboard.GetSearchQnaBoardMyListResponseDto;



public interface QnaBoardService {

    // 문의사항 리스트 보기
    ResponseEntity<? super GetQnaBoardListResponseDto> getQnaBoardList();

    // 문의사항 작성하기    
    ResponseEntity<ResponseDto> postQnaBoard(PostQnaBoardRequestDto dto, String userId);

    // 문의사항 댓글 달기
    ResponseEntity<ResponseDto> postQnaComment(PostQnaCommentRequestDto dto, int receptionNumber);

    // 문의사항 리스트 검색하기
    ResponseEntity<? super GetSearchQnaBoardListResponseDto> getSearchQnaBoardList(String searchWord);

    // 문의사항 보기
    ResponseEntity<? super GetQnaBoardResponseDto> getQnaBoard(int receptionNumber);

    // 문의사항 수정하기
    ResponseEntity<ResponseDto> putQnaBoard(PutQnaBoardRequsetDto dto, int receptionNumber, String userId);

    // 문의사항 조회수 증가
    ResponseEntity<ResponseDto> increaseViewCount(int receptionNumber);

    // 문의사항 삭제하기
    ResponseEntity<ResponseDto> deleteQnaBoard(int receptionNumber, String userId);

    // 문의사항 나의 리스트 보기
    ResponseEntity<? super GetQnaBoardMyListResponseDto> getQnaBoardMyList();

    // 문의사항 나의 리스트 검색하기
    ResponseEntity<? super GetSearchQnaBoardMyListResponseDto> getSearchQnaBoardMyList(String searchWord);
}
