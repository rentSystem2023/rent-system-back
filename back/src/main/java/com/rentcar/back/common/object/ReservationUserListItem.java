package com.rentcar.back.common.object;

import java.util.List;
import java.util.ArrayList;

import com.rentcar.back.repository.resultSet.GetAllUserReservationResultSet;

import lombok.Getter;

@Getter
public class ReservationUserListItem {

    private Integer reservationCode;
    private String rentCompany;
    private String carName;
    private String carNumber;
    private String reservationStart;
    private String reservationEnd;
    private String userId;
    private String nickName;
    private String reservationState;

    public ReservationUserListItem(GetAllUserReservationResultSet resultSet) throws Exception {
        this.reservationCode = resultSet.getReservationCode();
        this.rentCompany = resultSet.getRentCompany();
        this.carName = resultSet.getCarName();
        this.carNumber = resultSet.getCarNumber();
        this.reservationStart = resultSet.getReservationStart();
        this.reservationEnd = resultSet.getReservationEnd();
        this.userId = resultSet.getUserId();
        this.nickName = resultSet.getNickName();
        this.reservationState = resultSet.getReservationState();
    }

    public static List<ReservationUserListItem> getList(List<GetAllUserReservationResultSet> resultSets)throws Exception {
        List<ReservationUserListItem> reservationUserList = new ArrayList<>();
        
        for (GetAllUserReservationResultSet resultSet : resultSets) {
            ReservationUserListItem item = new ReservationUserListItem(resultSet);
            reservationUserList.add(item);
        }

        return reservationUserList;
    }
}
