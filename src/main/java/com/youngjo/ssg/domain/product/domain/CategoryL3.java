package com.youngjo.ssg.domain.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class CategoryL3 extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_l3_id")
    private Long id;
    private String name;

    //==매핑==
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_l2_id")
    private CategoryL2 categoryL2;

    @JsonIgnore
    @OneToMany(mappedBy = "categoryL3", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CategoryL4> categoryL4List = new ArrayList<>();

    @Builder
    public CategoryL3(String name, CategoryL2 categoryL2) {
        this.name = name;
        this.categoryL2 = categoryL2;
    }

    public void linkToCategoryL4(CategoryL4 categoryL4) {
        this.categoryL4List.add(categoryL4);
    }

    public void linkToCategoryL2(CategoryL2 categoryL2) {
        this.categoryL2 = categoryL2;
        categoryL2.linkToCategoryL3(this);
    }
}
