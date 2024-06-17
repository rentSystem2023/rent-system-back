package com.rentcar.back.repository.resultSet;

public interface GetPopularCarResultSet {
    String getCarImageUrl();
    String getCarName();
    Integer getTotalReservationCount();
    String getCarRentCompany();
}
