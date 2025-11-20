package com.metoak.mes.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProductMaterialBatch implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String beamSn;

    private String material;

    private String batchNo;

    private String plc;

    private String plcVersion;

    private String operator;

    private String serialNo;

    private String workshopNo;

    private String workstationNo;

    private String productionProcessNo;

    public ProductMaterialBatch() {
    }

    public ProductMaterialBatch(String beamSn, String material, String operator, String serialNo, String workstationNo) {
        this.beamSn = beamSn;
        this.material = material;
        this.operator = operator;
        this.serialNo = serialNo;
        this.workstationNo = workstationNo;
    }

    public ProductMaterialBatch(String beamSn, String material, String batchNo, String plc, String plcVersion, String operator, String serialNo, String workshopNo, String workstationNo, String productionProcessNo) {
        this.beamSn = beamSn;
        this.material = material;
        this.batchNo = batchNo;
        this.plc = plc;
        this.plcVersion = plcVersion;
        this.operator = operator;
        this.serialNo = serialNo;
        this.workshopNo = workshopNo;
        this.workstationNo = workstationNo;
        this.productionProcessNo = productionProcessNo;
    }
}