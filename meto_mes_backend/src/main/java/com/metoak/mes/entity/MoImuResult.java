package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("mo_imu_result")
public class MoImuResult {

    private String cameraSn;

    private Double rRoll;

    private Double rPitch;

    private Double rYaw;

    private Double tX;

    private Double tY;

    private Double tZ;

    private Double accelerometerErrorMean;

    private Double accelerometerErrorStd;

    private Double gyroscopeErrorMean;

    private Double gyroscopeErrorStd;

    private Double reprojectionErrorMean;

    private Double reprojectionErrorStd;

    private Double accelerometerNoiseDensity;

    private Double accelerometerRandomWalk;

    private Double gyroscopeNoiseDensity;

    private Double gyroscopeRandomWalk;

    private Integer position;

    private Long calibresultId;

    private Integer status;

    private String errorNo;


}
