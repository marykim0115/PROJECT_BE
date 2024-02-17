package com.mary.commons.rests;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/* JSON 형식의 데이터를 표현하는 클래스 : RESTful 웹 서비스에서 클라이언트로 응답을 JSON 형식으로 전달 */
@Data
@NoArgsConstructor @RequiredArgsConstructor
public class JSONData<T> {
    private boolean success = true; //요청의 성공 여부
    private HttpStatus status = HttpStatus.OK; //HTTP 응답 상태 코드
    @NonNull private T data; //요청에 대한 데이터(=서버에서 전송하려는 데이터가)를 담는 필드
    private String message; //요청에 대한 추가적인 메시지를 담는 필드

}
