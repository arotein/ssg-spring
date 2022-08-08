package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.*;
import com.youngjo.ssg.global.enumeration.SalesSite;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class PdtBoardDetailResDto {
    private Long id;
    private String title;
    private String brand; // -> 목록 검색되게
    private SalesSite salesSite;
    private Boolean isEachShippingFee; // 배송비 개당 부과? true(1), false(0)
    private Integer shippingFee; // 0원(무료), 3000원 선택
    private Integer shippingFeeJeju; // 제주 추가금
    private Integer shippingFeeIsland; // 도서산간 추가금
    private Integer shippingFreeOver; // ~원 이상 무료, 10원 이상.
    private String pdtName;
    private String pdtDetailImgUrl;
    private String optionName1;
    private String optionName2;
    private List<Product> productList = new ArrayList<>();
    private List<ProductRequiredInfo> requiredInfo = new ArrayList<>();
    private Integer totalReviewQty; // 리뷰 작성시 count
    private Integer totalScore; // 0.5 ~ 5점 입력받기, 리뷰 작성시마다 + 1
    private Integer minPrice; // 물건마다 가격이 다를 때
    private Integer price; // 가격이 1개일 때
    private Integer love; // 좋아요 -> user와 연결
    private Integer salesVol; // 판매량 -> 구매시마다 + 1
    private String ctgL1;
    private String ctgL2;
    private String ctgL3;
    private String ctgL4;
    private List<Image> thumbImgList = new ArrayList<>();
    private List<Image> detailImgList = new ArrayList<>();

    public PdtBoardDetailResDto(ProductBoard pb) {
        this.id = pb.getId();
        this.title = pb.getTitle();
        this.brand = pb.getBrand();
        this.salesSite = pb.getSalesSite();
        this.isEachShippingFee = pb.getIsEachShippingFee();
        this.shippingFee = pb.getShippingFee();
        this.shippingFeeJeju = pb.getShippingFeeJeju();
        this.shippingFeeIsland = pb.getShippingFeeIsland();
        this.shippingFreeOver = pb.getShippingFreeOver();
        this.pdtName = pb.getPdtName();
        this.pdtDetailImgUrl = pb.getPdtDetailImgUrl();
        this.optionName1 = pb.getOptionName1();
        this.optionName2 = pb.getOptionName2();
        this.productList = pb.getProductList();
        this.requiredInfo = pb.getRequiredInfo();
        this.totalReviewQty = pb.getTotalReviewQty();
        this.totalScore = pb.getTotalScore();
        this.minPrice = pb.getMinPrice();
        this.price = pb.getOnePrice();
        this.love = pb.getLove();
        this.salesVol = pb.getSalesVol();
        this.ctgL1 = pb.getCategoryL4().getCategoryL3().getCategoryL2().getCategoryL1().getName();
        this.ctgL2 = pb.getCategoryL4().getCategoryL3().getCategoryL2().getName();
        this.ctgL3 = pb.getCategoryL4().getCategoryL3().getName();
        this.ctgL4 = pb.getCategoryL4().getName();
        this.thumbImgList = pb.getThumbImgList();
        this.detailImgList = pb.getDetailImgList();
    }
}
