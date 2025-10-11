package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.metoak.mes.common.annotate.FieldCode;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author kevin
 * @since 2025-06-25 20:41:32
 */
@Data
@TableName("mo_lens_cap_fastening")
@ApiModel(value = "MoLensCapFastening对象", description = "")
public class MoLensCapFastening implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String batchNo;

    @FieldCode(no = "000", type = "val", name = "锁附高度")
    private Float screwHeight;

    @FieldCode(no = "000", type = "usl")
    private Float screwHeightUsl;

    @FieldCode(no = "000", type = "lsl")
    private Float screwHeightLsl;

    @FieldCode(no = "003", type = "val", name = "锁附扭力")
    private Float screwTorque;

    @FieldCode(no = "003", type = "usl")
    private Float screwTorqueUsl;

    @FieldCode(no = "003", type = "lsl")
    private Float screwTorqueLsl;

    @FieldCode(no = "006", type = "val", name = "锁附压力")
    private Float screwPressure;

    @FieldCode(no = "006", type = "usl")
    private Float screwPressureUsl;

    @FieldCode(no = "006", type = "lsl")
    private Float screwPressureLsl;

    @FieldCode(no = "006", type = "spec")
    private Float screwPressureSpec;

    private String operator;

    private String ngReason;

    private Integer errorCode;

    private LocalDateTime addTime;

    private Integer stationNum;

    private Long moProcessStepProductionResultId;

    @Override
    public String toString() {
        return "MoLensCapFastening{" +
            "id = " + id +
            ", batchNo = " + batchNo +
            ", screwHeight = " + screwHeight +
            ", screwHeightUsl = " + screwHeightUsl +
            ", screwHeightLsl = " + screwHeightLsl +
            ", screwTorque = " + screwTorque +
            ", screwTorqueUsl = " + screwTorqueUsl +
            ", screwTorqueLsl = " + screwTorqueLsl +
            ", screwPressure = " + screwPressure +
            ", screwPressureUsl = " + screwPressureUsl +
            ", screwPressureLsl = " + screwPressureLsl +
            ", ngReason = " + ngReason +
            ", addTime = " + addTime +
            ", stationNum = " + stationNum +
        "}";
    }
}
