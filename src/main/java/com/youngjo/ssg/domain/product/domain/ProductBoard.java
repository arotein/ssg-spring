package com.youngjo.ssg.domain.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youngjo.ssg.global.common.BaseEntity;
import com.youngjo.ssg.global.enumeration.SalesSite;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/***
 * [미완성 기능]
 * User 엔티티 연결
 * Cart 엔티티 연결
 * Review 엔티티 연결
 * Coupon 엔티티 연결
 * Card 엔티티 연결
 *
 * [생각해볼 기능]
 * ShippingInfo.deliveryDate : 해당 Board의 최근 15일간 평균 배송일을 기반으로 제공
 * AutoCountInfo.love
 * AutoCountInfo.salesVol
 * Board 등록시 카테고리별 상품 count
 *
 * == ShippingInfo ==
 * isEachShippingFee : 배송비 개당 부과?
 * isPremium : 프리미엄 배송?
 * isCrossBorderShipping : 해외직구?
 * isOnlineOnly : 온라인 전용?
 * shippingFee : 배송비, 0원이면 무료
 * shippingFreeOver : ~원 이상 무료배송, 10원 이상 or null
 * availableDeliveryJeju : 제주 배송가능
 * availableDeliveryIsland : 도서산간 배송가능여부
 * shippingFeeJeju : 제주 추가금
 * shippingFeeIsland : 도서산간 추가금
 * courierCompany : 택배사
 *
 * == Auto Count ==
 * totalReviewQty : 리뷰 작성시 count
 * totalScore : 0.5 ~ 5.0 점 입력받기, 리뷰 작성시마자 +1
 * minPrice : 물건마다 가격이 다를 때, 아닐땐 null
 * onePrice : 모든 물건가격이 같을 때, 아닐땐 null
 * salesVol : 판매량, 구매 확정시마다 +1
 * deliveryDate : 예상 배송도착일(10일 이내 도착, 8/11(목) 도착예정), Unix Time -> 백에서 처리 후 리턴
 *
 * == Exchange Refund ==
 * returnAddress : 교환/반품 주소
 * exchangeShippingFee : 교환 배송비
 * returnShippingFee : 반품 배송비
 * premiumExchangeShippingFee : 프리미엄 배송 교환 배송비
 * premiumReturnShippingFee : 프리미엄 배송 반품 배송비
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProductBoard extends BaseEntity {
    // Board Information -> pdt마다 img 제공하는 board는 별도의 entity로 구성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_board_id")
    private Long id;
    private String title;
    private String brand; // -> 목록 검색되게

    private SalesSite salesSite; // 판매 싸이트
    // == ShippingInfo ==
    // true(1), false(0)
    private Boolean isEachShippingFee;
    private Boolean isPremium;
    private Boolean isCrossBorderShipping;
    private Boolean isOnlineOnly;

    private Integer shippingFee;
    private Integer shippingFreeOver;
    private Boolean availableDeliveryJeju;
    private Boolean availableDeliveryIsland;
    private Integer shippingFeeJeju;
    private Integer shippingFeeIsland;

    private String courierCompany;

    // == Product Detail Information =
    private String pdtName;

    // subProductList : 추가구성품 -> 미구현
//    private List<SubProduct> subProductList = new ArrayList<>();
//    private List<InstallmentPlan> installmentPlan = new ArrayList<>(); // 할부 -> 카드 엔티티 연결해야할듯

//    private List<ProductRequiredInfo> requiredInfo = new ArrayList<>(); // 상품 필수정보 -> 엔티티로 변경

    // == Exchange Refund ==
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "return_address_id")
    private Address returnAddress;
    private Integer exchangeShippingFee;
    private Integer returnShippingFee;
    private Integer premiumExchangeShippingFee;
    private Integer premiumReturnShippingFee;
    //위탁판매자 정보 추가하기

    // == Auto Count ==
    private Integer totalReviewQty;
    private Float totalScore;
    private Boolean isSamePrice;
    private Long minPrice;
    private Integer salesVol;
    private Long deliveryDate;

    // 카테고리 L3에 걸린 애들은 거르기 -> 실제로 L3에 걸거나 L4의 더미값으로 연결시키기
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "consignment_seller_info_id")
    private ConsignmentSellerInfo consignmentSellerInfo;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_l4_id")
    private CategoryL4 categoryL4;
    @JsonIgnore
    @OneToMany(mappedBy = "productBoardThumb", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImg> thumbImgList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "productBoardDetail", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImg> detailImgList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "productBoard", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MainProduct> mainProductList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "productBoard", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductRequiredInfo> productRequiredInfoList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "productBoard", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductBoardLike> productBoardLikeList = new ArrayList<>();


//    private NormalCart normalCart; // ManyToMany

    // 쿠폰 엔티티 연결(만들기)

//    @JoinColumn(name = "buy_id")
//    private Buy buy; -> n:m 이므로 중간테이블 만들기. review도 buy에 걸기? cart는 주문(buy)와 1:1 단방향

    @Builder
    public ProductBoard(String title, String brand, SalesSite salesSite, Boolean isEachShippingFee, Boolean isPremium, Boolean isCrossBorderShipping, Boolean isOnlineOnly, Integer shippingFee, Integer shippingFreeOver, Boolean availableDeliveryJeju, Boolean availableDeliveryIsland, Integer shippingFeeJeju, Integer shippingFeeIsland, String courierCompany, Long deliveryDate, String pdtName, Address returnAddress, Integer exchangeShippingFee, Integer returnShippingFee, Integer premiumExchangeShippingFee, Integer premiumReturnShippingFee, CategoryL4 categoryL4, ConsignmentSellerInfo consignmentSellerInfo) {
        this.title = title;
        this.brand = brand;
        this.salesSite = salesSite;
        this.isEachShippingFee = isEachShippingFee;
        this.isPremium = isPremium;
        this.isCrossBorderShipping = isCrossBorderShipping;
        this.isOnlineOnly = isOnlineOnly;
        this.shippingFee = shippingFee;
        this.shippingFreeOver = shippingFreeOver;
        this.availableDeliveryJeju = availableDeliveryJeju;
        this.availableDeliveryIsland = availableDeliveryIsland;
        this.shippingFeeJeju = shippingFeeJeju;
        this.shippingFeeIsland = shippingFeeIsland;
        this.courierCompany = courierCompany;
        this.deliveryDate = deliveryDate;
        this.pdtName = pdtName;
        this.returnAddress = returnAddress;
        this.exchangeShippingFee = exchangeShippingFee;
        this.returnShippingFee = returnShippingFee;
        this.premiumExchangeShippingFee = premiumExchangeShippingFee;
        this.premiumReturnShippingFee = premiumReturnShippingFee;
        this.categoryL4 = categoryL4;
        this.consignmentSellerInfo = consignmentSellerInfo;
    }

    public ProductBoard linkToProductList(List<MainProduct> mainProductList) {
        this.mainProductList = mainProductList;
        long min = mainProductList.get(0).getPrice();
        for (MainProduct mainProduct : mainProductList) {
            mainProduct.linkToProductBoard(this);
            if (min != mainProduct.getPrice()) { // 상품마다 가격이 다르다
                if (min > mainProduct.getPrice()) {
                    this.minPrice = mainProduct.getPrice();
                } else {
                    this.minPrice = min;
                }
                this.isSamePrice = false;
            } else { // 상품마다 가격이 같다
                this.isSamePrice = true;
                this.minPrice = min;
            }
        }
        return this;
    }

    public ProductBoard linkToCategoryL4(CategoryL4 categoryL4) {
        this.categoryL4 = categoryL4;
        categoryL4.linkToProductBoard(this);
        return this;
    }

    // 역방향 필요없어서 단방향으로만 연결됨
    public ProductBoard linkToProductThumbImgList(List<ProductImg> productImgList) {
        for (ProductImg img : productImgList) {
            this.thumbImgList.add(img);
            img.linkToProductBoardThumb(this);
        }
        return this;
    }

    // 역방향 필요없어서 단방향으로만 연결됨
    public ProductBoard linkToProductDetailImgList(List<ProductImg> productImgList) {
        for (ProductImg img : productImgList) {
            this.detailImgList.add(img);
            img.linkToProductBoardDetail(this);
        }
        return this;
    }

    public ProductBoard linkToPdtBoardLike(ProductBoardLike productBoardLike) {
        this.productBoardLikeList.add(productBoardLike);
        return this;
    }
}
