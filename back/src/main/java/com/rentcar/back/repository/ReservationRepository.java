package com.rentcar.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rentcar.back.entity.ReservationEntity;
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

    List<ReservationEntity> findByUserId(String userId);
}