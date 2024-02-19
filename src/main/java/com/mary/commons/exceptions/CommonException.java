package com.mary.commons.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

/* 예외 클래스 생성자 : 메시지와 HTTP 상태 코드를 받아서 예외를 생성 -> 클라이언트에 전달 */
public class CommonException extends RuntimeException{
    private HttpStatus status;
    private Map<String, List<String>> messages; //예외와 관련된 메시지를 담음. 예외에 대한 추가적인 정보를 담고자 할 때 사용

    public CommonException(Map<String, List<String>> messages, HttpStatus status) {
        super();
        this.status = status;
        this.messages = messages;
    }

    public CommonException(String message, HttpStatus status){
        super(message);
        this.status = status;
        this.messages = messages;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Map<String, List<String>> getMessages() {
        return messages;
    }
}