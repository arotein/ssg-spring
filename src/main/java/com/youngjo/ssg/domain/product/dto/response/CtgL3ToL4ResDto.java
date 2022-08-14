package com.youngjo.ssg.domain.product.dto.response;

import com.youngjo.ssg.domain.product.domain.CategoryL3;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CtgL3ToL4ResDto {
    private CtgL3Dto ctgL3;
    private List<CtgL4Dto> ctgL4List = new ArrayList<>();

    public CtgL3ToL4ResDto(CategoryL3 ctgL3) {
        this.ctgL3 = new CtgL3Dto(ctgL3.getId(), ctgL3.getName());
        this.ctgL4List = ctgL3.getCategoryL4List().stream()
                .map(ctg -> new CtgL4Dto(ctg.getId(), ctg.getName()))
                .collect(Collectors.toList());
    }

    @Getter
    @AllArgsConstructor
    class CtgL3Dto {
        private Long id;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    class CtgL4Dto {
        private Long id;
        private String name;
    }
}
