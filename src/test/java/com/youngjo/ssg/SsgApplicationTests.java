package com.youngjo.ssg;

import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@SpringBootTest
class SsgApplicationTests {

    //    @Test
    void contextLoads() {
        HMACSigner secretKey = HMACSigner.newSHA256Signer("youngjo");

        JWT rawJwt = new JWT()
                .setIssuer("youngjo")
                .setSubject("user") // 로그인 한 유저
                .setExpiration(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).plusHours(2))
                .addClaim("hi", "hello");

        JWT rawJwt2 = new JWT()
                .setIssuer("youngjo")
                .setSubject("user") // 로그인 한 유저
                .setExpiration(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).plusSeconds(0))
                .addClaim("hi", "hello");

        String encodedJwt = JWT.getEncoder().encode(rawJwt, secretKey);
        String encodedJwt2 = JWT.getEncoder().encode(rawJwt2, secretKey);
        // ㅡㅡㅡㅡㅡㅡㅡㅡ여기까지 인코딩 아래부턴 디코딩ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

        Verifier verifier = HMACVerifier.newVerifier("youngjo");

        JWT decodedJwt = JWT.getDecoder().decode(encodedJwt, verifier);
        JWT decodedJwt2 = JWT.getDecoder().decode(encodedJwt2, verifier); // 만료된 토큰이면 io.fusionauth.jwt.JWTExpiredException 발생

        System.out.println("decodedJwt = " + decodedJwt);
        System.out.println("만료됨? = " + decodedJwt.isExpired());
        System.out.println("2는 만료됨? = " + decodedJwt2.isExpired());
    }

    @Test
    void test() {
//        JSONPObject
    }
}

// 5e57e4b5e4ea9
// 6000200799
