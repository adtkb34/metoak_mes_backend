package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 生产流程/工艺流程
 * </p>
 *
 * @author kevin
 * @since 2025-06-17 18:02:42
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("mo_process_flow")
@ApiModel(value = "MoProcessFlow对象", description = "生产流程/工艺流程")
public class MoProcessFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("流程编号")
    private String processCode;

    @ApiModelProperty("流程名称")
    private String processName;

    @ApiModelProperty("工序代号")
    private String stageCode;

    @ApiModelProperty("流程说明")
    private String processDesc;

    @ApiModelProperty("添加时间")
    private LocalDateTime addTime;

    private Boolean isRequired;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getStageCode() {
        return stageCode;
    }

    public void setStageCode(String stageCode) {
        this.stageCode = stageCode;
    }

    public String getProcessDesc() {
        return processDesc;
    }

    public void setProcessDesc(String processDesc) {
        this.processDesc = processDesc;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    @Override
    public String toString() {
        return "MoProcessFlow{" +
            "id = " + id +
            ", processCode = " + processCode +
            ", processName = " + processName +
            ", stageCode = " + stageCode +
            ", processDesc = " + processDesc +
            ", addTime = " + addTime +
            ", isRequired = " + isRequired +
        "}";
    }
}
