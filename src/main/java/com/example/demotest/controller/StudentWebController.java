package com.example.demotest.controller;

import com.example.demotest.dto.StudentDto;
import com.example.demotest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenjunl
 * @description
 * @date 2023/7/26
 */
@RestController
@RequestMapping(value = "/student/test")
public class StudentWebController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public boolean insert(@RequestBody StudentDto dto) {
        return studentService.insert(dto);
    }

    @PutMapping(value = "{id:\\d+}")
    public boolean put(@PathVariable Integer id, @RequestBody StudentDto dto) {
        return studentService.put(id, dto);
    }

    @DeleteMapping(value = "{id:\\d+}")
    public boolean deleteById(@PathVariable Integer id) {
        return studentService.deleteById(id);
    }
}
