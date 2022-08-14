package com.youngjo.ssg.global.common;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneratorFileName {
    public static String generate() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String now_date = format.format(now);
        return String.format("SSGMALL_%s_%s", now_date, RandomStringUtils.randomNumeric(7));
    }
}
