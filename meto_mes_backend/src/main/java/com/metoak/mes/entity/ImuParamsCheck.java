package com.metoak.mes.entity;

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
 * @since 2025-11-18 19:08:39
 */
@TableName("imu_params_check")
@ApiModel(value = "ImuParamsCheck对象", description = "")
public class ImuParamsCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("taskid")
    private Long taskid;

    private String sn;

    private Long stepid;

    private Long result;

    private LocalDateTime timestamp;

    private Double accNoise;

    private Double accNoiseStd;

    private Double accRandomWalk;

    private Double accRandomWalkStd;

    private Double gyrNoise;

    private Double gyrNoiseStd;

    private Double gyrRandomWalk;

    private Double gyrRandomWalkStd;

    private Double rxLeft;

    private Double ryLeft;

    private Double rzLeft;

    private Double txLeft;

    private Double tyLeft;

    private Double tzLeft;

    private Double txLeftStdFloor;

    private Double txLeftStdCeil;

    private Double tyLeftStdFloor;

    private Double tyLeftStdCeil;

    private Double tzLeftStdFloor;

    private Double tzLeftStdCeil;

    private Double rxRight;

    private Double ryRight;

    private Double rzRight;

    private Double txRight;

    private Double tyRight;

    private Double tzRight;

    private Double qangleLeft;

    private Double qangleLeftStd;

    public Long getTaskid() {
        return taskid;
    }

    public void setTaskid(Long taskid) {
        this.taskid = taskid;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Long getStepid() {
        return stepid;
    }

    public void setStepid(Long stepid) {
        this.stepid = stepid;
    }

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Double getAccNoise() {
        return accNoise;
    }

    public void setAccNoise(Double accNoise) {
        this.accNoise = accNoise;
    }

    public Double getAccNoiseStd() {
        return accNoiseStd;
    }

    public void setAccNoiseStd(Double accNoiseStd) {
        this.accNoiseStd = accNoiseStd;
    }

    public Double getAccRandomWalk() {
        return accRandomWalk;
    }

    public void setAccRandomWalk(Double accRandomWalk) {
        this.accRandomWalk = accRandomWalk;
    }

    public Double getAccRandomWalkStd() {
        return accRandomWalkStd;
    }

    public void setAccRandomWalkStd(Double accRandomWalkStd) {
        this.accRandomWalkStd = accRandomWalkStd;
    }

    public Double getGyrNoise() {
        return gyrNoise;
    }

    public void setGyrNoise(Double gyrNoise) {
        this.gyrNoise = gyrNoise;
    }

    public Double getGyrNoiseStd() {
        return gyrNoiseStd;
    }

    public void setGyrNoiseStd(Double gyrNoiseStd) {
        this.gyrNoiseStd = gyrNoiseStd;
    }

    public Double getGyrRandomWalk() {
        return gyrRandomWalk;
    }

    public void setGyrRandomWalk(Double gyrRandomWalk) {
        this.gyrRandomWalk = gyrRandomWalk;
    }

    public Double getGyrRandomWalkStd() {
        return gyrRandomWalkStd;
    }

    public void setGyrRandomWalkStd(Double gyrRandomWalkStd) {
        this.gyrRandomWalkStd = gyrRandomWalkStd;
    }

    public Double getRxLeft() {
        return rxLeft;
    }

    public void setRxLeft(Double rxLeft) {
        this.rxLeft = rxLeft;
    }

    public Double getRyLeft() {
        return ryLeft;
    }

    public void setRyLeft(Double ryLeft) {
        this.ryLeft = ryLeft;
    }

    public Double getRzLeft() {
        return rzLeft;
    }

    public void setRzLeft(Double rzLeft) {
        this.rzLeft = rzLeft;
    }

    public Double getTxLeft() {
        return txLeft;
    }

    public void setTxLeft(Double txLeft) {
        this.txLeft = txLeft;
    }

    public Double getTyLeft() {
        return tyLeft;
    }

    public void setTyLeft(Double tyLeft) {
        this.tyLeft = tyLeft;
    }

    public Double getTzLeft() {
        return tzLeft;
    }

    public void setTzLeft(Double tzLeft) {
        this.tzLeft = tzLeft;
    }

    public Double getTxLeftStdFloor() {
        return txLeftStdFloor;
    }

    public void setTxLeftStdFloor(Double txLeftStdFloor) {
        this.txLeftStdFloor = txLeftStdFloor;
    }

    public Double getTxLeftStdCeil() {
        return txLeftStdCeil;
    }

    public void setTxLeftStdCeil(Double txLeftStdCeil) {
        this.txLeftStdCeil = txLeftStdCeil;
    }

    public Double getTyLeftStdFloor() {
        return tyLeftStdFloor;
    }

    public void setTyLeftStdFloor(Double tyLeftStdFloor) {
        this.tyLeftStdFloor = tyLeftStdFloor;
    }

    public Double getTyLeftStdCeil() {
        return tyLeftStdCeil;
    }

    public void setTyLeftStdCeil(Double tyLeftStdCeil) {
        this.tyLeftStdCeil = tyLeftStdCeil;
    }

    public Double getTzLeftStdFloor() {
        return tzLeftStdFloor;
    }

    public void setTzLeftStdFloor(Double tzLeftStdFloor) {
        this.tzLeftStdFloor = tzLeftStdFloor;
    }

    public Double getTzLeftStdCeil() {
        return tzLeftStdCeil;
    }

    public void setTzLeftStdCeil(Double tzLeftStdCeil) {
        this.tzLeftStdCeil = tzLeftStdCeil;
    }

    public Double getRxRight() {
        return rxRight;
    }

    public void setRxRight(Double rxRight) {
        this.rxRight = rxRight;
    }

    public Double getRyRight() {
        return ryRight;
    }

    public void setRyRight(Double ryRight) {
        this.ryRight = ryRight;
    }

    public Double getRzRight() {
        return rzRight;
    }

    public void setRzRight(Double rzRight) {
        this.rzRight = rzRight;
    }

    public Double getTxRight() {
        return txRight;
    }

    public void setTxRight(Double txRight) {
        this.txRight = txRight;
    }

    public Double getTyRight() {
        return tyRight;
    }

    public void setTyRight(Double tyRight) {
        this.tyRight = tyRight;
    }

    public Double getTzRight() {
        return tzRight;
    }

    public void setTzRight(Double tzRight) {
        this.tzRight = tzRight;
    }

    public Double getQangleLeft() {
        return qangleLeft;
    }

    public void setQangleLeft(Double qangleLeft) {
        this.qangleLeft = qangleLeft;
    }

    public Double getQangleLeftStd() {
        return qangleLeftStd;
    }

    public void setQangleLeftStd(Double qangleLeftStd) {
        this.qangleLeftStd = qangleLeftStd;
    }

    @Override
    public String toString() {
        return "ImuParamsCheck{" +
            "taskid = " + taskid +
            ", sn = " + sn +
            ", stepid = " + stepid +
            ", result = " + result +
            ", timestamp = " + timestamp +
            ", accNoise = " + accNoise +
            ", accNoiseStd = " + accNoiseStd +
            ", accRandomWalk = " + accRandomWalk +
            ", accRandomWalkStd = " + accRandomWalkStd +
            ", gyrNoise = " + gyrNoise +
            ", gyrNoiseStd = " + gyrNoiseStd +
            ", gyrRandomWalk = " + gyrRandomWalk +
            ", gyrRandomWalkStd = " + gyrRandomWalkStd +
            ", rxLeft = " + rxLeft +
            ", ryLeft = " + ryLeft +
            ", rzLeft = " + rzLeft +
            ", txLeft = " + txLeft +
            ", tyLeft = " + tyLeft +
            ", tzLeft = " + tzLeft +
            ", txLeftStdFloor = " + txLeftStdFloor +
            ", txLeftStdCeil = " + txLeftStdCeil +
            ", tyLeftStdFloor = " + tyLeftStdFloor +
            ", tyLeftStdCeil = " + tyLeftStdCeil +
            ", tzLeftStdFloor = " + tzLeftStdFloor +
            ", tzLeftStdCeil = " + tzLeftStdCeil +
            ", rxRight = " + rxRight +
            ", ryRight = " + ryRight +
            ", rzRight = " + rzRight +
            ", txRight = " + txRight +
            ", tyRight = " + tyRight +
            ", tzRight = " + tzRight +
            ", qangleLeft = " + qangleLeft +
            ", qangleLeftStd = " + qangleLeftStd +
        "}";
    }
}
