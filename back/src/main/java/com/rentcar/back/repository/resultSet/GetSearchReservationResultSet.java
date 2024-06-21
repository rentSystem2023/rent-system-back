package com.rentcar.back.repository.resultSet;

public interface GetSearchReservationResultSet {

    Integer getSuperPrice();
    Integer getLuxuryPrice();
    Integer getNormalPrice();
    String getCarName();
    String getCarImageUrl();
}
