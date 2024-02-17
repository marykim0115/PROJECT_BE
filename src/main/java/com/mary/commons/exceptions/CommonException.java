package com.mary.commons.exceptions;

import org.springframework.http.HttpStatus;

/* 예외 클래스 생성자 : 메시지와 HTTP 상태 코드를 받아서 예외를 생성 -> 클라이언트에 전달 */
public class CommonException extends RuntimeException{
    private HttpStatus status;

    public CommonException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}