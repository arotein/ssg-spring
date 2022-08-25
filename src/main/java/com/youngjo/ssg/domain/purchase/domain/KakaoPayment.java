package com.youngjo.ssg.domain.purchase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class KakaoPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kakao_payment_id")
    private Long id;
    // Response
    private String aid;
    private String tid;
    private String cid;
    private String sid;
    private String partner_order_id;
    private String partner_user_id;
    private String payment_method_type;
    private String item_name;
    private String item_code;
    private Integer quantity;
    private Timestamp created_at; // Json 직렬화
    private Timestamp approved_at; // Json 직렬화
    private String payload;
    // amount
    private Integer total;
    private Integer tax_free;
    private Integer vat;
    private Integer point;
    private Integer discount;
    private Integer green_deposit;
    // card_info
    private String purchase_corp;
    private String purchase_corp_code;
    private String issuer_corp;
    private String issuer_corp_code;
    private String kakaopay_purchase_corp;
    private String kakaopay_purchase_corp_code;
    private String kakaopay_issuer_corp;
    private String kakaopay_issuer_corp_code;
    private String bin;
    private String card_type;
    private String install_month;
    private String approved_id;
    private String card_mid;
    private String interest_free_install;
    private String card_item_code;

    @JsonIgnore
    @OneToOne(mappedBy = "kakaoPayment", fetch = FetchType.LAZY)
    private UserPurchase userPurchase;

    @Builder
    public KakaoPayment(String aid, String tid, String cid, String sid, String partner_order_id, String partner_user_id, String payment_method_type, String item_name, String item_code, Integer quantity, Timestamp created_at, Timestamp approved_at, String payload, Integer total, Integer tax_free, Integer vat, Integer point, Integer discount, Integer green_deposit, String purchase_corp, String purchase_corp_code, String issuer_corp, String issuer_corp_code, String kakaopay_purchase_corp, String kakaopay_purchase_corp_code, String kakaopay_issuer_corp, String kakaopay_issuer_corp_code, String bin, String card_type, String install_month, String approved_id, String card_mid, String interest_free_install, String card_item_code, UserPurchase userPurchase) {
        this.aid = aid;
        this.tid = tid;
        this.cid = cid;
        this.sid = sid;
        this.partner_order_id = partner_order_id;
        this.partner_user_id = partner_user_id;
        this.payment_method_type = payment_method_type;
        this.item_name = item_name;
        this.item_code = item_code;
        this.quantity = quantity;
        this.created_at = created_at;
        this.approved_at = approved_at;
        this.payload = payload;
        this.total = total;
        this.tax_free = tax_free;
        this.vat = vat;
        this.point = point;
        this.discount = discount;
        this.green_deposit = green_deposit;
        this.purchase_corp = purchase_corp;
        this.purchase_corp_code = purchase_corp_code;
        this.issuer_corp = issuer_corp;
        this.issuer_corp_code = issuer_corp_code;
        this.kakaopay_purchase_corp = kakaopay_purchase_corp;
        this.kakaopay_purchase_corp_code = kakaopay_purchase_corp_code;
        this.kakaopay_issuer_corp = kakaopay_issuer_corp;
        this.kakaopay_issuer_corp_code = kakaopay_issuer_corp_code;
        this.bin = bin;
        this.card_type = card_type;
        this.install_month = install_month;
        this.approved_id = approved_id;
        this.card_mid = card_mid;
        this.interest_free_install = interest_free_install;
        this.card_item_code = card_item_code;
        this.userPurchase = userPurchase;
    }

    public KakaoPayment linkToUserPurchase(UserPurchase purchase) {
        this.userPurchase = purchase;
        return this;
    }
}
