package com.rentcar.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rentcar.back.entity.ReservationEntity;
import com.rentcar.back.repository.resultSet.GetAllUserReservationResultSet;
import com.rentcar.back.repository.resultSet.GetSearchReservationPriceResultSet;
import com.rentcar.back.repository.resultSet.GetSearchReservationResultSet;
import com.rentcar.back.repository.resultSet.GetUserDetatilReservationResultSet;
import com.rentcar.back.repository.resultSet.GetUserReservationResultSet;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {
    ReservationEntity findByUserIdOrderByReservationDateDesc(String userId);

    @Query(value=
        "SELECT " +
        "CA.car_image_url as carImageUrl, " +
        "U.nick_name as nickName, " +
        "R.reservation_date as reservationDate, " +
        "R.reservation_code as reservationCode, " +
        "CO.rent_company as rentCompany " +
        "FROM reservation R " +
        "INNER JOIN user U " +
        "ON R.user_id = U.user_id " +
        "INNER JOIN company_car CC " +
        "ON R.company_car_code = CC.company_car_code " +
        "INNER JOIN car CA " +
        "ON CC.car_code = CA.car_code " +
        "INNER JOIN company CO " +
        "ON CC.company_code = CO.company_code " +
        "WHERE R.user_id = :userId"
    , nativeQuery=true)
    List<GetUserReservationResultSet> getUserReservationList(@Param("userId") String userId);

    @Query(value = 
        "SELECT " +
        "CA.car_image_url as carImageUrl, " +
        "U.nick_name as nickName, " +
        "R.insurance_type as insuranceType, " +
        "R.reservation_start as reservationStart, " +
        "R.reservation_end as reservationEnd, " +
        "CA.car_oil as carOil, " +
        "CA.grade, " +
        "CC.car_number as carNumber, " +
        "CO.rent_company as rentCompany, " +
        "CO.company_telnumber as companyTelnumber, " +
        "CO.address " +
        "FROM reservation R " +
        "INNER JOIN user U ON R.user_id = U.user_id " +
        "INNER JOIN company_car CC ON R.company_car_code = CC.company_car_code " +
        "INNER JOIN car CA ON CC.car_code = CA.car_code " +
        "INNER JOIN company CO ON CC.company_code = CO.company_code " +
        "WHERE R.user_Id = :userId"
    , nativeQuery = true)
    GetUserDetatilReservationResultSet getUserDetailReservationList(@Param("userId") String userId);

    ReservationEntity findByReservationCode (Integer reservationCode);

    List<ReservationEntity> findByReservationState(String reservationState);

    @Query(value = 
        "SELECT " +
        "R.reservation_code as reservationCode, " +
        "CO.rent_company as rentCompany, " +
        "CA.car_name as carName, " +
        "CC.car_number as carNumber, " +
        "R.reservation_start as reservationStart, " +
        "R.reservation_end as reservationEnd, " +
        "R.user_id as userId, " +
        "U.nick_name as nickName, " +
        "R.reservation_state as reservationState " +
        "FROM reservation R " +
        "INNER JOIN user U ON R.user_id = U.user_id " +
        "INNER JOIN company_car CC ON R.company_car_code = CC.company_car_code " +
        "INNER JOIN car CA ON CC.car_code = CA.car_code " +
        "INNER JOIN company CO ON CC.company_code = CO.company_code"
        , nativeQuery = true)
    List<GetAllUserReservationResultSet> getAllUserReservationList();

    @Query(value = 
    "SELECT " +
        "C.car_name AS carName, " +
        "C.car_image_url AS carImageUrl, " +
        "CC.normal_price AS normalPrice, " +
        "CC.luxury_price AS luxuryPrice, " +
        "CC.super_price AS superPrice " +
    "FROM company_car CC " +
    "LEFT JOIN car C ON CC.car_code = C.car_code " +
    "WHERE company_car_code NOT IN ( " +
        "SELECT company_car_code FROM reservation " +
        "WHERE  " +
        "(reservation_start < :reservationStart AND reservation_end > :reservationStart) " +
        "OR " +
        "(reservation_start < :reservationEnd AND reservation_end > :reservationEnd) " +
        "OR " +
        "(reservation_start > :reservationStart AND reservation_end < :reservationEnd) " +
    ") " +
    "AND company_code IN ( " +
        "SELECT company_code " +
        "FROM company " +
        "WHERE address = :address " +
    ") "
    , nativeQuery = true)
    List<GetSearchReservationResultSet> getSearchReservationList
    (@Param("address") String address, @Param("reservationStart") String reservationStart, @Param("reservationEnd") String reservationEnd);

    @Query(value =
    "SELECT " +
    "C.car_name AS carName, " +
    "C.car_image_url AS carImageUrl, " +
    "C.fuel_type AS fuelType, " +
    "C.reservation_count AS reservationCount, " +
    "C.car_year AS carYear, " +
    "CC.normal_price AS normalPrice, " +
    "CC.luxury_price AS luxuryPrice, " +
    "CC.super_price AS superPrice, " +
    "CM.rent_company AS rentCompany " +
"FROM company_car CC " +
"LEFT JOIN car C ON CC.car_code = C.car_code " +
"LEFT JOIN company CM ON CC.company_code = CM.company_code " +
"WHERE company_car_code NOT IN ( " +
    "SELECT company_car_code FROM reservation " +
    "WHERE " +
    "(reservation_start < '2024-05-21' AND reservation_end > '2024-05-21') " +
    "OR " +
    "(reservation_start < '2024-05-25' AND reservation_end > '2024-05-25') " +
    "OR " +
    "(reservation_start > '2024-05-21' AND reservation_end < '2024-05-25') " +
") " +
"AND CC.company_code IN ( " +
    "SELECT company_code " +
    "FROM company " +
    "WHERE address = '제주시' " +
") " +
"AND C.car_name = '마'; "
    , nativeQuery=true)
    List<GetSearchReservationPriceResultSet> getSearchReservationPriceList
    (@Param("address") String address, @Param("reservationStart") String reservationStart, @Param("reservationEnd") String reservationEnd, 
    @Param("carName") String carName);

}