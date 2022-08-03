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
@Table(name = "category_ss")
public class CategorySS extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_ss_id")
    private Long id;
    @Column(unique = true)
    private String name;

    //==매핑==
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_s_id")
    private CategoryS categoryS;

    @JsonIgnore
    @OneToMany(mappedBy = "categorySS", fetch = FetchType.LAZY)
    private List<Product> productList;

    @Builder
    public CategorySS(String name, CategoryS categoryS, List<Product> productList) {
        this.name = name;
        this.categoryS = categoryS;
        this.productList = productList;
    }

    public void linkToProduct(Product product) {
        this.productList.add(product);
    }

    public void linkToCategoryS(CategoryS categoryS) {
        this.categoryS = categoryS;
    }
}
