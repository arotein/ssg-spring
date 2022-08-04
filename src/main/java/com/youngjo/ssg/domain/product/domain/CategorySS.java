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
@Table(name = "category_ss")
public class CategorySS extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private List<Product> productList = new ArrayList<>();

    @Builder
    public CategorySS(String name, CategoryS categoryS) {
        this.name = name;
        this.categoryS = categoryS;
    }

    public void linkToProduct(Product product) {
        this.productList.add(product);
    }

    public void linkToCategoryS(CategoryS categoryS) {
        this.categoryS = categoryS;
        categoryS.linkToCategorySS(this);
    }
}
