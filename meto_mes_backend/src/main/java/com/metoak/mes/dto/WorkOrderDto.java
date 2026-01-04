package com.metoak.mes.dto;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrderDto {
    private Long id;
    private String no;
    private String spuProductNo;
    private Integer productCount;
    private String plannedEndTime;
    private String plannedStartTime;
    private String statusName;
    private String processFlowNo;

}