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
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;
    @Column(unique = true)
    private String name;
    private String src;

    //==매핑==
    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CategoryM> categoryMList = new ArrayList<>();

    @Builder
    public Category(String name, String src) {
        this.name = name;
        this.src = src;
    }

    public void linkToCategoryM(CategoryM categoryM) {
        this.categoryMList.add(categoryM);
    }
}
