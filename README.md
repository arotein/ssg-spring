# SSG Project Made of Spring

## ๐ ํ๋ก์ ํธ ์ค๋ช
- ssg.com ํด๋ก ์ฝ๋ฉ ๋ฐฑ์๋ ์๋ฒ์๋๋ค.
- ํ๋ก์ ํธ ๊ธฐ๊ฐ : 2022-08-01 ~ 2022-09-08 (2๊ฐ์)
- ์ธ์ : FrontEnd 3๋ช, BackEnd 1๋ช
- Java 11
- Spring Boot 2.6.8
- Gradle 7.5.1 

## ๐ ํ๋ก์ ํธ ํน์ง
- Monolithic Architecture๊ธฐ๋ฐ์ BackEnd API Server
- Spring Security์ JWT๋ฅผ ํ์ฉํ์ฌ ๋ก๊ทธ์ธ ๋ฐ ์ธ์ฆ/์ธ๊ฐ ๊ธฐ๋ฅ ๊ตฌํ
- JPA ๋ฐ QueryDSL์ ํ์ฉ

## ๐ ์ธํ๋ฆฌ์ ์ด ์คํ ์์
- resources/application.yml -> DB์ค์  ๋ฐ ํ๊ฒฝ๋ณ์ ํ์ธ -> ํ๊ฒฝ๋ณ์ ๋ฑ๋ก
- Gradle -> Tasks -> other -> compileQuerydsl ์คํ
- ์ ํ๋ฆฌ์ผ์ด์ ์คํ

## ๐ Gradle ์คํ ์์
- resources/application.yml -> DB์ค์  ๋ฐ ํ๊ฒฝ๋ณ์ ํ์ธ -> ํ๊ฒฝ๋ณ์ ๋ฑ๋ก
- gradlew bootrun
