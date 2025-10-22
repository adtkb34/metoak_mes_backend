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
@TableName("mo_uv_dispensing")
@ApiModel(value = "UvDispensing对象", description = "")
public class UvDispensing implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String beamSn;

    @FieldCode(no = "001", type = "val", label = "胶宽")
    @ApiModelProperty("胶宽")
    private Float glueWidth;

    @FieldCode(no = "001", type = "usl", label = "胶宽上限规格值")
    @ApiModelProperty("胶宽上限规格值")
    private Float glueWidthUsl;

    @FieldCode(no = "001", type = "lsl", label = "胶宽下限规格值")
    @ApiModelProperty("胶宽下限规格值")
    private Float glueWidthLsl;

    @FieldCode(no = "003", type = "val", label = "圆心偏差")
    @ApiModelProperty("圆心偏差")
    private Float circleCenterOffset;

    @FieldCode(no = "003", type = "usl", label = "圆心偏差上限规格值")
    @ApiModelProperty("圆心偏差上限规格值")
    private Float circleCenterOffsetUsl;

    @FieldCode(no = "005", type = "val", label = "图片路径")
    @ApiModelProperty("图片路径")
    private String imagePath;

    @FieldCode(no = "006", type = "val", label = "胶重")
    @ApiModelProperty("胶重")
    private Float checkGlueWeight;

    /* =========== 胶宽最小值 =========== */
    @FieldCode(no = "007", type = "val", label = "胶宽最小值")
    private Float glueWidthMin;                // 胶宽最小值

    /* =========== 胶宽最大值 =========== */
    @FieldCode(no = "008", type = "val", label = "胶宽最大值")
    private Float glueWidthMax;                // 胶宽最大值

    @FieldCode(no = "009", type = "val", label = "胶高")
    private Float glueHeight;                // 胶高

    private String position;

    private LocalDateTime addTime;

    private Integer errorCode;

    private String ngReason;

    private Integer stationNum;

    private String side;

    private Long moProcessStepProductionResultId;

    @Override
    public String toString() {
        return "UvDispensing{" +
            "id = " + id +
            ", beamSn = " + beamSn +
            ", glueWidth = " + glueWidth +
            ", glueWidthUpperSpec = " + glueWidthLsl +
            ", circleCenterOffset = " + circleCenterOffset +
            ", circleCenterOffsetUpperSpec = " + circleCenterOffsetUsl +
            ", imgPath = " + imagePath +
            ", addTime = " + addTime +
            ", errorCode = " + errorCode +
            ", ngReason = " + ngReason +
            ", stationNum = " + stationNum +
        "}";
    }
}
