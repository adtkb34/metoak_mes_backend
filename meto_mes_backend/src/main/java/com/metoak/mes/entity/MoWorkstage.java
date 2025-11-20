package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 工序表
 * </p>
 *
 * @author kevin
 * @since 2025-06-17 18:02:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("mo_workstage")
@ApiModel(value = "MoWorkstage对象", description = "工序表")
public class MoWorkstage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String stageCode;

    private String stepTypeNo;

    private String stageName;

    private String stageDesc;

    private LocalDateTime addTime;

    private Integer showReport;

    private Boolean showPtype;

    private Boolean manualResult;

    @Override
    public String toString() {
        return "MoWorkstage{" +
            "id = " + id +
            ", stageCode = " + stageCode +
            ", stageName = " + stageName +
            ", stageDesc = " + stageDesc +
            ", addTime = " + addTime +
            ", showReport = " + showReport +
            ", showPtype = " + showPtype +
            ", manualResult = " + manualResult +
        "}";
    }
}
