package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.metoak.mes.common.annotate.FieldCode;
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
 * 标定工具数据表
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("mo_calibration")
@ApiModel(value = "MoCalibration对象", description = "标定工具数据表")
public class MoCalibration implements Serializable {

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

    @ApiModelProperty("左清晰度")
    private Double sharpnessLeft;

    @ApiModelProperty("右清晰度")
    private Double sharpnessRight;

    @ApiModelProperty("左镜头裁剪行数")
    private Integer leftTrim;

    @ApiModelProperty("右镜头裁剪行数")
    private Integer rightTrim;

    @TableField("validPattern")
    @ApiModelProperty("有效图像张数")
    private Integer validPattern;

    @TableField("meanReprojectionError")
    @ApiModelProperty("平 左右相机均逆投影误差")
    private Double meanReprojectionError;

    @TableField("meanLeftReprojectionError")
    @ApiModelProperty("左相机平均逆投影误差")
    private Double meanLeftReprojectionError;

    @TableField("meanRightReprojectionError")
    @ApiModelProperty("右相机平均逆投影误差")
    private Double meanRightReprojectionError;

    @TableField("maxLeftReprojectionError")
    @ApiModelProperty("左相机最大逆投影误差")
    private Double maxLeftReprojectionError;

    @TableField("maxRightReprojectionError")
    @ApiModelProperty("右相机最大逆投影误差")
    private Double maxRightReprojectionError;

    @ApiModelProperty("原图的yml2data")
    private Double imgYml2dataFov;

    @ApiModelProperty("isp的yml2data")
    private Double ispYml2dataFov;

    @ApiModelProperty("epr文件（路径+名称）")
    @FieldCode(no = "350", type = "val")
    private String eprFilename;

    @ApiModelProperty("yml文件（路径+名称）")
    @FieldCode(no = "351", type = "val")
    private String ymlFilename;

    @ApiModelProperty("isp行差")
    @FieldCode(no = "352", type = "val")
    private Double ispLineOffset;

    @ApiModelProperty("标定文件存储路径")
    @FieldCode(no = "353", type = "val")
    private String filePath;

    @ApiModelProperty("错误码")
    private Integer errorCode;

    @FieldCode(no = "354", type = "val")
    private String toolVersion;

    private Long selfCheckErrcode;

    private String selfCheckErrorDesc;

//    @TableField(value = "count(*)", insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER, select = false)
//    private Long count;

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

    public Double getSharpnessLeft() {
        return sharpnessLeft;
    }

    public void setSharpnessLeft(Double sharpnessLeft) {
        this.sharpnessLeft = sharpnessLeft;
    }

    public Double getSharpnessRight() {
        return sharpnessRight;
    }

    public void setSharpnessRight(Double sharpnessRight) {
        this.sharpnessRight = sharpnessRight;
    }

    public Integer getLeftTrim() {
        return leftTrim;
    }

    public void setLeftTrim(Integer leftTrim) {
        this.leftTrim = leftTrim;
    }

    public Integer getRightTrim() {
        return rightTrim;
    }

    public void setRightTrim(Integer rightTrim) {
        this.rightTrim = rightTrim;
    }

    public Integer getValidPattern() {
        return validPattern;
    }

    public void setValidPattern(Integer validPattern) {
        this.validPattern = validPattern;
    }

    public Double getMeanReprojectionError() {
        return meanReprojectionError;
    }

    public void setMeanReprojectionError(Double meanReprojectionError) {
        this.meanReprojectionError = meanReprojectionError;
    }

    public Double getMeanLeftReprojectionError() {
        return meanLeftReprojectionError;
    }

    public void setMeanLeftReprojectionError(Double meanLeftReprojectionError) {
        this.meanLeftReprojectionError = meanLeftReprojectionError;
    }

    public Double getMeanRightReprojectionError() {
        return meanRightReprojectionError;
    }

    public void setMeanRightReprojectionError(Double meanRightReprojectionError) {
        this.meanRightReprojectionError = meanRightReprojectionError;
    }

    public Double getMaxLeftReprojectionError() {
        return maxLeftReprojectionError;
    }

    public void setMaxLeftReprojectionError(Double maxLeftReprojectionError) {
        this.maxLeftReprojectionError = maxLeftReprojectionError;
    }

    public Double getMaxRightReprojectionError() {
        return maxRightReprojectionError;
    }

    public void setMaxRightReprojectionError(Double maxRightReprojectionError) {
        this.maxRightReprojectionError = maxRightReprojectionError;
    }

    public Double getImgYml2dataFov() {
        return imgYml2dataFov;
    }

    public void setImgYml2dataFov(Double imgYml2dataFov) {
        this.imgYml2dataFov = imgYml2dataFov;
    }

    public Double getIspYml2dataFov() {
        return ispYml2dataFov;
    }

    public void setIspYml2dataFov(Double ispYml2dataFov) {
        this.ispYml2dataFov = ispYml2dataFov;
    }

    public String getEprFilename() {
        return eprFilename;
    }

    public void setEprFilename(String eprFilename) {
        this.eprFilename = eprFilename;
    }

    public String getYmlFilename() {
        return ymlFilename;
    }

    public void setYmlFilename(String ymlFilename) {
        this.ymlFilename = ymlFilename;
    }

    public Double getIspLineOffset() {
        return ispLineOffset;
    }

    public void setIspLineOffset(Double ispLineOffset) {
        this.ispLineOffset = ispLineOffset;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getToolVersion() {
        return toolVersion;
    }

    public void setToolVersion(String toolVersion) {
        this.toolVersion = toolVersion;
    }

    public Long getSelfCheckErrcode() {
        return selfCheckErrcode;
    }

    public void setSelfCheckErrcode(Long selfCheckErrcode) {
        this.selfCheckErrcode = selfCheckErrcode;
    }

    public String getSelfCheckErrorDesc() {
        return selfCheckErrorDesc;
    }

    public void setSelfCheckErrorDesc(String selfCheckErrorDesc) {
        this.selfCheckErrorDesc = selfCheckErrorDesc;
    }

//    public Long getCount() {
//        return count;
//    }
//
//    public void setCount(Long count) {
//        this.count = count;
//    }

    @Override
    public String toString() {
        return "MoCalibration{" +
            "id = " + id +
            ", cameraSn = " + cameraSn +
            ", stationNumber = " + stationNumber +
            ", operator = " + operator +
            ", startTime = " + startTime +
            ", endTime = " + endTime +
            ", sharpnessLeft = " + sharpnessLeft +
            ", sharpnessRight = " + sharpnessRight +
            ", leftTrim = " + leftTrim +
            ", rightTrim = " + rightTrim +
            ", validPattern = " + validPattern +
            ", meanReprojectionError = " + meanReprojectionError +
            ", meanLeftReprojectionError = " + meanLeftReprojectionError +
            ", meanRightReprojectionError = " + meanRightReprojectionError +
            ", maxLeftReprojectionError = " + maxLeftReprojectionError +
            ", maxRightReprojectionError = " + maxRightReprojectionError +
            ", imgYml2dataFov = " + imgYml2dataFov +
            ", ispYml2dataFov = " + ispYml2dataFov +
            ", eprFilename = " + eprFilename +
            ", ymlFilename = " + ymlFilename +
            ", ispLineOffset = " + ispLineOffset +
            ", filePath = " + filePath +
            ", errorCode = " + errorCode +
            ", toolVersion = " + toolVersion +
        "}";
    }
}
