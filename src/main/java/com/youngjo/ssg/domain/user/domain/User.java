package com.youngjo.ssg.domain.user.domain;

import com.youngjo.ssg.domain.user.enumeration.UserStatus;
import com.youngjo.ssg.global.common.BaseEntity;

import javax.persistence.*;


@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Integer point;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ENABLED;
}
