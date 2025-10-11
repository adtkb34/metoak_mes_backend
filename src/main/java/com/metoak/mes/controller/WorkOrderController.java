package com.metoak.mes.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.dto.ProcessFlowDto;
import com.metoak.mes.dto.WorkOrderDto;
import com.metoak.mes.entity.MoProduceOrder;
import com.metoak.mes.service.IMoProduceOrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/api/mes/v1/workOrders")
public class WorkOrderController {

    @Autowired
    private IMoProduceOrderService moProduceOrderService;

    @GetMapping
    @Operation(summary = "获取所有的工单")
    public Result<List<WorkOrderDto>> f() {
        try {
            String projectDir = System.getProperty("user.dir"); // 当前运行目录
            String scriptPath = projectDir + "/common/py/get_work_order.py";
            // Python 命令 + 参数
            ProcessBuilder pb = new ProcessBuilder("python", scriptPath);

            pb.redirectErrorStream(true); // 合并错误输出流

            Process process = pb.start();

            // 获取输出结果
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8)
            );

            String line;
            ArrayList<WorkOrderDto> workOrderDtos = new ArrayList<>();
            List<MoProduceOrder> moProduceOrders = null;
            while ((line = reader.readLine()) != null) {
                ObjectMapper mapper = new ObjectMapper();
                List<List<Object>> rawList = mapper.readValue(line, new TypeReference<List<List<Object>>>() {
                });

                moProduceOrders = rawList.stream().map(arr -> {
                    MoProduceOrder p = new MoProduceOrder();
                    p.setWorkOrderCode((String) arr.get(0));
                    p.setMaterialName((String) arr.get(1));
                    p.setProduceCount((int) ((Number) arr.get(2)).doubleValue());
                    p.setPlannedStarttime(LocalDateTime.parse((String) arr.get(3)));
                    p.setPlannedEndtime(LocalDateTime.parse((String) arr.get(4)));
                    p.setMaterialCode((String) arr.get(5));
//                    p.setOrderState((int) arr.get(5));
                    return p;
                }).toList();
            }
            moProduceOrders.forEach(item -> {
                moProduceOrderService.updateOrAddByCode(item);
            });

            Map<String, String> map = moProduceOrderService.list().stream()
                    .filter(f -> f.getMaterialCode()   != null)
                    .filter(f -> f.getFlowCode() != null)
                    .collect(Collectors.toMap(
                            MoProduceOrder::getMaterialCode,          // key = 料号
                            MoProduceOrder::getFlowCode,   // value = 工艺编号
                            (v1, v2) -> v2                     // 重复 key 时取后者
                    ));
            moProduceOrderService.list().forEach(item -> {
                if (item.getFlowCode() == null && map.containsKey(item.getMaterialCode())) {
                    item.setFlowCode(map.get(item.getMaterialCode()));
                    moProduceOrderService.updateById(item);
                }
                workOrderDtos.add(
                        WorkOrderDto.builder().
                                id(item.getId()).
                                no(item.getWorkOrderCode()).
                                spuProductNo(item.getMaterialCode()).
                                plannedStartTime(item.getPlannedStarttime().toString()).
                                plannedEndTime(item.getPlannedEndtime().toString()).
                                productCount(item.getProduceCount()).
                                processFlowNo(item.getFlowCode()).
                                build());
            });
            return Result.ok(workOrderDtos);
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }

    }

    @PutMapping
    @Operation(summary = "更新工单中的工艺编号")
    public Result<Boolean> edit(@RequestBody @Valid WorkOrderDto workOrderDto) {
        LambdaUpdateWrapper<MoProduceOrder> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(MoProduceOrder::getWorkOrderCode, workOrderDto.getNo()).set(MoProduceOrder::getFlowCode, workOrderDto.getProcessFlowNo());

        return  Result.ok(moProduceOrderService.update(wrapper));
    }
}
