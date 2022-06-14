package com.evil.highconcurrentinventorydeduction.infrastructure;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Response<T> {
    private String message;
    private Integer code;
    private T data;

    public Response(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
