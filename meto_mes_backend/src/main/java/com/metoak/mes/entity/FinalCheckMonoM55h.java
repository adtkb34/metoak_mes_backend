package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.metoak.mes.common.annotate.FieldCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

/**
 * <p>
 * 
 * </p>
 *
 * @author kevin
 * @since 2025-08-25 10:48:51
 */
@Data
@TableName("final_check_mono_m55h")
@ApiModel(value = "FinalCheckMonoM55h对象", description = "")
public class FinalCheckMonoM55h implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String sn;

    @FieldCode(no = "000", type = "val")
    private Integer errorCode;

    @FieldCode(no = "001", type = "val")
    private String operator;

    @FieldCode(no = "002", type = "val")
    private Boolean imageOk;

    private Long moProcessStepProductionResultId; // 过站记录关联ID

    @FieldCode(no = "004", type = "val")
    private LocalDateTime startTime; // 开始时间

    @FieldCode(no = "005", type = "val")
    private LocalDateTime endTime; // 结束时间

    @FieldCode(no = "006", type = "val")
    private Integer cameraType; // 相机类型: 0竖版;1横版

    // 检测功能相关字段
    @FieldCode(no = "007", type = "val")
    private Boolean isImageDirtyDetectEnabled;

    @FieldCode(no = "008", type = "val")
    private Integer imageDirtyCount;

    @FieldCode(no = "009", type = "val")
    private Boolean isBoardClarityDetectEnabled;

    @FieldCode(no = "010", type = "val")
    private Float boardClarity;

    @FieldCode(no = "011", type = "val")
    private Float boardClarityRefMin;

    @FieldCode(no = "012", type = "val")
    private Boolean isBoardColorCastDetectEnabled;

    @FieldCode(no = "013", type = "val")
    private Float boardColorCastRMean;

    @FieldCode(no = "014", type = "val")
    private Float boardColorCastGMean;

    @FieldCode(no = "015", type = "val")
    private Float boardColorCastBMean;

    @FieldCode(no = "016", type = "val")
    private Float boardColorCastRStddev;

    @FieldCode(no = "017", type = "val")
    private Float boardColorCastGStddev;

    @FieldCode(no = "018", type = "val")
    private Float boardColorCastBStddev;

    @FieldCode(no = "019", type = "val")
    private Float boardColorCastMaxdiffRatioRef;

    @FieldCode(no = "020", type = "val")
    private Float boardColorCastMaxdiffValueRef;

    @FieldCode(no = "021", type = "val")
    private Boolean isBoardCodDetectEnabled;

    @FieldCode(no = "022", type = "val")
    private Float boardCodX;

    @FieldCode(no = "023", type = "val")
    private Float boardCodY;

    @FieldCode(no = "024", type = "val")
    private Float boardCodXRef;

    @FieldCode(no = "025", type = "val")
    private Float boardCodXTolerance;

    @FieldCode(no = "026", type = "val")
    private Float boardCodYRef;

    @FieldCode(no = "027", type = "val")
    private Float boardCodYTolerance;

    @FieldCode(no = "028", type = "val")
    private String imagePath;

    @FieldCode(no = "029", type = "val")
    private Integer checkResult;

    @FieldCode(no = "030", type = "val")
    private Integer can0Ok;

    @FieldCode(no = "031", type = "val")
    private Integer can1Ok;

    @FieldCode(no = "032", type = "val")
    private String versionMcuRef;

    @FieldCode(no = "033", type = "val")
    private String versionProductRef;

    @FieldCode(no = "034", type = "val")
    private String versionMcuStd;

    @FieldCode(no = "035", type = "val")
    private String versionProductStd;

    @Override
    public String toString() {
        return "FinalCheckMonoM55h{" +
            "id = " + id +
            ", sn = " + sn +
            ", errorCode = " + errorCode +
            ", operator = " + operator +
            ", imageOk = " + imageOk +
            ", imagePath = " + imagePath +
            ", checkResult = " + checkResult +
            ", can0Ok = " + can0Ok +
            ", can1Ok = " + can1Ok +
        "}";
    }
}
