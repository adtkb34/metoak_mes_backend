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
@TableName("final_check_mono_m55h")
@ApiModel(value = "FinalCheckMonoM55h对象", description = "")
public class FinalCheckMonoM55h implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private LocalDateTime datetime;

    private String sn;

    private Integer errorCode;

    private String operator;

    private Boolean imageOk;

    private String imagePath;

    private Boolean checkResult;

    private Boolean can0Ok;

    private Boolean can1Ok;

    private String versionAdas;

    private String versionSpi;

    private String versionMcu;

    private Double focus;

    private Double baseline;

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

    public Boolean getImageOk() {
        return imageOk;
    }

    public void setImageOk(Boolean imageOk) {
        this.imageOk = imageOk;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Boolean getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(Boolean checkResult) {
        this.checkResult = checkResult;
    }

    public Boolean getCan0Ok() {
        return can0Ok;
    }

    public void setCan0Ok(Boolean can0Ok) {
        this.can0Ok = can0Ok;
    }

    public Boolean getCan1Ok() {
        return can1Ok;
    }

    public void setCan1Ok(Boolean can1Ok) {
        this.can1Ok = can1Ok;
    }

    public String getVersionAdas() {
        return versionAdas;
    }

    public void setVersionAdas(String versionAdas) {
        this.versionAdas = versionAdas;
    }

    public String getVersionSpi() {
        return versionSpi;
    }

    public void setVersionSpi(String versionSpi) {
        this.versionSpi = versionSpi;
    }

    public String getVersionMcu() {
        return versionMcu;
    }

    public void setVersionMcu(String versionMcu) {
        this.versionMcu = versionMcu;
    }

    public Double getFocus() {
        return focus;
    }

    public void setFocus(Double focus) {
        this.focus = focus;
    }

    public Double getBaseline() {
        return baseline;
    }

    public void setBaseline(Double baseline) {
        this.baseline = baseline;
    }

    @Override
    public String toString() {
        return "FinalCheckMonoM55h{" +
            "id = " + id +
            ", datetime = " + datetime +
            ", sn = " + sn +
            ", errorCode = " + errorCode +
            ", operator = " + operator +
            ", imageOk = " + imageOk +
            ", imagePath = " + imagePath +
            ", checkResult = " + checkResult +
            ", can0Ok = " + can0Ok +
            ", can1Ok = " + can1Ok +
            ", versionAdas = " + versionAdas +
            ", versionSpi = " + versionSpi +
            ", versionMcu = " + versionMcu +
            ", focus = " + focus +
            ", baseline = " + baseline +
        "}";
    }
}
