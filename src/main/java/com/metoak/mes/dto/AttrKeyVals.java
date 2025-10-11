package com.metoak.mes.dto;

import lombok.Data;

import java.util.Map;

@Data
public class AttrKeyVals {
    private Map<String, Map<String, StepAttributeDto>> steps;
    private Map<String, Map<String, DeviceAttribute>> devices;

}