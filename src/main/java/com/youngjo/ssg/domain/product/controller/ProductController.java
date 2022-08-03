package com.youngjo.ssg.domain.product.controller;

import com.youngjo.ssg.domain.product.dto.response.SlideImgDto;
import com.youngjo.ssg.global.security.bean.ClientInfoLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ClientInfoLoader clientInfoLoader;

    @GetMapping("/slide/img")
    public List<SlideImgDto> imgs(HttpServletRequest request, HttpServletResponse response) {
        log.info("/slide/img 요청들어옴");
        log.info("method = {}", request.getMethod());
//        log.info("loginId = {}", dto.getLoginId());
//        log.info("password = {}", dto.getPassword());

        String[] urls = {
                "https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202208/2022080114085258428785450978_125.jpg&w=750&t=90391597d521e894b5ecdf583a578e1f06b2a46d",
                "https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202207/2022072913301745431834913283_620.jpg&w=750&t=1b1ed0795e074eba045ba768cadf532d6fc57a87",
                "https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202207/2022072913304369217802728780_525.jpg&w=750&t=5c8be47341b46bc16a8642b10f6972422a3c1632"
        };

        List<SlideImgDto> dtos = new ArrayList<>();
        for (int k = 0; k < 3; k++) {
            dtos.add(SlideImgDto.builder()
                    .id((long) k + 1)
                    .name("안농")
                    .url(urls[k])
                    .build());
        }
        return dtos;
    }

//    @PostMapping("/add")
//    public CommonResponse<String> signUp(@Validated @RequestBody) {
//        return new CommonResponse<String>()
//                .setData();
}
