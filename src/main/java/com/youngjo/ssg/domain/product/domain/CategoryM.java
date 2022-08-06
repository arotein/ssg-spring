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
@Table(name = "category_m")
@IdGenTable
public class CategoryM extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SeqTable.name)
    @Column(name = "category_m_id")
    private Long id;
    private String name;
    private String imgUrl;

    //==매핑==
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "categoryM", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CategoryS> categorySList = new ArrayList<>();

    @Builder
    public CategoryM(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }


    public void linkToCategoryS(CategoryS categoryS) {
        this.categorySList.add(categoryS);
    }

    public void linkToCategory(Category category) {
        this.category = category;
        category.linkToCategoryM(this);
    }
}
