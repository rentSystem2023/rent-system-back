package com.rentcar.back.common.object;

import java.util.List;
import java.util.ArrayList;

import com.rentcar.back.common.util.ChangeDateFormatUtil;
import com.rentcar.back.entity.QnaBoardEntity;

import lombok.Getter;

@Getter
public class QnaListItem {
    private Integer receptionNumber;
    private Boolean status;
    private String title;
    private String writerId;
    private String writeDatetime;
    private Integer viewCount;

    private QnaListItem(QnaBoardEntity qnaBoardEntity) throws Exception {
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

    public static List<QnaListItem> getList(List<QnaBoardEntity> qnaBoardEntities) throws Exception {
        List<QnaListItem> qnaList = new ArrayList<>();
    
        for (QnaBoardEntity qnaBoardEntity: qnaBoardEntities) {
            QnaListItem boardListItem = new QnaListItem(qnaBoardEntity);
            qnaList.add(boardListItem);
        }

        return qnaList;
    }
}