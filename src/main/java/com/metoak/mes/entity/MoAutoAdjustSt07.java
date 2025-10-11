package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * AA调焦数据表
 * </p>
 *
 * @author kevin
 * @since 2025-06-25 20:41:32
 */
@TableName("mo_auto_adjust_st07")
@ApiModel(value = "MoAutoAdjustSt07对象", description = "AA调焦数据表")
public class MoAutoAdjustSt07 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("横梁SN")
    private String beamSn;

    @ApiModelProperty("左侧镜头或右侧镜头")
    private String side;

    @ApiModelProperty("收到数据的时间")
    private LocalDateTime addTime;

    @ApiModelProperty("中心MTF平均数值，实际测试值")
    private Double mtfCenterValue;

    @ApiModelProperty("规格下限")
    private Double mtfCenterLsl;

    @ApiModelProperty("规格上限")
    private Double mtfCenterUsl;

    @ApiModelProperty("左上MTF平均数值")
    private Double mtfLeftupValue;

    private Double mtfLeftupLsl;

    private Double mtfLeftupUsl;

    private Double mtfRightupValue;

    private Double mtfRightupLsl;

    private Double mtfRightupUsl;

    private Double mtfLeftdownValue;

    private Double mtfLeftdownLsl;

    private Double mtfLeftdownUsl;

    private Double mtfRightdownValue;

    private Double mtfRightdownLsl;

    private Double mtfRightdownUsl;

    private Double mtfCornerDiffValue;

    private Double mtfCornerDiffLsl;

    private Double mtfCornerDiffUsl;

    private String imagePath;

    @ApiModelProperty("松开夹爪检查，中心MTF平均数值，实际测试值")
    private Double mtfCenterValue2;

    @ApiModelProperty("规格下限")
    private Double mtfCenterLsl2;

    @ApiModelProperty("规格上限")
    private Double mtfCenterUsl2;

    private Double mtfLeftupValue2;

    private Double mtfLeftupLsl2;

    private Double mtfLeftupUsl2;

    private Double mtfRightupValue2;

    private Double mtfRightupLsl2;

    private Double mtfRightupUsl2;

    private Double mtfLeftdownValue2;

    private Double mtfLeftdownLsl2;

    private Double mtfLeftdownUsl2;

    private Double mtfRightdownValue2;

    private Double mtfRightdownLsl2;

    private Double mtfRightdownUsl2;

    private Double mtfCornerDiffValue2;

    private Double mtfCornerDiffLsl2;

    private Double mtfCornerDiffUsl2;

    private String imagePath2;

    private Long taskid;

    private Integer typeNum;

    private Double ocXOffset;

    private Double ocXOffsetUpperSpec;

    private Double ocYOffsetUpperSpec;

    private Double ocYOffset;

    private Double codXOffset;

    private Double codYOffset;

    private Double codXOffsetUpperSpec;

    private Double codYOffsetUpperSpec;

    private Double mtfRangeOffset;

    private Double mtfRangeOffsetUpperSpec;

    private Double mtfRangeOffsetLowerSpec;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeamSn() {
        return beamSn;
    }

    public void setBeamSn(String beamSn) {
        this.beamSn = beamSn;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public Double getMtfCenterValue() {
        return mtfCenterValue;
    }

    public void setMtfCenterValue(Double mtfCenterValue) {
        this.mtfCenterValue = mtfCenterValue;
    }

    public Double getMtfCenterLsl() {
        return mtfCenterLsl;
    }

    public void setMtfCenterLsl(Double mtfCenterLsl) {
        this.mtfCenterLsl = mtfCenterLsl;
    }

    public Double getMtfCenterUsl() {
        return mtfCenterUsl;
    }

    public void setMtfCenterUsl(Double mtfCenterUsl) {
        this.mtfCenterUsl = mtfCenterUsl;
    }

    public Double getMtfLeftupValue() {
        return mtfLeftupValue;
    }

    public void setMtfLeftupValue(Double mtfLeftupValue) {
        this.mtfLeftupValue = mtfLeftupValue;
    }

    public Double getMtfLeftupLsl() {
        return mtfLeftupLsl;
    }

    public void setMtfLeftupLsl(Double mtfLeftupLsl) {
        this.mtfLeftupLsl = mtfLeftupLsl;
    }

    public Double getMtfLeftupUsl() {
        return mtfLeftupUsl;
    }

    public void setMtfLeftupUsl(Double mtfLeftupUsl) {
        this.mtfLeftupUsl = mtfLeftupUsl;
    }

    public Double getMtfRightupValue() {
        return mtfRightupValue;
    }

    public void setMtfRightupValue(Double mtfRightupValue) {
        this.mtfRightupValue = mtfRightupValue;
    }

    public Double getMtfRightupLsl() {
        return mtfRightupLsl;
    }

    public void setMtfRightupLsl(Double mtfRightupLsl) {
        this.mtfRightupLsl = mtfRightupLsl;
    }

    public Double getMtfRightupUsl() {
        return mtfRightupUsl;
    }

    public void setMtfRightupUsl(Double mtfRightupUsl) {
        this.mtfRightupUsl = mtfRightupUsl;
    }

    public Double getMtfLeftdownValue() {
        return mtfLeftdownValue;
    }

    public void setMtfLeftdownValue(Double mtfLeftdownValue) {
        this.mtfLeftdownValue = mtfLeftdownValue;
    }

    public Double getMtfLeftdownLsl() {
        return mtfLeftdownLsl;
    }

    public void setMtfLeftdownLsl(Double mtfLeftdownLsl) {
        this.mtfLeftdownLsl = mtfLeftdownLsl;
    }

    public Double getMtfLeftdownUsl() {
        return mtfLeftdownUsl;
    }

    public void setMtfLeftdownUsl(Double mtfLeftdownUsl) {
        this.mtfLeftdownUsl = mtfLeftdownUsl;
    }

    public Double getMtfRightdownValue() {
        return mtfRightdownValue;
    }

    public void setMtfRightdownValue(Double mtfRightdownValue) {
        this.mtfRightdownValue = mtfRightdownValue;
    }

    public Double getMtfRightdownLsl() {
        return mtfRightdownLsl;
    }

    public void setMtfRightdownLsl(Double mtfRightdownLsl) {
        this.mtfRightdownLsl = mtfRightdownLsl;
    }

    public Double getMtfRightdownUsl() {
        return mtfRightdownUsl;
    }

    public void setMtfRightdownUsl(Double mtfRightdownUsl) {
        this.mtfRightdownUsl = mtfRightdownUsl;
    }

    public Double getMtfCornerDiffValue() {
        return mtfCornerDiffValue;
    }

    public void setMtfCornerDiffValue(Double mtfCornerDiffValue) {
        this.mtfCornerDiffValue = mtfCornerDiffValue;
    }

    public Double getMtfCornerDiffLsl() {
        return mtfCornerDiffLsl;
    }

    public void setMtfCornerDiffLsl(Double mtfCornerDiffLsl) {
        this.mtfCornerDiffLsl = mtfCornerDiffLsl;
    }

    public Double getMtfCornerDiffUsl() {
        return mtfCornerDiffUsl;
    }

    public void setMtfCornerDiffUsl(Double mtfCornerDiffUsl) {
        this.mtfCornerDiffUsl = mtfCornerDiffUsl;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Double getMtfCenterValue2() {
        return mtfCenterValue2;
    }

    public void setMtfCenterValue2(Double mtfCenterValue2) {
        this.mtfCenterValue2 = mtfCenterValue2;
    }

    public Double getMtfCenterLsl2() {
        return mtfCenterLsl2;
    }

    public void setMtfCenterLsl2(Double mtfCenterLsl2) {
        this.mtfCenterLsl2 = mtfCenterLsl2;
    }

    public Double getMtfCenterUsl2() {
        return mtfCenterUsl2;
    }

    public void setMtfCenterUsl2(Double mtfCenterUsl2) {
        this.mtfCenterUsl2 = mtfCenterUsl2;
    }

    public Double getMtfLeftupValue2() {
        return mtfLeftupValue2;
    }

    public void setMtfLeftupValue2(Double mtfLeftupValue2) {
        this.mtfLeftupValue2 = mtfLeftupValue2;
    }

    public Double getMtfLeftupLsl2() {
        return mtfLeftupLsl2;
    }

    public void setMtfLeftupLsl2(Double mtfLeftupLsl2) {
        this.mtfLeftupLsl2 = mtfLeftupLsl2;
    }

    public Double getMtfLeftupUsl2() {
        return mtfLeftupUsl2;
    }

    public void setMtfLeftupUsl2(Double mtfLeftupUsl2) {
        this.mtfLeftupUsl2 = mtfLeftupUsl2;
    }

    public Double getMtfRightupValue2() {
        return mtfRightupValue2;
    }

    public void setMtfRightupValue2(Double mtfRightupValue2) {
        this.mtfRightupValue2 = mtfRightupValue2;
    }

    public Double getMtfRightupLsl2() {
        return mtfRightupLsl2;
    }

    public void setMtfRightupLsl2(Double mtfRightupLsl2) {
        this.mtfRightupLsl2 = mtfRightupLsl2;
    }

    public Double getMtfRightupUsl2() {
        return mtfRightupUsl2;
    }

    public void setMtfRightupUsl2(Double mtfRightupUsl2) {
        this.mtfRightupUsl2 = mtfRightupUsl2;
    }

    public Double getMtfLeftdownValue2() {
        return mtfLeftdownValue2;
    }

    public void setMtfLeftdownValue2(Double mtfLeftdownValue2) {
        this.mtfLeftdownValue2 = mtfLeftdownValue2;
    }

    public Double getMtfLeftdownLsl2() {
        return mtfLeftdownLsl2;
    }

    public void setMtfLeftdownLsl2(Double mtfLeftdownLsl2) {
        this.mtfLeftdownLsl2 = mtfLeftdownLsl2;
    }

    public Double getMtfLeftdownUsl2() {
        return mtfLeftdownUsl2;
    }

    public void setMtfLeftdownUsl2(Double mtfLeftdownUsl2) {
        this.mtfLeftdownUsl2 = mtfLeftdownUsl2;
    }

    public Double getMtfRightdownValue2() {
        return mtfRightdownValue2;
    }

    public void setMtfRightdownValue2(Double mtfRightdownValue2) {
        this.mtfRightdownValue2 = mtfRightdownValue2;
    }

    public Double getMtfRightdownLsl2() {
        return mtfRightdownLsl2;
    }

    public void setMtfRightdownLsl2(Double mtfRightdownLsl2) {
        this.mtfRightdownLsl2 = mtfRightdownLsl2;
    }

    public Double getMtfRightdownUsl2() {
        return mtfRightdownUsl2;
    }

    public void setMtfRightdownUsl2(Double mtfRightdownUsl2) {
        this.mtfRightdownUsl2 = mtfRightdownUsl2;
    }

    public Double getMtfCornerDiffValue2() {
        return mtfCornerDiffValue2;
    }

    public void setMtfCornerDiffValue2(Double mtfCornerDiffValue2) {
        this.mtfCornerDiffValue2 = mtfCornerDiffValue2;
    }

    public Double getMtfCornerDiffLsl2() {
        return mtfCornerDiffLsl2;
    }

    public void setMtfCornerDiffLsl2(Double mtfCornerDiffLsl2) {
        this.mtfCornerDiffLsl2 = mtfCornerDiffLsl2;
    }

    public Double getMtfCornerDiffUsl2() {
        return mtfCornerDiffUsl2;
    }

    public void setMtfCornerDiffUsl2(Double mtfCornerDiffUsl2) {
        this.mtfCornerDiffUsl2 = mtfCornerDiffUsl2;
    }

    public String getImagePath2() {
        return imagePath2;
    }

    public void setImagePath2(String imagePath2) {
        this.imagePath2 = imagePath2;
    }

    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
    }

    public Integer getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(Integer typeNum) {
        this.typeNum = typeNum;
    }

    public Double getOcXOffset() {
        return ocXOffset;
    }

    public void setOcXOffset(Double ocXOffset) {
        this.ocXOffset = ocXOffset;
    }

    public Double getOcXOffsetUpperSpec() {
        return ocXOffsetUpperSpec;
    }

    public void setOcXOffsetUpperSpec(Double ocXOffsetUpperSpec) {
        this.ocXOffsetUpperSpec = ocXOffsetUpperSpec;
    }

    public Double getOcYOffsetUpperSpec() {
        return ocYOffsetUpperSpec;
    }

    public void setOcYOffsetUpperSpec(Double ocYOffsetUpperSpec) {
        this.ocYOffsetUpperSpec = ocYOffsetUpperSpec;
    }

    public Double getOcYOffset() {
        return ocYOffset;
    }

    public void setOcYOffset(Double ocYOffset) {
        this.ocYOffset = ocYOffset;
    }

    public Double getCodXOffset() {
        return codXOffset;
    }

    public void setCodXOffset(Double codXOffset) {
        this.codXOffset = codXOffset;
    }

    public Double getCodYOffset() {
        return codYOffset;
    }

    public void setCodYOffset(Double codYOffset) {
        this.codYOffset = codYOffset;
    }

    public Double getCodXOffsetUpperSpec() {
        return codXOffsetUpperSpec;
    }

    public void setCodXOffsetUpperSpec(Double codXOffsetUpperSpec) {
        this.codXOffsetUpperSpec = codXOffsetUpperSpec;
    }

    public Double getCodYOffsetUpperSpec() {
        return codYOffsetUpperSpec;
    }

    public void setCodYOffsetUpperSpec(Double codYOffsetUpperSpec) {
        this.codYOffsetUpperSpec = codYOffsetUpperSpec;
    }

    public Double getMtfRangeOffset() {
        return mtfRangeOffset;
    }

    public void setMtfRangeOffset(Double mtfRangeOffset) {
        this.mtfRangeOffset = mtfRangeOffset;
    }

    public Double getMtfRangeOffsetUpperSpec() {
        return mtfRangeOffsetUpperSpec;
    }

    public void setMtfRangeOffsetUpperSpec(Double mtfRangeOffsetUpperSpec) {
        this.mtfRangeOffsetUpperSpec = mtfRangeOffsetUpperSpec;
    }

    public Double getMtfRangeOffsetLowerSpec() {
        return mtfRangeOffsetLowerSpec;
    }

    public void setMtfRangeOffsetLowerSpec(Double mtfRangeOffsetLowerSpec) {
        this.mtfRangeOffsetLowerSpec = mtfRangeOffsetLowerSpec;
    }

    @Override
    public String toString() {
        return "MoAutoAdjustSt07{" +
            "id = " + id +
            ", beamSn = " + beamSn +
            ", side = " + side +
            ", addTime = " + addTime +
            ", mtfCenterValue = " + mtfCenterValue +
            ", mtfCenterLsl = " + mtfCenterLsl +
            ", mtfCenterUsl = " + mtfCenterUsl +
            ", mtfLeftupValue = " + mtfLeftupValue +
            ", mtfLeftupLsl = " + mtfLeftupLsl +
            ", mtfLeftupUsl = " + mtfLeftupUsl +
            ", mtfRightupValue = " + mtfRightupValue +
            ", mtfRightupLsl = " + mtfRightupLsl +
            ", mtfRightupUsl = " + mtfRightupUsl +
            ", mtfLeftdownValue = " + mtfLeftdownValue +
            ", mtfLeftdownLsl = " + mtfLeftdownLsl +
            ", mtfLeftdownUsl = " + mtfLeftdownUsl +
            ", mtfRightdownValue = " + mtfRightdownValue +
            ", mtfRightdownLsl = " + mtfRightdownLsl +
            ", mtfRightdownUsl = " + mtfRightdownUsl +
            ", mtfCornerDiffValue = " + mtfCornerDiffValue +
            ", mtfCornerDiffLsl = " + mtfCornerDiffLsl +
            ", mtfCornerDiffUsl = " + mtfCornerDiffUsl +
            ", imagePath = " + imagePath +
            ", mtfCenterValue2 = " + mtfCenterValue2 +
            ", mtfCenterLsl2 = " + mtfCenterLsl2 +
            ", mtfCenterUsl2 = " + mtfCenterUsl2 +
            ", mtfLeftupValue2 = " + mtfLeftupValue2 +
            ", mtfLeftupLsl2 = " + mtfLeftupLsl2 +
            ", mtfLeftupUsl2 = " + mtfLeftupUsl2 +
            ", mtfRightupValue2 = " + mtfRightupValue2 +
            ", mtfRightupLsl2 = " + mtfRightupLsl2 +
            ", mtfRightupUsl2 = " + mtfRightupUsl2 +
            ", mtfLeftdownValue2 = " + mtfLeftdownValue2 +
            ", mtfLeftdownLsl2 = " + mtfLeftdownLsl2 +
            ", mtfLeftdownUsl2 = " + mtfLeftdownUsl2 +
            ", mtfRightdownValue2 = " + mtfRightdownValue2 +
            ", mtfRightdownLsl2 = " + mtfRightdownLsl2 +
            ", mtfRightdownUsl2 = " + mtfRightdownUsl2 +
            ", mtfCornerDiffValue2 = " + mtfCornerDiffValue2 +
            ", mtfCornerDiffLsl2 = " + mtfCornerDiffLsl2 +
            ", mtfCornerDiffUsl2 = " + mtfCornerDiffUsl2 +
            ", imagePath2 = " + imagePath2 +
            ", taskid = " + taskid +
            ", typeNum = " + typeNum +
            ", ocXOffset = " + ocXOffset +
            ", ocXOffsetUpperSpec = " + ocXOffsetUpperSpec +
            ", ocYOffsetUpperSpec = " + ocYOffsetUpperSpec +
            ", ocYOffset = " + ocYOffset +
            ", codXOffset = " + codXOffset +
            ", codYOffset = " + codYOffset +
            ", codXOffsetUpperSpec = " + codXOffsetUpperSpec +
            ", codYOffsetUpperSpec = " + codYOffsetUpperSpec +
            ", mtfRangeOffset = " + mtfRangeOffset +
            ", mtfRangeOffsetUpperSpec = " + mtfRangeOffsetUpperSpec +
            ", mtfRangeOffsetLowerSpec = " + mtfRangeOffsetLowerSpec +
        "}";
    }
}
