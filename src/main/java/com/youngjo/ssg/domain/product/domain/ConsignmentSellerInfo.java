package com.youngjo.ssg.domain.product.domain;

import com.youngjo.ssg.domain.user.domain.Address;
import com.youngjo.ssg.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/***
 * ConsignmentSellerInfo : 위탁 판매자 정보
 *
 * name : 업체명
 * address : 주소
 * mailOrderNum : 통신판매번호
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ConsignmentSellerInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consignment_seller_info_id")
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "consignment_seller_address_id")
    private Address consignmentSellerAddress;
    private String mailOrderNum;

    public ConsignmentSellerInfo linkToAddress(Address address) {
        this.consignmentSellerAddress = address;
        return this;
    }
}
