package com.metoak.mes.common;

import com.metoak.mes.enums.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class MOException extends RuntimeException {
    //异常状态码
    private Integer code;
    /**
     * 通过状态码和错误消息创建异常对象
     * @param message
     * @param code
     */
    public MOException(String message, Integer code) {
        super(message);
        this.code = code;
    }
    /**
     * 根据响应结果枚举对象创建异常对象
     * @param resultCodeEnum
     */
    public MOException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getName());
        this.code = resultCodeEnum.getCode();
    }

    public MOException(ResultCodeEnum resultCodeEnum, String message) {
        super(resultCodeEnum.getName() + message);
        this.code = resultCodeEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "LeaseException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}