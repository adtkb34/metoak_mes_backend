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
@TableName("mo_beam_material_code")
@ApiModel(value = "MoBeamMaterialCode对象", description = "")
public class MoBeamMaterialCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String projectName;

    private String materialCode;

    private String materialLetter;

    private String productType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialLetter() {
        return materialLetter;
    }

    public void setMaterialLetter(String materialLetter) {
        this.materialLetter = materialLetter;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "MoBeamMaterialCode{" +
            "id = " + id +
            ", projectName = " + projectName +
            ", materialCode = " + materialCode +
            ", materialLetter = " + materialLetter +
            ", productType = " + productType +
        "}";
    }
}
