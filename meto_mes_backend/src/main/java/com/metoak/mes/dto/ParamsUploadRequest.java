package com.metoak.mes.dto;

import lombok.Data;

/**
 * 上传参数集请求
 */
@Data
public class ParamsUploadRequest {

    /** 参数集类型 */
    private Integer type;

    /** 参数集名称 */
    private String name;

    /** 版本描述 */
    private String description;

    /** 参数内容（JSON 字符串） */
    private String params;

    /** 上传用户 */
    private String username;
}
