package com.metoak.mes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StepAttributeDto {
    private String key;
    private Map<String, TaskDto> tasks;

}
