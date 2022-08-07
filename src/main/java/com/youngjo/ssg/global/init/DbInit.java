package com.youngjo.ssg.global.init;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.io.IOException;


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
//            initService.boardTest();
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

        @Modifying(clearAutomatically = true)
        public void boardTest() {
        }
    }
}