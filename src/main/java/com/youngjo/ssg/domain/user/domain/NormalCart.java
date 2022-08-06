package com.youngjo.ssg.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.domain.product.domain.Product;
import com.youngjo.ssg.global.common.BaseEntity;
import com.youngjo.ssg.global.common.IdGenTable;
import com.youngjo.ssg.global.common.SeqTable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdGenTable
public class NormalCart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SeqTable.name)
    @Column(name = "normal_cart_id")
    private Long id;

    private Integer qty;

    //==매핑==
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "normalCart", fetch = FetchType.LAZY)
    private List<Product> product = new ArrayList<>();

    @Builder
    public NormalCart(Integer qty) {
        this.qty = qty;
    }
}
