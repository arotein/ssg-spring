package com.youngjo.ssg.domain.user.domain;

import com.youngjo.ssg.global.common.IdGenTable;
import com.youngjo.ssg.global.common.SeqTable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdGenTable
public class Delivery { // 유저의 배송지들
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SeqTable.name)
    @Column(name = "delivery_id")
    private Long id;

    private String alias;
    private String recipient;
    private String phone;
    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;
}
