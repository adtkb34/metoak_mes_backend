package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("mo_auto_adjust_info")
@ApiModel(value = "MoAutoAdjustInfo对象", description = "")
public class MoAutoAdjustInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("sn号")
    private String beamSn;

    @ApiModelProperty("工站编号")
    private Integer stationNum;

    @ApiModelProperty("操作时间")
    private LocalDateTime operationTime;

    @ApiModelProperty("操作结果")
    private Integer operationResult;

    private LocalDateTime addTime;

    private String ngReason;

    private Integer errorCode;

    private String toolVersion;

    @Override
    public String toString() {
        return "MoAutoAdjustInfo{" +
            "id = " + id +
            ", beamSn = " + beamSn +
            ", stationNum = " + stationNum +
            ", operationTime = " + operationTime +
            ", operationResult = " + operationResult +
            ", addTime = " + addTime +
            ", ngReason = " + ngReason +
            ", errorCode = " + errorCode +
            ", toolVersion = " + toolVersion +
        "}";
    }
}
