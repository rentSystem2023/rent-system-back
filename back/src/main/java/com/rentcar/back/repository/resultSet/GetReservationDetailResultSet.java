package com.rentcar.back.repository.resultSet;

public interface GetReservationDetailResultSet {

    Integer getInsurancePrice();
    Integer getReservationCode();
    String getUserId();
    String getCarName();
    String getCarNumber();
    String getRentCompany();
    String getInsuranceType();
    String getReservationEnd();
    String getReservationStart();
    String getReservationState();
}
