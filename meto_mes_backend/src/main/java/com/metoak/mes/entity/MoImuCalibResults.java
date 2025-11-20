package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.metoak.mes.common.annotate.FieldCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author kevin
 * @since 2025-07-01 19:21:55
 */
@Data
@TableName("mo_imu_calib_results")
@ApiModel(value = "MoImuCalibResults对象", description = "")
public class MoImuCalibResults implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @FieldCode(no = "000", type = "val")
    private String sn;

    @FieldCode(no = "001", type = "val")
    private String caseName;

    @FieldCode(no = "002", type = "val")
    private Double reprojectionErrorCam0Mean;

    @FieldCode(no = "003", type = "val")
    private Double reprojectionErrorCam0Median;

    @FieldCode(no = "004", type = "val")
    private Double reprojectionErrorCam0Std;

    @FieldCode(no = "005", type = "val")
    private Double gyroscopeErrorImu0Mean;

    @FieldCode(no = "006", type = "val")
    private Double gyroscopeErrorImu0Median;

    @FieldCode(no = "007", type = "val")
    private Double gyroscopeErrorImu0Std;

    @FieldCode(no = "008", type = "val")
    private Double accelerometerErrorImu0Mean;

    @FieldCode(no = "009", type = "val")
    private Double accelerometerErrorImu0Median;

    @FieldCode(no = "010", type = "val")
    private Double accelerometerErrorImu0Std;

    @FieldCode(no = "011", type = "val")
    private Double txMm;

    @FieldCode(no = "012", type = "val")
    private Double tyMm;

    @FieldCode(no = "013", type = "val")
    private Double tzMm;

    @FieldCode(no = "014", type = "val")
    private Double timeshiftMs;

    @FieldCode(no = "015", type = "val")
    private Double qAngle;

    @FieldCode(no = "016", type = "val")
    private String root;

    @FieldCode(no = "017", type = "val")
    private String path;

    @FieldCode(no = "018", type = "val")
    private String relativePathBag;

    @FieldCode(no = "019", type = "val")
    private String relativePathYmlImu;

    @FieldCode(no = "020", type = "val")
    private String relativePathYmlImucam;

    @FieldCode(no = "021", type = "val")
    private String softwareTool;

    @FieldCode(no = "022", type = "val")
    private String softwareToolVersion;

    @FieldCode(no = "023", type = "val")
    private Long moCalibrationId;


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
