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
 * @since 2025-06-27 14:30:56
 */
@Data
@TableName("mo_lens_mtf_checking")
@ApiModel(value = "MoLensMtfChecking对象", description = "")
public class MoLensMtfChecking implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String batchNo;

    private Integer frequency;             // 频率，如 "60lp/mm"

    private Integer block;                // 区间

    private Integer position;             // 位置编号（如1-7）

    @FieldCode(no = "003", type = "val", name = "T方向MTF")
    private Float tMtf;

    @FieldCode(no = "004", type = "val", name = "S方向MTF")
    private Float sMtf;

    @FieldCode(no = "005", type = "val", name = "T方向峰值位置")
    private Float tPeak;

    @FieldCode(no = "006", type = "val", name = "S方向峰值位置")
    private Float sPeak;

    @FieldCode(no = "007", type = "val", name = "T方向傅里叶面积")
    private Float tFs;

    @FieldCode(no = "008", type = "val", name = "S方向傅里叶面积")
    private Float sFs;

    @FieldCode(no = "009", type = "val", name = "像散")
    private Float astigmatism;

    @FieldCode(no = "010", type = "val", name = "场曲-T方向")
    private Float fieldCurvatureT;

    @FieldCode(no = "011", type = "val", name = "场曲-S方向")
    private Float fieldCurvatureS;

    @FieldCode(no = "012", type = "val", name = "偏移量-T方向")
    private Float offsetT;

    @FieldCode(no = "013", type = "val", name = "偏移量-S方向")
    private Float offsetS;

    @FieldCode(no = "014", type = "val", name = "均匀性-T方向")
    private Float uniformityT;

    @FieldCode(no = "015", type = "val", name = "均匀性-S方向")
    private Float uniformityS;

    @FieldCode(no = "016", type = "val", name = "焦深")
    private Float depthOfFocus;

    @FieldCode(no = "017", type = "val", name = "后焦位置")
    private Float backFocalDistance;

    @FieldCode(no = "018", type = "val", name = "焦距")
    private String ngReason;

    private LocalDateTime addTime;

    private Integer stationNum;

    private Integer errorCode;

    private String operator;

    private Long moProcessStepProductionResultId;

    @Override
    public String toString() {
        return "MoLensMtfChecking{" +
            "id = " + id +
            ", batchNo = " + batchNo +
            ", ngReason = " + ngReason +
            ", addTime = " + addTime +
            ", stationNum = " + stationNum +
            ", errorCode = " + errorCode +
            ", operator = " + operator +
        "}";
    }
}
