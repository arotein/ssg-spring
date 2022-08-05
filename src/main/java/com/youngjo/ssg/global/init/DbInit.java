package com.youngjo.ssg.global.init;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.youngjo.ssg.domain.product.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.youngjo.ssg.domain.product.domain.QCategoryM.categoryM;

@Slf4j
@Component
@RequiredArgsConstructor
public class DbInit {
    private final InitService initService;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlOption;

    @PostConstruct
    public void init() throws IOException {
        if (ddlOption.equals("create")) {
            initService.categoryInit();
            initService.categoryInit2();
            initService.happyLoungeInit();
            log.info("DbInit 실행완료");
        }
    }

    @Component
    @Transactional
    static class InitService {
        private final EntityManager entityManager;
        private final JPAQueryFactory queryFactory;

        @Autowired
        public InitService(EntityManager entityManager) {
            this.entityManager = entityManager;
            this.queryFactory = new JPAQueryFactory(entityManager);
        }

        @Value("${global.xlsx-dir}")
        private String filePath;
        // sheet는 1부터, row, col은 0부터

        @Modifying(clearAutomatically = true)
        public void happyLoungeInit() throws IOException {
            XSSFWorkbookFactory wb = new XSSFWorkbookFactory();
            InputStream file = new FileInputStream(filePath + "/happyLoungeInit.xlsx");
            String[] titleList = {"쇼케이스",
                    "패션/언더웨어",
                    "뷰티",
                    "슈즈/잡화",
                    "스포츠",
                    "유아동",
                    "디지털",
                    "리빙",
                    "식품",
                    "생활용품"};
            List<String> valueList = new ArrayList<>();
            XSSFWorkbook ws = wb.create(file);
            for (int i = 0; i < titleList.length; i++) {
                XSSFSheet sheet = ws.getSheetAt(i);
                for (int row = 1; row <= sheet.getLastRowNum(); row++) {
                    for (int col = 0; col <= 10; col++) {
                        try {
                            valueList.add(sheet.getRow(row).getCell(col).getStringCellValue());
                        } catch (Exception e) {
                            valueList.add(null);
                        }
                    }
                    if (valueList.get(8) == null) {
                        valueList.set(8, "0");
                    } else if (valueList.get(8).equals("무료배송")) {
                        valueList.set(8, "0");
                        valueList.set(10, "무료배송");
                    }
                    HappyLoungeItem item = HappyLoungeItem.builder()
                            .name(RandomStringUtils.randomAlphabetic(7))
                            .imgUrl1(valueList.get(0))
                            .imgUrl2(valueList.get(1))
                            .imgUrl3(valueList.get(2))
                            .imgUrl4(valueList.get(3))
                            .title1(valueList.get(4))
                            .title2(valueList.get(5))
                            .productTitle(valueList.get(6))
                            .price(parseToInt(valueList.get(7)))
                            .pick(parseToInt(valueList.get(8)))
                            .productUrl(valueList.get(9))
                            .isFreeShipping(parseToBool(valueList.get(10)))
                            .ctg(titleList[i])
                            .build();
                    entityManager.persist(item);
                    valueList.clear();
                }
            }
        }

        private Integer parseToInt(String str) {
            try {
                return Integer.parseInt(str);
            } catch (Exception e) {
                return 0;
            }
        }

        private Boolean parseToBool(String str) {
            try {
                return str.equals("무료배송");
            } catch (Exception e) {
                return false;
            }
        }

        // L2 src add
        @Modifying(clearAutomatically = true)
        public void categoryInit2() throws IOException {
            XSSFWorkbookFactory wb = new XSSFWorkbookFactory();
            InputStream file = new FileInputStream(filePath + "/categoryAll.xlsx");
            XSSFSheet sheet = wb.create(file).getSheetAt(1);
            long idx = 0;
            for (int row = 0; row <= sheet.getLastRowNum(); row++) {
                for (int col = 0; col <= 10; col++) {
                    String src;
                    try {
                        src = sheet.getRow(row).getCell(col).getStringCellValue(); // ctgL2 src
                        idx++;
                        queryFactory.update(categoryM)
                                .set(categoryM.src, src)
                                .where(categoryM.id.eq(idx))
                                .execute();
                    } catch (Exception e) {
                    }
                }
            }
        }

