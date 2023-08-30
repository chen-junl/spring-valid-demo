package com.example.demotest.service;

import cn.hutool.core.collection.CollUtil;
import com.example.demotest.dto.StudentValidDto;
import com.example.demotest.dto.StudentValidatedDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author chenjunl
 * @description
 * @date 2023/7/26
 */
@Service
@Slf4j
public class StudentValidService {



    public StudentValidDto findById(Integer id) {
        StudentValidDto studentDto = new StudentValidDto();
        studentDto.setId(id);
        studentDto.setTitle("test_53090f53f448");
        studentDto.setEmailList(CollUtil.newArrayList("test_e7e3a855833b", "test_769e6268ba9b"));
        return studentDto;
    }

    public boolean insert(StudentValidDto dto) {
        log.info("新增内容,数据:{}", dto);
        return true;
    }

    public boolean put(Integer id, StudentValidDto dto) {
        log.info("修改内容,id:{},数据:{}", id, dto);
        return true;
    }

    public boolean deleteById(Integer id) {
        log.info("删除内容,id:{}", id);
        return true;
    }

}
