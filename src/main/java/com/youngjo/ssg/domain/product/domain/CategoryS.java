package com.youngjo.ssg.domain.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "categoryS", fetch = FetchType.LAZY)
    private List<CategorySS> categorySSList;

    @Builder
    public CategoryS(String name, CategoryM categoryM, List<CategorySS> categorySSList) {
        this.name = name;
        this.categoryM = categoryM;
        this.categorySSList = categorySSList;
    }

    public void linkToCategorySS(CategorySS categorySS) {
        this.categorySSList.add(categorySS);
    }

    public void linkToCategoryM(CategoryM categoryM) {
        this.categoryM = categoryM;
    }
}
