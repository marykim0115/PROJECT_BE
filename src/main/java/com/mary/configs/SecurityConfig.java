package com.mary.configs;

import com.mary.configs.jwt.CustomJwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // @PreAuthorize("hasAuthority('ADMIN'))
public class SecurityConfig {

    @Autowired
    private CustomJwtFilter customJwtFilter;

    @Autowired
    private CorsFilter corsFilter;

    /* 1. Spring Security에서 보안 필터 체인을 설정하는 메서드 : 보안 설정 무효화*/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(c -> c.disable())
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.exceptionHandling(c -> {
            c.authenticationEntryPoint((req,  res,  e) -> {
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED); //  401
            });

            c.accessDeniedHandler((req,res, e) -> {
                res.sendError(HttpServletResponse.SC_FORBIDDEN); // 403
            });
        });

        http.authorizeHttpRequests(c -> {
            c.requestMatchers(
                            "/api/v1/member", // 회원가입
                            "/api/v1/member/token").permitAll() // 로그인
                    .anyRequest().authenticated(); // 나머지 URL은 모두 회원 인증(토큰 인증)
        });

        return http.build();
    }

    /* 2. 회원가입 설정 : 암호화된 비밀번호를 생성 및 저장. 인증시 비교하는 데 사용
    PasswordEncoder : Spring Security에서 사용자의 비밀번호를 안전하게 저장하고 비교하기 위해 사용
    BCryptPasswordEncoder : BCrypt는 해시 함수의 한 형태. 사용되는 비밀번호 암호화함. 비밀번호 해싱을 함 */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
