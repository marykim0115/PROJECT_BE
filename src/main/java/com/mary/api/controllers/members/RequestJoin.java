package com.mary.api.controllers.members;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

/* 회원가입 요청 처리 레코드
    -> 회원가입 요청 데이터를 전달하기 위해 사용
    -> 주어진 제약 조건에 따라 데이터의 유효성 검증 역할 */
@Builder
public record RequestJoin(
        @NotBlank @Email
        String email,
        @NotBlank @Size(min=8)
        String password,

        @NotBlank
        String confirmPassword,

        @NotBlank
        String name,
        String mobile,

        @AssertTrue
        Boolean agree //이용 약관 동의 여부
) {}
