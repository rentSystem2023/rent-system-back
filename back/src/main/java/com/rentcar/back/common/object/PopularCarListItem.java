package com.rentcar.back.common.object;

import com.rentcar.back.entity.CarEntity;

import java.util.List;
import java.util.ArrayList;

import lombok.Getter;

@Getter
public class PopularCarListItem {
    private String carImageUrl;
    private String carName;

    private PopularCarListItem(CarEntity carEntity) throws Exception {
        
        this.carImageUrl = carEntity.getCarImageUrl();
        this.carName = carEntity.getCarName();
    }

    public static List<PopularCarListItem> getList(List<CarEntity> carentities) throws Exception {
        List<PopularCarListItem> popularList = new ArrayList<>();

        for (CarEntity carEntity: carentities) {
            PopularCarListItem popularCarListItem = new PopularCarListItem(carEntity);
            popularList.add(popularCarListItem);
        }

        return popularList;
    }
}
