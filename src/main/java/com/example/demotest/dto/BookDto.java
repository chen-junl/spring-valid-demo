package com.example.demotest.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author chenjunl
 * @description
 * @date 2023/7/28
 */
@Data
public class BookDto {
    /**
     * id
     */
    @NotNull(message = "书本id不能为空")
    private Integer id;
    /**
     * 标题
     */
    @NotEmpty(message = "书本标题不能为空")
    private String title;

}
