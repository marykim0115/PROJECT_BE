package com.mary.api.controllers.members;

import com.mary.commons.Utils;
import com.mary.commons.rests.JSONData;
import com.mary.entities.Member;
import com.mary.models.members.MemberInfo;
import com.mary.models.members.MemberLoginService;
import com.mary.models.members.MemberSaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/* 회원가입 요청 처리 */
@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberSaveService saveService;
    private final MemberLoginService loginService;

    /** 회원가입 요청 처리 메서드

      - POST 요청을 받으면 -> RequestJoin 처리 후, Request Body 객체의 데이터 유효성 검증
      - 데이터에 유효성 검증 오류가 있으면, 해당 오류를 Errors 객체에 담아 처리
     * @param form
     * @param errors
     */
    @PostMapping
    public ResponseEntity<JSONData> join(@RequestBody @Valid RequestJoin form, Errors errors) {
        saveService.save(form, errors);

        errorProcess(errors);

        JSONData data = new JSONData();
        data.setStatus(HttpStatus.CREATED);

        return ResponseEntity.status(data.getStatus()).body(data);
    }

    @PostMapping("/token")
    public ResponseEntity<JSONData> token(@RequestBody @Valid RequestLogin form, Errors errors) {
        errorProcess(errors);

        String accessToken = loginService.login(form);

        /**
         * 1. 응답 body - JSONData 형식으로
         * 2. 응답 헤더 - Authorization: Bearer 토큰
         */

        JSONData data = new JSONData(accessToken);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        return ResponseEntity.status(data.getStatus()).headers(headers).body(data);
    }


    @GetMapping("/info")
    public JSONData info(@AuthenticationPrincipal MemberInfo memberInfo) {
        Member member = memberInfo.getMember();

        return new JSONData(member);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String admin() {
        return "관리자 페이지 접속....";
    }

    private void errorProcess(Errors errors) {
        if (errors.hasErrors()) {
            throw new BadRequestException(Utils.getMessages(errors));
        }
    }
}
