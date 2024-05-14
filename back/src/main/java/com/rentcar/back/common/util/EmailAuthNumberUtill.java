package com.rentcar.back.common.util;

import java.util.Random;

// 이메일 인증 번호 유틸리티 클래스

public class EmailAuthNumberUtill {

    // 4자리 인증번호 생성 메서드
    // - 0~9의 4자리 임의의 문자열 생성

    // 인스턴스 생성없이 사용할 수 있도록 STATIC 지정
    // 3가지의 방법
    public static String createNumber() {
        String authNumber = "";
        Random random = new Random();

        for (int index = 0; index < 4; index++)
            authNumber += random.nextInt(10);
        return authNumber;

    }

    // - A~Z의 4자리 임의의 문자열
    public static String createCode() {
        char[] authChar = new char[4];
        Random random = new Random();

        for (int index = 0; index < authChar.length; index++)
            authChar[index] = (char) (random.nextInt(25) + 65);

        return new String(authChar);
    }

    // -A~Z, 0~9 의 4자리 임의의 문자열
    // boolean 으로 2가지의 경우의 수를 만들기
    // 아스키코드 방식

    public static String createCodeNumber() {
        char[] authChar = new char[4];
        Random random = new Random();

        for (int index = 0; index < authChar.length; index++) {
            boolean flag = random.nextBoolean();
            if (flag) {
                authChar[index] = (char) (random.nextInt(10) + 48);
            } else {
                authChar[index] = (char) (random.nextInt(25) + 65);
            }

        }

        return new String(authChar);
    }

}
