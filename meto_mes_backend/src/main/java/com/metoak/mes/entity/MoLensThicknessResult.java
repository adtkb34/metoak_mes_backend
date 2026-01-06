package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>
 * </p>
 */
@Data
@TableName("mo_lens_thickness_result")
@ApiModel(value = "MoLensThicknessResult对象", description = "")
public class MoLensThicknessResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String batchNo;

    private String ngReason;

    private LocalDateTime addTime;

    private Integer stationNum;

    private Integer errorCode;

    private Long moProcessStepProductionResultId;

    private Integer lensTypeIndex;

    private Float height;
}
