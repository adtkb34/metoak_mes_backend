package com.metoak.mes.k3Cloud.dto;

import lombok.Data;

@Data
public class BarcodeSaveRequest {

    /** 条形码 */
    private String barcode;

    /** 条码规则编码 */
    private String barcodeRule;

    /** 金蝶物料编码（如果需要） */
    private String materialCode;
}
