package com.youngjo.ssg.global.enumeration;

/***
 * PurchaseStatus : 구매요청 처리
 * DeliveryStatus : 구매처리 완료 후 배송처리
 */
public enum DeliveryStatus {
    DEPOSIT_WAITING("입금대기"), // 현금결제시
    COMPLETE_PAYMENT("결제완료"),
    READY_TO_SHIP("상품준비중"),
    SHIPPING("배송중"),
    DELIVERY_COMPLETED("배송완료"),
    CONFIRMATION_OF_PURCHASE("구매확정"),
    CANCELLATION_REQUEST("취소요청"),
    CANCELED("취소완료"),
    RETURN_REQUEST("반품요청"),
    RETURN_COMPLETED("반품완료"),
    EXCHANGE_REQUEST("교환요청"),
    EXCHANGE_COMPLETED("교환완료");

    private String value;

    DeliveryStatus(String value) {
        this.value = value;
    }

    public DeliveryStatus findInstance(String str) {
        switch (str.strip()) {
            case "입금대기":
                return DeliveryStatus.DEPOSIT_WAITING;
            case "결제완료":
                return DeliveryStatus.COMPLETE_PAYMENT;
            case "상품준비중":
                return DeliveryStatus.READY_TO_SHIP;
            case "배송중":
                return DeliveryStatus.SHIPPING;
            case "배송완료":
                return DeliveryStatus.DELIVERY_COMPLETED;
            case "구매확정":
                return DeliveryStatus.CONFIRMATION_OF_PURCHASE;
            case "취소요청":
                return DeliveryStatus.CANCELLATION_REQUEST;
            case "취소완료":
                return DeliveryStatus.CANCELED;
            case "반품요청":
                return DeliveryStatus.RETURN_REQUEST;
            case "반품완료":
                return DeliveryStatus.RETURN_COMPLETED;
            case "교환요청":
                return DeliveryStatus.EXCHANGE_REQUEST;
            case "교환완료":
                return DeliveryStatus.EXCHANGE_COMPLETED;
            default:
                throw new IllegalArgumentException("Delivery status not found.");
        }
    }

    public String getValue() {
        return value;
    }
}
