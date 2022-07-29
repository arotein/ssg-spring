package com.youngjo.ssg.global.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class BaseEntity {
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;
}