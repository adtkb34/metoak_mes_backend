package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author kevin
 * @since 2025-09-15 17:42:42
 */
@Data
@TableName("mo_step_attr_keys")
@ApiModel(value = "MoStepAttrKeys对象", description = "")
public class MoStepAttrKeys implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("工序编号")
    private String stepTypeNo;

    @ApiModelProperty("属性键")
    private String attrKey;

    @ApiModelProperty("属性编号")
    private String attrNo;

    @Override
    public String toString() {
        return "MoStepAttrKeys{" +
            "stepTypeNo = " + stepTypeNo +
            ", attrKey = " + attrKey +
            ", attrNo = " + attrNo +
        "}";
    }
}
