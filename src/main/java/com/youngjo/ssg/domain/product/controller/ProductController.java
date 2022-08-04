package com.youngjo.ssg.domain.product.controller;

import com.youngjo.ssg.domain.product.dto.response.SlideImgDto;
import com.youngjo.ssg.global.security.bean.ClientInfoLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {
    private final ClientInfoLoader clientInfoLoader;

    @GetMapping("/slide/img")
    public List<SlideImgDto> imgs() {
        log.info("/slide/img 요청들어옴");

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

    @GetMapping("/category/img")
    public List<SlideImgDto> imgs2() {
        log.info("/slide/imgs2 요청들어옴");

        String[] urls = {
                "https://sui.ssgcdn.com/cmpt/banner/202207/2022072216460396994225724522_92.png",
                "https://sui.ssgcdn.com/cmpt/banner/202207/2022072216423296939917157991_956.png",
                "https://sui.ssgcdn.com/cmpt/banner/202207/2022072216431332432781237278_289.png",
                "https://sui.ssgcdn.com/cmpt/banner/202207/2022072216435453442063698206_435.png"
        };

        List<SlideImgDto> dtos = new ArrayList<>();
        System.out.println("urls.length = " + urls.length);
        for (int k = 0; k < urls.length; k++) {
            dtos.add(SlideImgDto.builder()
                    .id((long) k + 1)
                    .name(RandomStringUtils.randomAlphanumeric(11))
                    .url(urls[k])
                    .build());
        }
        return dtos;
    }

    @GetMapping("/happyLoungeItem")
    public List<SlideImgDto> imgs3() {
        log.info("/slide/imgs3 요청들어옴");

        String[] urls = {
                "https://sui.ssgcdn.com/cmpt/banner/202207/2022072216460396994225724522_92.png",
                "https://sui.ssgcdn.com/cmpt/banner/202207/2022072216460396994225724522_92.png",
                "https://sui.ssgcdn.com/cmpt/banner/202207/2022072216431332432781237278_289.png",
                "https://sui.ssgcdn.com/cmpt/banner/202207/2022072216435453442063698206_435.png"
        };

        List<SlideImgDto> dtos = new ArrayList<>();
        System.out.println("urls.length = " + urls.length);
        for (int k = 0; k < urls.length; k++) {
            dtos.add(SlideImgDto.builder()
                    .id((long) k + 1)
                    .name(RandomStringUtils.randomAlphanumeric(11))
                    .url(urls[k])
                    .build());
        }
        return dtos;
    }

    @GetMapping("/img4")
    public List<SlideImgDto> imgs4() {
        log.info("/slide/imgs4 요청들어옴");

        String[] urls = {
                "https://sui.ssgcdn.com/cmpt/banner/202207/2022072216460396994225724522_92.png",
                "https://sui.ssgcdn.com/cmpt/banner/202207/2022072216460396994225724522_92.png",
                "https://sui.ssgcdn.com/cmpt/banner/202207/2022072216431332432781237278_289.png",
                "https://sui.ssgcdn.com/cmpt/banner/202207/2022072216435453442063698206_435.png"
        };

        List<SlideImgDto> dtos = new ArrayList<>();
        System.out.println("urls.length = " + urls.length);
        for (int k = 0; k < urls.length; k++) {
            dtos.add(SlideImgDto.builder()
                    .id((long) k + 1)
                    .name(RandomStringUtils.randomAlphanumeric(11))
                    .url(urls[k])
                    .build());
        }
        return dtos;
    }
}
