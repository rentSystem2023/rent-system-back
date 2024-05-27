package com.rentcar.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rentcar.back.dto.request.board.qnaboard.PostQnaBoardRequestDto;
import com.rentcar.back.dto.request.board.qnaboard.PostQnaCommentRequestDto;
import com.rentcar.back.dto.request.board.qnaboard.PutQnaBoardRequsetDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.board.qnaboard.GetQnaBoardListResponseDto;
import com.rentcar.back.dto.response.board.qnaboard.GetQnaBoardMyListResponseDto;
import com.rentcar.back.dto.response.board.qnaboard.GetQnaBoardResponseDto;
import com.rentcar.back.dto.response.board.qnaboard.GetSearchQnaBoardListResponseDto;
import com.rentcar.back.dto.response.board.qnaboard.GetSearchQnaBoardMyListResponseDto;
import com.rentcar.back.service.QnaBoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rentcar/qna")
@RequiredArgsConstructor
public class QnaBoardController {
    
    private final QnaBoardService qnaBoardService;

    // Q&A 작성하기
    @PostMapping("/regist")
    ResponseEntity<ResponseDto> postQnaBoard (
        @RequestBody @Valid PostQnaBoardRequestDto requestBody, 
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = qnaBoardService.postQnaBoard(requestBody, userId);
        return response;
    }

    // Q&A 답글 작성
    @PostMapping("/{receptionNumber}/comment")
    public ResponseEntity<ResponseDto> postQnaComment (
        @RequestBody @Valid PostQnaCommentRequestDto requestBody,
        @PathVariable("receptionNumber") int receptionNumber
    ) {
        ResponseEntity<ResponseDto> response = qnaBoardService.postQnaComment(requestBody, receptionNumber);
        return response;
    }

    // Q&A 전체 게시물 리스트 불러오기
    @GetMapping("/list")
    public ResponseEntity<? super GetQnaBoardListResponseDto> getQnaBoardList () {
        ResponseEntity<? super GetQnaBoardListResponseDto> response = qnaBoardService.getQnaBoardList();
        return response;
    }

    // Q&A 검색 게시물 리스트 불러오기
    @GetMapping("/list/{search}")
    public ResponseEntity<? super GetSearchQnaBoardListResponseDto> getSearchBoardList (
        @RequestParam("word") String word
    ) {
        ResponseEntity<? super GetSearchQnaBoardListResponseDto> response = qnaBoardService.getSearchQnaBoardList(word);
        return response;
    }

    // Q&A 게시물 불러오기
    @GetMapping("/list/{receptionNumber}")
    public ResponseEntity<? super GetQnaBoardResponseDto> getQnaBoard (
        @PathVariable("receptionNumber") int receptionNumber
    ) {
        ResponseEntity<? super GetQnaBoardResponseDto> response = qnaBoardService.getQnaBoard(receptionNumber);
        return response;
    }

    // Q&A 수정하기
    @PutMapping("/{receptionNumber}/modify")
    public ResponseEntity<ResponseDto> putQnaBoard (
        @RequestBody @Valid PutQnaBoardRequsetDto requestBody,
        @PathVariable("receptionNumber") int receptionNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = qnaBoardService.putQnaBoard(requestBody, receptionNumber, userId);
        return response;
    }

    // Q&A 게시물 조회수 증가
    @PatchMapping("/{receptionNumber}/increase-view-count")
    public ResponseEntity<ResponseDto> increaseViewCount (
        @PathVariable("receptionNumber") int receptionNumber
    ) {
        ResponseEntity<ResponseDto> response = qnaBoardService.increaseViewCount(receptionNumber);
        return response;
    }

    // Q&A 게시물 삭제하기
    @DeleteMapping("/{receptionNumber}/delete")
    public ResponseEntity<ResponseDto> deleteQnaBoard (
        @PathVariable("receptionNumber") int receptionNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = qnaBoardService.deleteQnaBoard(receptionNumber, userId);
        return response;
    }

    // 나의 Q&A 리스트 불러오기
    @GetMapping("/mylist")
    public ResponseEntity<? super GetQnaBoardMyListResponseDto> getQnaBoardMyList () {
        ResponseEntity<? super GetQnaBoardMyListResponseDto> response = qnaBoardService.getQnaBoardMyList();
        return response;
    }


    // 나의 Q&A 검색 리스트 찾기
    @GetMapping("/mylist/search")
    public ResponseEntity<? super GetSearchQnaBoardMyListResponseDto> getSearchBoardMyList (     
        @RequestParam("word") String word
    ) {
        ResponseEntity<? super GetSearchQnaBoardMyListResponseDto> response = qnaBoardService.getSearchQnaBoardMyList(word);
        return response;
    

    }
}