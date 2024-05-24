package com.rentcar.back.repository.resultSet;

public interface GetSearchReservationResultSet {
    String getCarName();
    String getCarImageUrl();
    Integer getNormalPrice();
    Integer getLuxuryPrice();
    Integer getSuperPrice();
}
