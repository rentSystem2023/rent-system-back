package com.rentcar.back.repository.resultSet;

public interface GetReservationDetailResultSet {
    Integer getReservationCode();
    String getRentCompany();
    String getCarName();
    String getCarNumber();
    String getReservationStart();
    String getReservationEnd();
    String getUserId();
    String getReservationState();
}
