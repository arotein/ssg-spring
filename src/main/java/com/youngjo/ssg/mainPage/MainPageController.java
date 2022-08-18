package com.youngjo.ssg.mainPage;

import com.youngjo.ssg.mainPage.dto.response.HotBrandReqDto;
import com.youngjo.ssg.mainPage.dto.response.NewServiceBottomReqDto;
import com.youngjo.ssg.mainPage.dto.response.NewServiceTopReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mainPage")
@Slf4j
public class MainPageController { // 메인 페이지에서 사용되는 정보들
    @GetMapping("/newService/top")
    public List<NewServiceTopReqDto> newServiceTop() {
        log.info("GET /api/mainPage/newService/top request");
        List<NewServiceTopReqDto> dtoList = new ArrayList<>();
        dtoList.add(new NewServiceTopReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022072217195349135856376585_644.png", "2022 추석 명절", "미리 준비하는 올 추석 명절 선물"));
        dtoList.add(new NewServiceTopReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022072217190693512854525285_422.jpg", "MONDAY MOON", "럭셔리 뷰티부터 다양한 뷰티 컨텐츠까지!"));
        dtoList.add(new NewServiceTopReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022072510290015700334005033_491.png", "SSG Luxury", "명품의 신세계를 만나다"));
        return dtoList;
    }

    @GetMapping("/newService/bottom")
    public List<NewServiceBottomReqDto> newServiceBottom() {
        log.info("GET /api/mainPage/newService/bottom request");
        List<NewServiceBottomReqDto> dtoList = new ArrayList<>();
        dtoList.add(new NewServiceBottomReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022072018095879442344852334_902.png", "명절"));
        dtoList.add(new NewServiceBottomReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022072018085132408328907832_886.png", "해피라운지"));
        dtoList.add(new NewServiceBottomReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022072018090271094493915549_213.png", "공식 브랜드관"));
        dtoList.add(new NewServiceBottomReqDto("https://sui.ssgcdn.com/cmpt/banner/202206/2022062117422173428272685827_495.png", "백화점"));
        dtoList.add(new NewServiceBottomReqDto("https://sui.ssgcdn.com/cmpt/banner/202208/2022081713240558915556806555_740.png", "SSG럭셔리"));
        dtoList.add(new NewServiceBottomReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022071814342769215729184572_167.png", "스마일클럽"));
        dtoList.add(new NewServiceBottomReqDto("https://sui.ssgcdn.com/cmpt/banner/202208/2022080210585625059006668900_230.png", "이벤트"));
        dtoList.add(new NewServiceBottomReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022072018091578124885200588_919.png", "선물하기"));
        dtoList.add(new NewServiceBottomReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022072220331105257687978768_380.png", "베스트"));
        dtoList.add(new NewServiceBottomReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022072018093759774844126584_698.png", "신상품"));
        return dtoList;
    }

    @GetMapping("/hotBrand")
    public List<HotBrandReqDto> hotBrand() {
        log.info("GET /api/mainPage/hotBrand request");
        List<HotBrandReqDto> dtoList = new ArrayList<>();
        dtoList.add(new HotBrandReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022072217285171385923771692_663.png", "샤넬"));
        dtoList.add(new HotBrandReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022072217290217490624463162_656.png", "페라가모"));
        dtoList.add(new HotBrandReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022071408544663020208606020_479.jpg", "몽블랑"));
        dtoList.add(new HotBrandReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022072217291500626364175636_449.png", "랄프로렌"));
        dtoList.add(new HotBrandReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022072217292235974324881532_737.png", "나이키"));
        dtoList.add(new HotBrandReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022072217293106766790060779_109.png", "GAP"));
        dtoList.add(new HotBrandReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022072217293825100958393095_837.png", "구찌"));
        dtoList.add(new HotBrandReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022072217295055984869592586_907.png", "버버리"));
        dtoList.add(new HotBrandReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022072217295919357862288786_757.png", "마시모두띠"));
        dtoList.add(new HotBrandReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022072217300774619162616916_799.png", "휴고보스"));
        dtoList.add(new HotBrandReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022072217301614134541677454_258.png", "룰루레몬"));
        dtoList.add(new HotBrandReqDto("https://sui.ssgcdn.com/cmpt/banner/202207/2022072217302515229659906965_991.png", "삼성"));
        return dtoList;
    }

}
