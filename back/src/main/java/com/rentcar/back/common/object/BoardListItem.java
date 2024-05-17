package com.rentcar.back.common.object;

import java.util.List;
import java.util.ArrayList;

import com.rentcar.back.common.util.ChangeDateFormatUtil;
import com.rentcar.back.entity.QnaBoardEntity;

import lombok.Getter;

@Getter
public class BoardListItem {
    private Integer receptionNumber;
    private Boolean status;
    private String title;
    private String writerId;
    private String writeDatetime;
    private Integer viewCount;

    private BoardListItem(QnaBoardEntity qnaBoardEntity) throws Exception {
        String writeDatetime = ChangeDateFormatUtil.changeYYMMDD(qnaBoardEntity.getWriteDatetime());

        String writerId = qnaBoardEntity.getWriterId();
        writerId = writerId.substring(0, 1) + "*".repeat(writerId.length() - 1);
        
        this.receptionNumber = qnaBoardEntity.getReceptionNumber();
        this.status = qnaBoardEntity.getStatus();
        this.title = qnaBoardEntity.getTitle();
        this.writerId = writerId;
        this.writeDatetime = writeDatetime;
        this.viewCount = qnaBoardEntity.getViewCount();
    }

    public static List<BoardListItem> getList(List<QnaBoardEntity> qnaBoardEntities) throws Exception {
        List<BoardListItem> boardList = new ArrayList<>();
    
        for (QnaBoardEntity boardEntity: qnaBoardEntities) {
            BoardListItem boardListItem = new BoardListItem(boardEntity);
            boardList.add(boardListItem);
        }

        return boardList;
    }
}