package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.metoak.mes.common.annotate.FieldCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author kevin
 * @since 2025-08-11 15:34:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("mo_stereo_precheck")
@ApiModel(value = "MoStereoPrecheck对象", description = "")
public class MoStereoPrecheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private LocalDateTime datetime;

    private String sn;

    private Integer errorCode;

    @FieldCode(no = "000", type = "val", label = "是否启用脏污检测")
    private Boolean isDirtyDetectEnabled;

    @FieldCode(no = "001", type = "val", label = "左目脏污计数")
    private Integer dirtyCountLeft;

    @FieldCode(no = "002", type = "val", label = "右目脏污计数")
    private Integer dirtyCountRight;

    @FieldCode(no = "003", type = "val", label = "脏污判定系数")
    private Double dirtyStandardCof;

    @FieldCode(no = "004", type = "val", label = "是否启用清晰度检测")
    private Boolean isClarityDetectEnabled;

    @FieldCode(no = "005", type = "val", label = "左目清晰度")
    private Double clarityLeft;

    @FieldCode(no = "006", type = "val", label = "右目清晰度")
    private Double clarityRight;

    @FieldCode(no = "007", type = "val", label = "清晰度标准值")
    private Double clarityStandardVal;

    @FieldCode(no = "008", type = "val", label = "清晰度最大偏差")
    private Double clarityStandardMdiff;

    @FieldCode(no = "009", type = "val", label = "是否启用亮度检测")
    private Boolean isLoDetectEnabled;

    @FieldCode(no = "010", type = "val", label = "亮度中值")
    private Double loMedium;

    @FieldCode(no = "011", type = "val", label = "亮度均值")
    private Double loMean;

    @FieldCode(no = "012", type = "val", label = "亮度最大值")
    private Double loMax;

    @FieldCode(no = "013", type = "val", label = "亮度最小值")
    private Double loMin;

    @FieldCode(no = "014", type = "val", label = "亮度标准差")
    private Double loStddev;

    @FieldCode(no = "015", type = "val", label = "亮度标准值")
    private Double loStandardVal;

    @FieldCode(no = "016", type = "val", label = "X偏移中值")
    private Double xOffsetMedium;

    @FieldCode(no = "017", type = "val", label = "X偏移均值")
    private Double xOffsetMean;

    @FieldCode(no = "018", type = "val", label = "X偏移最大值")
    private Double xOffsetMax;

    @FieldCode(no = "019", type = "val", label = "X偏移最小值")
    private Double xOffsetMin;

    @FieldCode(no = "020", type = "val", label = "X偏移标准差")
    private Double xOffsetStddev;

    @FieldCode(no = "021", type = "val", label = "X偏移标准值")
    private Double xOffsetStandardVal;

    @FieldCode(no = "022", type = "val", label = "X偏移标准容差")
    private Double xOffsetStandardTolerance;

    @FieldCode(no = "023", type = "val", label = "是否启用偏色检测")
    private Boolean isColorCastDetectEnabled;

    @FieldCode(no = "024", type = "val", label = "左目R通道均值")
    private Double colorCastRMeanLeft;

    @FieldCode(no = "025", type = "val", label = "左目G通道均值")
    private Double colorCastGMeanLeft;

    @FieldCode(no = "026", type = "val", label = "左目B通道均值")
    private Double colorCastBMeanLeft;

    @FieldCode(no = "027", type = "val", label = "右目R通道均值")
    private Double colorCastRMeanRight;

    @FieldCode(no = "028", type = "val", label = "右目G通道均值")
    private Double colorCastGMeanRight;

    @FieldCode(no = "029", type = "val", label = "右目B通道均值")
    private Double colorCastBMeanRight;

    @FieldCode(no = "030", type = "val", label = "左目R通道标准差")
    private Double colorCastRStddevLeft;

    @FieldCode(no = "031", type = "val", label = "左目G通道标准差")
    private Double colorCastGStddevLeft;

    @FieldCode(no = "032", type = "val", label = "左目B通道标准差")
    private Double colorCastBStddevLeft;

    @FieldCode(no = "033", type = "val", label = "右目R通道标准差")
    private Double colorCastRStddevRight;

    @FieldCode(no = "034", type = "val", label = "右目G通道标准差")
    private Double colorCastGStddevRight;

    @FieldCode(no = "035", type = "val", label = "右目B通道标准差")
    private Double colorCastBStddevRight;

    @FieldCode(no = "036", type = "val", label = "偏色R通道标准中心值")
    private Double colorCastStandardRCenter;

    @FieldCode(no = "037", type = "val", label = "偏色R通道标准容差")
    private Double colorCastStandardRTolerance;

    @FieldCode(no = "038", type = "val", label = "偏色G通道标准中心值")
    private Double colorCastStandardGCenter;

    @FieldCode(no = "039", type = "val", label = "偏色G通道标准容差")
    private Double colorCastStandardGTolerance;

    @FieldCode(no = "040", type = "val", label = "偏色B通道标准中心值")
    private Double colorCastStandardBCenter;

    @FieldCode(no = "041", type = "val", label = "偏色B通道标准容差")
    private Double colorCastStandardBTolerance;

    @FieldCode(no = "042", type = "val", label = "是否启用COD检测")
    private Boolean isCodDetectEnabled;

    @FieldCode(no = "043", type = "val", label = "左目COD X值")
    private Double codXLeft;

    @FieldCode(no = "044", type = "val", label = "左目COD Y值")
    private Double codYLeft;

    @FieldCode(no = "045", type = "val", label = "右目COD X值")
    private Double codXRight;

    @FieldCode(no = "046", type = "val", label = "右目COD Y值")
    private Double codYRight;

    @FieldCode(no = "047", type = "val", label = "COD X标准中心值")
    private Double codStandardXCenter;

    @FieldCode(no = "048", type = "val", label = "COD X标准容差")
    private Double codStandardXTolerance;

    @FieldCode(no = "049", type = "val", label = "COD Y标准中心值")
    private Double codStandardYCenter;

    @FieldCode(no = "050", type = "val", label = "COD Y标准容差")
    private Double codStandardYTolerance;

    @FieldCode(no = "051", type = "val", label = "IMU数据是否有效")
    private Boolean isImuValid;


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
