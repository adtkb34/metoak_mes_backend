package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.metoak.mes.common.annotate.FieldCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 生产结果记录表
 * </p>
 *
 * @author kevin
 * @since 2024-11-16 14:52:00
 */
@TableName("mo_s316_final_check")
@ApiModel(value = "MoS316FinalCheck对象", description = "生产结果记录表")
public class MoS316FinalCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("【过站表】记录关联ID。通用字段在过站表里面：数据库表单-过站信息")
    private Long moProcessStepProductionResultId;

    @ApiModelProperty("相机序列号")
    private String sn;

    @ApiModelProperty("开始时间")
    @FieldCode(no = "000", type = "val")
    private LocalDateTime startTime;

    @FieldCode(no = "001", type = "val")
    private LocalDateTime endTime;

    @FieldCode(no = "002", type = "val")
    private Integer errorCode;

    @FieldCode(no = "003", type = "val")
    private Boolean isOqc;

    @FieldCode(no = "004", type = "val")
    private Integer productType;

    @FieldCode(no = "005", type = "val")
    private Boolean isSigned;

    @FieldCode(no = "006", type = "val")
    private String versionMcu;

    @FieldCode(no = "007", type = "val")
    private Float imuTx;

    @FieldCode(no = "008", type = "val")
    private Float imuTy;

    @FieldCode(no = "009", type = "val")
    private Float imuTz;

    @FieldCode(no = "010", type = "val")
    private Float imuQangleDeg;

    @FieldCode(no = "011", type = "val")
    private Float distance1Error;

    @FieldCode(no = "012", type = "val")
    private Float distance1Density;

    @FieldCode(no = "013", type = "val")
    private Float distance2Error;

    @FieldCode(no = "014", type = "val")
    private Float distance2Density;

    @FieldCode(no = "015", type = "val")
    private String materialRoot;

    @Override
    public String toString() {
        return "MoS316FinalCheck{" +
                "id=" + id +
                ", moProcessStepProductionResultId=" + moProcessStepProductionResultId +
                ", sn='" + sn + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", errorCode=" + errorCode +
                ", isOqc=" + isOqc +
                ", productType=" + productType +
                ", isSigned=" + isSigned +
                ", versionMcu='" + versionMcu + '\'' +
                ", imuTx=" + imuTx +
                ", imuTy=" + imuTy +
                ", imuTz=" + imuTz +
                ", imuQangleDeg=" + imuQangleDeg +
                ", distance1Error=" + distance1Error +
                ", distance1Density=" + distance1Density +
                ", distance2Error=" + distance2Error +
                ", distance2Density=" + distance2Density +
                ", materialRoot='" + materialRoot + '\'' +
                '}';
    }
}
