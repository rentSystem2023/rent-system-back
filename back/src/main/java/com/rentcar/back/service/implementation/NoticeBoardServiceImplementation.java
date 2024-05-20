package com.rentcar.back.service.implementation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rentcar.back.dto.request.board.noticeboard.PostNoticeBoardRequestDto;
import com.rentcar.back.dto.request.board.noticeboard.PutNoticeBoardRequestDto;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.board.noticeboard.GetNoticeBoardListResponseDto;
import com.rentcar.back.dto.response.board.noticeboard.GetNoticeBoardResponseDto;
import com.rentcar.back.dto.response.board.noticeboard.GetSearchNoticeBoardListResponseDto;
import com.rentcar.back.entity.NoticeBoardEntity;
import com.rentcar.back.repository.NoticeBoardRepository;
import com.rentcar.back.repository.UserRepository;
import com.rentcar.back.service.NoticeBoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class NoticeBoardServiceImplementation implements NoticeBoardService {

    private final NoticeBoardRepository noticeBoardRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<ResponseDto> postNoticeBoard(PostNoticeBoardRequestDto dto, String userId) {

        try {
            
            boolean isExistUser = userRepository.existsById(userId);
            if (!isExistUser) return ResponseDto.authenticationFailed();
            
            NoticeBoardEntity noticeBoardEntity = new NoticeBoardEntity(dto, userId);
            noticeBoardRepository.save(noticeBoardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<? super GetNoticeBoardListResponseDto> getNoticeBoardList() {

        try {

            List<NoticeBoardEntity> noticeBoardEntities = noticeBoardRepository.findByOrderByRegistNumberDesc();
            return GetNoticeBoardListResponseDto.success(noticeBoardEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    }

    @Override
    public ResponseEntity<? super GetSearchNoticeBoardListResponseDto> getSearchNoticeBoardList(String searchWord) {

        try {

            List<NoticeBoardEntity> noticeBoardEntities = noticeBoardRepository
                    .findByTitleContainsOrderByRegistNumberDesc(searchWord);
            return GetSearchNoticeBoardListResponseDto.success(noticeBoardEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetNoticeBoardResponseDto> getNoticeBoard(int registNumber) {

        try {

            NoticeBoardEntity noticeBoardEntity = noticeBoardRepository.findByRegistNumber(registNumber);
            if (noticeBoardEntity == null)
                return ResponseDto.noExistBoard();

            return GetNoticeBoardResponseDto.success(noticeBoardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<ResponseDto> increaseViewCount(int registNumber) {

        try {

            NoticeBoardEntity noticeBoardEntity = noticeBoardRepository.findByRegistNumber(registNumber);
            if (noticeBoardEntity == null)
                return ResponseDto.noExistBoard();

            noticeBoardEntity.increaseViewCount();
            noticeBoardRepository.save(noticeBoardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteNoticeBoard(int registNumber, String userId) {

        try {

            NoticeBoardEntity NoticeBoardEntity = noticeBoardRepository.findByRegistNumber(registNumber);
            if (NoticeBoardEntity == null)
                return ResponseDto.noExistBoard();

            String writerId = NoticeBoardEntity.getWriterId();
            boolean isWriter = userId.equals(writerId);
            if (!isWriter)
                return ResponseDto.authorizationFailed();

            noticeBoardRepository.delete(NoticeBoardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> putNoticeBoard(PutNoticeBoardRequestDto dto, int registNumber, String userId) {

        try {

            NoticeBoardEntity NoticeBoardEntity = noticeBoardRepository.findByRegistNumber(registNumber);
            if (NoticeBoardEntity == null)
                return ResponseDto.noExistBoard();

            String writerId = NoticeBoardEntity.getWriterId();
            boolean isWriter = userId.equals(writerId);
            if (!isWriter)
                return ResponseDto.authorizationFailed();

            NoticeBoardEntity.update(dto);

            noticeBoardRepository.save(NoticeBoardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }
}
