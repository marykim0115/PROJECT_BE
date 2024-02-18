package com.mary.commons.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 비밀번호 유효성을 검사하는 인터페이스 */
public interface PasswordValidator {
    /**
     * 비밀번호 복잡성 체크 - 비밀번호에 알파벳이 포함되어 있는지 확인하는 메서드
     *
     * @param password
     * @param caseIncentive
     *          true : 대소문자 상관없이 포함되는 패턴
     *          false : 소문자 + 대문자가 반드시 포함되는 패턴
     * @return
     */
    default boolean alphaCheck(String password, boolean caseIncentive) {
        // true : 대소문자 상관없이 포함되는 패턴
        if (caseIncentive) {
            Pattern pattern = Pattern.compile("[a-z]+", Pattern.CASE_INSENSITIVE);
            return pattern.matcher(password).find();
        }

        // false : 소문자 + 대문자가 반드시 포함되는 패턴
        Pattern pattern1 = Pattern.compile("[a-z]+");
        Pattern pattern2 = Pattern.compile("[A-Z]+");
        return pattern1.matcher(password).find() && pattern2.matcher(password).find();
    }
    /**
     * 비밀번호에 숫자가 하나 이상 포함되어 있는지 확인하는 메서드
     *
     * @param password
     * @return
     */
    default boolean numberCheck(String password) {
        Pattern pattern = Pattern.compile("\\d+"); // [0-9]
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    /**
     * 비밀번호에 특수문자가 하나 이상 포함되어 있는지 확인하는 메서드
     * @param password
     * @return
     */
    default boolean specialCharsCheck(String password) {
        Pattern pattern = Pattern.compile("[`~!#$%\\^&\\*()-_+=]+");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}
