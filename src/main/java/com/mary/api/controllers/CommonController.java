package com.mary.api.controllers;

import com.mary.commons.exceptions.CommonException;
import com.mary.commons.rests.JSONData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/* 공통 컨트롤러 예외 처리 정의
    <사용이유>
     1. 일관된 방식으로 응답을 전달
     2. 각 컨트롤러에서 예외 처리를 하지 않아도 됨 */
@RestControllerAdvice("com.mary.api.controllers")
public class CommonController {
    @ExceptionHandler(Exception.class) //모든 예외를 처리한다는 의미의 어노테이션
    public ResponseEntity<JSONData> errorHandler(Exception e) { //errorHandler(Exception e) : 예외가 발생하면 이 메서드가 호출되 예외를 처리하고, 클라이언트에게 적절한 응답을 전달한다.
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; //기본 내부 서버오류 상태코드 설정
        Object message = e.getMessage();

        if(e instanceof CommonException){
            CommonException commonException = (CommonException) e;
            status = commonException.getStatus();
            //예외가 CommonException 클래스의 인스턴스인 경우, 해당 예외의 상태 코드를 가져와 설정한다

            if (commonException.getMessages() != null) message = commonException.getMessages();
        } else if (e instanceof BadCredentialsException) {
            status = HttpStatus.UNAUTHORIZED; // 401
        } else if (e instanceof AccessDeniedException) {
            status = HttpStatus.FORBIDDEN; // 403
        }
        // BadCredentialsException -> 500 -> 401
        // AccessDeniedException -> 500 -> 403


        JSONData data = new JSONData();
        data.setSuccess(false);
        data.setStatus(status);
        data.setMessage(message);

        e.printStackTrace();

        return ResponseEntity.status(status).body(data);
    }
}