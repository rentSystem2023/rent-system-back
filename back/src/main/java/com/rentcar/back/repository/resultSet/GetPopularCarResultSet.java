package com.rentcar.back.repository.resultSet;

public interface GetPopularCarResultSet {

    Integer getTotalReservationCount();
    String getCarName();
    String getCarImageUrl();
    String getCarRentCompany();
}
