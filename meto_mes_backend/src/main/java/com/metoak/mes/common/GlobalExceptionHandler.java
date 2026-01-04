package com.metoak.mes.common;

import com.metoak.mes.common.result.ResultCodeEnum;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultBean<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        StringBuilder errorMsg = new StringBuilder("参数校验失败：");
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMsg.append("[")
                    .append(error.getField())
                    .append(": ")
                    .append(error.getDefaultMessage())
                    .append("] ");
        });
        return ResultBean.fail(ResultCodeEnum.FORM_VERIFICATION_FAILED.getCode(), errorMsg.toString().trim());
    }

    @ExceptionHandler(MOException.class)
    public ResultBean<?> handleMOExcept(MOException ex) {
        return ResultBean.fail(ex.getCode(), ex.getMessage());
    }

}