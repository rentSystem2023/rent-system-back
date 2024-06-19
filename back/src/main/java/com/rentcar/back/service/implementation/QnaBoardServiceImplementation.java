package com.rentcar.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rentcar.back.common.object.QnaMyListItem;
import com.rentcar.back.dto.request.board.qnaboard.PostQnaBoardRequestDto;
import com.rentcar.back.dto.request.board.qnaboard.PostQnaCommentRequestDto;
import com.rentcar.back.dto.request.board.qnaboard.PutQnaBoardRequsetDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.board.qnaboard.GetQnaBoardListResponseDto;
import com.rentcar.back.dto.response.board.qnaboard.GetQnaBoardMyListResponseDto;
import com.rentcar.back.dto.response.board.qnaboard.GetQnaBoardResponseDto;
import com.rentcar.back.dto.response.board.qnaboard.GetSearchQnaBoardListResponseDto;
import com.rentcar.back.dto.response.board.qnaboard.GetSearchQnaBoardMyListResponseDto;
import com.rentcar.back.entity.QnaBoardEntity;
import com.rentcar.back.repository.QnaBoardRepository;
import com.rentcar.back.repository.UserRepository;
import com.rentcar.back.service.QnaBoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaBoardServiceImplementation implements QnaBoardService {

    private final QnaBoardRepository qnaBoardRepository;
    private final UserRepository userRepository;

// 문의사항 작성하기
@Override
public ResponseEntity<ResponseDto> postQnaBoard(PostQnaBoardRequestDto dto, String userId) {
    try {

        boolean isExistUser = userRepository.existsById(userId);
        if (!isExistUser) return ResponseDto.authenticationFailed();

        QnaBoardEntity qnaBoardEntity = new QnaBoardEntity(dto, userId);
        qnaBoardRepository.save(qnaBoardEntity);

    } catch (Exception exception) {
        exception.printStackTrace();
        return ResponseDto.databaseError();
    }

    return ResponseDto.success();
}
    // 문의사항 전체 게시물 리스트 불러오기
    @Override
    public ResponseEntity<? super GetQnaBoardListResponseDto> getQnaBoardList() {

        try {

            List<QnaBoardEntity> qnaBoardEntities = qnaBoardRepository.findByOrderByReceptionNumberDesc();
            return GetQnaBoardListResponseDto.success(qnaBoardEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
    }

    // 문의사항 검색 게시물 리스트 불러오기
    @Override
    public ResponseEntity<? super GetSearchQnaBoardListResponseDto> getSearchQnaBoardList(String searchWord) {

        try {

            List<QnaBoardEntity> qnaboardEntities = qnaBoardRepository.findByTitleContainsOrderByReceptionNumberDesc(searchWord);
            return GetSearchQnaBoardListResponseDto.success(qnaboardEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 문의사항 게시물 불러오기
    @Override
    public ResponseEntity<? super GetQnaBoardResponseDto> getQnaBoard(int receptionNumber) {

        try {

            QnaBoardEntity qnaboardEntities = qnaBoardRepository.findByReceptionNumber(receptionNumber);
            if (qnaboardEntities == null) return ResponseDto.noExistBoard();

            return GetQnaBoardResponseDto.success(qnaboardEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    // 문의사항 게시물 조회수 증가
    @Override
    public ResponseEntity<ResponseDto> increaseViewCount(int receptionNumber) {

        try {

            QnaBoardEntity qnaboardEntities = qnaBoardRepository.findByReceptionNumber(receptionNumber);
            if (qnaboardEntities == null) return ResponseDto.noExistBoard();

            qnaboardEntities.increaseViewCount();
            qnaBoardRepository.save(qnaboardEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    // 문의사항 답글 작성
    @Override
    public ResponseEntity<ResponseDto> postQnaComment(PostQnaCommentRequestDto dto, int receptionNumber) {

        try {

            QnaBoardEntity qnaboardEntities = qnaBoardRepository.findByReceptionNumber(receptionNumber);
            if (qnaboardEntities == null) return ResponseDto.noExistBoard();

            boolean status = qnaboardEntities.getStatus();
            if (status) return ResponseDto.writtenComment();

            String comment = dto.getComment();
            qnaboardEntities.setStatus(true);
            qnaboardEntities.setComment(comment);

            qnaBoardRepository.save(qnaboardEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    // 문의사항 게시물 삭제하기
    @Override
    public ResponseEntity<ResponseDto> deleteQnaBoard(int receptionNumber, String userId) {
        
        try {

            QnaBoardEntity qnaboardEntities = qnaBoardRepository.findByReceptionNumber(receptionNumber);
            if (qnaboardEntities == null) return ResponseDto.noExistBoard();

            String writerId = qnaboardEntities.getWriterId();
            boolean isWriter = userId.equals(writerId);
            if (!isWriter) return ResponseDto.authorizationFailed();
            
            qnaBoardRepository.delete(qnaboardEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    // 문의사항 수정하기
    @Override
    public ResponseEntity<ResponseDto> putQnaBoard(PutQnaBoardRequsetDto dto, int receptionNumber, String userId) {

        try {

            QnaBoardEntity qnaboardEntities =qnaBoardRepository.findByReceptionNumber(receptionNumber);
            if (qnaboardEntities == null) return ResponseDto.noExistBoard();

            String writerId = qnaboardEntities.getWriterId();
            boolean isWriter = userId.equals(writerId);
            if (!isWriter) return ResponseDto.authorizationFailed();

            boolean status = qnaboardEntities.getStatus();       // 답글 작성되있는지 확인
            if (status) return ResponseDto.writtenComment();

            qnaboardEntities.update(dto);

            qnaBoardRepository.save(qnaboardEntities);


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }


    // 나의 문의사항 리스트 불러오기
    @Override
    public ResponseEntity<? super GetQnaBoardMyListResponseDto> getQnaBoardMyList(String writerId) {
        try {

            List<QnaBoardEntity> qnaBoardEntities = qnaBoardRepository.findByWriterIdOrderByReceptionNumberDesc(writerId);
            
            return GetQnaBoardMyListResponseDto.success(qnaBoardEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
    }

    // 나의 문의사항 검색 리스트 찾기
    @Override
    public ResponseEntity<? super  GetSearchQnaBoardMyListResponseDto> getSearchQnaBoardMyList(String searchWord) {
        
        try {

            List<QnaBoardEntity> qnaboardEntities = qnaBoardRepository.findByTitleContainsOrderByReceptionNumberDesc(searchWord);
            return GetSearchQnaBoardMyListResponseDto.success(qnaboardEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
}



