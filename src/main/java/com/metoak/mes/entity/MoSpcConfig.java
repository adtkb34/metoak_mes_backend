package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * SPC 配置表
 * </p>
 *
 * @author kevin
 * @since 2025-08-25 10:48:51
 */
@TableName("mo_spc_config")
@ApiModel(value = "MoSpcConfig对象", description = "SPC 配置表")
public class MoSpcConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增 ID（主键）")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户 ID")
    private Integer userId;

    @ApiModelProperty("修改时间")
    private LocalDateTime datetime;

    @ApiModelProperty("工站")
    private Integer station;

    @ApiModelProperty("表名")
    private String tableName;

    @ApiModelProperty("字段名")
    private String fieldName;

    @ApiModelProperty("控制上限")
    private Double usl;

    @ApiModelProperty("控制下限")
    private Double lsl;

    @ApiModelProperty("子组长度")
    private Integer subgroupLength;

    @ApiModelProperty("判异规则")
    private Integer rules;

    @ApiModelProperty("是否实时更新数据")
    private Boolean isRealTime;

    @ApiModelProperty("统计长度")
    private Integer statisticsLength;

    @ApiModelProperty("左右目")
    private Integer position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public Integer getStation() {
        return station;
    }

    public void setStation(Integer station) {
        this.station = station;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Double getUsl() {
        return usl;
    }

    public void setUsl(Double usl) {
        this.usl = usl;
    }

    public Double getLsl() {
        return lsl;
    }

    public void setLsl(Double lsl) {
        this.lsl = lsl;
    }

    public Integer getSubgroupLength() {
        return subgroupLength;
    }

    public void setSubgroupLength(Integer subgroupLength) {
        this.subgroupLength = subgroupLength;
    }

    public Integer getRules() {
        return rules;
    }

    public void setRules(Integer rules) {
        this.rules = rules;
    }

    public Boolean getIsRealTime() {
        return isRealTime;
    }

    public void setIsRealTime(Boolean isRealTime) {
        this.isRealTime = isRealTime;
    }

    public Integer getStatisticsLength() {
        return statisticsLength;
    }

    public void setStatisticsLength(Integer statisticsLength) {
        this.statisticsLength = statisticsLength;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "MoSpcConfig{" +
            "id = " + id +
            ", userId = " + userId +
            ", datetime = " + datetime +
            ", station = " + station +
            ", tableName = " + tableName +
            ", fieldName = " + fieldName +
            ", usl = " + usl +
            ", lsl = " + lsl +
            ", subgroupLength = " + subgroupLength +
            ", rules = " + rules +
            ", isRealTime = " + isRealTime +
            ", statisticsLength = " + statisticsLength +
            ", position = " + position +
        "}";
    }
}
