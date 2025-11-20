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
 * 
 * </p>
 *
 * @author kevin
 * @since 2025-08-11 15:34:00
 */
@TableName("vision_result")
@ApiModel(value = "VisionResult对象", description = "")
public class VisionResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private LocalDateTime datetime;

    private String sn;

    private Integer errorCode;

    private Integer dirtyCountLeft;

    private Integer dirtyCountRight;

    private Double clarityLeft;

    private Double clarityRight;

    private Double loMedium;

    private Double loMean;

    private Double loMax;

    private Double loMin;

    private Double loStddev;

    private Double colorCastRMeanLeft;

    private Double colorCastGMeanLeft;

    private Double colorCastBMeanLeft;

    private Double colorCastRMeanRight;

    private Double colorCastGMeanRight;

    private Double colorCastBMeanRight;

    private Double colorCastRStddevLeft;

    private Double colorCastGStddevLeft;

    private Double colorCastBStddevLeft;

    private Double colorCastRStddevRight;

    private Double colorCastGStddevRight;

    private Double colorCastBStddevRight;

    private Double codXLeft;

    private Double codYLeft;

    private Double codXRight;

    private Double codYRight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getDirtyCountLeft() {
        return dirtyCountLeft;
    }

    public void setDirtyCountLeft(Integer dirtyCountLeft) {
        this.dirtyCountLeft = dirtyCountLeft;
    }

    public Integer getDirtyCountRight() {
        return dirtyCountRight;
    }

    public void setDirtyCountRight(Integer dirtyCountRight) {
        this.dirtyCountRight = dirtyCountRight;
    }

    public Double getClarityLeft() {
        return clarityLeft;
    }

    public void setClarityLeft(Double clarityLeft) {
        this.clarityLeft = clarityLeft;
    }

    public Double getClarityRight() {
        return clarityRight;
    }

    public void setClarityRight(Double clarityRight) {
        this.clarityRight = clarityRight;
    }

    public Double getLoMedium() {
        return loMedium;
    }

    public void setLoMedium(Double loMedium) {
        this.loMedium = loMedium;
    }

    public Double getLoMean() {
        return loMean;
    }

    public void setLoMean(Double loMean) {
        this.loMean = loMean;
    }

    public Double getLoMax() {
        return loMax;
    }

    public void setLoMax(Double loMax) {
        this.loMax = loMax;
    }

    public Double getLoMin() {
        return loMin;
    }

    public void setLoMin(Double loMin) {
        this.loMin = loMin;
    }

    public Double getLoStddev() {
        return loStddev;
    }

    public void setLoStddev(Double loStddev) {
        this.loStddev = loStddev;
    }

    public Double getColorCastRMeanLeft() {
        return colorCastRMeanLeft;
    }

    public void setColorCastRMeanLeft(Double colorCastRMeanLeft) {
        this.colorCastRMeanLeft = colorCastRMeanLeft;
    }

    public Double getColorCastGMeanLeft() {
        return colorCastGMeanLeft;
    }

    public void setColorCastGMeanLeft(Double colorCastGMeanLeft) {
        this.colorCastGMeanLeft = colorCastGMeanLeft;
    }

    public Double getColorCastBMeanLeft() {
        return colorCastBMeanLeft;
    }

    public void setColorCastBMeanLeft(Double colorCastBMeanLeft) {
        this.colorCastBMeanLeft = colorCastBMeanLeft;
    }

    public Double getColorCastRMeanRight() {
        return colorCastRMeanRight;
    }

    public void setColorCastRMeanRight(Double colorCastRMeanRight) {
        this.colorCastRMeanRight = colorCastRMeanRight;
    }

    public Double getColorCastGMeanRight() {
        return colorCastGMeanRight;
    }

    public void setColorCastGMeanRight(Double colorCastGMeanRight) {
        this.colorCastGMeanRight = colorCastGMeanRight;
    }

    public Double getColorCastBMeanRight() {
        return colorCastBMeanRight;
    }

    public void setColorCastBMeanRight(Double colorCastBMeanRight) {
        this.colorCastBMeanRight = colorCastBMeanRight;
    }

    public Double getColorCastRStddevLeft() {
        return colorCastRStddevLeft;
    }

    public void setColorCastRStddevLeft(Double colorCastRStddevLeft) {
        this.colorCastRStddevLeft = colorCastRStddevLeft;
    }

    public Double getColorCastGStddevLeft() {
        return colorCastGStddevLeft;
    }

    public void setColorCastGStddevLeft(Double colorCastGStddevLeft) {
        this.colorCastGStddevLeft = colorCastGStddevLeft;
    }

    public Double getColorCastBStddevLeft() {
        return colorCastBStddevLeft;
    }

    public void setColorCastBStddevLeft(Double colorCastBStddevLeft) {
        this.colorCastBStddevLeft = colorCastBStddevLeft;
    }

    public Double getColorCastRStddevRight() {
        return colorCastRStddevRight;
    }

    public void setColorCastRStddevRight(Double colorCastRStddevRight) {
        this.colorCastRStddevRight = colorCastRStddevRight;
    }

    public Double getColorCastGStddevRight() {
        return colorCastGStddevRight;
    }

    public void setColorCastGStddevRight(Double colorCastGStddevRight) {
        this.colorCastGStddevRight = colorCastGStddevRight;
    }

    public Double getColorCastBStddevRight() {
        return colorCastBStddevRight;
    }

    public void setColorCastBStddevRight(Double colorCastBStddevRight) {
        this.colorCastBStddevRight = colorCastBStddevRight;
    }

    public Double getCodXLeft() {
        return codXLeft;
    }

    public void setCodXLeft(Double codXLeft) {
        this.codXLeft = codXLeft;
    }

    public Double getCodYLeft() {
        return codYLeft;
    }

    public void setCodYLeft(Double codYLeft) {
        this.codYLeft = codYLeft;
    }

    public Double getCodXRight() {
        return codXRight;
    }

    public void setCodXRight(Double codXRight) {
        this.codXRight = codXRight;
    }

    public Double getCodYRight() {
        return codYRight;
    }

    public void setCodYRight(Double codYRight) {
        this.codYRight = codYRight;
    }

    @Override
    public String toString() {
        return "VisionResult{" +
            "id = " + id +
            ", datetime = " + datetime +
            ", sn = " + sn +
            ", errorCode = " + errorCode +
            ", dirtyCountLeft = " + dirtyCountLeft +
            ", dirtyCountRight = " + dirtyCountRight +
            ", clarityLeft = " + clarityLeft +
            ", clarityRight = " + clarityRight +
            ", loMedium = " + loMedium +
            ", loMean = " + loMean +
            ", loMax = " + loMax +
            ", loMin = " + loMin +
            ", loStddev = " + loStddev +
            ", colorCastRMeanLeft = " + colorCastRMeanLeft +
            ", colorCastGMeanLeft = " + colorCastGMeanLeft +
            ", colorCastBMeanLeft = " + colorCastBMeanLeft +
            ", colorCastRMeanRight = " + colorCastRMeanRight +
            ", colorCastGMeanRight = " + colorCastGMeanRight +
            ", colorCastBMeanRight = " + colorCastBMeanRight +
            ", colorCastRStddevLeft = " + colorCastRStddevLeft +
            ", colorCastGStddevLeft = " + colorCastGStddevLeft +
            ", colorCastBStddevLeft = " + colorCastBStddevLeft +
            ", colorCastRStddevRight = " + colorCastRStddevRight +
            ", colorCastGStddevRight = " + colorCastGStddevRight +
            ", colorCastBStddevRight = " + colorCastBStddevRight +
            ", codXLeft = " + codXLeft +
            ", codYLeft = " + codYLeft +
            ", codXRight = " + codXRight +
            ", codYRight = " + codYRight +
        "}";
    }
}
