package com.rentcar.back.entity;

import jakarta.persistence.Id;

public class QnaBoardEntity {
    
    @Id
    private Integer receptionNumber;
    private boolean status;
    private String title;
    private String contents;
    private String writerId;
    private String writeDatetime;
    private Integer viewCount;
    private String comment;
    private String imageUrl;
    private String category;
    private boolean publicStatus;
}
