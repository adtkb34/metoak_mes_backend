package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.metoak.mes.common.annotate.FieldCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author kevin
 * @since 2025-06-20 15:49:09
 */
@Data
@TableName("mo_dirty_check")
@ApiModel(value = "MoDirtyCheck对象", description = "")
public class MoDirtyCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @FieldCode(no = "001", type = "val")
    @ApiModelProperty("污点大小上限规格值")
    private Float stainSizeUsl;

    @FieldCode(no = "002", type = "val")
    @ApiModelProperty("污点数上限规格值")
    private Float stainCountUsl;

    @FieldCode(no = "003", type = "val")
    @ApiModelProperty("污点描述")
    private String stains;

    @FieldCode(no = "004", type = "val")
    @ApiModelProperty("图片路径")
    private String imagePath;

    @FieldCode(no = "005", type = "spec")
    @ApiModelProperty("Plasma设定功率")
    private Float plasmaPowerSpec;

    @FieldCode(no = "006", type = "spec")
    @ApiModelProperty("镜头夹爪设定值")
    private Float lensGripperSpec;

    private Integer errorCode;

    private String beamSn;

    private String ngReason;

    private LocalDateTime addTime;

    private Integer stationNum;

    private String side;

    private String position;

    private Long moProcessStepProductionResultId;

    @Override
    public String toString() {
        return "MoDirtyCheck{" +
            "id = " + id +
            ", stainSizeUpperSpec = " + stainSizeUsl +
            ", stainCountUpperSpec = " + stainCountUsl +
            ", stains = " + stains +
            ", imgPath = " + imagePath +
            ", errorCode = " + errorCode +
            ", beamSn = " + beamSn +
            ", ngReason = " + ngReason +
            ", addTime = " + addTime +
            ", stationNum = " + stationNum +
        "}";
    }
}
