package com.rentcar.back.repository.resultSet;

public interface GetSearchReservationPriceResultSet {
    String getCarName();
    String getCarImageUrl();
    String getFuelType();
    String getRentCompany();
    String getCarYear();
    String getAddress();
    Integer getNormalPrice();
    Integer getLuxuryPrice();
    Integer getSuperPrice();
}
