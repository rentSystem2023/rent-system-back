package com.rentcar.back.repository.resultSet;

public interface GetSearchReservationPriceResultSet {
    String getCarName();
    String getCarImageUrl();
    String getFuelType();
    String getRentCompany();
    Integer getReservationCount();
    String getAddress();
    Integer getNormalPrice();
    Integer getLuxuryPrice();
    Integer getSuperPrice();
}
