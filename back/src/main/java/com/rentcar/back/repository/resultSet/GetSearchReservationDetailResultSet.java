package com.rentcar.back.repository.resultSet;

public interface GetSearchReservationDetailResultSet {

    Double getCompanyLat();
    Double getCompanyLng();
    Integer getCarOil();
    Integer getCapacity();
    Integer getSuperPrice();
    Integer getNormalPrice();
    Integer getLuxuryPrice();
    Integer getCompanyCarCode();
    String getBrand();
    String getGrade();
    String getCarName();
    String getCarYear();
    String getAddress();
    String getFuelType();
    String getRentCompany();
    String getCompanyRule();
    String getCarImageUrl();
    String getReservationEnd();
    String getCompanyTelnumber();
    String getReservationStart();
}
