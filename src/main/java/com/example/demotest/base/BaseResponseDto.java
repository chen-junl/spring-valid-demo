package com.example.demotest.base;

import lombok.Data;

/**
 * @author chenjunl
 * @description
 * @date 2023/7/28
 */
@Data
public class BaseResponseDto<T> {
    /**
     * 提示信息
     */
    private String message;
    /**
     * 内容
     */
    private T data;

    public BaseResponseDto() {
    }

    public BaseResponseDto(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public BaseResponseDto(String message) {
        this.message = message;
    }
}
