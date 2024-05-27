package com.rentcar.back.common.object;

import java.util.List;
import java.util.ArrayList;

import com.rentcar.back.repository.resultSet.GetSearchReservationPriceResultSet;

import lombok.Getter;

@Getter
public class ReservationCarPriceListItem {
    private String carName;
    private String carImageUrl;
    private String fuelType;
    private String rentCompany;
    private Integer reservationCount;
    private String carYear;
    private Integer normalPrice;
    private Integer luxuryPrice;
    private Integer superPrice;

    public ReservationCarPriceListItem (GetSearchReservationPriceResultSet resultSet) throws Exception {

        this.carName = resultSet.getCarName();
        this.carImageUrl = resultSet.getCarImageUrl();
        this.fuelType = resultSet.getFuelType();
        this.rentCompany = resultSet.getRentCompany();
        this.reservationCount = resultSet.getReservationCount();
        this.carYear = resultSet.getCarYear();
        this.normalPrice = resultSet.getNormalPrice();
        this.luxuryPrice = resultSet.getLuxuryPrice();
        this.superPrice = resultSet.getSuperPrice();
    }

    public static List<ReservationCarPriceListItem> getList (List<GetSearchReservationPriceResultSet> resultSets) throws Exception {
        List<ReservationCarPriceListItem> reservationCarPriceList = new ArrayList<>();
        for (GetSearchReservationPriceResultSet resultSet: resultSets) {
            ReservationCarPriceListItem item = new ReservationCarPriceListItem(resultSet);
            reservationCarPriceList.add(item);
        }
        return reservationCarPriceList;
    }
}
