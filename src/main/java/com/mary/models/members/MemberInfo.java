package com.mary.models.members;

import com.mary.entities.Member;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


/* 사용자 인증 및 권한 부여에 사용
    * UserDetails : Spring Security에서 사용자 정보를 나타내는 인터페이스. 사용자의 권한, 인증 정보, 계정 상태를 제공 */
@Data @Builder
public class MemberInfo implements UserDetails {

    private String email;
    private String password;
    private Member member;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
        // 사용자가 가지고 있는 권한 목록을 반환
    }

    @Override
    public String getPassword() {
        return password;
        //사용자의 비밀번호를 반환
    }

    @Override
    public String getUsername() {
        return email;
        //사용자의 이름(또는 식별자)을 반환 -> 여기서는 이메일 반환
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
        //사용자의 계정이 만료되지 않았는지 여부를 반환
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
        //사용자의 계정이 잠겨있지 않은지 여부를 반환
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        //사용자의 인증 정보(비밀번호 등)가 만료되지 않았는지 여부를 반환
    }

    @Override
    public boolean isEnabled() {
        return true;
        //사용자의 계정이 활성화되어 있는지 여부를 반환
    }
}

