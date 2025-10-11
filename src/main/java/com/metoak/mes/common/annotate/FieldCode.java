package com.metoak.mes.common.annotate;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldCode {
    String no();    // 编号，如 "002"
    String type();  // val、lsl、usl、spec
    String name() default "";
}