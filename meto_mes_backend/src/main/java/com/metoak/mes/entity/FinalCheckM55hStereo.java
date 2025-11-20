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
 * 距离测量结果记录
 * </p>
 *
 * @author kevin
 * @since 2025-11-18 19:08:39
 */
@Data
@TableName("final_check_m55h_stereo")
@ApiModel(value = "FinalCheckM55hStereo对象", description = "距离测量结果记录")
public class FinalCheckM55hStereo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("关联生产结果ID")
    private Long moProcessStepProductionResultId;

    @ApiModelProperty("相机序列号")
    private String cameraSn;

    @ApiModelProperty("检测开始时间")
    @FieldCode(no = "000", type = "val")
    private LocalDateTime startTime;

    @ApiModelProperty("检测结束时间")
    @FieldCode(no = "001", type = "val")
    private LocalDateTime endTime;

    @ApiModelProperty("整体测试结果 0 NG / 1 OK")
    @FieldCode(no = "002", type = "val")
    private Byte testResult;

    @ApiModelProperty("CAN0 测试结果")
    @FieldCode(no = "003", type = "val")
    private Byte testResultCan0;

    @ApiModelProperty("CAN1 测试结果")
    @FieldCode(no = "004", type = "val")
    private Byte testResultCan1;

    @ApiModelProperty("LED 测试结果")
    @FieldCode(no = "005", type = "val")
    private Byte testResultLed;

    @ApiModelProperty("AHD 测试结果")
    @FieldCode(no = "006", type = "val")
    private Byte testResultAhd;

    @ApiModelProperty("视差稠密度测试结果")
    @FieldCode(no = "007", type = "val")
    private Byte testResultDisparityDensity;

    @ApiModelProperty("视差精度测试结果")
    @FieldCode(no = "008", type = "val")
    private Byte testResultAccuracy;

    @ApiModelProperty("固件版本测试结果")
    @FieldCode(no = "009", type = "val")
    private Byte testResultFirmwareVersion;

    @ApiModelProperty("视差图保存路径")
    @FieldCode(no = "010", type = "val")
    private String dispImagePath;

    @ApiModelProperty("视差稠密度结果值")
    @FieldCode(no = "011", type = "val")
    private Double density;

    @ApiModelProperty("稠密度目标值")
    @FieldCode(no = "012", type = "val")
    private Double densityRef;

    @ApiModelProperty("真值距离 (GT)")
    @FieldCode(no = "013", type = "val")
    private Double distanceGt;

    @ApiModelProperty("测量距离值")
    @FieldCode(no = "014", type = "val")
    private Double distanceMeasure;

    @ApiModelProperty("精度指标阈值")
    @FieldCode(no = "015", type = "val")
    private Double accuracyRef;

    @ApiModelProperty("固件版本基准值")
    @FieldCode(no = "016", type = "val")
    private String multifirmwareVersionRef;

    @ApiModelProperty("实际固件版本")
    @FieldCode(no = "017", type = "val")
    private String multifirmwareVersion;

    @ApiModelProperty("错误码")
    @FieldCode(no = "018", type = "val")
    private Integer errorCode;

    @ApiModelProperty("写入的 ECU 固件版本")
    @FieldCode(no = "019", type = "val")
    private String ecuFirmwareWritenVersion;

    @ApiModelProperty("写入的 ECU 生产时间")
    @FieldCode(no = "020", type = "val")
    private String ecuProductionTimeWriten;

    @Override
    public String toString() {
        return "FinalCheckM55hStereo{" +
            "id = " + id +
            ", moProcessStepProductionResultId = " + moProcessStepProductionResultId +
            ", cameraSn = " + cameraSn +
            ", startTime = " + startTime +
            ", endTime = " + endTime +
            ", testResult = " + testResult +
            ", testResultCan0 = " + testResultCan0 +
            ", testResultCan1 = " + testResultCan1 +
            ", testResultLed = " + testResultLed +
            ", testResultAhd = " + testResultAhd +
            ", testResultDisparityDensity = " + testResultDisparityDensity +
            ", testResultAccuracy = " + testResultAccuracy +
            ", testResultFirmwareVersion = " + testResultFirmwareVersion +
            ", dispImagePath = " + dispImagePath +
            ", density = " + density +
            ", densityRef = " + densityRef +
            ", distanceGt = " + distanceGt +
            ", distanceMeasure = " + distanceMeasure +
            ", accuracyRef = " + accuracyRef +
            ", multifirmwareVersionRef = " + multifirmwareVersionRef +
            ", multifirmwareVersion = " + multifirmwareVersion +
            ", errorCode = " + errorCode +
            ", ecuFirmwareWritenVersion = " + ecuFirmwareWritenVersion +
            ", ecuProductionTimeWriten = " + ecuProductionTimeWriten +
        "}";
    }
}
