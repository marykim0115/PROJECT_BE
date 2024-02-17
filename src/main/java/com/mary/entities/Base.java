package com.mary.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass @Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class Base {
    @CreatedDate
    @Column(updatable = false) //생성 날짜는 바뀌면 안되므로 DB에 업데이트 되지 않게 하기
    private LocalDateTime createdAt; //생성 날짜

    @LastModifiedDate
    @Column(insertable = false) // INSERT 작업 중에 DB에 포함되지 않음
    private LocalDateTime modifiedAt; //수정 날짜
}
