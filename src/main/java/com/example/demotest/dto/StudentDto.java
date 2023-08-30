package com.example.demotest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenjunl
 * @Description 表实体类
 * @date 2022-12-08 10:37:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 修改者
     */
    private String modifier;
    /**
     * 排序
     */
    private Float sortIndex;
    /**
     * 标题
     */
    private String title;
    /**
     * 书本
     */
    private BookDto book;

}
