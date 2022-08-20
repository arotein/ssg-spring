package com.youngjo.ssg.domain.search.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
public class SearchQueryResDto {
    @NotBlank
    private String query;
}
