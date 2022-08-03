package com.youngjo.ssg.global.init;

import com.youngjo.ssg.domain.product.domain.Category;
import com.youngjo.ssg.domain.product.domain.CategoryM;
import com.youngjo.ssg.domain.product.domain.CategoryS;
import com.youngjo.ssg.domain.product.domain.CategorySS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryInit {
    private final InitService initService;

    @PostConstruct
    public void init() throws IOException {
        initService.categoryInit();
        log.info("categoryInit 실행완료");
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager entityManager;
        @Value("${global.category-xlsx-dir}")
        private String filePath;

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
            List<String> catList = List.of(cats);

            XSSFWorkbookFactory wb = new XSSFWorkbookFactory();
            InputStream file = new FileInputStream(filePath);
            XSSFSheet sheet = wb.create(file).getSheetAt(0);


            Category catL1 = null;
            CategoryM catL2 = null;
            CategoryS catL3 = null;
            String value = null;


            for (int row = 0; row < 786; row++) {
                for (int col = 0; col < 19; col++) {
                    try {
                        // 시트값 존재하는지 체크
                        value = sheet.getRow(row).getCell(col).getStringCellValue();
                    } catch (Exception e) {
                        continue;
                    }
                    // L1체크
                    if (catList.contains(value) && col == 0) {
                        catL1 = Category.builder().name(value).build();
                        continue;
                    } else {
                        // L2체크
                        try {
                            sheet.getRow(row).getCell(1).getStringCellValue();
                        } catch (Exception e) {
                            catL2 = CategoryM.builder().name(value).build();
                            catL2.linkToCategory(catL1);
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