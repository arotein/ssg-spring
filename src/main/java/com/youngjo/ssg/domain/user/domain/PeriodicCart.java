package com.youngjo.ssg.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.domain.product.domain.Product;
import com.youngjo.ssg.global.common.BaseEntity;
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
public class PeriodicCart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "periodic_cart_id")
    private Long id;

    private Integer qty;

    //==매핑==
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "periodicCart")
    private List<Product> product = new ArrayList<>();

    @Builder
    public PeriodicCart(Integer qty, User user) {
        this.qty = qty;
        this.user = user;
    }
}
