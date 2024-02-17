package com.mary.api.controllers.members;

import jakarta.validation.Valid;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* 회원가입 요청 처리 */
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {
    @PostMapping
    public void join(@RequestBody @Valid RequestJoin form, Errors errors) {
        //회원가입 요청 처리 메서드
        //POST 요청을 받으면 -> RequestJoin 처리 후, Request Body 객체의 데이터 유효성 검증
        //데이터에 유효성 검증 오류가 있으면, 해당 오류를 Errors 객체에 담아 처리
    }
}
