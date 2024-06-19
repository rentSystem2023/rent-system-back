package com.rentcar.back.entity;

import com.rentcar.back.dto.request.board.noticeboard.PostNoticeBoardRequestDto;
import com.rentcar.back.dto.request.board.noticeboard.PutNoticeBoardRequestDto;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="noticeBoard")
@Table(name="notice_board")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeBoardEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     
    private Integer registNumber;
    private String title;
    private String contents;
    private String writerId;
    private String writeDatetime;
    private Integer viewCount;
    private String imageUrl;
    
    public NoticeBoardEntity(PostNoticeBoardRequestDto dto, String userId) {
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String writeDatetime = simpleDateFormat.format(now);

        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.writerId = userId;
        this.writeDatetime = writeDatetime;
        this.viewCount = 0;
        this.imageUrl = dto.getImageUrl(); 
    }

    // 조회수 증가
    public void increaseViewCount() {
        this.viewCount++;
    }

    // 게시물 수정
    public void update(PutNoticeBoardRequestDto dto) {         
        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.imageUrl = dto.getImageUrl();
    }
}
