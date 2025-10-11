package com.metoak.mes.common.util;


import com.metoak.mes.common.MOException;
import com.metoak.mes.enums.ResultCodeEnum;
import jakarta.validation.ConstraintViolation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Component
public class CheckUtil {
    private static final Logger logger = LoggerFactory.getLogger(CheckUtil.class);

    @Autowired
    private static MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource source) {
        messageSource = source; // 通过 setter 注入静态字段
    }

    public static void check(boolean condition, String msg, ResultCodeEnum esultCodeEnum, Object... args) {
        if (!condition) {
            fail(msg, esultCodeEnum, args);
        }
    }

    public static void notNull(Object obj, String msg, Object... args) {
        if (obj == null) {
            fail(msg, ResultCodeEnum.NULL, args);
        }
    }

    public static void notEmpty(Collection<?> obj, String msg, Object... args) {
        if (obj == null || obj.isEmpty()) {
            fail(msg, ResultCodeEnum.EMPTY, args);
        }
    }

//    public static void notBlank(String obj, String msg, Object... args) {
//        if (obj == null || obj.isBlank()) {
//            fail(msg, ResultCodeEnum.BLANK, args);
//        }
//    }

//    public static void listNotBlank(List<String> objs, String msg, Object... args) {
//        objs.forEach(item -> {
//            notBlank(item, msg, args);
//        });
//    }

    private static void fail(String msg, ResultCodeEnum esultCodeEnum, Object... args) {
        Integer code = esultCodeEnum.getCode();
        logger.error("Code: {}, Msg: {}", code, msg);
        throw new MOException(msg, code);
    }

    public static <T> String getViolationResult(Set<ConstraintViolation<T>> violations) {
        if (!violations.isEmpty()) {
            // 如果校验失败，抛出自定义异常或返回错误信息
            StringBuilder errorMessage = new StringBuilder("校验失败：");
            for (ConstraintViolation<T> violation : violations) {
                errorMessage.append(violation.getPropertyPath()).append(" ").append(violation.getMessage()).append(";");
            }
            return errorMessage.toString();
        }
        return "校验成功";
    }
}