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
        this.cardNumber = cardNumberFormatMatching(cardNumber);
        this.expDate = expirationDateFormatMatching(expDate);
        this.cvc = cvcFormatMatching(cvc);
        this.password = passwordFormatMatching(password);
        this.company = company;
        this.user = user;
    }

    private String cardNumberFormatMatching(String cardNumber) {
        if (!"^([\\d]{4}-){3}[\\d]{4}$".matches(cardNumber)) {
            throw new IllegalArgumentException("Invalid card number");
        }
        return cardNumber;
    }

    private String expirationDateFormatMatching(String expirationDate) {
        if (!"^(0[1-9]|1[0-2])([2-9][\\d])$".matches(expirationDate)) {
            throw new IllegalArgumentException("Invalid expiration date");
        }
        return expirationDate;
    }

    private String cvcFormatMatching(String cvc) {
        if (!"^[\\d]{3}$".matches(cvc)) {
            throw new IllegalArgumentException("Invalid cvc");
        }
        return cvc;
    }

    private String passwordFormatMatching(String password) {
        if (!"^[\\d]{2}$".matches(password)) {
            throw new IllegalArgumentException("Invalid password");
        }
        return password;
    }
}
