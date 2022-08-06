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
@Table(name = "category_ss")
@IdGenTable
public class CategorySS extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SeqTable.name)
    @Column(name = "category_ss_id")
    private Long id;
    private String name;

    //==매핑==
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_s_id")
    private CategoryS categoryS;

    @JsonIgnore
    @OneToMany(mappedBy = "categorySS", fetch = FetchType.LAZY)
    private List<ProductBoard> productBoardList = new ArrayList<>();

    @Builder
    public CategorySS(String name, CategoryS categoryS) {
        this.name = name;
        this.categoryS = categoryS;
    }

    public void linkToProductBoard(ProductBoard board) {
        this.productBoardList.add(board);
    }

    public void linkToCategoryS(CategoryS categoryS) {
        this.categoryS = categoryS;
        categoryS.linkToCategorySS(this);
    }
}
