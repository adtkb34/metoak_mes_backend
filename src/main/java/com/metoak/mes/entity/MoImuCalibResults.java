package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author kevin
 * @since 2025-07-01 19:21:55
 */
@TableName("mo_imu_calib_results")
@ApiModel(value = "MoImuCalibResults对象", description = "")
public class MoImuCalibResults implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String caseName;

    private String sn;

    private Double reprojectionErrorCam0Mean;

    private Double reprojectionErrorCam0Median;

    private Double reprojectionErrorCam0Std;

    private Double gyroscopeErrorImu0Mean;

    private Double gyroscopeErrorImu0Median;

    private Double gyroscopeErrorImu0Std;

    private Double accelerometerErrorImu0Mean;

    private Double accelerometerErrorImu0Median;

    private Double accelerometerErrorImu0Std;

    private Double txMm;

    private Double tyMm;

    private Double tzMm;

    private Double timeshiftMs;

    private Double qAngle;

    private String root;

    private String path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Double getReprojectionErrorCam0Mean() {
        return reprojectionErrorCam0Mean;
    }

    public void setReprojectionErrorCam0Mean(Double reprojectionErrorCam0Mean) {
        this.reprojectionErrorCam0Mean = reprojectionErrorCam0Mean;
    }

    public Double getReprojectionErrorCam0Median() {
        return reprojectionErrorCam0Median;
    }

    public void setReprojectionErrorCam0Median(Double reprojectionErrorCam0Median) {
        this.reprojectionErrorCam0Median = reprojectionErrorCam0Median;
    }

    public Double getReprojectionErrorCam0Std() {
        return reprojectionErrorCam0Std;
    }

    public void setReprojectionErrorCam0Std(Double reprojectionErrorCam0Std) {
        this.reprojectionErrorCam0Std = reprojectionErrorCam0Std;
    }

    public Double getGyroscopeErrorImu0Mean() {
        return gyroscopeErrorImu0Mean;
    }

    public void setGyroscopeErrorImu0Mean(Double gyroscopeErrorImu0Mean) {
        this.gyroscopeErrorImu0Mean = gyroscopeErrorImu0Mean;
    }

    public Double getGyroscopeErrorImu0Median() {
        return gyroscopeErrorImu0Median;
    }

    public void setGyroscopeErrorImu0Median(Double gyroscopeErrorImu0Median) {
        this.gyroscopeErrorImu0Median = gyroscopeErrorImu0Median;
    }

    public Double getGyroscopeErrorImu0Std() {
        return gyroscopeErrorImu0Std;
    }

    public void setGyroscopeErrorImu0Std(Double gyroscopeErrorImu0Std) {
        this.gyroscopeErrorImu0Std = gyroscopeErrorImu0Std;
    }

    public Double getAccelerometerErrorImu0Mean() {
        return accelerometerErrorImu0Mean;
    }

    public void setAccelerometerErrorImu0Mean(Double accelerometerErrorImu0Mean) {
        this.accelerometerErrorImu0Mean = accelerometerErrorImu0Mean;
    }

    public Double getAccelerometerErrorImu0Median() {
        return accelerometerErrorImu0Median;
    }

    public void setAccelerometerErrorImu0Median(Double accelerometerErrorImu0Median) {
        this.accelerometerErrorImu0Median = accelerometerErrorImu0Median;
    }

    public Double getAccelerometerErrorImu0Std() {
        return accelerometerErrorImu0Std;
    }

    public void setAccelerometerErrorImu0Std(Double accelerometerErrorImu0Std) {
        this.accelerometerErrorImu0Std = accelerometerErrorImu0Std;
    }

    public Double getTxMm() {
        return txMm;
    }

    public void setTxMm(Double txMm) {
        this.txMm = txMm;
    }

    public Double getTyMm() {
        return tyMm;
    }

    public void setTyMm(Double tyMm) {
        this.tyMm = tyMm;
    }

    public Double getTzMm() {
        return tzMm;
    }

    public void setTzMm(Double tzMm) {
        this.tzMm = tzMm;
    }

    public Double getTimeshiftMs() {
        return timeshiftMs;
    }

    public void setTimeshiftMs(Double timeshiftMs) {
        this.timeshiftMs = timeshiftMs;
    }

    public Double getqAngle() {
        return qAngle;
    }

    public void setqAngle(Double qAngle) {
        this.qAngle = qAngle;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "MoImuCalibResults{" +
            "id = " + id +
            ", caseName = " + caseName +
            ", sn = " + sn +
            ", reprojectionErrorCam0Mean = " + reprojectionErrorCam0Mean +
            ", reprojectionErrorCam0Median = " + reprojectionErrorCam0Median +
            ", reprojectionErrorCam0Std = " + reprojectionErrorCam0Std +
            ", gyroscopeErrorImu0Mean = " + gyroscopeErrorImu0Mean +
            ", gyroscopeErrorImu0Median = " + gyroscopeErrorImu0Median +
            ", gyroscopeErrorImu0Std = " + gyroscopeErrorImu0Std +
            ", accelerometerErrorImu0Mean = " + accelerometerErrorImu0Mean +
            ", accelerometerErrorImu0Median = " + accelerometerErrorImu0Median +
            ", accelerometerErrorImu0Std = " + accelerometerErrorImu0Std +
            ", txMm = " + txMm +
            ", tyMm = " + tyMm +
            ", tzMm = " + tzMm +
            ", timeshiftMs = " + timeshiftMs +
            ", qAngle = " + qAngle +
            ", root = " + root +
            ", path = " + path +
        "}";
    }
}
