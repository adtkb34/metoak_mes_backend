package com.metoak.mes.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metoak.mes.common.mapping.ProcessMappingRegistry;
import com.metoak.mes.common.mapping.StepMapping;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.common.util.FieldExtractor;
import com.metoak.mes.dto.OptionDto;
import com.metoak.mes.dto.WorkOrderDto;
import com.metoak.mes.enums.StepMappingEnum;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xukaiwen
 * @since 2024-10-14 09:43:16
 */
@RestController
@RequestMapping("/api/mes/v1/spc")
public class SPCController {

    @Autowired
    private FieldExtractor fieldExtractor;

    @GetMapping("steps")
    @Operation(summary = "获取工序")
    public Result<List<OptionDto>> fetchSteps() {
       return Result.ok(Arrays.stream(StepMappingEnum.values())
               .map(step -> new OptionDto(step.getCode(), step.getDescription()))
               .collect(Collectors.toList()));
    }

    @GetMapping("attrs")
    @Operation(summary = "通过工序获取属性")
    public Result<List<OptionDto>> fetchAttrsByStep(@RequestParam String stepNo) {
        System.out.println(stepNo);
        ProcessMappingRegistry.ProcessMapping processMapping = ProcessMappingRegistry.get(stepNo);
        Class<?> entityClass = processMapping.getEntityClass();
        return Result.ok(FieldExtractor.extractValFields(entityClass));
    }


    @GetMapping
    @Operation(summary = "通过工序获取属性值")
    public Result<List<Object>> fetch(@RequestParam String stepNo,
                                         @RequestParam String field,
                                      @RequestParam(required = false) String start,
                                      @RequestParam(required = false) String end,
                                      @RequestParam Integer limit) {

        return Result.ok( fieldExtractor.getFieldValuesByNo(ProcessMappingRegistry.get(stepNo), field, start, end, limit));
    }
}
