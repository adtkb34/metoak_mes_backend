package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author kevin
 * @since 2025-06-21 14:50:22
 */
@Data
@TableName("mo_device_status")
@ApiModel(value = "MoDeviceStatus对象", description = "")
public class MoDeviceStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String productMaterialNo;

    private String deviceNo;

    private String others;

    private Integer productionCount;

    private Integer defectCount;

    private Integer uph;

    private Float oee;

    private Integer status;

    private LocalDateTime addTime;
    @Override
    public String toString() {
        return "MoDeviceStatus{" +
            "id = " + id +
            ", productMaterialNo = " + productMaterialNo +
            ", deviceNo = " + deviceNo +
            ", addTime = " + addTime +
        "}";
    }
}
