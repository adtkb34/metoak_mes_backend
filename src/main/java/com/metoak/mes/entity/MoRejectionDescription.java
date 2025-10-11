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
@TableName("mo_rejection_description")
@ApiModel(value = "MoRejectionDescription对象", description = "")
public class MoRejectionDescription implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "description_id", type = IdType.AUTO)
    private Integer descriptionId;

    private Integer workstationId;

    private String description;

    private Boolean valid;

    public Integer getDescriptionId() {
        return descriptionId;
    }

    public void setDescriptionId(Integer descriptionId) {
        this.descriptionId = descriptionId;
    }

    public Integer getWorkstationId() {
        return workstationId;
    }

    public void setWorkstationId(Integer workstationId) {
        this.workstationId = workstationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "MoRejectionDescription{" +
            "descriptionId = " + descriptionId +
            ", workstationId = " + workstationId +
            ", description = " + description +
            ", valid = " + valid +
        "}";
    }
}
