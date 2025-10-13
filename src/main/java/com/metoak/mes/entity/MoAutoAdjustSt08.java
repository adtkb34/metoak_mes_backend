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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * AA调焦数据表
 * </p>
 *
 * @author kevin
 * @since 2025-06-17 18:02:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("mo_auto_adjust_st08")
@ApiModel(value = "MoAutoAdjustSt07对象", description = "AA调焦数据表")
public class MoAutoAdjustSt08 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("横梁SN")
    private String beamSn;

    @ApiModelProperty("左侧镜头或右侧镜头")
    private String side;

    private String position;

    private String stage;

    private String ngReason;

    private String errorCode;

    private String stationNum;

    @ApiModelProperty("收到数据的时间")
    private LocalDateTime addTime;

    private Long moProcessStepProductionResultId;
    @FieldCode(no = "002", type = "val", hasPosition = true)
    @ApiModelProperty("X偏移量")
    private Float ocXOffset;

    @FieldCode(no = "002", type = "usl", hasPosition = true)
    @ApiModelProperty("X偏移量上限规格值")
    private Float ocXOffsetUsl;

    @FieldCode(no = "004", type = "val", hasPosition = true)
    @ApiModelProperty("Y偏移量")
    private Float ocYOffset;

    @FieldCode(no = "004", type = "usl", hasPosition = true)
    @ApiModelProperty("Y偏移量上限规格值")
    private Float ocYOffsetUsl;

    @FieldCode(no = "006", type = "val", hasPosition = true)
    @ApiModelProperty("COD X偏移量")
    private Float codXOffset;

    @FieldCode(no = "006", type = "usl", hasPosition = true)
    @ApiModelProperty("COD X偏移量上限规格值")
    private Float codXOffsetUsl;

    @FieldCode(no = "008", type = "val", hasPosition = true)
    @ApiModelProperty("COD Y偏移量")
    private Float codYOffset;

    @FieldCode(no = "008", type = "usl", hasPosition = true)
    @ApiModelProperty("COD Y偏移量上限规格值")
    private Float codYOffsetUsl;

    @FieldCode(no = "010", type = "val", hasPosition = true)
    @ApiModelProperty("中心MTF平均数值，实际测试值")
    private Double mtfCenterValue;

    @FieldCode(no = "010", type = "lsl", hasPosition = true)
    @ApiModelProperty("中心MTF平均数值，规格下限")
    private Double mtfCenterLsl;

    @FieldCode(no = "013", type = "val", hasPosition = true)
    @ApiModelProperty("左上MTF平均数值")
    private Double mtfLeftupValue;

    @FieldCode(no = "013", type = "lsl", hasPosition = true)
    @ApiModelProperty("左上MTF平均数值，规格下限")
    private Double mtfLeftupLsl;

    @FieldCode(no = "016", type = "val", hasPosition = true)
    @ApiModelProperty("右上MTF平均数值")
    private Double mtfRightupValue;

    @FieldCode(no = "016", type = "lsl", hasPosition = true)
    @ApiModelProperty("右上MTF平均数值，规格下限")
    private Double mtfRightupLsl;

    @FieldCode(no = "019", type = "val", hasPosition = true)
    @ApiModelProperty("左下MTF平均数值")
    private Double mtfLeftdownValue;

    @FieldCode(no = "019", type = "lsl", hasPosition = true)
    @ApiModelProperty("左下MTF平均数值，规格下限")
    private Double mtfLeftdownLsl;

    @FieldCode(no = "022", type = "val", hasPosition = true)
    @ApiModelProperty("右下MTF平均数值")
    private Double mtfRightdownValue;

    @FieldCode(no = "022", type = "lsl", hasPosition = true)
    @ApiModelProperty("右下MTF平均数值，规格下限")
    private Double mtfRightdownLsl;

    @FieldCode(no = "025", type = "val", hasPosition = true)
    @ApiModelProperty("MTF范围偏移")
    private Float mtfRangeOffset;

    @FieldCode(no = "025", type = "usl", hasPosition = true)
    @ApiModelProperty("MTF范围偏移上限规格值")
    private Float mtfRangeOffsetUsl;

    @FieldCode(no = "028", type = "val", hasPosition = true)
    @ApiModelProperty("图像路径")
    private String imagePath;

    @FieldCode(no = "029", type = "val")
    @ApiModelProperty("AA夹爪抓取压力")
    private Float aaClawGripPressure;

    @FieldCode(no = "029", type = "spec")
    @ApiModelProperty("AA夹爪抓取压力规格")
    private Float aaClawGripPressureSpec;

    @FieldCode(no = "031", type = "val", hasPosition = true)
    @ApiModelProperty("释放爪与UV固化MTF中心差")
    private Float releaseClawMinusUvCuredMtfCenterDiff;

    @FieldCode(no = "031", type = "usl", hasPosition = true)
    @ApiModelProperty("释放爪与UV固化MTF中心差上限规格值")
    private Float releaseClawMinusUvCuredMtfCenterDiffUsl;

    @FieldCode(no = "032", type = "val", hasPosition = true)
    @ApiModelProperty("释放爪与UV固化MTF左上差")
    private Float releaseClawMinusUvCuredMtfTlDiff;

    @FieldCode(no = "032", type = "usl", hasPosition = true)
    @ApiModelProperty("释放爪与UV固化MTF左上差上限规格值")
    private Float releaseClawMinusUvCuredMtfTlDiffUsl;

    @FieldCode(no = "033", type = "val", hasPosition = true)
    @ApiModelProperty("释放爪与UV固化MTF右上差")
    private Float releaseClawMinusUvCuredMtfTrDiff;

    @FieldCode(no = "033", type = "usl", hasPosition = true)
    @ApiModelProperty("释放爪与UV固化MTF右上差上限规格值")
    private Float releaseClawMinusUvCuredMtfTrDiffUsl;

    @FieldCode(no = "034", type = "val", hasPosition = true)
    @ApiModelProperty("释放爪与UV固化MTF左下差")
    private Float releaseClawMinusUvCuredMtfBlDiff;

    @FieldCode(no = "034", type = "usl", hasPosition = true)
    @ApiModelProperty("释放爪与UV固化MTF左下差上限规格值")
    private Float releaseClawMinusUvCuredMtfBlDiffUsl;

    @FieldCode(no = "035", type = "val", hasPosition = true)
    @ApiModelProperty("释放爪与UV固化MTF右下差")
    private Float releaseClawMinusUvCuredMtfBrDiff;

    @FieldCode(no = "035", type = "usl", hasPosition = true)
    @ApiModelProperty("释放爪与UV固化MTF右下差上限规格值")
    private Float releaseClawMinusUvCuredMtfBrDiffUsl;//    private Long taskid;
