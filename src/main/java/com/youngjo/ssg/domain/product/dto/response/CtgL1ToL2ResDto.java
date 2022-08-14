package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.CategoryL1;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class CtgL1ToL2ResDto {
    private CtgL1Dto ctgL1;
    private List<CtgL2Dto> ctgL2List = new ArrayList<>();

    public CtgL1ToL2ResDto(CategoryL1 ctgL1, List<CtgMainResDto.CtgL2Dto> ctgL2List) {
        this.ctgL1 = new CtgL1Dto(ctgL1.getId(), ctgL1.getName());
        this.ctgL2List = ctgL2List.stream()
                .map(ctg -> new CtgL2Dto(ctg.getId(), ctg.getName()))
                .collect(Collectors.toList());
    }

    @Getter
    @AllArgsConstructor
    class CtgL1Dto {
        private Long id;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    class CtgL2Dto {
        private Long id;
        private String name;
    }
}
