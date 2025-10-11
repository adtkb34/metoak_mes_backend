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
 * @since 2025-06-25 20:41:32
 */
@Data
@TableName("mo_lens_assembly")
@ApiModel(value = "MoLensAssembly对象", description = "")
public class MoLensAssembly implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String batchNo;

//    @FieldCode(no = "000", type = "val")
//    private Float assemblyPressure;

    @FieldCode(no = "000", type = "spec")
    private Float assemblyPressureSpec;

//    @FieldCode(no = "000", type = "usl")
//    private Float assemblyPressureUsl;
//
//    @FieldCode(no = "000", type = "lsl")
//    private Float assemblyPressureLsl;
//
//    @FieldCode(no = "003", type = "val")
//    private Float assemblyHeight;

    @FieldCode(no = "003", type = "spec")
    private Float assemblyHeightSpec;

//    @FieldCode(no = "003", type = "usl")
//    private Float assemblyHeightUsl;
//
//    @FieldCode(no = "003", type = "lsl")
//    private Float assemblyHeightLsl;
//
//    @FieldCode(no = "004", type = "spec")
//    private Float assemblyAngleSpec;

    private Integer position;

    private String operator;

    private String ngReason;

    private Integer errorCode;

    private LocalDateTime addTime;

    private Integer stationNum;

    private Long moProcessStepProductionResultId;

    @Override
    public String toString() {
        return "MoLensAssembly{" +
            "id = " + id +
            ", batchNo = " + batchNo +
            ", ngReason = " + ngReason +
            ", addTime = " + addTime +
            ", stationNum = " + stationNum +
        "}";
    }
}
