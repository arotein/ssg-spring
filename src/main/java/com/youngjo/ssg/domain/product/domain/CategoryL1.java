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
@Table(name = "category_l1")
@IdGenTable
public class CategoryL1 extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SeqTable.name)
    @Column(name = "category_l1_id")
    private Long id;
    @Column(unique = true)
    private String name;
    private String imgUrl;

    //==매핑==
    @JsonIgnore
    @OneToMany(mappedBy = "categoryL1", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CategoryL2> categoryL2List = new ArrayList<>();

    @Builder
    public CategoryL1(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public void linkToCategoryL2(CategoryL2 categoryL2) {
        this.categoryL2List.add(categoryL2);
    }
}
