package com.youngjo.ssg.global.enumeration;

public enum SalesSite {
    SSG_MALL("SSG_MALL"),
    SSG_DEPART("SSG_DEPART"),
    EMART_MALL("EMART_MALL"),
    CONSTRUCT(null);

    private String value;

    SalesSite(String value) {
        this.value = value;
    }

    public SalesSite findInstance(String str) {
        switch (str.toUpperCase()) {
            case "SSG_MALL":
                return SalesSite.SSG_MALL;
            case "SSG_DEPART":
                return SalesSite.SSG_DEPART;
            case "EMART_MALL":
                return SalesSite.EMART_MALL;
            default:
                throw new IllegalArgumentException("Sales site not found.");
        }
    }


}
