package com.youngjo.ssg.global.init;

import com.youngjo.ssg.domain.product.domain.Category;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class CategoryInit {
    private final InitService initService;

    @PostConstruct
    public void init() throws IOException {
//        initService.categoryInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager entityManager;

        public void categoryInit() throws IOException {
            String[] catNames = {
                    "패션의류",
                    "명품 잡화 쥬얼리",
                    "뷰티",
                    "유아동",
                    "스포츠 레저",
                    "가구 인테리어",
                    "주방 생활 건강",
                    "반려동물",
                    "식품",
                    "가전",
                    "디지털 렌탈 컴퓨터 차량용품",
                    "e쿠폰 서비스 여행",
                    "문구 도서 취미"
            };

            for (String name : catNames) {
                entityManager.persist(Category.builder()
                        .name(name)
                        .build());
            }

            XSSFWorkbookFactory wb = new XSSFWorkbookFactory();
            InputStream file = new FileInputStream("C:\\Users\\교육생07\\Desktop\\YoungJo-BE\\excelFile\\categoryAll.xlsx");
            XSSFSheet sheet = wb.create(file).getSheetAt(0);
            XSSFCell cell = sheet.getRow(1).getCell(0);
            System.out.println("cell = " + cell);
//            sheet.

//            Row row = sheet.getRow(2);
//            Cell cell = row.getCell(3);
//            if (cell == null)
//                cell = row.createCell(3);
//            cell.setCellType(CellType.STRING);
//            cell.setCellValue("a test");
        }
    }
}