package com.youngjo.ssg.domain.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Address {
    private String city;
    private String street;
    private String detail;
    private String postalCode;

    @Override
    public boolean equals(Object ad) {
        if (ad instanceof Address) {
            Address address = (Address) ad;
            return address.city.equals(city) &&
                    address.street.equals(street) &&
                    address.detail.equals(detail) &&
                    address.postalCode.equals(postalCode);
        } else {
            throw new IllegalArgumentException("It is not an Address type");
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, detail, postalCode);
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