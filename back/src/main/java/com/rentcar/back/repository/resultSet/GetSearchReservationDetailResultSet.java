package com.rentcar.back.repository.resultSet;

public interface GetSearchReservationDetailResultSet {
    String getCarName();
    String getCarImageUrl();
    String getCarYear();
    String getBrand();
    String getGrade();
    Integer getCarOil();
    String getFuelType();
    Integer getCapacity();
    Integer getNormalPrice();
    Integer getLuxuryPrice();
    Integer getSuperPrice();
    String getRentCompany();
    String getAddress();
    String getCompanyTelnumber();
    String getCompanyRule();
    String getReservationStart();
    String getReservationEnd();
}
