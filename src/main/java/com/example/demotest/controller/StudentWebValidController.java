package com.example.demotest.controller;

import com.example.demotest.dto.StudentValidDto;
import com.example.demotest.dto.StudentValidatedDto;
import com.example.demotest.service.StudentValidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author chenjunl
 * @description
 * @date 2023/7/26
 */
@RestController
@RequestMapping(value = "/student/test/valid")
public class StudentWebValidController {

    @Autowired
    private StudentValidService studentService;

    @GetMapping

    public StudentValidDto findById(@RequestParam @Min(value = 1, message = "不能小于1") Integer id) {
        return studentService.findById(id);
    }

    @PostMapping
    public boolean insert(@RequestBody @Valid StudentValidDto dto) {
        return studentService.insert(dto);
    }

    @PutMapping(value = "{id:\\d+}")
    public boolean put(@PathVariable Integer id, @Valid @RequestBody StudentValidDto dto) {
        return studentService.put(id, dto);
    }

    @DeleteMapping(value = "{id:\\d+}")
    public boolean deleteById(@PathVariable Integer id) {
        return studentService.deleteById(id);
    }
}
