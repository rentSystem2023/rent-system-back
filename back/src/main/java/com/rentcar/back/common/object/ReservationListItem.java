package com.rentcar.back.common.object;

import java.util.List;
import java.util.ArrayList;

import com.rentcar.back.common.util.ChangeDateFormatUtil;
import com.rentcar.back.repository.resultSet.GetUserReservationResultSet;

import lombok.Getter;

@Getter
public class ReservationListItem {
    private String carImageUrl;
    private String nickName;
    private String reservationDate;
    private String reservationCode;
    private String rentCompany;

    public ReservationListItem (GetUserReservationResultSet resultSet) throws Exception {
        String reservationDate = ChangeDateFormatUtil.changeYYYYMMDD(resultSet.getReservationDate());
        this.carImageUrl = resultSet.getCarImageUrl();
        this.nickName = resultSet.getNickName();
        this.reservationDate = reservationDate;
        this.reservationCode = resultSet.getReservationCode();
        this.rentCompany = resultSet.getRentCompany();
    }

    public static List<ReservationListItem> getList (List<GetUserReservationResultSet> resultSets) throws Exception {
        List<ReservationListItem> reservationList = new ArrayList<>();
        for (GetUserReservationResultSet resultSet: resultSets) {
            ReservationListItem item = new ReservationListItem(resultSet);
            reservationList.add(item);
        }
        return reservationList;
    }
}
