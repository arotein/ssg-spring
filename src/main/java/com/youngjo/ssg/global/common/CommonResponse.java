package com.youngjo.ssg.global.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommonResponse<T> {
    private Boolean success = true;
    private T data;
    private String errorMessage;

    public CommonResponse<T> setSuccess(Boolean result) {
        this.success = result;
        return this;
    }

    public CommonResponse<T> setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public CommonResponse<T> setData(T data) {
        this.data = data;
        return this;
    }
}
