package com.youngjo.ssg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SsgApplication {
    public static void main(String[] args) {
        SpringApplication.run(SsgApplication.class, args);
    }
}
