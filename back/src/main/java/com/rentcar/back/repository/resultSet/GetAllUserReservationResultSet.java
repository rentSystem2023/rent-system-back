package com.rentcar.back.repository.resultSet;

public interface GetAllUserReservationResultSet {

    Integer getReservationCode();
    String getUserId();
    String getCarName();
    String getNickName();
    String getCarNumber();
    String getRentCompany();
    String getReservationEnd();
    String getReservationState();
    String getReservationStart();
}
