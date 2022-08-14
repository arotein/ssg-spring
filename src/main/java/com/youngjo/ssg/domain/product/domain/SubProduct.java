//package com.youngjo.ssg.domain.product.domain;
//
//import com.youngjo.ssg.global.common.BaseEntity;
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
///***
// * 추가구성품 엔티티 -> 미구현 결정
// */
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Entity
//public class SubProduct extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "sub_product_id")
//    private Long id;
//    private String modelCode; // 제조사에서 부여한 상품 모델명
//    private Integer price;
//    private Integer stock;
//
//    //==매핑==
////    @JsonIgnore
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "product_board_id")
////    private ProductBoard productBoard;
//
//    @Builder
//    public SubProduct(String modelCode, Integer price, Integer stock) {
//        this.modelCode = modelCode;
//        this.price = price;
//        this.stock = stock;
//    }
//
////    public void linkToProductBoard(ProductBoard productBoard) {
////        this.productBoard = productBoard;
////    }
//}
