package com.youngjo.ssg.domain.product.controller;

import com.youngjo.ssg.domain.product.dto.response.SlideImgDto;
import com.youngjo.ssg.global.security.bean.ClientInfoLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
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

        String[] urls = {
                "https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202208/2022080114085258428785450978_125.jpg&w=750&t=90391597d521e894b5ecdf583a578e1f06b2a46d",
                "https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202207/2022072913301745431834913283_620.jpg&w=750&t=1b1ed0795e074eba045ba768cadf532d6fc57a87",
                "https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202207/2022072913304369217802728780_525.jpg&w=750&t=5c8be47341b46bc16a8642b10f6972422a3c1632",
                "https://sitem.ssgcdn.com/26/48/06/item/1000410064826_i1_232.jpg", "https://sitem.ssgcdn.com/31/06/38/item/1000442380631_i1_232.jpg", "https://sitem.ssgcdn.com/28/71/90/item/1000340907128_i1_232.jpg", "https://sitem.ssgcdn.com/69/07/91/item/1000444910769_i1_232.jpg", "https://sitem.ssgcdn.com/63/58/68/item/1000438685863_i1_232.jpg", "https://sitem.ssgcdn.com/30/91/00/item/1000441009130_i1_232.jpg", "https://sitem.ssgcdn.com/26/66/87/item/1000440876626_i1_232.jpg", "https://sitem.ssgcdn.com/20/30/78/item/1000439783020_i1_232.jpg", "https://sitem.ssgcdn.com/99/01/38/item/1000442380199_i1_232.jpg", "https://sitem.ssgcdn.com/75/05/53/item/1000043530575_i1_232.jpg", "https://sitem.ssgcdn.com/12/92/52/item/1000375529212_i1_232.jpg", "https://sitem.ssgcdn.com/25/01/38/item/1000442380125_i1_232.jpg", "https://sitem.ssgcdn.com/40/19/60/item/1000403601940_i1_232.jpg", "https://sitem.ssgcdn.com/20/47/48/item/1000434484720_i1_232.jpg", "https://sitem.ssgcdn.com/70/86/76/item/1000435768670_i1_232.jpg", "https://sitem.ssgcdn.com/79/87/76/item/1000435768779_i1_232.jpg", "https://sitem.ssgcdn.com/08/66/50/item/1000434506608_i1_232.jpg", "https://sitem.ssgcdn.com/36/15/20/item/1000410201536_i1_232.jpg", "https://sitem.ssgcdn.com/57/05/38/item/1000442380557_i1_232.jpg", "https://sitem.ssgcdn.com/36/71/47/item/1000414477136_i1_232.jpg", "https://sitem.ssgcdn.com/16/61/68/item/1000438686116_i1_232.jpg", "https://sitem.ssgcdn.com/46/45/87/item/1000352874546_i1_232.jpg", "https://sitem.ssgcdn.com/41/13/83/item/1000206831341_i1_232.jpg", "https://sitem.ssgcdn.com/67/07/49/item/1000375490767_i1_232.jpg", "https://sitem.ssgcdn.com/21/84/79/item/1000349798421_i1_232.jpg", "https://sitem.ssgcdn.com/47/44/47/item/1000051474447_i1_232.jpg", "https://sitem.ssgcdn.com/28/23/23/item/1000050232328_i1_232.jpg", "https://sitem.ssgcdn.com/42/01/67/item/1000414670142_i1_232.jpg", "https://sitem.ssgcdn.com/95/43/01/item/1000422014395_i1_232.jpg", "https://sitem.ssgcdn.com/54/97/37/item/1000442379754_i1_232.jpg", "https://sitem.ssgcdn.com/64/07/78/item/1000439780764_i1_232.jpg", "https://sitem.ssgcdn.com/64/59/10/item/1000441105964_i1_232.jpg", "https://sitem.ssgcdn.com/11/85/13/item/1000305138511_i1_232.jpg", "https://sitem.ssgcdn.com/71/33/21/item/1000099213371_i1_232.jpg", "https://sitem.ssgcdn.com/52/26/35/item/1000326352652_i1_232.jpg", "https://sitem.ssgcdn.com/53/46/28/item/1000063284653_i1_232.jpg", "https://sitem.ssgcdn.com/68/47/33/item/1000425334768_i1_232.jpg", "https://sitem.ssgcdn.com/22/07/38/item/1000442380722_i1_232.jpg", "https://sitem.ssgcdn.com/84/28/04/item/1000353042884_i1_232.jpg", "https://sitem.ssgcdn.com/10/36/49/item/1000438493610_i1_232.jpg", "https://sitem.ssgcdn.com/38/45/87/item/1000414874538_i1_232.jpg", "https://sitem.ssgcdn.com/24/00/38/item/1000442380024_i1_232.jpg", "https://sitem.ssgcdn.com/42/74/76/item/1000405767442_i1_232.jpg", "https://sitem.ssgcdn.com/81/77/52/item/1000062527781_i1_232.jpg", "https://sitem.ssgcdn.com/89/60/51/item/1000379516089_i1_232.jpg", "https://sitem.ssgcdn.com/86/89/11/item/1000066118986_i1_232.jpg", "https://sitem.ssgcdn.com/82/70/99/item/1000318997082_i1_232.jpg", "https://sitem.ssgcdn.com/74/02/38/item/1000442380274_i1_232.jpg", "https://sitem.ssgcdn.com/10/71/99/item/1000318997110_i1_232.jpg", "https://sitem.ssgcdn.com/22/54/11/item/1000052115422_i1_232.jpg", "https://sitem.ssgcdn.com/60/40/76/item/1000378764060_i1_232.jpg", "https://sitem.ssgcdn.com/68/97/28/item/1000401289768_i1_232.jpg", "https://sitem.ssgcdn.com/42/85/53/item/1000219538542_i1_232.jpg", "https://sitem.ssgcdn.com/59/41/26/item/1000258264159_i1_232.jpg", "https://sitem.ssgcdn.com/30/48/04/item/1000426044830_i1_232.jpg", "https://sitem.ssgcdn.com/93/04/01/item/1000328010493_i1_232.jpg", "https://sitem.ssgcdn.com/06/31/43/item/1000279433106_i1_232.jpg", "https://sitem.ssgcdn.com/50/89/13/item/1000305138950_i1_232.jpg", "https://sitem.ssgcdn.com/80/28/46/item/1000361462880_i1_232.jpg", "https://sui.ssgcdn.com/ui/m_ssg/img/common/b.gif"
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

    @GetMapping("/category/img")
    public List<SlideImgDto> imgs2(HttpServletRequest request) {
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

    @GetMapping("/HappyLoungeItem/img")
    public List<SlideImgDto> imgs3(HttpServletRequest request) {
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
    public List<SlideImgDto> imgs4(HttpServletRequest request) {
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
