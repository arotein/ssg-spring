package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.CategoryL2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class CtgL2ToL3ResDto {
    private CtgL2Dto ctgL2;
    private List<CtgL3Dto> ctgL3List = new ArrayList<>();

    public CtgL2ToL3ResDto(CategoryL2 ctgL2) {
        this.ctgL2 = new CtgL2Dto(ctgL2.getId(), ctgL2.getName());
        this.ctgL3List = ctgL2.getCategoryL3List().stream()
                .map(ctg -> new CtgL3Dto(ctg.getId(), ctg.getName()))
                .collect(Collectors.toList());
    }

    @Getter
    @AllArgsConstructor
    class CtgL2Dto {
        private Long id;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    class CtgL3Dto {
        private Long id;
        private String name;
    }
}
