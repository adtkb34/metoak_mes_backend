package com.metoak.mes.traceability.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialBindVo {
    private String materialCode;
    private String materialName;
    private String cameraSn;
    private String category;
    private String categoryNo;
    private String materialBatchNo;
    private String materialSerialNo;
    private LocalDateTime createTime;
    private String operator;
    private int isDeleted;
}
