# SSG Project Made of Spring

## 🍜 프로젝트 설명
- ssg.com 클론코딩 백엔드 서버입니다.
- 프로젝트 기간 : 2022-08-01 ~ 2022-09-08
- Java 11
- Spring Boot 2.6.8
- Gradle 7.5.1 

## 🍜 프로젝트 특징
- Monolithic Architecture기반의 BackEnd API Server
- Spring Security와 JWT를 활용하여 로그인 및 인증/인가 기능 구현
- JPA 및 QueryDSL을 활용

## 🍜 인텔리제이 실행 순서
- resources/application.yml -> DB설정 및 환경변수 확인 -> 환경변수 등록
- Gradle -> Tasks -> other -> compileQuerydsl 실행
- 애플리케이션 실행

## 🍜 Gradle 실행 순서
- resources/application.yml -> DB설정 및 환경변수 확인 -> 환경변수 등록
- gradlew bootrun
