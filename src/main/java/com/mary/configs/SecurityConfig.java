package com.mary.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /* 1. Spring Security에서 보안 필터 체인을 설정하는 메서드 */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.build();
    }

    /* 회원가입 설정 : 암호화된 비밀번호를 생성 및 저장. 인증시 비교하는 데 사용
    PasswordEncoder : Spring Security에서 사용자의 비밀번호를 안전하게 저장하고 비교하기 위해 사용
    BCryptPasswordEncoder : BCrypt는 해시 함수의 한 형태. 사용되는 비밀번호 암호화함. 비밀번호 해싱을 함 */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
