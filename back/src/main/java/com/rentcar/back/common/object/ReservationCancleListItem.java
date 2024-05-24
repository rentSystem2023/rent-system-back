package com.rentcar.back.common.object;

import java.util.List;
import java.util.ArrayList;

import com.rentcar.back.entity.ReservationEntity;

import lombok.Getter;

@Getter
public class ReservationCancleListItem {
    private String userId;
    private String reservationState;

    public ReservationCancleListItem (ReservationEntity reservationEntity) throws Exception {
        this.userId = reservationEntity.getUserId();
        this.reservationState = reservationEntity.getReservationState();
    }

    public static List<ReservationCancleListItem> getList(List<ReservationEntity> reservationEntities) throws Exception {
        List<ReservationCancleListItem> reservationCancleList = new ArrayList<>();

        for (ReservationEntity reservationEntity: reservationEntities) {
            ReservationCancleListItem cancleListItem = new ReservationCancleListItem(reservationEntity);
            reservationCancleList.add(cancleListItem);
        }

        return reservationCancleList;
    }
}
