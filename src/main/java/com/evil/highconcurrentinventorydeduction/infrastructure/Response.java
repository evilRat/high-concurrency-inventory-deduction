package com.evil.highconcurrentinventorydeduction.infrastructure;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private String message;
    private Integer code;
    private T data;

    public Response(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
