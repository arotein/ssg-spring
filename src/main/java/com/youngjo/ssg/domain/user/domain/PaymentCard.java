package com.youngjo.ssg.domain.user.domain;

import com.youngjo.ssg.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PaymentCard extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_card_id")
    private Long id;
    private String alias;
    private String cardOwner;
    private String cardNumber; // 0000-0000-0000-0000
    private String expDate; // MMYY, 만료날짜
    private String cvc; // 3자리 수
    private String password; // 카드비밀번호 앞 2자리
    private String company;
    //개인정보 제공, 이용동의 필드 추가하기

    //==매핑==
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public PaymentCard(String alias, String cardOwner, String cardNumber, String expDate, String cvc, String password, String company, User user) {
        this.alias = alias;
        this.cardOwner = cardOwner;
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.cvc = cvc;
        this.password = password;
        this.company = company;
        this.user = user;
    }

    public PaymentCard cardNumberFormatMatching() {
        if (cardNumber == null || !cardNumber.matches("^([\\d]{4}-){3}[\\d]{4}$")) {
            throw new IllegalArgumentException("Invalid card number");
        }
        return this;
    }

    public PaymentCard expirationDateFormatMatching() {
        if (expDate == null || !expDate.matches("^(0[1-9]|1[0-2])([2-9][\\d])$")) {
            throw new IllegalArgumentException("Invalid expiration date");
        }
        return this;
    }

    public PaymentCard cvcFormatMatching() {
        if (cvc == null || !cvc.matches("^[\\d]{3}$")) {
            throw new IllegalArgumentException("Invalid cvc");
        }
        return this;
    }

    public PaymentCard passwordFormatMatching() {
        if (password == null || !password.matches("^[\\d]{2}$")) {
            throw new IllegalArgumentException("Invalid password");
        }
        return this;
    }
}
