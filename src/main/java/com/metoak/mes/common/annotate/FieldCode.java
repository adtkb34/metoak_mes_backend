package com.metoak.mes.common.annotate;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldCode {

    /**
     * 编号，如 "002"
     */
    String no();

    /**
     * 数据类型：val、lsl、usl、spec
     */
    String type();

    /**
     * 字段中文名
     */
    String name() default "";

    /**
     * 字段展示名称。若未设置则回退到 {@link #name()}。
     */
    String label() default "";

    /**
     * 静态位置编号。如果 {@link #hasPosition()} 为 {@code true}，则忽略该值。
     */
    int position() default Integer.MIN_VALUE;

    /**
     * 是否从实体字段（通常为 position 字段）中获取位置编号。
     */
    boolean hasPosition() default false;

    /**
     * 同一编号下的序号，用于前端排序或标记。
     */
    int index() default Integer.MIN_VALUE;
}
