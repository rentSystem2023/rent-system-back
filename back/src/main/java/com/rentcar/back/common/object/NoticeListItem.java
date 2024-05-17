package com.rentcar.back.common.object;

import java.util.List;
import java.util.ArrayList;

import com.rentcar.back.common.util.ChangeDateFormatUtil;
import com.rentcar.back.entity.NoticeBoardEntity;

import lombok.Getter;

@Getter
public class NoticeListItem {
    private Integer registNumber;
    private String title;
    private String writeDatetime;
    private Integer viewCount;

    private NoticeListItem(NoticeBoardEntity noticeBoardEntity) throws Exception {
        String writeDatetime = ChangeDateFormatUtil.changeYYMMDD(noticeBoardEntity.getWriteDateTime());
        
        this.registNumber = noticeBoardEntity.getRegistNumber();
        this.title = noticeBoardEntity.getTitle();
        this.writeDatetime = writeDatetime;
        this.viewCount = noticeBoardEntity.getViewCount();
    }

    public static List<NoticeListItem> getList(List<NoticeBoardEntity> boardEntities) throws Exception {
       List<NoticeListItem> noticeList = new ArrayList<>();
       
       for (NoticeBoardEntity noticeBoardEntity: boardEntities) {
            NoticeListItem noticeListItem = new NoticeListItem(noticeBoardEntity);
            noticeList.add(noticeListItem);
       }

       return noticeList;
    }
}
