package com.example.demotest.service;

import cn.hutool.core.collection.CollUtil;
import com.example.demotest.dto.StudentValidatedDto;
import com.example.demotest.dto.StudentValidatedGroupDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenjunl
 * @description
 * @date 2023/7/26
 */
@Service
@Slf4j
public class StudentValidatedService {

    public StudentValidatedDto findById(Integer id) {
        StudentValidatedDto studentDto = new StudentValidatedDto();
        studentDto.setId(id);
        studentDto.setTitle("test_53090f53f448");
        studentDto.setEmailList(CollUtil.newArrayList("test_e7e3a855833b", "test_769e6268ba9b"));
        return studentDto;
    }

    public List<StudentValidatedDto> query(StudentValidatedDto dto) {
        StudentValidatedDto studentDto = new StudentValidatedDto();
        studentDto.setId(dto.getId());
        studentDto.setTitle(dto.getTitle());
        studentDto.setEmailList(dto.getEmailList());
        return CollUtil.newArrayList(studentDto);
    }

    public boolean insert(StudentValidatedDto dto) {
        log.info("新增内容,数据:{}", dto);
        return true;
    }

    public boolean put(Integer id, StudentValidatedDto dto) {
        log.info("修改内容,id:{},数据:{}", id, dto);
        return true;
    }

    public boolean deleteById(Integer id) {
        log.info("删除内容,id:{}", id);
        return true;
    }

    public boolean insertGroup(StudentValidatedGroupDto dto) {
        log.info("新增内容,数据:{}", dto);
        return true;
    }

    public boolean putGroup(Integer id, StudentValidatedGroupDto dto) {
        log.info("修改内容,id:{},数据:{}", id, dto);
        return true;
    }
}
