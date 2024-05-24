package com.rentcar.back.common.object;

import com.rentcar.back.repository.resultSet.GetSearchReservationResultSet;

import lombok.Getter;

@Getter
public class ReservationCarListItem {
    private String carName;
    private String carImageUrl;
    private Integer normalPrice;
    private Integer luxuryPrice;
    private Integer superPrice;

    public ReservationCarListItem (GetSearchReservationResultSet resultSet) throws Exception {
        
        this.carName = resultSet.getCarName();
        this.carImageUrl = resultSet.getCarImageUrl();
        this.normalPrice = resultSet.getNormalPrice();
        this.luxuryPrice = resultSet.getLuxuryPrice();
        
}
