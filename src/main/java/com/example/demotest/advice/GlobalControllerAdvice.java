package com.example.demotest.advice;

import com.example.demotest.base.BaseResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenjunl
 * @description
 * @date 2023/7/28
 */
@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

    /**
     * 参数校验异常 @RequestBody中的参数校验异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponseDto<Object> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errorMessage = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        log.warn("参数校验异常", ex);
        return new BaseResponseDto<>("参数校验错误", errorMessage);
    }

    /**
     * 参数校验异常 @RequestParam和@PathVariable中的参数校验异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponseDto<Object> constraintViolationException(ConstraintViolationException ex) {
        List<String> errorMessage = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        log.warn("参数校验异常", ex);
        return new BaseResponseDto<>("参数校验错误", errorMessage);
    }

    /**
     * 参数校验异常 get请求中对象的形式 或者form表单形式 的参数校验异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponseDto<Object> bindException(BindException ex) {
        List<String> errorMessage = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        log.warn("参数校验异常", ex);
        return new BaseResponseDto<>("参数校验错误", errorMessage);
    }

}
