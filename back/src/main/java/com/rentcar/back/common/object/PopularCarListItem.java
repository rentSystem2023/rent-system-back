package com.rentcar.back.common.object;

import com.rentcar.back.repository.resultSet.GetPopularCarResultSet;

import java.util.List;
import java.util.ArrayList;

import lombok.Getter;

// @Getter
// public class PopularCarListItem {
//     private String carImageUrl;
//     private String carName;
//     private Integer reservationCount;
//     private String carRentCompany;

//     private PopularCarListItem(CarEntity carEntity) throws Exception {
        
//         this.carImageUrl = carEntity.getCarImageUrl();
//         this.carName = carEntity.getCarName();
//         this.reservationCount = carEntity.getReservationCount();
//         this.carRentCompany = carEntity.getCarRentCompany();
//     }

//     public static List<PopularCarListItem> getList(List<CarEntity> carentities) throws Exception {
//         List<PopularCarListItem> popularList = new ArrayList<>();

//         for (CarEntity carEntity: carentities) {
//             PopularCarListItem popularCarListItem = new PopularCarListItem(carEntity);
//             popularList.add(popularCarListItem);
//         }

//         return popularList;
//     }
// }


@Getter
public class PopularCarListItem {
    private String carImageUrl;
    private String carName;
    private Integer totalReservationCount;

    public PopularCarListItem (GetPopularCarResultSet resultSet) throws Exception {
        this.carImageUrl = resultSet.getCarImageUrl();
        this.carName = resultSet.getCarName();
        this.totalReservationCount = resultSet.getTotalReservationCount();
    }

    public static List<PopularCarListItem> getList (List<GetPopularCarResultSet> resultSets) throws Exception {
        List<PopularCarListItem> popularList = new ArrayList<>();
        for (GetPopularCarResultSet resultSet: resultSets) {
            PopularCarListItem item = new PopularCarListItem(resultSet);
            popularList.add(item);
        }
        return popularList;
    }
}

