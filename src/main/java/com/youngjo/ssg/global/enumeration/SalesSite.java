package com.youngjo.ssg.global.enumeration;

public enum SalesSite {
    SSG_MALL("신세계몰"),
    SSG_DEPART("신세계백화점"),
    EMART_MALL("이마트몰"),
    CONSTRUCT(null);

    private String value;

    SalesSite(String value) {
        this.value = value;
    }

    public SalesSite findInstance(String str) {
        switch (str.strip()) {
            case "신세계몰":
                return SalesSite.SSG_MALL;
            case "신세계백화점":
                return SalesSite.SSG_DEPART;
            case "이마트몰":
                return SalesSite.EMART_MALL;
            default:
                throw new IllegalArgumentException("Sales site not found.");
        }
    }

    public String getValue() {
        return value;
    }
}
