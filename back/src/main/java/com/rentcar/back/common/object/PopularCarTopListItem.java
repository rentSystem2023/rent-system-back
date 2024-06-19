package com.rentcar.back.common.object;

import java.util.List;
import java.util.ArrayList;

import com.rentcar.back.repository.resultSet.GetPopularCarResultSet;

import lombok.Getter;

@Getter
public class PopularCarTopListItem {
    private String carImageUrl;
    private String carName;
    private Integer totalReservationCount;

    public PopularCarTopListItem(GetPopularCarResultSet resultSet) throws Exception {
        this.carImageUrl = resultSet.getCarImageUrl();
        this.carName = resultSet.getCarName();
        this.totalReservationCount = resultSet.getTotalReservationCount();
    }

    public static List<PopularCarTopListItem> getList(List<GetPopularCarResultSet> resultSets) throws Exception {
        List<PopularCarTopListItem> popularCarTopList = new ArrayList<>();
        for (GetPopularCarResultSet resultSet : resultSets) {
            PopularCarTopListItem item = new PopularCarTopListItem(resultSet);
            popularCarTopList.add(item);
        }
        return popularCarTopList;
    }
}
