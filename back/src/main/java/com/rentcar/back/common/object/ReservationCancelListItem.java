package com.rentcar.back.common.object;

import java.util.List;
import java.util.ArrayList;

import com.rentcar.back.entity.ReservationEntity;

import lombok.Getter;

@Getter
public class ReservationCancelListItem {
    private String userId;
    private String reservationState;

    public ReservationCancelListItem(ReservationEntity reservationEntity) throws Exception {
        this.userId = reservationEntity.getUserId();
        this.reservationState = reservationEntity.getReservationState();
    }

    public static List<ReservationCancelListItem> getList(List<ReservationEntity> reservationEntities)throws Exception {
        List<ReservationCancelListItem> reservationCancelList = new ArrayList<>();

        for (ReservationEntity reservationEntity : reservationEntities) {
            ReservationCancelListItem cancelListItem = new ReservationCancelListItem(reservationEntity);
            reservationCancelList.add(cancelListItem);
        }

        return reservationCancelList;
    }
}
