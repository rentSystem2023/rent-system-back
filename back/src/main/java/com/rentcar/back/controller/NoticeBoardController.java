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

import com.rentcar.back.dto.request.board.noticeboard.PostNoticeBoardRequestDto;
import com.rentcar.back.dto.request.board.noticeboard.PutNoticeBoardRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.board.noticeboard.GetNoticeBoardListResponseDto;
import com.rentcar.back.dto.response.board.noticeboard.GetNoticeBoardResponseDto;
import com.rentcar.back.dto.response.board.noticeboard.GetSearchNoticeBoardListResponseDto;
import com.rentcar.back.service.NoticeBoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rentcar/notice")
@RequiredArgsConstructor
public class NoticeBoardController {
    
    private final NoticeBoardService noticeBoardService;

    // 공지사항 작성하기
    @PostMapping("/regist")
    ResponseEntity<ResponseDto> postNoticeBoard (
        @RequestBody @Valid PostNoticeBoardRequestDto requestBody, 
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<ResponseDto> response = noticeBoardService.postNoticeBoard(requestBody, userId);
        return response;
    }

    // 공지사항 전체 게시물 리스트 불러오기
    @GetMapping("/list")
    public ResponseEntity<? super GetNoticeBoardListResponseDto> getNoticeBoardList () {
        ResponseEntity<? super GetNoticeBoardListResponseDto> response = noticeBoardService.getNoticeBoardList();
        return response;
    }

    // 공지사항 검색 게시물 리스트 불러오기
    @GetMapping("/list/search")
    public ResponseEntity<? super GetSearchNoticeBoardListResponseDto> getSearchNoticeBoardList (
        @RequestParam("word") String word
    ){
        ResponseEntity<? super GetSearchNoticeBoardListResponseDto> response = noticeBoardService.getSearchNoticeBoardList(word);
        return response;
    }   

    // 공지사항 게시물 불러오기
    @GetMapping("/list/{registNumber}")
    public ResponseEntity<? super GetNoticeBoardResponseDto> getNoticeBoard (
        @PathVariable("registNumber") int registNumber
    ){
        ResponseEntity<? super GetNoticeBoardResponseDto> response = noticeBoardService.getNoticeBoard(registNumber);
        return response;
    }

    // 공지사항 수정하기
    @PutMapping("/{registNumber}/modify")
    public ResponseEntity<ResponseDto> putNoticeBoard (
        @RequestBody @Valid PutNoticeBoardRequestDto requestBody,
        @PathVariable("registNumber") int registNumber,
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<ResponseDto> response = noticeBoardService.putNoticeBoard(requestBody, registNumber, userId);
        return response;
    }

    // 공지사항 게시물 조회수 증가
    @PatchMapping("/{registNumber}/increase-view-count")
    public ResponseEntity<ResponseDto> increaseViewCount (
        @PathVariable("registNumber") int registNumber
    ){
        ResponseEntity<ResponseDto> response = noticeBoardService.increaseViewCount(registNumber);
        return response;
    }

    // 공지사항 게시물 삭제하기
    @DeleteMapping("/{registNumber}/delete")
    public ResponseEntity<ResponseDto> deleteNoticeBoard (
        @PathVariable("registNumber") int registNumber,
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<ResponseDto> response = noticeBoardService.deleteNoticeBoard(registNumber, userId);
        return response;  
    }

}
