package com.rentcar.back.common.object;

import com.rentcar.back.repository.resultSet.GetPopularCarResultSet;

import java.util.List;
import java.util.ArrayList;

import lombok.Getter;

@Getter
public class PopularCarListItem {
    
    private String carImageUrl;
    private String carName;
    private Integer totalReservationCount;

    public PopularCarListItem(GetPopularCarResultSet resultSet) throws Exception {
        this.carImageUrl = resultSet.getCarImageUrl();
        this.carName = resultSet.getCarName();
        this.totalReservationCount = resultSet.getTotalReservationCount();
    }

    public static List<PopularCarListItem> getList(List<GetPopularCarResultSet> resultSets) throws Exception {
        List<PopularCarListItem> popularList = new ArrayList<>();
        
        for (GetPopularCarResultSet resultSet : resultSets) {
            PopularCarListItem item = new PopularCarListItem(resultSet);
            popularList.add(item);
        }
        return popularList;
    }
}
