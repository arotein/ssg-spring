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
@Table(name = "category_m")
public class CategoryM extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_m_id")
    private Long id;
    @Column(unique = true)
    private String name;

    //==매핑==
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "categoryM", fetch = FetchType.LAZY)
    private List<CategoryS> categorySList = new ArrayList<>();

    @Builder
    public CategoryM(String name, Category category, List<CategoryS> categorySList) {
        this.name = name;
        this.category = category;
        this.categorySList = categorySList;
    }

    public void linkToCategoryS(CategoryS categoryS) {
        this.categorySList.add(categoryS);
    }

    public void linkToCategory(Category category) {
        this.category = category;
    }
}
