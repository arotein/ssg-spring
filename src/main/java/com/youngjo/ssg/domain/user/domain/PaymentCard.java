package com.youngjo.ssg.domain.user.domain;

/***
 * 정기배송 결제 서비스에만 이용됨 -> 생략
 */
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Entity
//public class PaymentCard extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "payment_card_id")
//    private Long id;
//    private String alias;
//    private String cardOwner; // 영문
//    private String cardNumber; // 0000-0000-0000-0000
//    private String expDate; // MMYY, 만료날짜
//    private String cvc; // 3자리 수
//    private String password; // 카드비밀번호 앞 2자리
//    private String company; // 카드사 선택(enum)
//    //개인정보 제공, 이용동의 필드 추가하기
//
//    //==매핑==
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @Builder
//    public PaymentCard(String alias, String cardOwner, String cardNumber, String expDate, String cvc, String password, String company) {
//        this.alias = alias;
//        this.cardOwner = cardOwnerFormatMatching(cardOwner);
//        this.cardNumber = cardNumberFormatMatching(cardNumber);
//        this.expDate = expDateFormatMatching(expDate);
//        this.cvc = cvcFormatMatching(cvc);
//        this.password = passwordFormatMatching(password);
//        this.company = company;
//    }
//
//    public String cardOwnerFormatMatching(String cardOwner) {
//        if (cardOwner == null || !cardOwner.matches("^[a-zA-Z ]$")) {
//            throw new IllegalArgumentException("Invalid card owner");
//        }
//        return cardOwner;
//    }
//
//    public String cardNumberFormatMatching(String cardNumber) {
//        if (cardNumber == null || !cardNumber.matches("^([\\d]{4}-){3}[\\d]{4}$")) {
//            throw new IllegalArgumentException("Invalid card number");
//        }
//        return cardNumber;
//    }
//
//    public String expDateFormatMatching(String expDate) {
//        if (expDate == null || !expDate.matches("^(0[1-9]|1[0-2])([2-9][\\d])$")) {
//            throw new IllegalArgumentException("Invalid expiration date");
//        }
//        return expDate;
//    }
//
//    public String cvcFormatMatching(String cvc) {
//        if (cvc == null || !cvc.matches("^[\\d]{3}$")) {
//            throw new IllegalArgumentException("Invalid cvc");
//        }
//        return cvc;
//    }
//
//    public String passwordFormatMatching(String password) {
//        if (password == null || !password.matches("^[\\d]{2}$")) {
//            throw new IllegalArgumentException("Invalid password");
//        }
//        return password;
//    }
//}
