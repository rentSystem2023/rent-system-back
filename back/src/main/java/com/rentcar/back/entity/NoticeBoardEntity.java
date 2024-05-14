package com.rentcar.back.entity;

import jakarta.persistence.Id;

public class NoticeBoardEntity {
    
    @Id
    private Integer registNumber;
    private String title;
    private String contents;
    private String writerId;
    private String writeDateTime;
    private Integer viewCount;
    private String imageUrl;
}
