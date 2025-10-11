package com.metoak.mes.common;

import com.metoak.mes.common.result.ResultCodeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    //返回码
    private Integer code;

    //返回消息
    private String message;

    //返回数据
    private T data;

    public ResultBean() {
    }

    public ResultBean(T data) {
        this.data = data;
    }

    private static <T> ResultBean<T> build(T data) {
        ResultBean<T> result = new ResultBean<>();
        if (data != null)
            result.setData(data);
        return result;
    }

    public static <T> ResultBean<T> build(T body, ResultCodeEnum resultCodeEnum) {
        ResultBean<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }


    public static <T> ResultBean<T> ok(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static <T> ResultBean<T> ok() {
        return ResultBean.ok(null);
    }

    public static <T> ResultBean<T> fail() {
        return build(null, ResultCodeEnum.FAIL);
    }

    public static <T> ResultBean<T> fail(Integer code, String message) {
        ResultBean<T> result = build(null);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}