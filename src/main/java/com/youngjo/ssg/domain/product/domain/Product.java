//package com.youngjo.ssg.domain.product.domain;

//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Entity
//@IdGenTable
//public class Product extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = SeqTable.name)
//    @Column(name = "product_id")
//    private Long id;
//    private String name;
//    private String modelCode;
//    private Integer price;
//    private Integer stock;
//    private String option1;
//    private String option2;
//
//    //==매핑==
//    @JsonIgnore
//    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
//    private List<Review> reviewList = new ArrayList<>();
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "normal_cart_id")
//    private NormalCart normalCart;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "buy_id")
//    private Buy buy;
//
////    @Builder
//}
