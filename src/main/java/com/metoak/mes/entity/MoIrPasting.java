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
@TableName("mo_ir_pasting")
@ApiModel(value = "MoIrPasting对象", description = "")
public class MoIrPasting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String batchNo;

    @FieldCode(no = "000", type = "val")
    private String assemblyTime;

    @FieldCode(no = "001", type = "val")
    private Float dispenseTime;

    @FieldCode(no = "002", type = "val")
    private Float cureTimeSpec;

    private String operator;

    private String ngReason;

    private LocalDateTime addTime;

    private Integer stationNum;

    private Integer errorCode;

    private Long moProcessStepProductionResultId;

    @Override
    public String toString() {
        return "MoIrPasting{" +
            "id = " + id +
            ", batchNo = " + batchNo +
            ", assemblyTime = " + assemblyTime +
            ", dispenseTime = " + dispenseTime +
            ", cureTimeSpec = " + cureTimeSpec +
            ", ngReason = " + ngReason +
            ", addTime = " + addTime +
            ", stationNum = " + stationNum +
        "}";
    }
}
