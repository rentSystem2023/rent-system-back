package com.rentcar.back.repository.resultSet;

public interface GetSearchReservationPriceResultSet {

    Integer getSuperPrice();
    Integer getLuxuryPrice();
    Integer getNormalPrice();
    String getCarName();
    String getCarYear();
    String getAddress();
    String getFuelType();
    String getCarImageUrl();
    String getRentCompany();
}
