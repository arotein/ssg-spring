package com.youngjo.ssg.global.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommonResponse<T> {
    private Boolean success = true;
    private T data;
    private String errorMessage;

    public CommonResponse<T> setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