        // category init, L1 src add
        @Modifying(clearAutomatically = true)
        public void categoryInit() throws IOException {
            String[] cats = {
                    "패션의류",
                    "명품/잡화/쥬얼리",
                    "뷰티",
                    "유아동",
                    "스포츠/레저",
                    "가구/인테리어",
                    "주방/생활/건강",
                    "반려동물",
                    "식품",
                    "가전",
                    "디지털/렌탈/컴퓨터/차량용품",
                    "e쿠폰/서비스/여행",
                    "문구/도서/취미"
            };
            String[] urls = {
                    "https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202109/2021090117370424472933833393_256.jpg&w=150&h=150&edit=c&t=1563dd18551f16231432da715468c33acc03630e",
                    "https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202007/2020072917411353521969696196_273.jpg&w=150&h=150&edit=c&t=48614e250da988610b4e7935868e8f1290e7c231",
                    "https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202007/2020072916174616090674372167_34.jpg&w=150&h=150&edit=c&t=08dd324b76e992bbf3cf69fee132454ef1ae2b2f",
                    "https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202007/2020072916180739844304103530_576.jpg&w=150&h=150&edit=c&t=24e30ad244143647858f78afb39731d96b5256bc",
                    "https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202007/2020072916183081222145720314_367.jpg&w=150&h=150&edit=c&t=8791b688cc54899753a9adb2f82dc585c52a8ae0",
                    "https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202007/2020072917404751786590472759_591.jpg&w=150&h=150&edit=c&t=3a7d4a334ee9337474b984a6ea9867fd36181d57",
                    "https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202105/2021051315282642621637316163_499.jpg&w=150&h=150&edit=c&t=74b16620d21c0412d55cec8083a145421623a236",
                    "https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202109/2021091312493245557072867707_591.jpg&w=150&h=150&edit=c&t=b1ff290c681d07b086d3b13334f222294befce91",
                    "https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202007/2020072916185554662649910364_729.jpg&w=150&h=150&edit=c&t=437f0d41210a78741f89ba827407c26b2e8798ce",
                    "https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202007/2020072916221442699359630445_591.jpg&w=150&h=150&edit=c&t=5c5d93292562b819f3509eb400fbd9d49d86ad6e",
                    "https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202007/2020072916192109082788875378_90.jpg&w=150&h=150&edit=c&t=b83371edfebf91bca55682016e958688bc59cb78",
                    "https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202007/2020072916223916760668536166_464.jpg&w=150&h=150&edit=c&t=82ec94eba3bd7b859529cacae02f3b1287f780b5",
                    "https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202105/2021051316532756213053205305_963.jpg&w=150&h=150&edit=c&t=292c99e60f69fb8779ef1e40fe7096612bcb0165"
            };
            List<String> catList = List.of(cats);

            XSSFWorkbookFactory wb = new XSSFWorkbookFactory();
            InputStream file = new FileInputStream(filePath + "/categoryAll.xlsx");
            XSSFSheet sheet = wb.create(file).getSheetAt(0);

            Category catL1 = null;
            CategoryM catL2 = null;
            CategoryS catL3 = null;
            String value = null;
            int idx = 0;


            for (int row = 0; row <= sheet.getLastRowNum(); row++) {
                for (int col = 0; col < 19; col++) {
                    try {
                        // 시트값 존재하는지 체크
                        value = sheet.getRow(row).getCell(col).getStringCellValue();
                    } catch (Exception e) {
                        continue;
                    }
                    // L1체크
                    if (catList.contains(value) && col == 0) {
                        catL1 = Category.builder().name(value).src(urls[idx]).build();
                        idx++;
                    } else {
                        // L2체크
                        try {
                            sheet.getRow(row).getCell(1).getStringCellValue();
                        } catch (Exception e) {
                            catL2 = CategoryM.builder().name(value).build();
                            catL2.linkToCategory(catL1);
                            entityManager.persist(catL2);
                            continue;
                        }
                        // L3체크
                        if (col == 0) {
                            catL3 = CategoryS.builder().name(value).build();
                            catL3.linkToCategoryM(catL2);
                            continue;
                        }
                        // L4체크
                        if (col != 0 && !value.equals("a")) {
                            CategorySS catL4 = CategorySS.builder().name(value).build();
                            catL4.linkToCategoryS(catL3);
                            entityManager.persist(catL4);
                        }
                    }
                }
            }
        }
    }
}