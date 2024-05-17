package com.rentcar.back.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import com.rentcar.back.dto.request.board.qnaboard.PostQnaBoardRequestDto;
import com.rentcar.back.dto.request.board.qnaboard.PutQnaBoardRequsetDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="qnaboard")
@Table(name="qnaboard")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QnaBoardEntity {
    
    @Id
    private Integer receptionNumber;
    private Boolean status;
    private String title;
    private String contents;
    private String writerId;
    private String writeDatetime;
    private Integer viewCount;
    private String comment;
    private String imageUrl;    // ??
    private String category;    // 
    private boolean publicState;   

    public QnaBoardEntity(PostQnaBoardRequestDto dto, String userId) {
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String writeDatetime = simpleDateFormat.format(now);

        this.status = false;
        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.writerId = userId;
        this.writeDatetime = writeDatetime;
        this.viewCount = 0;
        this.publicState = true;
    }

    // 조회수 증가
    public void increaseViewCount() {
        this.viewCount++;
    }

    // 게시물 수정
    public void update(PutQnaBoardRequsetDto dto) {         
        this.title = dto.getTitle();
        this.contents = dto.getContents();
    }
}
