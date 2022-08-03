package com.youngjo.ssg.domain.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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
    private String name;

    //==매핑==
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_m_id")
    private CategoryM categoryM;

    @JsonIgnore
    @OneToMany(mappedBy = "categoryS", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CategorySS> categorySSList = new ArrayList<>();

    @Builder
    public CategoryS(String name, CategoryM categoryM) {
        this.name = name;
        this.categoryM = categoryM;
    }

    public void linkToCategorySS(CategorySS categorySS) {
        this.categorySSList.add(categorySS);
    }

    public void linkToCategoryM(CategoryM categoryM) {
        this.categoryM = categoryM;
        categoryM.linkToCategoryS(this);
    }
}
