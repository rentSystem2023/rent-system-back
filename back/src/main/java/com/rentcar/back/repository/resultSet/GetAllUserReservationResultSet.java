package com.rentcar.back.repository.resultSet;

public interface GetAllUserReservationResultSet {
    String getReservaionCode();
    String getRentCompany();
    String getCarName();
    String getCarNumber();
    String getReservationStart();
    String getReservationEnd();
    String getUserId();
    String getNickName();
    String getReservationState();
}
