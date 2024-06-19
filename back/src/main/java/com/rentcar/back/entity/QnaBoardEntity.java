package com.rentcar.back.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.rentcar.back.dto.request.board.qnaboard.PostQnaBoardRequestDto;

import com.rentcar.back.dto.request.board.qnaboard.PutQnaBoardRequsetDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "qnaBoard")
@Table(name = "qna_board")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QnaBoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer receptionNumber;
    private Boolean status;
    private String title;
    private String contents;
    private String writerId;
    private String writeDatetime;
    private Integer viewCount;
    private String comment;
    private String imageUrl;
    private String category;
    private Boolean publicState;

    public QnaBoardEntity(PostQnaBoardRequestDto dto, String userId) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String writeDatetime = now.format(formatter);

        this.status = false;
        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.writerId = userId;
        this.writeDatetime = writeDatetime;
        this.viewCount = 0;
        this.category = dto.getCategory();
        this.publicState = false;
        this.imageUrl = dto.getImageUrl(); // 이미지 URL 설정
        this.publicState = dto.getPublicState();
    }

    // 조회수 증가
    public void increaseViewCount() {
        this.viewCount++;
    }

    // 게시물 수정
    public void update(PutQnaBoardRequsetDto dto) {
        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.category = dto.getCategory();
        this.contents = dto.getContents();
        this.imageUrl = dto.getImageUrl();
        this.publicState = dto.getPublicState();
    }
}