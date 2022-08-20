package com.youngjo.ssg.domain.product.domain;

import lombok.*;

import javax.persistence.*;

/***
 * city : 도시
 * street : 도로명주소
 * detail : 유저에게 입력받는 나머지 주소
 * postalCode : 우편번호
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;
    private String city;
    private String street;
    private String detail;
    private String postalCode;

    @Builder
    public Address(String city, String street, String detail, String postalCode) {
        this.city = city;
        this.street = street;
        this.detail = detail;
        this.postalCode = postalCode;
    }

    public Address updateAddress(String city, String street, String detail, String postalCode) {
        this.city = city;
        this.street = street;
        this.detail = detail;
        this.postalCode = postalCode;
        return this;
    }
}

/***
 * kakao API response data
 * https://postcode.map.daum.net/guide
 *
 * zonecode: "47815"
 * address: "부산 동래구 충렬대로182번가길 8"
 * addressEnglish: "8, Chungnyeol-daero 182beonga-gil, Dongnae-gu, Busan, Korea"
 * addressType: "R"
 * bcode: "2626010700"
 * bname: "명륜동"
 * bnameEnglish: "Myeongnyun-dong"
 * bname1: ""
 * bname1English: ""
 * bname2: "명륜동"
 * bname2English: "Myeongnyun-dong"
 * sido: "부산"
 * sidoEnglish: "Busan"
 * sigungu: "동래구"
 * sigunguEnglish: "Dongnae-gu"
 * sigunguCode: "26260"
 * userLanguageType: "K"
 * query: "충렬대로182번가길8"
 * buildingName: "덕성오피스텔"
 * buildingCode: "2626010700105150032000112"
 * apartment: "N"
 * jibunAddress: "부산 동래구 명륜동 515-32"
 * jibunAddressEnglish: "515-32, Myeongnyun-dong, Dongnae-gu, Busan, Korea"
 * roadAddress: "부산 동래구 충렬대로182번가길 8"
 * roadAddressEnglish: "8, Chungnyeol-daero 182beonga-gil, Dongnae-gu, Busan, Korea"
 * autoRoadAddress: ""
 * autoRoadAddressEnglish: ""
 * autoJibunAddress: ""
 * autoJibunAddressEnglish: ""
 * userSelectedType: "R"
 * noSelected: "N"
 * hname: ""
 * roadnameCode: "4190495"
 * roadname: "충렬대로182번가길"
 * roadnameEnglish: "Chungnyeol-daero 182beonga-gil"
 */