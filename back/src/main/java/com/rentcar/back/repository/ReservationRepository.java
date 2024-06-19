package com.rentcar.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rentcar.back.entity.ReservationEntity;
import com.rentcar.back.repository.resultSet.GetAllUserReservationResultSet;
import com.rentcar.back.repository.resultSet.GetReservationDetailResultSet;
import com.rentcar.back.repository.resultSet.GetSearchReservationDetailResultSet;
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
        "R.reservation_state as reservationState, " +
        "R.reservation_date as reservationDate, " +
        "R.reservation_code as reservationCode, " +
        "R.reservation_start as reservationStart, " +
        "R.reservation_end as reservationEnd, " +
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
        "R.reservation_state as reservationState, " +
        "CA.car_name as carName, " +
        "CA.car_oil as carOil, " +
        "CA.grade, " +
        "CC.car_number as carNumber, " +
        "CO.rent_company as rentCompany, " +
        "CO.company_telnumber as companyTelnumber, " +
        "CO.address, " +
        "CASE " +
        "WHEN R.insurance_type = 'normal' THEN CC.normal_price * DATEDIFF(R.reservation_end, R.reservation_start) " +
        "WHEN R.insurance_type = 'luxury' THEN CC.luxury_price * DATEDIFF(R.reservation_end, R.reservation_start) " +
        "WHEN R.insurance_type = 'super' THEN CC.super_price * DATEDIFF(R.reservation_end, R.reservation_start) " +
        "ELSE NULL " +
    "END AS insurancePrice " +
        "FROM reservation R " +
        "INNER JOIN user U ON R.user_id = U.user_id " +
        "INNER JOIN company_car CC ON R.company_car_code = CC.company_car_code " +
        "INNER JOIN car CA ON CC.car_code = CA.car_code " +
        "INNER JOIN company CO ON CC.company_code = CO.company_code " +
        "WHERE R.user_Id = :userId " +
        "AND R.reservation_code = :reservationCode"
    , nativeQuery = true)
    GetUserDetatilReservationResultSet getUserDetailReservationList(@Param("userId") String userId, @Param("reservationCode") Integer reservationCode);

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

    // 예약 상세 불러오기(관리자)
    @Query(value = 
        "SELECT " +
        "R.reservation_code as reservationCode, " +
        "CO.rent_company as rentCompany, " +
        "CA.car_name as carName, " +
        "CC.car_number as carNumber, " +
        "R.reservation_start as reservationStart, " +
        "R.reservation_end as reservationEnd, " +
        "R.user_id as userId, " +
        "R.reservation_state as reservationState, " +
        "R.insurance_type AS insuranceType, " +
        "CASE " +
        "WHEN R.insurance_type = 'normal' THEN CC.normal_price * DATEDIFF(R.reservation_end, R.reservation_start) " +
        "WHEN R.insurance_type = 'luxury' THEN CC.luxury_price * DATEDIFF(R.reservation_end, R.reservation_start) " +
        "WHEN R.insurance_type = 'super' THEN CC.super_price * DATEDIFF(R.reservation_end, R.reservation_start) " +
        "ELSE NULL " +
        "END AS insurancePrice " +
        "FROM reservation R " +
        "INNER JOIN user U ON R.user_id = U.user_id " +
        "INNER JOIN company_car CC ON R.company_car_code = CC.company_car_code " +
        "INNER JOIN car CA ON CC.car_code = CA.car_code " +
        "INNER JOIN company CO ON CC.company_code = CO.company_code " +
        "WHERE R.reservation_code = :reservationCode"
    , nativeQuery = true)
    GetReservationDetailResultSet getReservationDetail(@Param ("reservationCode") Integer reservaitonCode);


    // 예약 검색 리스트 불러오기(관리자)
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
        "INNER JOIN company CO ON CC.company_code = CO.company_code " +
        "WHERE R.reservation_code = :reservationCode"
    , nativeQuery = true)
    List<GetAllUserReservationResultSet> findByReservationCodeOrderByReservationCode(@Param("reservationCode") Integer ReservationCode);

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
        "(reservation_start <= :reservationStart AND reservation_end >= :reservationStart) " +
        "OR " +
        "(reservation_start <= :reservationEnd AND reservation_end >= :reservationEnd) " +
        "OR " +
        "(reservation_start >= :reservationStart AND reservation_end <= :reservationEnd) " +
        ") " +
        "AND company_code IN ( " +
        "SELECT company_code " +
        "FROM company " +
        ") "
    , nativeQuery = true)
    List<GetSearchReservationResultSet> getSearchReservationList
    (@Param("reservationStart") String reservationStart, @Param("reservationEnd") String reservationEnd);

    // 원본
    // @Query(value =
    //     "SELECT " +
    //     "C.car_name AS carName, " +
    //     "C.car_image_url AS carImageUrl, " +
    //     "C.fuel_type AS fuelType, " +
    //     "C.reservation_count AS reservationCount, " +
    //     "CM.address, " +
    //     "CC.normal_price AS normalPrice, " +
    //     "CC.luxury_price AS luxuryPrice, " +
    //     "CC.super_price AS superPrice, " +
    //     "CM.rent_company AS rentCompany " +
    //     "FROM company_car CC " +
    //     "LEFT JOIN car C ON CC.car_code = C.car_code " +
    //     "LEFT JOIN company CM ON CC.company_code = CM.company_code " +
    //     "WHERE company_car_code NOT IN ( " +
    //     "SELECT company_car_code FROM reservation " +
    //     "WHERE " +
    //     "(reservation_start <= :reservationStart AND reservation_end >= :reservationStart) " +
    //     "OR " +
    //     "(reservation_start <= :reservationEnd AND reservation_end >= :reservationEnd) " +
    //     "OR " +
    //     "(reservation_start >= :reservationStart AND reservation_end <= :reservationEnd) " +
    //     ") " +
    //     "AND CC.company_code IN ( " +
    //     "SELECT company_code " +
    //     "FROM company " +
    //     "WHERE address = :address " +
    //     ") " +
    //     "AND C.car_name = :carName"
    // , nativeQuery=true)
    // List<GetSearchReservationPriceResultSet> getSearchReservationPriceList (
    //     @Param("address") String address, @Param("reservationStart") String reservationStart, @Param("reservationEnd") String reservationEnd, @Param("carName") String carName
    // );

    // 수정 그룹바이 함(프론트 확인필요) 검색2번페이지
    @Query(value =
    "SELECT " +
    "C.car_name AS carName, " +
    "MAX(C.car_image_url) AS carImageUrl, " +
    "MAX(C.fuel_type) AS fuelType, " +
    "MAX(C.car_year) AS carYear, " +
    "CM.address, " +
    "MAX(CC.normal_price) AS normalPrice, " +
    "MAX(CC.luxury_price) AS luxuryPrice, " +
    "MAX(CC.super_price) AS superPrice, " +
    "CM.rent_company AS rentCompany " +
    "FROM company_car CC " +
    "LEFT JOIN car C ON CC.car_code = C.car_code " +
    "LEFT JOIN company CM ON CC.company_code = CM.company_code " +
    "WHERE company_car_code NOT IN ( " +
    "SELECT company_car_code FROM reservation " +
    "WHERE " +
    "(reservation_start <= :reservationStart AND reservation_end >= :reservationStart) " +
    "OR " +
    "(reservation_start <= :reservationEnd AND reservation_end >= :reservationEnd) " +
    "OR " +
    "(reservation_start >= :reservationStart AND reservation_end <= :reservationEnd) " +
    ") " +
    "AND CC.company_code IN ( " +
    "SELECT company_code " +
    "FROM company " +
    ") " +
    "AND C.car_name = :carName " +
    "GROUP BY C.car_name, CM.address, CM.rent_company"
, nativeQuery=true)
List<GetSearchReservationPriceResultSet> getSearchReservationPriceList (
    @Param("reservationStart") String reservationStart, @Param("reservationEnd") String reservationEnd, @Param("carName") String carName
);

    @Query(value = 
        "SELECT " +
        "C.car_name AS carName, " +
        "C.car_image_url AS carImageUrl, " +
        "C.car_year AS carYear, " +
        "C.brand, " +
        "C.grade, " +
        "C.car_oil AS carOil, " +
        "C.fuel_type AS fuelType, " +
        "C.capacity, " +
        "CC.normal_price AS normalPrice, " +
        "CC.luxury_price AS luxuryPrice, " +
        "CC.super_price AS superPrice, " +
        "CC.company_car_code AS companyCarCode, " +
        "CM.rent_company AS rentCompany, " +
        "CM.address, " +
        "CM.company_telnumber AS companyTelnumber, " +
        "CM.company_rule AS companyRule, " +
        "CM.company_lat As companyLat, " +
        "CM.company_lng AS companyLng, " +
        "R.reservation_start AS reservationStart, " +
        "R.reservation_end AS reservationEnd " +
        "FROM company_car CC " +
        "LEFT JOIN car C ON CC.car_code = C.car_code " +
        "LEFT JOIN company CM ON CC.company_code = CM.company_code " +
        "LEFT JOIN reservation R ON CC.company_car_code = R.company_car_code " +
        "WHERE CC.company_car_code NOT IN ( " +
        "SELECT company_car_code " +
        "FROM reservation " +
        "WHERE " +
        "(reservation_start <= :reservationStart AND reservation_end >= :reservationStart) " +
        "OR " +
        "(reservation_start <= :reservationEnd AND reservation_end >= :reservationEnd) " +
        "OR " +
        "(reservation_start >= :reservationStart AND reservation_end <= :reservationEnd) " +
        ") " +
        "AND CC.company_code IN ( " +
        "SELECT company_code " +
        "FROM company " +
        ") " +
        "AND C.car_name = :carName " +
        "AND CM.rent_company = :rentCompany " +
        "LIMIT 1"
    , nativeQuery=true)
    GetSearchReservationDetailResultSet getSearchReservationDetailList (
        @Param("reservationStart") String reservationStart, @Param("reservationEnd") String reservationEnd, 
        @Param("carName") String carName, @Param("rentCompany") String rentCompany
    );





}