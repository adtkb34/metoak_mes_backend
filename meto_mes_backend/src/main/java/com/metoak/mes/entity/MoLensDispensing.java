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
@TableName("mo_lens_dispensing")
@ApiModel(value = "MoLensDispensing对象", description = "")
public class MoLensDispensing implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String batchNo;

    @FieldCode(no = "000", type = "val", label = "前盖点胶时间")
    private Float coverDispenseTime;

    @FieldCode(no = "001", type = "val", label = "前盖固化时间")
    private Float coverCureTime;

    private String operator;

    private String ngReason;

    private LocalDateTime addTime;

    private Integer stationNum;

    private Integer errorCode;

    private Long moProcessStepProductionResultId;

    private Integer position;

    @Override
    public String toString() {
        return "MoLensDispensing{" +
            "id = " + id +
            ", batchNo = " + batchNo +
            ", ngReason = " + ngReason +
            ", addTime = " + addTime +
            ", stationNum = " + stationNum +
        "}";
    }
}
