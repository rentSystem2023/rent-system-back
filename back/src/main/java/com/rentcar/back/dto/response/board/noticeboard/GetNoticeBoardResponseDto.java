package com.rentcar.back.dto.response.board.noticeboard;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rentcar.back.common.util.ChangeDateFormatUtil;
import com.rentcar.back.dto.response.ResponseCode;
import com.rentcar.back.dto.response.ResponseDto;
import com.rentcar.back.dto.response.ResponseMessage;
import com.rentcar.back.entity.NoticeBoardEntity;

import lombok.Getter;

@Getter
public class GetNoticeBoardResponseDto extends ResponseDto {
    
    private Integer registNumber;
    private String title;
    private String writerId;
    private String writeDatetime;
    private Integer viewCount;
    private String contents;
    private String imageUrl;

    private GetNoticeBoardResponseDto (NoticeBoardEntity noticeBoardEntity) throws Exception {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        String writeDatetime = ChangeDateFormatUtil.changeYYYYMMDD(noticeBoardEntity.getWriteDatetime());

        this.registNumber = noticeBoardEntity.getRegistNumber();
        this.title = noticeBoardEntity.getTitle();
        this.writerId = noticeBoardEntity.getWriterId();
        this.writeDatetime = writeDatetime;
        this.viewCount = noticeBoardEntity.getViewCount();
        this.contents = noticeBoardEntity.getContents();
        this.imageUrl = noticeBoardEntity.getImageUrl();
    }

    public static ResponseEntity<GetNoticeBoardResponseDto> success(NoticeBoardEntity noticeBoardEntity) throws Exception {
        GetNoticeBoardResponseDto responseBody = new GetNoticeBoardResponseDto(noticeBoardEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
