package com.youngjo.ssg.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PdtStaticDto {

    @Getter
    @AllArgsConstructor
    public static class CtgL1Dto {
        private Long id;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    public static class CtgL2Dto {
        private Long id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    public static class CtgL2ImgResDto {
        private Long id;
        private String name;
        private String imgUrl;
    }

    @Getter
    @AllArgsConstructor
    public static class CtgL3Dto {
        private Long id;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    public static class CtgL4Dto {
        private Long id;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    public static class ConsignmentSellerInfoResDto {
        private String name;
        private String consignmentSellerAddress;
        private String mailOrderNum;
    }

    @Getter
    @AllArgsConstructor
    public static class ProductImgResDto {
        private String imgPath;
    }

    @Getter
    @NoArgsConstructor
    public static class MainProductReqDto {
        private String modelCode;
        private String optionName1;
        private String optionValue1;
        private String optionName2;
        private String optionValue2;
        private Long price;
        private Integer stock;
    }

    @Getter
    @AllArgsConstructor
    public static class MainProductResDto {
        private Long id;
        private String modelCode;
        private String optionName1;
        private String optionValue1;
        private String optionName2;
        private String optionValue2;
        private Long price;
        private Integer stock;
    }

    @Getter
    @AllArgsConstructor
    public static class ProductRequiredInfoResDto {
        private String infoTitle;
        private String infoCnt;
    }

    @Getter
    @NoArgsConstructor
    public static class TagReqDto {
        private String keyword;
    }
}
