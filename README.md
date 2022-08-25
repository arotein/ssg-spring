# SSG Project Made of Spring

## 🍜 프로젝트 설명
1. ssg.com 클론코딩 백엔드 서버입니다.
2. spring boot 2.6.8으로 작성되었습니다.

## 🍜 실행 순서
1. application.yml -> DB설정 확인
2. Gradle -> Tasks -> other -> compileQuerydsl 실행
3. 애플리케이션 실행

## 🍜 구현된 기능(패키지)
1. 회원가입(domain.user)
2. JWT를 이용한 로그인 및 인증(global.security)
3. Category 메뉴바(domain.product)
4. 상품 상세보기, 상품 목록보기(domain.product)
5. 상품 좋아요, 상품 정렬(domain.product)
6. 장바구니(domain.user)
7. 상품 검색(domain.search)
8. 배송지 관리(domain.user)
9. 상품 주문(domain.purchase) -> 예정

## 🍜 사용된 환경변수
1. ${SSG_DB_USERNAME}
2. ${SSG_DB_PASSWORD}
3. ${SSG_JWT_SIGNER}
4. ~~${SSL_PASSWORD}~~
5. ~~${OAUTH2_GOOGLE_ID}~~
6. ~~${OAUTH2_GOOGLE_SECRET}~~