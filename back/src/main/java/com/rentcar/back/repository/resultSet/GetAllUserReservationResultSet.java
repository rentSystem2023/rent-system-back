package com.rentcar.back.repository.resultSet;

public interface GetAllUserReservationResultSet {
    Integer getReservationCode();
    String getRentCompany();
    String getCarName();
    String getCarNumber();
    String getReservationStart();
    String getReservationEnd();
    String getUserId();
    String getNickName();
    String getReservationState();
}
