package com.youngjo.ssg.domain.purchase.domain;

//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Entity
//public class AnonymousPurchase extends BaseEntity { //비회원 주문
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "anonymous_buy_id")
//    private Long id;
//    private String serial; // 주문 고유번호
//    private String recipient;
//    private String phone;
//    private String email;
//    //    private Address address;
//    private Integer totalPrice;
//    private String paymentType; // 결제종류
//    // 결제 상세정보는 카카오 API 참고해서 작성하기
//
//    //==매핑==
////    @JsonIgnore
////    @OneToMany(mappedBy = "buy", fetch = FetchType.LAZY)
////    private List<Product> productList = new ArrayList<>();
//
//    @Builder
//}

/***
 * kakao 결제 API
 * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment
 */