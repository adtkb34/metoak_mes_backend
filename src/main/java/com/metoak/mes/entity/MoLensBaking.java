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
 * @since 2025-06-25 21:07:40
 */
@Data
@TableName("mo_lens_baking")
@ApiModel(value = "MoLensBaking对象", description = "")
public class    MoLensBaking implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String batchNo;

    @FieldCode(no = "000", type = "val")
    private Float cureTime;

    @FieldCode(no = "001", type = "val")
    private Float curePower;

    @FieldCode(no = "002", type = "val")
    private Float cureTemperature;

    private String operator;

    private String ngReason;

    private LocalDateTime addTime;

    private Integer stationNum;

    private Integer errorCode;

    private Long moProcessStepProductionResultId;

    @Override
    public String toString() {
        return "MoLensBaking{" +
            "id = " + id +
            ", batchNo = " + batchNo +
            ", cureTime = " + cureTime +
            ", curePower = " + curePower +
            ", cureTemperature = " + cureTemperature +
            ", ngReason = " + ngReason +
            ", addTime = " + addTime +
            ", stationNum = " + stationNum +
        "}";
    }
}
