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
 * 
 * </p>
 *
 * @author kevin
 * @since 2025-06-21 14:50:22
 */
@TableName("mo_uv_dispensing")
@ApiModel(value = "MoUvDispensing对象", description = "")
public class MoUvDispensing implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String beamSn;

    private Double glueWidth;

    private Double glueWidthUpperSpec;

    private Double circleCenterOffset;

    private Double circleCenterOffsetUpperSpec;

    @ApiModelProperty("图片路径")
    private String imgPath;

    private LocalDateTime addTime;

    private Integer errorCode;

    private String ngReason;

    private Integer stationNum;

    private String side;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBeamSn() {
        return beamSn;
    }

    public void setBeamSn(String beamSn) {
        this.beamSn = beamSn;
    }

    public Double getGlueWidth() {
        return glueWidth;
    }

    public void setGlueWidth(Double glueWidth) {
        this.glueWidth = glueWidth;
    }

    public Double getGlueWidthUpperSpec() {
        return glueWidthUpperSpec;
    }

    public void setGlueWidthUpperSpec(Double glueWidthUpperSpec) {
        this.glueWidthUpperSpec = glueWidthUpperSpec;
    }

    public Double getCircleCenterOffset() {
        return circleCenterOffset;
    }

    public void setCircleCenterOffset(Double circleCenterOffset) {
        this.circleCenterOffset = circleCenterOffset;
    }

    public Double getCircleCenterOffsetUpperSpec() {
        return circleCenterOffsetUpperSpec;
    }

    public void setCircleCenterOffsetUpperSpec(Double circleCenterOffsetUpperSpec) {
        this.circleCenterOffsetUpperSpec = circleCenterOffsetUpperSpec;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getNgReason() {
        return ngReason;
    }

    public void setNgReason(String ngReason) {
        this.ngReason = ngReason;
    }

    public Integer getStationNum() {
        return stationNum;
    }

    public void setStationNum(Integer stationNum) {
        this.stationNum = stationNum;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    @Override
    public String toString() {
        return "MoUvDispensing{" +
            "id = " + id +
            ", beamSn = " + beamSn +
            ", glueWidth = " + glueWidth +
            ", glueWidthUpperSpec = " + glueWidthUpperSpec +
            ", circleCenterOffset = " + circleCenterOffset +
            ", circleCenterOffsetUpperSpec = " + circleCenterOffsetUpperSpec +
            ", imgPath = " + imgPath +
            ", addTime = " + addTime +
            ", errorCode = " + errorCode +
            ", ngReason = " + ngReason +
            ", stationNum = " + stationNum +
            ", side = " + side +
        "}";
    }
}
