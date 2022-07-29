package com.youngjo.ssg.domain.user.domain;

import com.youngjo.ssg.domain.user.enumeration.Role;
import com.youngjo.ssg.global.common.BaseEntity;
import com.youngjo.ssg.global.enumeration.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User extends BaseEntity {
    // 클라이언트 정보
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String loginId; // 로그인시 필요한 id

    private String password;
    private String name;

    @Column(unique = true)
    private String email;

    private String phone;
    private String address;
    private Integer point;

    // 시스템 정보
    private Timestamp lastAccessTime;
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_NORMAL;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ENABLED;
}
