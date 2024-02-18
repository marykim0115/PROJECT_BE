package com.mary.commons.validators;

/* 휴대전화 번호의 유효성을 검사하는 인터페이스 */
public interface MobileValidator {
    default boolean mobileNumCheck(String mobile) {
        /**
         * 010-3481-2101
         * 010_3481_2101
         * 010 3481 2101
         *
         * 1. 형식의 통일화 - 숫자가 아닌 문자 전부 제거 -> 숫자
         * 2. 패턴 생성 체크
         */
        mobile = mobile.replaceAll("\\D", ""); // [ \\D ] = 문자를 나타내는 정규 표현식
        String pattern = "^01[016]\\d{3,4}\\d{4}$";

        return mobile.matches(pattern);
    }
}
