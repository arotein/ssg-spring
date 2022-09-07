package com.youngjo.ssg.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserStaticDto {
    @Getter
    @NoArgsConstructor
    public static class AddressReqDto {
        private String city;
        private String street;
        private String detail;
        private String postalCode;

        public AddressReqDto streetDataFix() {
            if (city.equals(street.split(" ")[0])) {
                street = street.replaceFirst(String.format("%s ", city), "");
            }
            return this;
        }
    }
}
