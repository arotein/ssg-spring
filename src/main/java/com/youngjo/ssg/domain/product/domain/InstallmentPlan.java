package com.youngjo.ssg.domain.product.domain;

import com.youngjo.ssg.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/***
 * cardCompany : 카드사 -> 엔티티 생성해야하지만 생략
 * interestFreeBaseAmount : 무이자 최소 금액
 * installmentMonths : 무이자 할부개월수
 * benefitStartTime : 혜택 시작 시각
 * benefitEndTime : 혜택 종료 시각
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class InstallmentPlan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "installment_plan_id")
    private Long id;

    private String cardCompany;
    private Integer interestFreeBaseAmount;
    private Integer installmentMonths;
    private Timestamp benefitStartTime;
    private Timestamp benefitEndTime;
}
