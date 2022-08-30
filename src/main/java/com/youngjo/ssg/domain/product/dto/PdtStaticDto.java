package com.youngjo.ssg.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PdtStaticDto {

    @Data
    @AllArgsConstructor
    public static class CtgL1Dto {
        private Integer listIndex;
        private Long id;
        private String name;

        public CtgL1Dto(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @Data
    @AllArgsConstructor
    public static class CtgL2Dto {
        private Integer listIndex;
        private Long id;
        private String name;

        public CtgL2Dto(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @Data
    @AllArgsConstructor
    public static class CtgL2ImgResDto {
        private Integer listIndex;
        private Long id;
        private String name;
        private String imgUrl;
        private String imgAlt;

        public CtgL2ImgResDto(Long id, String name, String imgUrl, String imgAlt) {
            this.id = id;
            this.name = name;
            this.imgUrl = imgUrl;
            this.imgAlt = imgAlt;
        }
    }

    @Data
    @AllArgsConstructor
    public static class CtgL3Dto {
        private Integer listIndex;
        private Long id;
        private String name;

        public CtgL3Dto(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @Data
    @AllArgsConstructor
    public static class CtgL4Dto {
        private Integer listIndex;
        private Long id;
        private String name;

        public CtgL4Dto(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @Data
    @AllArgsConstructor
    public static class ConsignmentSellerInfoResDto {
        private Integer listIndex;
        private Long id;
        private String name;
        private String consignmentSellerAddress;
        private String mailOrderNum;

        public ConsignmentSellerInfoResDto(Long id, String name, String consignmentSellerAddress, String mailOrderNum) {
            this.id = id;
            this.name = name;
            this.consignmentSellerAddress = consignmentSellerAddress;
            this.mailOrderNum = mailOrderNum;
        }
    }

    @Data
    @AllArgsConstructor
    public static class ProductImgResDto {
        private Integer listIndex;
        private Long id;
        private String imgPath;
        private String imgAlt;

        public ProductImgResDto(Long id, String imgPath, String imgAlt) {
            this.id = id;
            this.imgPath = imgPath;
            this.imgAlt = imgAlt;
        }
    }

    @Data
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

    @Data
    @AllArgsConstructor
    public static class MainProductResDto {
        private Integer listIndex;
        private Long id;
        private String modelCode;
        private String optionName1;
        private String optionValue1;
        private String optionName2;
        private String optionValue2;
        private Long price;
        private Integer stock;

        public MainProductResDto(Long id, String modelCode, String optionName1, String optionValue1, String optionName2, String optionValue2, Long price, Integer stock) {
            this.id = id;
            this.modelCode = modelCode;
            this.optionName1 = optionName1;
            this.optionValue1 = optionValue1;
            this.optionName2 = optionName2;
            this.optionValue2 = optionValue2;
            this.price = price;
            this.stock = stock;
        }
    }

    @Data
    @AllArgsConstructor
    public static class ProductRequiredInfoResDto {
        private Integer listIndex;
        private Long id;
        private String infoTitle;
        private String infoCnt;

        public ProductRequiredInfoResDto(Long id, String infoTitle, String infoCnt) {
            this.id = id;
            this.infoTitle = infoTitle;
            this.infoCnt = infoCnt;
        }
    }

    @Data
    @NoArgsConstructor
    public static class TagReqDto {
        private String keyword;
    }

    @Data
    @AllArgsConstructor
    public static class OptionNameResDto {
        private Integer listIndex;
        private Integer id;
        private String optValue;

        public OptionNameResDto(Integer id, String optValue) {
            this.id = id;
            this.optValue = optValue;
        }
    }
}
