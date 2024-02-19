package com.mary.commons;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.*;

/** Utils :
  1. 다양한 상황에서 사용할 수 있는 메시지 관리
  2. Errors 객체에서 필드 오류 메시지를 추출
  +) 유효성 검사나 에러 처리와 관련된 메시지를 쉽게 관리할 수 있음
 */
public class Utils {
    private static ResourceBundle validationsBundle;
    private static ResourceBundle errorsBundle;

    static {
        validationsBundle = ResourceBundle.getBundle("messages.validations"); // messages/validations.properties 리소스 번들을 로드함
        errorsBundle = ResourceBundle.getBundle("messages.errors"); //messages/errors.properties 리소스 번들을 로드함
    }

    /** 주어진 코드에 해당하는 메시지를 반환
        메시지는 validationsBundle 또는 errorsBundle에서 가져옴
     *
     * @param code = 리소스 번들에서 찾을 메시지의 키
     * @param bundleType = "validations" or "error"
     * @return
     */
    public static String getMessage(String code, String bundleType) {
        bundleType = Objects.requireNonNullElse(bundleType, "validation"); //기본 번들 : validations
        ResourceBundle bundle = bundleType.equals("error")? errorsBundle:validationsBundle;
        try {
            return bundle.getString(code);
        } catch (Exception e) {
            return null; //예외 발생하면 null 반환
        }
    }

    /** Spring의 Errors 객체에서 필드 오류에 대한 메시지를 추출하여 맵 형태로 반환
     *
     * 각 필드에 대해 오류 메시지의 리스트를 맵 형태로 반환
     * getMessage 메서드를 통해 메세지 검색. 오류 발생하면 null 반환
     *
     * @param errors
     * @return
     */
    public static Map<String, List<String>> getMessages(Errors errors) {
        try {
            Map<String, List<String>> data = new HashMap<>();
            for (FieldError error : errors.getFieldErrors()) {
                String field = error.getField();
                List<String> messages = Arrays.stream(error.getCodes()).sorted(Comparator.reverseOrder())
                        .map(c -> getMessage(c, "validation"))
                        .filter(c -> c != null)
                        .toList();

                data.put(field, messages);
            }

            return data;

        } catch (Exception e) {
            return null;
        }
    }
}
