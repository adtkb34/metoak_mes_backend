package com.metoak.mes.params.vo;

import lombok.Data;
import java.util.Map;

@Data
public class ParamsNode {

    /**
     * 参数集 / 参数明细 ID
     */
    private String id;

    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数描述
     */
    private String desc;

    /**
     * 版本号，如 1.0.5
     */
    private String version;

    /**
     * 动态参数内容
     */
    private String detail;
}
