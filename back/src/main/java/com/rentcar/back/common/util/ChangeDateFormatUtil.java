package com.rentcar.back.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

// 날짜 형식을 변경하는 유틸리티 클래스

public class ChangeDateFormatUtil {

    public static String changeYYMMDD(String original) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datetime = simpleDateFormat.parse(original);
        simpleDateFormat = new SimpleDateFormat("yy.MM.dd");
        String joinDate = simpleDateFormat.format(datetime);
        return joinDate;
    }

    public static String changeYYYYMMDD(String original) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datetime = simpleDateFormat.parse(original);
        simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String writeDatetime = simpleDateFormat.format(datetime);
        return writeDatetime;
    }

    public static String registYYMMDD(String original) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date datetime = simpleDateFormat.parse(original);
        simpleDateFormat = new SimpleDateFormat("yy.MM.dd");
        String registDate = simpleDateFormat.format(datetime);
        return registDate;
    }

}
