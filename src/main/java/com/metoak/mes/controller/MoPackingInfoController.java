package com.metoak.mes.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.entity.MoPackingInfo;
import com.metoak.mes.mapper.MoPackingInfoMapper;
import com.metoak.mes.service.IMoPackingInfoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/mes/v1/packing")
public class MoPackingInfoController {

    @Autowired
    private IMoPackingInfoService service;

    @GetMapping
    @Operation(summary = "获取相机打包信息")
    public Result listPackingInfo(@RequestParam(required = false) String serialNo,
                                  @RequestParam(required = false) String cameraPartTypeId) {
        LambdaQueryWrapper<MoPackingInfo> wrapper = new LambdaQueryWrapper<MoPackingInfo>().eq(MoPackingInfo::getCameraSn, serialNo);
        List<MoPackingInfo> list = service.list(wrapper);
        return Result.ok(list);
    }

    @PutMapping("update2ReturnRepair/byPackingCode")
    @Operation(summary = "通过箱号将记录更改为返厂状态")
    public Result updateByPackingCode(@RequestParam String packingCode,
                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnRepairDate) {
        return Result.ok(service.updateByPackingCode(packingCode, returnRepairDate));
    }

    @PutMapping("update2ReturnRepair/byCameraSns")
    @Operation(summary = "通过序列号将记录更改为返厂状态")
    public Result updateByCameraSns(@RequestParam List<String> cameraSns,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnRepairDate) {
        return Result.ok(service.updateByCameraSns(cameraSns, returnRepairDate));
    }

    @DeleteMapping("byPackingCode")
    @Operation(summary = "通过箱号将记录删除")
    public Result deleteByPackingCode(@RequestParam String packingCode) {
        return Result.ok(service.deleteByPackingCode(packingCode));
    }

    @DeleteMapping("byCameraSns")
    @Operation(summary = "通过序列号将记录删除")
    public Result deleteByCameraSns(@RequestParam List<String> cameraSns) {
        return Result.ok(service.deleteByCameraSns(cameraSns));
    }
}
