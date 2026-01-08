package com.metoak.mes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDto {

    private String category;

    private String categoryNo;

    private String serialNo;

    private String batchNo;

    private Integer position;

    private Boolean isProduct;

    private Boolean isBatchNoContainsMaterialNo;



    @Override
    public String toString() {
        return "MaterialDto{" +
                "category='" + category + '\'' +
                ", categoryNo='" + categoryNo + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", position=" + position +
                ", isProduct=" + isProduct +
                ", isBatchNoContainsMaterialNo=" + isBatchNoContainsMaterialNo +
                '}';
    }
}