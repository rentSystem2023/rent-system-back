package com.rentcar.back.common.object;

import java.util.List;

import com.rentcar.back.common.util.ChangeDateFormatUtil;
import com.rentcar.back.entity.QnaBoardEntity;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class QnaMyListItem {
    private Integer receptionNumber;
    private Boolean status;
    private String title;
    private String writerId;
    private String writeDatetime;
    private Integer viewCount;

    private QnaMyListItem(QnaBoardEntity qnaBoardEntity) throws Exception {
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


    public static List<QnaMyListItem> getList(List<QnaBoardEntity>qnaBoardEntities) throws Exception {
        List<QnaMyListItem> qnaMyList = new ArrayList<>();
        
        for (QnaBoardEntity qnaBoardEntity: qnaBoardEntities) {
            QnaMyListItem boardListItem = new QnaMyListItem(qnaBoardEntity);
            qnaMyList.add(boardListItem);
        }

        return qnaMyList;
}
}