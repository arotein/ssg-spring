package com.youngjo.ssg.global.common;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Component
public class ImgNameGenerator {
    public String generateName() {
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        String prefix = String.format("SSG_%s", yyyyMMdd.format(new Timestamp(System.currentTimeMillis())));
        String randomStr = RandomStringUtils.randomAlphanumeric(7);
        return String.format("%s_%s", prefix, randomStr);
    }
}
