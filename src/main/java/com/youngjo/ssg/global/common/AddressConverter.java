package com.youngjo.ssg.global.common;

import com.youngjo.ssg.domain.user.domain.Address;

public class AddressConverter {
    public static String convertToString(Address address) {
        return String.format("(%s)%s %s %s", address.getPostalCode(), address.getCity(), address.getStreet(), address.getDetail());
    }
}
