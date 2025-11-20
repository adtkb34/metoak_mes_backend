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
@TableName("mo_stereo_precheck")
@ApiModel(value = "MoStereoPrecheck对象", description = "")
public class MoStereoPrecheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private LocalDateTime datetime;

    private String sn;

    private Integer errorCode;

    private Boolean isDirtyDetectEnabled;

    private Integer dirtyCountLeft;

    private Integer dirtyCountRight;

    private Double dirtyStandardCof;

    private Boolean isClarityDetectEnabled;

    private Double clarityLeft;

    private Double clarityRight;

    private Double clarityStandardVal;

    private Double clarityStandardMdiff;

    private Boolean isLoDetectEnabled;

    private Double loMedium;

    private Double loMean;

    private Double loMax;

    private Double loMin;

    private Double loStddev;

    private Double loStandardVal;

    private Double xOffsetMedium;

    private Double xOffsetMean;

    private Double xOffsetMax;

    private Double xOffsetMin;

    private Double xOffsetStddev;

    private Double xOffsetStandardVal;

    private Double xOffsetStandardTolerance;

    private Boolean isColorCastDetectEnabled;

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

    private Double colorCastStandardRCenter;

    private Double colorCastStandardRTolerance;

    private Double colorCastStandardGCenter;

    private Double colorCastStandardGTolerance;

    private Double colorCastStandardBCenter;

    private Double colorCastStandardBTolerance;

    private Boolean isCodDetectEnabled;

    private Double codXLeft;

    private Double codYLeft;

    private Double codXRight;

    private Double codYRight;

    private Double codStandardXCenter;

    private Double codStandardXTolerance;

    private Double codStandardYCenter;

    private Double codStandardYTolerance;

    private Boolean isImuValid;

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

    public Boolean getIsDirtyDetectEnabled() {
        return isDirtyDetectEnabled;
    }

    public void setIsDirtyDetectEnabled(Boolean isDirtyDetectEnabled) {
        this.isDirtyDetectEnabled = isDirtyDetectEnabled;
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

    public Double getDirtyStandardCof() {
        return dirtyStandardCof;
    }

    public void setDirtyStandardCof(Double dirtyStandardCof) {
        this.dirtyStandardCof = dirtyStandardCof;
    }

    public Boolean getIsClarityDetectEnabled() {
        return isClarityDetectEnabled;
    }

    public void setIsClarityDetectEnabled(Boolean isClarityDetectEnabled) {
        this.isClarityDetectEnabled = isClarityDetectEnabled;
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

    public Double getClarityStandardVal() {
        return clarityStandardVal;
    }

    public void setClarityStandardVal(Double clarityStandardVal) {
        this.clarityStandardVal = clarityStandardVal;
    }

    public Double getClarityStandardMdiff() {
        return clarityStandardMdiff;
    }

    public void setClarityStandardMdiff(Double clarityStandardMdiff) {
        this.clarityStandardMdiff = clarityStandardMdiff;
    }

    public Boolean getIsLoDetectEnabled() {
        return isLoDetectEnabled;
    }

    public void setIsLoDetectEnabled(Boolean isLoDetectEnabled) {
        this.isLoDetectEnabled = isLoDetectEnabled;
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

    public Double getLoStandardVal() {
        return loStandardVal;
    }

    public void setLoStandardVal(Double loStandardVal) {
        this.loStandardVal = loStandardVal;
    }

    public Double getxOffsetMedium() {
        return xOffsetMedium;
    }

    public void setxOffsetMedium(Double xOffsetMedium) {
        this.xOffsetMedium = xOffsetMedium;
    }

    public Double getxOffsetMean() {
        return xOffsetMean;
    }

    public void setxOffsetMean(Double xOffsetMean) {
        this.xOffsetMean = xOffsetMean;
    }

    public Double getxOffsetMax() {
        return xOffsetMax;
    }

    public void setxOffsetMax(Double xOffsetMax) {
        this.xOffsetMax = xOffsetMax;
    }

    public Double getxOffsetMin() {
        return xOffsetMin;
    }

    public void setxOffsetMin(Double xOffsetMin) {
        this.xOffsetMin = xOffsetMin;
    }

    public Double getxOffsetStddev() {
        return xOffsetStddev;
    }

    public void setxOffsetStddev(Double xOffsetStddev) {
        this.xOffsetStddev = xOffsetStddev;
    }

    public Double getxOffsetStandardVal() {
        return xOffsetStandardVal;
    }

    public void setxOffsetStandardVal(Double xOffsetStandardVal) {
        this.xOffsetStandardVal = xOffsetStandardVal;
    }

    public Double getxOffsetStandardTolerance() {
        return xOffsetStandardTolerance;
    }

    public void setxOffsetStandardTolerance(Double xOffsetStandardTolerance) {
        this.xOffsetStandardTolerance = xOffsetStandardTolerance;
    }

    public Boolean getIsColorCastDetectEnabled() {
        return isColorCastDetectEnabled;
    }

    public void setIsColorCastDetectEnabled(Boolean isColorCastDetectEnabled) {
        this.isColorCastDetectEnabled = isColorCastDetectEnabled;
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

    public Double getColorCastStandardRCenter() {
        return colorCastStandardRCenter;
    }

    public void setColorCastStandardRCenter(Double colorCastStandardRCenter) {
        this.colorCastStandardRCenter = colorCastStandardRCenter;
    }

    public Double getColorCastStandardRTolerance() {
        return colorCastStandardRTolerance;
    }

    public void setColorCastStandardRTolerance(Double colorCastStandardRTolerance) {
        this.colorCastStandardRTolerance = colorCastStandardRTolerance;
    }

    public Double getColorCastStandardGCenter() {
        return colorCastStandardGCenter;
    }

    public void setColorCastStandardGCenter(Double colorCastStandardGCenter) {
        this.colorCastStandardGCenter = colorCastStandardGCenter;
    }

    public Double getColorCastStandardGTolerance() {
        return colorCastStandardGTolerance;
    }

    public void setColorCastStandardGTolerance(Double colorCastStandardGTolerance) {
        this.colorCastStandardGTolerance = colorCastStandardGTolerance;
    }

    public Double getColorCastStandardBCenter() {
        return colorCastStandardBCenter;
    }

    public void setColorCastStandardBCenter(Double colorCastStandardBCenter) {
        this.colorCastStandardBCenter = colorCastStandardBCenter;
    }

    public Double getColorCastStandardBTolerance() {
        return colorCastStandardBTolerance;
    }

    public void setColorCastStandardBTolerance(Double colorCastStandardBTolerance) {
        this.colorCastStandardBTolerance = colorCastStandardBTolerance;
    }

    public Boolean getIsCodDetectEnabled() {
        return isCodDetectEnabled;
    }

    public void setIsCodDetectEnabled(Boolean isCodDetectEnabled) {
        this.isCodDetectEnabled = isCodDetectEnabled;
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

    public Double getCodStandardXCenter() {
        return codStandardXCenter;
    }

    public void setCodStandardXCenter(Double codStandardXCenter) {
        this.codStandardXCenter = codStandardXCenter;
    }

    public Double getCodStandardXTolerance() {
        return codStandardXTolerance;
    }

    public void setCodStandardXTolerance(Double codStandardXTolerance) {
        this.codStandardXTolerance = codStandardXTolerance;
    }

    public Double getCodStandardYCenter() {
        return codStandardYCenter;
    }

    public void setCodStandardYCenter(Double codStandardYCenter) {
        this.codStandardYCenter = codStandardYCenter;
    }

    public Double getCodStandardYTolerance() {
        return codStandardYTolerance;
    }

    public void setCodStandardYTolerance(Double codStandardYTolerance) {
        this.codStandardYTolerance = codStandardYTolerance;
    }

    public Boolean getIsImuValid() {
        return isImuValid;
    }

    public void setIsImuValid(Boolean isImuValid) {
        this.isImuValid = isImuValid;
    }

    @Override
    public String toString() {
        return "MoStereoPrecheck{" +
            "id = " + id +
            ", datetime = " + datetime +
            ", sn = " + sn +
            ", errorCode = " + errorCode +
            ", isDirtyDetectEnabled = " + isDirtyDetectEnabled +
            ", dirtyCountLeft = " + dirtyCountLeft +
            ", dirtyCountRight = " + dirtyCountRight +
            ", dirtyStandardCof = " + dirtyStandardCof +
            ", isClarityDetectEnabled = " + isClarityDetectEnabled +
            ", clarityLeft = " + clarityLeft +
            ", clarityRight = " + clarityRight +
            ", clarityStandardVal = " + clarityStandardVal +
            ", clarityStandardMdiff = " + clarityStandardMdiff +
            ", isLoDetectEnabled = " + isLoDetectEnabled +
            ", loMedium = " + loMedium +
            ", loMean = " + loMean +
            ", loMax = " + loMax +
            ", loMin = " + loMin +
            ", loStddev = " + loStddev +
            ", loStandardVal = " + loStandardVal +
            ", xOffsetMedium = " + xOffsetMedium +
            ", xOffsetMean = " + xOffsetMean +
            ", xOffsetMax = " + xOffsetMax +
            ", xOffsetMin = " + xOffsetMin +
            ", xOffsetStddev = " + xOffsetStddev +
            ", xOffsetStandardVal = " + xOffsetStandardVal +
            ", xOffsetStandardTolerance = " + xOffsetStandardTolerance +
            ", isColorCastDetectEnabled = " + isColorCastDetectEnabled +
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
            ", colorCastStandardRCenter = " + colorCastStandardRCenter +
            ", colorCastStandardRTolerance = " + colorCastStandardRTolerance +
            ", colorCastStandardGCenter = " + colorCastStandardGCenter +
            ", colorCastStandardGTolerance = " + colorCastStandardGTolerance +
            ", colorCastStandardBCenter = " + colorCastStandardBCenter +
            ", colorCastStandardBTolerance = " + colorCastStandardBTolerance +
            ", isCodDetectEnabled = " + isCodDetectEnabled +
            ", codXLeft = " + codXLeft +
            ", codYLeft = " + codYLeft +
            ", codXRight = " + codXRight +
            ", codYRight = " + codYRight +
            ", codStandardXCenter = " + codStandardXCenter +
            ", codStandardXTolerance = " + codStandardXTolerance +
            ", codStandardYCenter = " + codStandardYCenter +
            ", codStandardYTolerance = " + codStandardYTolerance +
            ", isImuValid = " + isImuValid +
        "}";
    }
}
