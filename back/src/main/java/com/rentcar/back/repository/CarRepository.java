package com.rentcar.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.rentcar.back.entity.CarEntity;
import com.rentcar.back.repository.resultSet.GetPopularCarResultSet;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Integer> {
    
    List<CarEntity> findTop4ByOrderByReservationCountDesc();

    CarEntity findByCarCode(Integer carCode);

    boolean existsByCarName(String carName);

    @Query(value = 
        "SELECT " + 
        "car_name AS carName, " +
        "car_image_url AS carImageUrl, " +
        "SUM(reservation_count) AS totalReservationCount " +
        "FROM car " +
        "GROUP BY car_name, car_image_url " +
        "ORDER BY totalReservationCount DESC " +
        "LIMIT 4"
    ,nativeQuery = true)
    List<GetPopularCarResultSet> findTop4ByTotalReservationCount();
}
