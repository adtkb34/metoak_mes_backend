package com.metoak.mes.controller;

import com.metoak.mes.common.ResultBean;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.dto.ProcessFlowDto;
import com.metoak.mes.entity.MoProcessFlow;
import com.metoak.mes.entity.MoWorkstage;
import com.metoak.mes.enums.ResultCodeEnum;
import com.metoak.mes.service.IMoProcessFlowService;
import com.metoak.mes.service.IMoWorkstageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 生产流程/工艺流程 前端控制器
 * </p>
 *
 * @author kevin
 * @since 2025-06-17 18:02:42
 */
@RestController
@RequestMapping("/api/mes/v1/basic-information/process-flow")
public class MoProcessFlowController {

    @Autowired
    private IMoProcessFlowService moProcessFlowService;

    @Autowired
    private IMoWorkstageService moWorkstageService;

    @GetMapping
    @Operation(summary = "获取所有的工艺")
    public Result<List<ProcessFlowDto>> fetchMulti() {
        List<MoProcessFlow> srcList = moProcessFlowService.list();
        if (srcList.isEmpty()) {
            return Result.ok(Collections.emptyList());
        }

        // 按 processCode 分组
        Map<String, List<MoProcessFlow>> groupMap = srcList.stream()
                .collect(Collectors.groupingBy(MoProcessFlow::getProcessCode));

        List<ProcessFlowDto> result = groupMap.entrySet().stream()
                .map(entry -> {
                    String processCode = entry.getKey();
                    List<MoProcessFlow> list = entry.getValue();

                    ProcessFlowDto dto = new ProcessFlowDto();
                    dto.setProcessCode(processCode);
                    dto.setProcessName(list.get(0).getProcessName());
                    dto.setProcessDesc(list.get(0).getProcessDesc());
                    dto.setStageNames(
                            list.stream()
                                    .map(MoProcessFlow::getStageCode)
                                    .map(stageCode -> moWorkstageService.getByCode(stageCode)) // 或你自定义的查法
                                    .collect(Collectors.toList())
                    );
                    return dto;
                })
                .collect(Collectors.toList());

        return Result.ok(result);
    }

    @PostMapping
    @Operation(summary = "添加工艺")
    public Result<Integer> add(@RequestBody @Valid ProcessFlowDto processFlowDto) {
        if (!moProcessFlowService.fetchByCode(processFlowDto.getProcessCode()).isEmpty()) {
            return Result.fail(-1, "添加失败，工艺编号存在");
        }
        moProcessFlowService.add(processFlowDto);

        return Result.ok();
    }

    @PatchMapping
    @Operation(summary = "更新工艺")
    public Result<Boolean> edit(@RequestBody @Valid ProcessFlowDto processFlowDto) {

        return Result.ok(moProcessFlowService.editByCode(processFlowDto));
    }

}
