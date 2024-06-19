package com.rentcar.back.common.object;

import java.util.List;
import java.util.ArrayList;

import com.rentcar.back.repository.resultSet.GetSearchReservationResultSet;

import lombok.Getter;

@Getter
public class ReservationCarListItem {
    private String carName;
    private String carImageUrl;
    private Integer normalPrice;
    private Integer luxuryPrice;
    private Integer superPrice;

    public ReservationCarListItem(GetSearchReservationResultSet resultSet) throws Exception {

        this.carName = resultSet.getCarName();
        this.carImageUrl = resultSet.getCarImageUrl();
        this.normalPrice = resultSet.getNormalPrice();
        this.luxuryPrice = resultSet.getLuxuryPrice();
        this.superPrice = resultSet.getSuperPrice();
    }

    public static List<ReservationCarListItem> getList(List<GetSearchReservationResultSet> resultSets)throws Exception {
        List<ReservationCarListItem> reservationCarList = new ArrayList<>();
        for (GetSearchReservationResultSet resultSet : resultSets) {
            ReservationCarListItem item = new ReservationCarListItem(resultSet);
            reservationCarList.add(item);
        }
        return reservationCarList;
    }

}
