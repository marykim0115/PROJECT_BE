package com.mary.models.members;

import com.mary.commons.contants.MemberType;
import com.mary.entities.Member;
import com.mary.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* 사용자가 로그인할 때(= Spring Security가 사용자의 인증 또는 권한 검사를 수행)마다,
   사용자의 이메일 정보를 받아와서 해당 사용자의 상세 정보를 가져온다.(=로드)
   이 정보는 Spring Security에서 인증 및 권한 검사에 사용된다.

    * UserDetailsService: Spring Security에서 사용자의 상세 정보를 로드하는 인터페이스
    => 사용자 이름(사용자의 식별자, 예-이메일)을 기반으로 사용자의 상세 정보를 가져올 수 있음 */
@Service
@RequiredArgsConstructor
public class MemberInfoService implements UserDetailsService {

    private final MemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = repository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException(username));

        MemberType type = Objects.requireNonNullElse(member.getType(), MemberType.USER);
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(type.name()));

        return MemberInfo.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .authorities(authorities)
                .member(member)
                .build();
        //사용자 이름(이메일)을 기반으로 DB에서 사용자를 검색하고, 해당 사용자의 상세 정보(사용자의 인증, 권한 정보 등)를 UserDetails 객체로 매핑하여 반환
    }
}
