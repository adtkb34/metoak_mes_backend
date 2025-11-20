package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.lang.Float;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.metoak.mes.common.annotate.FieldCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Column;
import lombok.Data;

/**
 * <p>
 * 横梁外观检测数据
 * </p>
 *
 * @author kevin
 * @since 2025-07-04 16:08:02
 */
@Data
@TableName("mo_beam_appearance_inspection")
@ApiModel(value = "MoBeamAppearanceInspection对象", description = "横梁外观检测数据")
public class MoBeamAppearanceInspection implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @FieldCode(no = "000", type = "val", label = "AA孔直径")
    private BigDecimal aaHoleDiameter;              // AA孔直径

    @FieldCode(no = "000", type = "usl", label = "AA孔直径上限")
    @Column(name = "aa_hole_diameter_usl")
    private Float aaHoleDiameterUsl;

    @FieldCode(no = "000", type = "lsl", label = "AA孔直径下限")
    @Column(name = "aa_hole_diameter_lsl")
    private Float aaHoleDiameterLsl;

    @FieldCode(no = "001", type = "val", label = "AA孔孔距")
    private Float aaHoleDistance;              // AA孔两孔孔距

    @FieldCode(no = "001", type = "usl", label = "AA孔孔距上限")
    @Column(name = "aa_hole_distance_usl")
    private Float aaHoleDistanceUsl;

    @FieldCode(no = "001", type = "lsl", label = "AA孔孔距下限")
    @Column(name = "aa_hole_distance_lsl")
    private Float aaHoleDistanceLsl;

    @FieldCode(no = "002", type = "val", label = "AA孔中心X值")
    private Float aaHoleCenterX;               // AA孔中心X值

    @FieldCode(no = "003", type = "val", label = "AA孔中心Y值")
    private Float aaHoleCenterY;               // AA孔中心Y值

    @FieldCode(no = "004", type = "val", label = "螺纹孔孔距")
    private Float threadHoleDistance;          // 螺纹孔四孔孔距

    @FieldCode(no = "004", type = "usl", label = "螺纹孔孔距上限")
    @Column(name = "thread_hole_distance_usl")
    private Float threadHoleDistanceUsl;

    @FieldCode(no = "004", type = "lsl", label = "螺纹孔孔距下限")
    @TableField("thread_hole_distance_lsl")
    private Float threadHoleDistanceLsl;

    @FieldCode(no = "005", type = "val", label = "螺纹孔中心X值")
    private Float threadHoleCenterX;           // 螺纹孔中心X值

    @FieldCode(no = "006", type = "val", label = "螺纹孔中心Y值")
    private Float threadHoleCenterY;           // 螺纹孔中心Y值

    @FieldCode(no = "007", type = "val", label = "贴合面高度差")
    private Float surfaceHeightDiff;           // 两贴合面高度差

    @FieldCode(no = "007", type = "usl", label = "贴合面高度差上限")
    private Float surfaceHeightDiffUsl;

    @FieldCode(no = "008", type = "val", label = "贴合面与横梁差值")
    private Float surfaceToBeamDiff;           // 贴合面与横梁基准面差值

    @FieldCode(no = "008", type = "usl", label = "贴合面与横梁差值上限")
    private Float surfaceToBeamDiffUsl;

    @FieldCode(no = "009", type = "val", label = "横梁长度")
    private Float beamLength;                  // 横梁整体长度

    @FieldCode(no = "009", type = "usl", label = "横梁长度上限")
    @Column(name = "beam_length_usl")
    private Float beamLengthUsl;

    @FieldCode(no = "009", type = "lsl", label = "横梁长度下限")
    @Column(name = "beam_length_lsl")
    private Float beamLengthLsl;

    @FieldCode(no = "010", type = "val", label = "横梁宽度")
    private Float beamWidth;                   // 横梁整体宽度

    @FieldCode(no = "010", type = "usl", label = "横梁宽度上限")
    @Column(name = "beam_width_usl")
    private Float beamWidthUsl;

    @FieldCode(no = "010", type = "lsl", label = "横梁宽度下限")
    @Column(name = "beam_width_lsl")
    private Float beamWidthLsl;

    /* ===========  1. AA孔到页边距离 x  =========== */
    @FieldCode(no = "011", type = "val", label = "AA孔到页边距离X")
    private Float aaHoleSideDistanceX;                   // AA孔到页边距离x

    @FieldCode(no = "011", type = "usl", label = "AA孔到页边距离X上限")
    @Column(name = "aa_hole_side_distance_x_usl")
    private Float aaHoleSideDistanceXUsl;                // AA孔到页边距离x-上限

    @FieldCode(no = "011", type = "lsl", label = "AA孔到页边距离X下限")
    @Column(name = "aa_hole_side_distance_x_lsl")
    private Float aaHoleSideDistanceXLsl;                // AA孔到页边距离x-下限

    /* ===========  2. AA孔到页边距离 y  =========== */
    @FieldCode(no = "012", type = "val", label = "AA孔到页边距离Y")
    private Float aaHoleSideDistanceY;                   // AA孔到页边距离y

    @FieldCode(no = "012", type = "usl", label = "AA孔到页边距离Y上限")
    @Column(name = "aa_hole_side_distance_y_usl")
    private Float aaHoleSideDistanceYUsl;                // AA孔到页边距离y-上限

    @FieldCode(no = "012", type = "lsl", label = "AA孔到页边距离Y下限")
    @Column(name = "aa_hole_side_distance_y_lsl")
    private Float aaHoleSideDistanceYLsl;                // AA孔到页边距离y-下限

    /* ===========  3. 外侧螺纹孔到内边距横向距离  =========== */
    @FieldCode(no = "013", type = "val", label = "外侧螺纹孔到内边距横向距离")
    private Float threadHoleDistanceXOut;                // 外侧螺纹孔到内边距横向距离

    @FieldCode(no = "013", type = "usl", label = "外侧螺纹孔到内边距横向距离上限")
    @Column(name = "thread_hole_distance_x_out_usl")
    private Float threadHoleDistanceXOutUsl;             // 外侧螺纹孔到内边距横向距离-上限

    @FieldCode(no = "013", type = "lsl", label = "外侧螺纹孔到内边距横向距离下限")
    @Column(name = "thread_hole_distance_x_out_lsl")
    private Float threadHoleDistanceXOutLsl;             // 外侧螺纹孔到内边距横向距离-下限

    /* ===========  4. 外侧螺纹孔到内边距纵向距离  =========== */
    @FieldCode(no = "014", type = "val", label = "外侧螺纹孔到内边距纵向距离")
    private Float threadHoleDistanceYOut;                // 外侧螺纹孔到内边距纵向距离

    @FieldCode(no = "014", type = "usl", label = "外侧螺纹孔到内边距纵向距离上限")
    @Column(name = "thread_hole_distance_y_out_usl")
    private Float threadHoleDistanceYOutUsl;             // 外侧螺纹孔到内边距纵向距离-上限

    @FieldCode(no = "014", type = "lsl", label = "外侧螺纹孔到内边距纵向距离下限")
    @Column(name = "thread_hole_distance_y_out_lsl")
    private Float threadHoleDistanceYOutLsl;             // 外侧螺纹孔到内边距纵向距离-下限

    /* ===========  5. 内侧螺纹孔到内边距横向距离  =========== */
    @FieldCode(no = "015", type = "val", label = "内侧螺纹孔到内边距横向距离")
    private Float threadHoleDistanceXIn;                 // 内侧螺纹孔到内边距横向距离

    @FieldCode(no = "015", type = "usl", label = "内侧螺纹孔到内边距横向距离上限")
    @Column(name = "thread_hole_distance_x_in_usl")
    private Float threadHoleDistanceXInUsl;              // 内侧螺纹孔到内边距横向距离-上限

    @FieldCode(no = "015", type = "lsl", label = "内侧螺纹孔到内边距横向距离下限")
    @Column(name = "thread_hole_distance_x_in_lsl")
    private Float threadHoleDistanceXInLsl;              // 内侧螺纹孔到内边距横向距离-下限

    /* ===========  6. 内侧螺纹孔到内边距纵向距离  =========== */
    @FieldCode(no = "016", type = "val", label = "内侧螺纹孔到内边距纵向距离")
    private Float threadHoleDistanceYIn;                 // 内侧螺纹孔到内边距纵向距离

    @FieldCode(no = "016", type = "usl", label = "内侧螺纹孔到内边距纵向距离上限")
    @Column(name = "thread_hole_distance_y_in_usl")
    private Float threadHoleDistanceYInUsl;              // 内侧螺纹孔到内边距纵向距离-上限

    @FieldCode(no = "016", type = "lsl", label = "内侧螺纹孔到内边距纵向距离下限")
    @Column(name = "thread_hole_distance_y_in_lsl")
    private Float threadHoleDistanceYInLsl;              // 内侧螺纹孔到内边距纵向距离-下限

    private Long moProcessStepProductionResultId;

    private Integer errorCode;

    private Integer stationNum;

    private String ngReason;

    private LocalDateTime addTime;

    private String beamSn;

    private String position;

    @Override
    public String toString() {
        return "MoBeamAppearanceInspection{" +
                "id=" + id +
                ", aaHoleDiameter=" + aaHoleDiameter +
                ", aaHoleDiameterUsl=" + aaHoleDiameterUsl +
                ", aaHoleDiameterLsl=" + aaHoleDiameterLsl +
                ", aaHoleDistance=" + aaHoleDistance +
                ", aaHoleDistanceUsl=" + aaHoleDistanceUsl +
                ", aaHoleDistanceLsl=" + aaHoleDistanceLsl +
                ", aaHoleCenterX=" + aaHoleCenterX +
                ", aaHoleCenterY=" + aaHoleCenterY +
                ", threadHoleDistance=" + threadHoleDistance +
                ", threadHoleDistanceUsl=" + threadHoleDistanceUsl +
                ", threadHoleDistanceLsl=" + threadHoleDistanceLsl +
                ", threadHoleCenterX=" + threadHoleCenterX +
                ", threadHoleCenterY=" + threadHoleCenterY +
                ", surfaceHeightDiff=" + surfaceHeightDiff +
                ", surfaceHeightDiffUsl=" + surfaceHeightDiffUsl +
                ", surfaceToBeamDiff=" + surfaceToBeamDiff +
                ", surfaceToBeamDiffUsl=" + surfaceToBeamDiffUsl +
                ", beamLength=" + beamLength +
                ", beamLengthUsl=" + beamLengthUsl +
                ", beamLengthLsl=" + beamLengthLsl +
                ", beamWidth=" + beamWidth +
                ", beamWidthUsl=" + beamWidthUsl +
                ", beamWidthLsl=" + beamWidthLsl +
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
