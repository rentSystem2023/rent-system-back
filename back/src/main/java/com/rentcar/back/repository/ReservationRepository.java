package com.rentcar.back.repository;

import java.util.List;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rentcar.back.entity.ReservationEntity;
import com.rentcar.back.repository.resultSet.GetAllUserReservationResultSet;
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
        "INNER JOIN car C ON CC.car_code = C.car_code " +
        "INNER JOIN company CP ON CP.company_code = CC.company_code " +
        "WHERE CP.address = :address " +
        "AND NOT EXISTS ( " +
        "SELECT 1 " +
        "FROM reservation R " +
        "WHERE " +
        "R.company_car_code = CC.company_car_code " +
        "AND ( " +
        "(R.reservation_start <= :reservationStart AND R.reservation_end >= :reservationEnd) " +
        "OR (R.reservation_start <= :reservationStart AND R.reservation_end >= :reservationEnd) " +
        ") " + 
        ")"
        , nativeQuery = true)
    List<> ();
        
}