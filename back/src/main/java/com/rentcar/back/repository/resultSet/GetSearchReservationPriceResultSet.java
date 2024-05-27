package com.rentcar.back.repository.resultSet;

public interface GetSearchReservationPriceResultSet {
    String getCarName();
    String getCarImageUrl();
    String getFuelType();
    String getRentCompany();
    Integer getReservationCount();
    String getCarYear();
    Integer getNormalPrice();
    Integer getLuxuryPrice();
    Integer getSuperPrice();
}
