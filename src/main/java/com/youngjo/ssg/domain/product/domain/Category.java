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
@IdGenTable
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SeqTable.name)
    @Column(name = "category_id")
    private Long id;
    @Column(unique = true)
    private String name;
    private String imgUrl;

    //==매핑==
    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CategoryM> categoryMList = new ArrayList<>();

    @Builder
    public Category(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public void linkToCategoryM(CategoryM categoryM) {
        this.categoryMList.add(categoryM);
    }
}
