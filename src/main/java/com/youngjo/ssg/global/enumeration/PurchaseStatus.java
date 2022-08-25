package com.youngjo.ssg.global.enumeration;

/***
 * PurchaseStatus : 구매요청 처리
 * DeliveryStatus : 구매처리 완료 후 배송처리
 */
public enum PurchaseStatus {
    PROCEEDING("구매처리중"),
    PURCHASE_COMPLETED("구매처리완료"),
    PURCHASE_CANCELED("구매취소됨");

    private String value;

    PurchaseStatus(String value) {
        this.value = value;
    }

    public PurchaseStatus findInstance(String str) {
        switch (str.strip()) {
            case "구매처리중":
                return PurchaseStatus.PROCEEDING;
            case "구매처리완료":
                return PurchaseStatus.PURCHASE_COMPLETED;
            case "구매취소됨":
                return PurchaseStatus.PURCHASE_CANCELED;
            default:
                throw new IllegalArgumentException("Purchase status not found.");
        }
    }

    public String getValue() {
        return value;
    }
}
