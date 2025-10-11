package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author kevin
 * @since 2025-06-17 18:02:42
 */
@TableName("mo_error_desc")
@ApiModel(value = "MoErrorDesc对象", description = "")
public class MoErrorDesc implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("工序名称")
    private String stage;

    private Integer posIndex;

    @ApiModelProperty("错误码")
    private Integer errorCode;

    @ApiModelProperty("描述")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Integer getPosIndex() {
        return posIndex;
    }

    public void setPosIndex(Integer posIndex) {
        this.posIndex = posIndex;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MoErrorDesc{" +
            "id = " + id +
            ", stage = " + stage +
            ", posIndex = " + posIndex +
            ", errorCode = " + errorCode +
            ", description = " + description +
        "}";
    }
}
