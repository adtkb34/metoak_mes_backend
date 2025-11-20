package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 调焦工具数据表
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
@TableName("mo_adjust_focus")
@ApiModel(value = "MoAdjustFocus对象", description = "调焦工具数据表")
public class MoAdjustFocus implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String cameraSn;

    @ApiModelProperty("工位号")
    private Integer stationNumber;

    @ApiModelProperty("操作人员")
    private String operator;

    @ApiModelProperty("工序开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("工序结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty("左侧镜头或右侧镜头")
    private String side;

    @ApiModelProperty("中心清晰度")
    private Double sharpnessCenter;

    @ApiModelProperty("中心清晰度最大值")
    private Double sharpnessCenterMax;

    @ApiModelProperty("左上角清晰度")
    private Double sharpnessLeftup;

    @ApiModelProperty("左上角清晰度最大值")
    private Double sharpnessLeftupMax;

    private Double sharpnessRightup;

    private Double sharpnessRightupMax;

    private Double sharpnessLeftdown;

    private Double sharpnessLeftdownMax;

    private Double sharpnessRightdown;

    private Double sharpnessRightdownMax;

    @ApiModelProperty("镜头sn号")
    private String lensSn;

    private String cmosSn;

    @ApiModelProperty("图像路径")
    private String imagePath;

    @ApiModelProperty("调焦综合结果")
    private Integer errorCode;

    @ApiModelProperty("中心值最低规格线")
    private Double mtfCenterLsl;

    @ApiModelProperty("中心值最高规格线")
    private Double mtfCenterUsl;

    @ApiModelProperty("左上角值低高规格线")
    private Double mtfLeftupLsl;

    @ApiModelProperty("左上角值最高规格线")
    private Double mtfLeftupUsl;

    private Double mtfRightupLsl;

    private Double mtfRightupUsl;

    private Double mtfLeftdownLsl;

    private Double mtfLeftdownUsl;

    private Double mtfRightdownLsl;

    private Double mtfRightdownUsl;

    @ApiModelProperty("工具软件版本号")
    private String toolVersion;

    @ApiModelProperty("明场黑点数量")
    private Integer lightFieldBlackPoint;

    @ApiModelProperty("明场判断为黑点的像素阈值")
    private Integer lightFieldBlackThreshold;

    @ApiModelProperty("暗场白点数量")
    private Integer darkFieldWhitePoint;

    @ApiModelProperty("暗场判断为白点的像素阈值")
    private Integer darkFieldWhiteThreshold;

    @ApiModelProperty("异常点数量阈值")
    private Integer abnormalPointThreshold;

    @ApiModelProperty("脏污区域数量")
    private Integer dirtyCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCameraSn() {
        return cameraSn;
    }

    public void setCameraSn(String cameraSn) {
        this.cameraSn = cameraSn;
    }

    public Integer getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(Integer stationNumber) {
        this.stationNumber = stationNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Double getSharpnessCenter() {
        return sharpnessCenter;
    }

    public void setSharpnessCenter(Double sharpnessCenter) {
        this.sharpnessCenter = sharpnessCenter;
    }

    public Double getSharpnessCenterMax() {
        return sharpnessCenterMax;
    }

    public void setSharpnessCenterMax(Double sharpnessCenterMax) {
        this.sharpnessCenterMax = sharpnessCenterMax;
    }

    public Double getSharpnessLeftup() {
        return sharpnessLeftup;
    }

    public void setSharpnessLeftup(Double sharpnessLeftup) {
        this.sharpnessLeftup = sharpnessLeftup;
    }

    public Double getSharpnessLeftupMax() {
        return sharpnessLeftupMax;
    }

    public void setSharpnessLeftupMax(Double sharpnessLeftupMax) {
        this.sharpnessLeftupMax = sharpnessLeftupMax;
    }

    public Double getSharpnessRightup() {
        return sharpnessRightup;
    }

    public void setSharpnessRightup(Double sharpnessRightup) {
        this.sharpnessRightup = sharpnessRightup;
    }

    public Double getSharpnessRightupMax() {
        return sharpnessRightupMax;
    }

    public void setSharpnessRightupMax(Double sharpnessRightupMax) {
        this.sharpnessRightupMax = sharpnessRightupMax;
    }

    public Double getSharpnessLeftdown() {
        return sharpnessLeftdown;
    }

    public void setSharpnessLeftdown(Double sharpnessLeftdown) {
        this.sharpnessLeftdown = sharpnessLeftdown;
    }

    public Double getSharpnessLeftdownMax() {
        return sharpnessLeftdownMax;
    }

    public void setSharpnessLeftdownMax(Double sharpnessLeftdownMax) {
        this.sharpnessLeftdownMax = sharpnessLeftdownMax;
    }

    public Double getSharpnessRightdown() {
        return sharpnessRightdown;
    }

    public void setSharpnessRightdown(Double sharpnessRightdown) {
        this.sharpnessRightdown = sharpnessRightdown;
    }

    public Double getSharpnessRightdownMax() {
        return sharpnessRightdownMax;
    }

    public void setSharpnessRightdownMax(Double sharpnessRightdownMax) {
        this.sharpnessRightdownMax = sharpnessRightdownMax;
    }

    public String getLensSn() {
        return lensSn;
    }

    public void setLensSn(String lensSn) {
        this.lensSn = lensSn;
    }

    public String getCmosSn() {
        return cmosSn;
    }

    public void setCmosSn(String cmosSn) {
        this.cmosSn = cmosSn;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Double getMtfCenterLsl() {
        return mtfCenterLsl;
    }

    public void setMtfCenterLsl(Double mtfCenterLsl) {
        this.mtfCenterLsl = mtfCenterLsl;
    }

    public Double getMtfCenterUsl() {
        return mtfCenterUsl;
    }

    public void setMtfCenterUsl(Double mtfCenterUsl) {
        this.mtfCenterUsl = mtfCenterUsl;
    }

    public Double getMtfLeftupLsl() {
        return mtfLeftupLsl;
    }

    public void setMtfLeftupLsl(Double mtfLeftupLsl) {
        this.mtfLeftupLsl = mtfLeftupLsl;
    }

    public Double getMtfLeftupUsl() {
        return mtfLeftupUsl;
    }

    public void setMtfLeftupUsl(Double mtfLeftupUsl) {
        this.mtfLeftupUsl = mtfLeftupUsl;
    }

    public Double getMtfRightupLsl() {
        return mtfRightupLsl;
    }

    public void setMtfRightupLsl(Double mtfRightupLsl) {
        this.mtfRightupLsl = mtfRightupLsl;
    }

    public Double getMtfRightupUsl() {
        return mtfRightupUsl;
    }

    public void setMtfRightupUsl(Double mtfRightupUsl) {
        this.mtfRightupUsl = mtfRightupUsl;
    }

    public Double getMtfLeftdownLsl() {
        return mtfLeftdownLsl;
    }

    public void setMtfLeftdownLsl(Double mtfLeftdownLsl) {
        this.mtfLeftdownLsl = mtfLeftdownLsl;
    }

    public Double getMtfLeftdownUsl() {
        return mtfLeftdownUsl;
    }

    public void setMtfLeftdownUsl(Double mtfLeftdownUsl) {
        this.mtfLeftdownUsl = mtfLeftdownUsl;
    }

    public Double getMtfRightdownLsl() {
        return mtfRightdownLsl;
    }

    public void setMtfRightdownLsl(Double mtfRightdownLsl) {
        this.mtfRightdownLsl = mtfRightdownLsl;
    }

    public Double getMtfRightdownUsl() {
        return mtfRightdownUsl;
    }

    public void setMtfRightdownUsl(Double mtfRightdownUsl) {
        this.mtfRightdownUsl = mtfRightdownUsl;
    }

    public String getToolVersion() {
        return toolVersion;
    }

    public void setToolVersion(String toolVersion) {
        this.toolVersion = toolVersion;
    }

    public Integer getLightFieldBlackPoint() {
        return lightFieldBlackPoint;
    }

    public void setLightFieldBlackPoint(Integer lightFieldBlackPoint) {
        this.lightFieldBlackPoint = lightFieldBlackPoint;
    }

    public Integer getLightFieldBlackThreshold() {
        return lightFieldBlackThreshold;
    }

    public void setLightFieldBlackThreshold(Integer lightFieldBlackThreshold) {
        this.lightFieldBlackThreshold = lightFieldBlackThreshold;
    }

    public Integer getDarkFieldWhitePoint() {
        return darkFieldWhitePoint;
    }

    public void setDarkFieldWhitePoint(Integer darkFieldWhitePoint) {
        this.darkFieldWhitePoint = darkFieldWhitePoint;
    }

    public Integer getDarkFieldWhiteThreshold() {
        return darkFieldWhiteThreshold;
    }

    public void setDarkFieldWhiteThreshold(Integer darkFieldWhiteThreshold) {
        this.darkFieldWhiteThreshold = darkFieldWhiteThreshold;
    }

    public Integer getAbnormalPointThreshold() {
        return abnormalPointThreshold;
    }

    public void setAbnormalPointThreshold(Integer abnormalPointThreshold) {
        this.abnormalPointThreshold = abnormalPointThreshold;
    }

    public Integer getDirtyCount() {
        return dirtyCount;
    }

    public void setDirtyCount(Integer dirtyCount) {
        this.dirtyCount = dirtyCount;
    }

    @Override
    public String toString() {
        return "MoAdjustFocus{" +
            "id = " + id +
            ", cameraSn = " + cameraSn +
            ", stationNumber = " + stationNumber +
            ", operator = " + operator +
            ", startTime = " + startTime +
            ", endTime = " + endTime +
            ", side = " + side +
            ", sharpnessCenter = " + sharpnessCenter +
            ", sharpnessCenterMax = " + sharpnessCenterMax +
            ", sharpnessLeftup = " + sharpnessLeftup +
            ", sharpnessLeftupMax = " + sharpnessLeftupMax +
            ", sharpnessRightup = " + sharpnessRightup +
            ", sharpnessRightupMax = " + sharpnessRightupMax +
            ", sharpnessLeftdown = " + sharpnessLeftdown +
            ", sharpnessLeftdownMax = " + sharpnessLeftdownMax +
            ", sharpnessRightdown = " + sharpnessRightdown +
            ", sharpnessRightdownMax = " + sharpnessRightdownMax +
            ", lensSn = " + lensSn +
            ", cmosSn = " + cmosSn +
            ", imagePath = " + imagePath +
            ", errorCode = " + errorCode +
            ", mtfCenterLsl = " + mtfCenterLsl +
            ", mtfCenterUsl = " + mtfCenterUsl +
            ", mtfLeftupLsl = " + mtfLeftupLsl +
            ", mtfLeftupUsl = " + mtfLeftupUsl +
            ", mtfRightupLsl = " + mtfRightupLsl +
            ", mtfRightupUsl = " + mtfRightupUsl +
            ", mtfLeftdownLsl = " + mtfLeftdownLsl +
            ", mtfLeftdownUsl = " + mtfLeftdownUsl +
            ", mtfRightdownLsl = " + mtfRightdownLsl +
            ", mtfRightdownUsl = " + mtfRightdownUsl +
            ", toolVersion = " + toolVersion +
            ", lightFieldBlackPoint = " + lightFieldBlackPoint +
            ", lightFieldBlackThreshold = " + lightFieldBlackThreshold +
            ", darkFieldWhitePoint = " + darkFieldWhitePoint +
            ", darkFieldWhiteThreshold = " + darkFieldWhiteThreshold +
            ", abnormalPointThreshold = " + abnormalPointThreshold +
            ", dirtyCount = " + dirtyCount +
        "}";
    }
}
