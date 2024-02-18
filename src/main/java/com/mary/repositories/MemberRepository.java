package com.mary.repositories;

import com.mary.entities.Member;
import com.mary.entities.QMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {
    Optional<Member> findByEmail(String email); //email로 회원정보 조회

    default boolean exists(String email) {
        return exists(QMember.member.email.eq(email));
    }
}