//
//    private Integer typeNum;

    // ========== 036 UV曝光时间规格 ==========
    @FieldCode(no = "036", type = "spec")
    @ApiModelProperty("UV曝光时间规格(s)")
    private BigDecimal uvExposureTimeSpec;

    // ========== 037 UV能量规格 ==========
    @FieldCode(no = "037", type = "spec")
    @ApiModelProperty("UV能量规格(mJ/cm²)")
    private BigDecimal uvEnergySpec;

    // ========== 038 胶水重量规格 ==========
    @FieldCode(no = "038", type = "spec")
    @ApiModelProperty("胶水重量规格(mg)")
    private BigDecimal checkGlueWeightSpec;

    // ========== 039 轴移动距离规格 ==========
    @FieldCode(no = "039", type = "spec")
    @ApiModelProperty("轴移动距离规格(mm)")
    private BigDecimal axisMovementDistanceSpec;

    // ========== 040 测试卡照度规格 ==========
    @FieldCode(no = "040", type = "spec")
    @ApiModelProperty("测试卡照度规格(lux)")
    private BigDecimal testCardIlluminanceSpec;

    // ========== 041 测试卡高度规格 ==========
    @FieldCode(no = "041", type = "spec")
    @ApiModelProperty("测试卡高度规格(mm)")
    private BigDecimal testCardHeightSpec;

    /* =========== 白场脏污 =========== */
    @FieldCode(no = "042", type = "val", hasPosition = true)
    private Float whiteFieldDirt;                // 白场脏污

    @FieldCode(no = "042", type = "usl", hasPosition = true)
    private Float whiteFieldDirtUsl;             // 白场脏污-上限

    /* =========== 黑场暗点 =========== */
    @FieldCode(no = "043", type = "val", hasPosition = true)
    private Float blackFieldDarkSpot;            // 黑场暗点

    @FieldCode(no = "043", type = "usl", hasPosition = true)
    private Float blackFieldDarkSpotUsl;         // 黑场暗点-上限

    @FieldCode(no = "044", type = "val")
    @ApiModelProperty("中心MTF两目差值，实际测试值")
    private Double mtfCenterValueDiff;

    @FieldCode(no = "044", type = "usl")
    @ApiModelProperty("中心MTF两目差值，规格上限")
    private Double mtfCenterDiffUsl;

    @FieldCode(no = "045", type = "val")
    @ApiModelProperty("左上MTF两目差值")
    private Double mtfLeftupValueDiff;

    @FieldCode(no = "045", type = "usl")
    @ApiModelProperty("左上MTF两目差值，规格上限")
    private Double mtfLeftupDiffUsl;

    @FieldCode(no = "046", type = "val")
    @ApiModelProperty("右上MTF两目差值")
    private Double mtfRightupValueDiff;

    @FieldCode(no = "046", type = "usl")
    @ApiModelProperty("右上MTF两目差值，规格上限")
    private Double mtfRightupDiffUsl;

    @FieldCode(no = "047", type = "val")
    @ApiModelProperty("左下MTF两目差值")
    private Double mtfLeftdownValueDiff;

    @FieldCode(no = "047", type = "usl")
    @ApiModelProperty("左下MTF两目差值，规格上限")
    private Double mtfLeftdownDiffUsl;

    @FieldCode(no = "048", type = "val")
    @ApiModelProperty("右下MTF两目差值")
    private Double mtfRightdownValueDiff;

    @FieldCode(no = "048", type = "usl")
    @ApiModelProperty("右下MTF两目差值，规格上限")
    private Double mtfRightdownDiffUsl;
}
