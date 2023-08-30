package com.example.demotest.controller;

import com.example.demotest.dto.StudentValidatedDto;
import com.example.demotest.dto.StudentValidatedGroupDto;
import com.example.demotest.group.InsertGroup;
import com.example.demotest.group.UpdateGroup;
import com.example.demotest.service.StudentValidatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * @author chenjunl
 * @description
 * @date 2023/7/26
 */
@RestController
//注意:使用@RequestParam 或者@PathVariable 的方式传递参数,必须在类上添加@Validated注解
@Validated
@RequestMapping(value = "/student/test/validated")
public class StudentWebValidatedController {

    @Autowired
    private StudentValidatedService studentService;

    /**
     * get请求url参数或者 form表单参数,必须在class上添加@Validated才能生效
     *
     * @param id
     * @return
     */
    @GetMapping
    public StudentValidatedDto findById(@Min(value = 1, message = "id不能小于1") @RequestParam Integer id) {
        return studentService.findById(id);
    }

    /**
     * get请求对象
     *
     * @param dto
     * @return
     */
    @GetMapping(value = "query")
    public List<StudentValidatedDto> query(@Validated StudentValidatedDto dto) {
        return studentService.query(dto);
    }

    /**
     * body参数
     *
     * @param dto
     * @return
     */
    @PostMapping
    public boolean insert(@Validated @RequestBody StudentValidatedDto dto) {
        return studentService.insert(dto);
    }

    /**
     * url占位符参数 body参数
     *
     * @param id
     * @param dto
     * @return
     */
    @PutMapping(value = "{id:\\d+}")
    public boolean put(@Min(value = 1, message = "id不能小于2") @PathVariable Integer id,
                       @Validated @RequestBody StudentValidatedDto dto) {
        return studentService.put(id, dto);
    }

    /**
     * url占位符参数,必须在class上添加@Validated才能生效
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "{id:\\d+}")
    public boolean deleteById(@Min(value = 1, message = "id不能小于2") @PathVariable Integer id) {
        return studentService.deleteById(id);
    }


    /**
     * 使用分组,并且分组有继承Default默认分组
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "group")
    public boolean insertGroup(@Validated(value = {InsertGroup.class}) @RequestBody StudentValidatedGroupDto dto) {
        return studentService.insertGroup(dto);
    }

    /**
     * 使用分组,并且分组有继承Default默认分组,只会验证bean中有UpdateGroup的字段,其余字段不会验证
     *
     * @param id
     * @param dto
     * @return
     */
    @PutMapping(value = "group/{id:\\d+}")
    public boolean putGroup(@Min(value = 1, message = "id不能小于2") @PathVariable Integer id,
                            @Validated(value = {UpdateGroup.class}) @RequestBody StudentValidatedGroupDto dto) {
        return studentService.putGroup(id, dto);
    }

    /**
     * bean中有分组,但是验证是没有使用分组,添加分组的字段不会进行验证
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "group/all")
    public boolean insertGroupAll(@Validated @RequestBody StudentValidatedGroupDto dto) {
        return studentService.insertGroup(dto);
    }

}
