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
 * 综合检测记录表
 * </p>
 *
 * @author kevin
 * @since 2025-07-04 16:08:02
 */
@TableName("mo_aa_final_comprehensive_inspection")
@ApiModel(value = "MoAaFinalComprehensiveInspection对象", description = "综合检测记录表")
public class MoAaFinalComprehensiveInspection implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    // ========== 000 消费电流 ==========
    @FieldCode(no = "000", type = "val", label = "消费电流 (mA)")
    @ApiModelProperty("消费电流 (mA)")
    private BigDecimal currentConsumption;

    @FieldCode(no = "000", type = "usl", label = "消费电流上限 (mA)")
    @ApiModelProperty("消费电流上限 (mA)")
    private BigDecimal currentConsumptionUsl;

    @FieldCode(no = "000", type = "lsl", label = "消费电流下限 (mA)")
    @ApiModelProperty("消费电流下限 (mA)")
    private BigDecimal currentConsumptionLsl;

    // ========== 001 帧率 ==========
    @FieldCode(no = "001", type = "val", label = "帧率 (fps)")
    @ApiModelProperty("帧率 (fps)")
    private BigDecimal frameRate;

    @FieldCode(no = "001", type = "usl", label = "帧率上限 (fps)")
    @ApiModelProperty("帧率上限 (fps)")
    private BigDecimal frameRateUsl;

    @FieldCode(no = "001", type = "lsl", label = "帧率下限 (fps)")
    @ApiModelProperty("帧率下限 (fps)")
    private BigDecimal frameRateLsl;

    // ========== 002 坏点数量（无上下限） ==========
    @FieldCode(no = "002", type = "val", label = "坏点数量")
    @ApiModelProperty("坏点数量")
    private Integer badPixels;

    // ========== 003 脏污等级（无上下限） ==========
    @FieldCode(no = "003", type = "val", label = "脏污等级或描述")
    @ApiModelProperty("脏污等级或描述")
    private String contaminationLevel;

    // ========== 005 出图质量-色彩（无上下限） ==========
    @FieldCode(no = "005", type = "val", label = "出图质量-色彩")
    @ApiModelProperty("出图质量-色彩")
    private String imageQualityColor;

    // ========== 007 Flare（无上下限） ==========
    @FieldCode(no = "007", type = "val", label = "Flare（炫光）等级或描述")
    @ApiModelProperty("Flare（炫光）等级或描述")
    private String flareLevel;

    // ========== 008 OC X偏差 ==========
    @FieldCode(no = "008", type = "val", label = "OC X偏差值 (mm)")
    @ApiModelProperty("OC X偏差值 (mm)")
    private BigDecimal ocXOffset;

    @FieldCode(no = "008", type = "usl", label = "OC X偏差上限 (mm)")
    @ApiModelProperty("OC X偏差上限 (mm)")
    private BigDecimal ocXOffsetUsl;

    @FieldCode(no = "008", type = "lsl", label = "OC X偏差下限 (mm)")
    @ApiModelProperty("OC X偏差下限 (mm)")
    private BigDecimal ocXOffsetLsl;

    // ========== 009 OC Y偏差 ==========
    @FieldCode(no = "009", type = "val", label = "OC Y偏差值 (mm)")
    @ApiModelProperty("OC Y偏差值 (mm)")
    private BigDecimal ocYOffset;

    @FieldCode(no = "009", type = "usl", label = "OC Y偏差上限 (mm)")
    @ApiModelProperty("OC Y偏差上限 (mm)")
    private BigDecimal ocYOffsetUsl;

    @FieldCode(no = "009", type = "lsl", label = "OC Y偏差下限 (mm)")
    @ApiModelProperty("OC Y偏差下限 (mm)")
    private BigDecimal ocYOffsetLsl;

    // ========== 010 SFR中心 ==========
    @FieldCode(no = "010", type = "val", label = "SFR中心清晰度")
    @ApiModelProperty("SFR中心清晰度")
    private BigDecimal sfrCenter;

    @FieldCode(no = "010", type = "usl", label = "SFR中心清晰度上限")
    @ApiModelProperty("SFR中心清晰度上限")
    private BigDecimal sfrCenterUsl;

    @FieldCode(no = "010", type = "lsl", label = "SFR中心清晰度下限")
    @ApiModelProperty("SFR中心清晰度下限")
    private BigDecimal sfrCenterLsl;

    // ========== 011 SFR左上 ==========
    @FieldCode(no = "011", type = "val", label = "SFR左上清晰度")
    @ApiModelProperty("SFR左上清晰度")
    private BigDecimal sfrTopLeft;

    @FieldCode(no = "011", type = "usl", label = "SFR左上清晰度上限")
    @ApiModelProperty("SFR左上清晰度上限")
    private BigDecimal sfrTopLeftUsl;

    @FieldCode(no = "011", type = "lsl", label = "SFR左上清晰度下限")
    @ApiModelProperty("SFR左上清晰度下限")
    private BigDecimal sfrTopLeftLsl;

    // ========== 012 SFR右上 ==========
    @FieldCode(no = "012", type = "val", label = "SFR右上清晰度")
    @ApiModelProperty("SFR右上清晰度")
    private BigDecimal sfrTopRight;

    @FieldCode(no = "012", type = "usl", label = "SFR右上清晰度上限")
    @ApiModelProperty("SFR右上清晰度上限")
    private BigDecimal sfrTopRightUsl;

    @FieldCode(no = "012", type = "lsl", label = "SFR右上清晰度下限")
    @ApiModelProperty("SFR右上清晰度下限")
    private BigDecimal sfrTopRightLsl;

    // ========== 013 SFR左下 ==========
    @FieldCode(no = "013", type = "val", label = "SFR左下清晰度")
    @ApiModelProperty("SFR左下清晰度")
    private BigDecimal sfrBottomLeft;

    @FieldCode(no = "013", type = "usl", label = "SFR左下清晰度上限")
    @ApiModelProperty("SFR左下清晰度上限")
    private BigDecimal sfrBottomLeftUsl;

    @FieldCode(no = "013", type = "lsl", label = "SFR左下清晰度下限")
    @ApiModelProperty("SFR左下清晰度下限")
    private BigDecimal sfrBottomLeftLsl;

    // ========== 013 SFR左下 ==========
    @FieldCode(no = "014", type = "val", label = "SFR左下清晰度")
    @ApiModelProperty("SFR左下清晰度")
    private BigDecimal sfrBottomRight;

    @FieldCode(no = "014", type = "usl", label = "SFR左下清晰度上限")
    @ApiModelProperty("SFR左下清晰度上限")
    private BigDecimal sfrBottomRightUsl;

    @FieldCode(no = "014", type = "lsl", label = "SFR左下清晰度下限")
    @ApiModelProperty("SFR左下清晰度下限")
    private BigDecimal sfrBottomRightLsl;

    // ========== 自定义图表规格 ==========
    @FieldCode(no = "015", type = "val", label = "自定义图表规格")
    @ApiModelProperty("自定义图表规格")
    private String customChartSpec;

    // ========== 色板规格 ==========
    @FieldCode(no = "016", type = "val", label = "色板规格")
    @ApiModelProperty("色板规格")
    private String colorBoardSpec;

    // ========== 夹具编号规格 ==========
    @FieldCode(no = "017", type = "val", label = "夹具编号规格")
    @ApiModelProperty("夹具编号规格")
    private String fixtureNoSpec;

    // ========== 光源规格 ==========
    @FieldCode(no = "018", type = "val", label = "光源规格")
    @ApiModelProperty("光源规格")
    private String illuminationSpec;

    // ========== 高度规格 ==========
    @FieldCode(no = "019", type = "val", label = "高度规格 (mm)")
    @ApiModelProperty("高度规格 (mm)")
    private BigDecimal heightSpec;

    @FieldCode(no = "020", type = "val", label = "俯仰角")
    @ApiModelProperty("俯仰角")
    private BigDecimal fov;

    private Long moProcessStepProductionResultId;

    private Integer errorCode;

    private Integer stationNum;

    private String ngReason;

    private LocalDateTime addTime;

    private String beamSn;

    private String position;
}
