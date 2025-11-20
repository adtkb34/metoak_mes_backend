package com.metoak.mes.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProcessFlowDto {

    private String processCode;
    private String processName;
    private String processDesc;
    private List<String> stageCodes;
    private List<String> stageNames;

}
