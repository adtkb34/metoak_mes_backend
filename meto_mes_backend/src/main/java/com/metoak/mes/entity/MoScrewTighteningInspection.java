package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.metoak.mes.common.annotate.FieldCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 锁付检测数据
 * </p>
 *
 * @author kevin
 * @since 2025-07-04 16:08:02
 */
@TableName("mo_screw_tightening_inspection")
@ApiModel(value = "MoScrewTighteningInspection对象", description = "锁付检测数据")
public class MoScrewTighteningInspection implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @FieldCode(no = "000", type = "val", label = "扭力 (N·m)")
    @ApiModelProperty("扭力 (N·m)")
    private BigDecimal torque;

    @FieldCode(no = "000", type = "usl", label = "扭力上限 (N·m)")
    @ApiModelProperty("扭力 (N·m)")
    private BigDecimal torqueUsl;

    @FieldCode(no = "000", type = "lsl", label = "扭力下限 (N·m)")
    @ApiModelProperty("扭力 (N·m)")
    private BigDecimal torqueLsl;

    @FieldCode(no = "001", type = "val", label = "角度 (°)")
    @ApiModelProperty("角度 (°)")
    private BigDecimal angle;

    @FieldCode(no = "001", type = "usl", label = "角度上限 (°)")
    @ApiModelProperty("角度 (°)")
    private BigDecimal angleUsl;

    @FieldCode(no = "001", type = "lsl", label = "角度下限 (°)")
    @ApiModelProperty("角度 (°)")
    private BigDecimal angleLsl;

    @FieldCode(no = "002", type = "val", label = "用时")
    @ApiModelProperty("用时")
    private BigDecimal duration;

    @FieldCode(no = "002", type = "usl", label = "用时上限")
    @ApiModelProperty("用时")
    private BigDecimal durationUsl;

    @FieldCode(no = "002", type = "lsl", label = "用时下限")
    @ApiModelProperty("用时")
    private BigDecimal durationLsl;

    @FieldCode(no = "003", type = "val", label = "锁付高度 (mm)")
    @ApiModelProperty("锁付高度 (mm)")
    private BigDecimal screwHeight;

    @FieldCode(no = "003", type = "usl", label = "锁付高度上限 (mm)")
    @ApiModelProperty("锁付高度 (mm)")
    private BigDecimal screwHeightUsl;

    private Long moProcessStepProductionResultId;

    private Integer errorCode;

    private Integer stationNum;

    private String ngReason;

    private LocalDateTime addTime;

    private String beamSn;

    private String position;

    @Override
    public String toString() {
        return "MoScrewTighteningInspection{" +
                "id=" + id +
                ", torque=" + torque +
                ", torqueUsl=" + torqueUsl +
                ", torqueLsl=" + torqueLsl +
                ", angle=" + angle +
                ", angleUsl=" + angleUsl +
                ", angleLsl=" + angleLsl +
                ", screwHeight=" + screwHeight +
                ", screwHeightUsl=" + screwHeightUsl +
                ", moProcessStepProductionResultId=" + moProcessStepProductionResultId +
                ", errorCode=" + errorCode +
                ", stationNum=" + stationNum +
                ", ngReason='" + ngReason + '\'' +
                ", addTime=" + addTime +
                ", beamSn='" + beamSn + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
