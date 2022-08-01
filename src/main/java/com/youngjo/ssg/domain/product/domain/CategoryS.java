package com.youngjo.ssg.domain.product.domain;

import com.youngjo.ssg.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "category_s")
public class CategoryS extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_s_id")
    private Long id;
    @Column(unique = true)
    private String name;

    //==매핑==
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_m_id")
    private CategoryM categoryM;

    @OneToOne(mappedBy = "categoryS", fetch = FetchType.LAZY)
    private Product product;
}
