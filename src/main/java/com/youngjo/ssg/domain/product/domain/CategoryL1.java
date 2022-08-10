package com.youngjo.ssg.domain.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.domain.product.domain.common.Category;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class CategoryL1 extends Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void linkToCategoryL2(CategoryL2 categoryL2) {
        this.categoryL2List.add(categoryL2);
    }
}
