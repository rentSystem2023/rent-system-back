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
    
    ResponseEntity<ResponseDto> postQnaBoard(PostQnaBoardRequestDto dto, String userId);
    ResponseEntity<ResponseDto> postQnaComment(PostQnaCommentRequestDto dto, int receptionNumber);
    ResponseEntity<? super GetQnaBoardListResponseDto> getQnaBoardList();
    ResponseEntity<? super GetSearchQnaBoardListResponseDto> getSearchQnaBoardList(String searchWord);
    ResponseEntity<? super GetQnaBoardResponseDto> getQnaBoard(int receptionNumber);

    ResponseEntity<ResponseDto> putQnaBoard(PutQnaBoardRequsetDto dto, int receptionNumber, String userId);

    ResponseEntity<ResponseDto> increaseViewCount(int receptionNumber);

    ResponseEntity<ResponseDto> deleteQnaBoard(int receptionNumber, String userId);

    //QnA MyList
    ResponseEntity<? super GetQnaBoardMyListResponseDto> getQnaBoardMyList();

    //QnA search
    ResponseEntity<? super GetSearchQnaBoardMyListResponseDto> getSearchQnaBoardMyList(String searchWord);
}
