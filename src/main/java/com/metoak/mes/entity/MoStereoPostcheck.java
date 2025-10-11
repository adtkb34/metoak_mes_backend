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
 * @since 2025-08-25 10:48:51
 */
@TableName("mo_stereo_postcheck")
@ApiModel(value = "MoStereoPostcheck对象", description = "")
public class MoStereoPostcheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private LocalDateTime datetime;

    private String sn;

    private Integer errorCode;

    private String operator;

    private Integer station;

    private Boolean isVersionOk;

    private Boolean isImageOk;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getStation() {
        return station;
    }

    public void setStation(Integer station) {
        this.station = station;
    }

    public Boolean getIsVersionOk() {
        return isVersionOk;
    }

    public void setIsVersionOk(Boolean isVersionOk) {
        this.isVersionOk = isVersionOk;
    }

    public Boolean getIsImageOk() {
        return isImageOk;
    }

    public void setIsImageOk(Boolean isImageOk) {
        this.isImageOk = isImageOk;
    }

    @Override
    public String toString() {
        return "MoStereoPostcheck{" +
            "id = " + id +
            ", datetime = " + datetime +
            ", sn = " + sn +
            ", errorCode = " + errorCode +
            ", operator = " + operator +
            ", station = " + station +
            ", isVersionOk = " + isVersionOk +
            ", isImageOk = " + isImageOk +
        "}";
    }
}
