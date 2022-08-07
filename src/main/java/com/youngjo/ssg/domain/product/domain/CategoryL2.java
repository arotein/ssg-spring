package com.youngjo.ssg.domain.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "category_l2")
@IdGenTable
public class CategoryL2 extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SeqTable.name)
    @Column(name = "category_l2_id")
    private Long id;
    private String name;
    private String imgUrl;

    //==매핑==
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_l1_id")
    private CategoryL1 categoryL1;

    @JsonIgnore
    @OneToMany(mappedBy = "categoryL2", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CategoryL3> categoryL3List = new ArrayList<>();

    @Builder
    public CategoryL2(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }


    public void linkToCategoryL3(CategoryL3 categoryL3) {
        this.categoryL3List.add(categoryL3);
    }

    public void linkToCategoryL1(CategoryL1 categoryL1) {
        this.categoryL1 = categoryL1;
        categoryL1.linkToCategoryL2(this);
    }
}
