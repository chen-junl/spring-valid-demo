package com.example.demotest.service;

import com.example.demotest.dto.StudentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author chenjunl
 * @description
 * @date 2023/7/26
 */
@Service
@Slf4j
public class StudentService {


    public boolean insert(StudentDto dto) {
        log.info("新增内容,数据:{}", dto);
        return true;
    }

    public boolean put(Integer id, StudentDto dto) {
        log.info("修改内容,id:{},数据:{}", id, dto);
        return true;
    }

    public boolean deleteById(Integer id) {
        log.info("删除内容,id:{}", id);
        return true;
    }
}
