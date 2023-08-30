package com.example.demotest.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author chenjunl
 * @Description 表实体类
 * @date 2022-12-08 10:37:11
 */
@Data
public class StudentValidDto {
    /**
     * 主键
     */
    @NotNull(message = "学生id不能为空")
    private Integer id;
    /**
     * 创建者
     */
    @NotNull(message = "学生创建者不能为空")
    private String creator;
    /**
     * 修改者
     */
    @NotEmpty(message = "学生修改者不能为空")
    private String modifier;
    /**
     * 排序
     */
    @Max(value = 100, message = "学生排序不能超过100")
    @NotNull(message = "学生排序不能为空")
    private Float sortIndex;
    /**
     * 标题
     */
    @NotEmpty(message = "学生名称不能为空")
    private String title;
    /**
     * 邮箱地址集合
     */
    @Size(min = 1, max = 5, message = "学生邮箱只能1-5个")
    @NotNull(message = "学生邮箱不能为空")
    private List<String> emailList;
    /**
     * 钱包余额
     */
    @Min(value = 1, message = "学生钱包余额不能小于1")
    @Max(value = 1, message = "学生钱包余额不能大于1000")
    @NotNull(message = "学生钱包余额不能为空")
    private BigDecimal walletBalance;
    /**
     * 书本
     */
    @Valid
    @NotNull(message = "书本不能为空")
    private BookDto book;
}
