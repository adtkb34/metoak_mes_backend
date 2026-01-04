package com.metoak.mes.params.controller;

import com.metoak.mes.common.MOException;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.common.result.ResultCodeEnum;
import com.metoak.mes.params.dto.ParamBaseCreateDto;
import com.metoak.mes.params.dto.ParamDetailCreateDto;
import com.metoak.mes.params.dto.ParamsQueryDto;
import com.metoak.mes.params.entity.MoParamsBase;
import com.metoak.mes.params.entity.MoParamsDetail;
import com.metoak.mes.params.service.IMoParamsBaseService;
import com.metoak.mes.params.service.IMoParamsDetailService;
import com.metoak.mes.params.vo.MoParamsVo;
import com.metoak.mes.params.vo.ParamsPayload;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 参数管理 前端控制器
 * </p>
 *
 * @author Qoder
 * @since 2025-11-18
 */
@RestController
@RequestMapping("/api/mes/v1/params")
@Tag(name = "参数管理", description = "参数集基础信息和详情管理")
public class ParamsController {

    @Autowired
    private IMoParamsBaseService paramsBaseService;

    @Autowired
    private IMoParamsDetailService paramsDetailService;

    @Operation(summary = "根据类型获取参数集列表")
    @PostMapping
    public Result<List<MoParamsVo>> list(@RequestBody ParamsQueryDto paramsQueryDto) {
        return paramsBaseService.list(paramsQueryDto);
    }


    @GetMapping
    public Result<ParamsPayload> list(
            @RequestParam(required = false) String sn,
            @RequestParam(required = false) Long orderId,
            @RequestParam(required = false) String flow,
            @RequestParam(required = false) String stepNo,
            @RequestParam(required = false) Long id
    ) {
        if (sn != null) return paramsBaseService.getBySn(sn);
        else if (orderId != null) return paramsBaseService.getByOrderId(orderId);
        else if (flow != null) return paramsBaseService.getByFlowNo(flow);
        else if (id != null) return paramsBaseService.getByDetailId(id);
        else if (stepNo != null) return paramsBaseService.getByStepNo(stepNo);
        else throw new MOException(ResultCodeEnum.PARAMETER_NOT_PROVIDED);
    }

    @Operation(summary = "保存参数集基础信息")
    @PostMapping("/base")
    public Result<Long> saveBase(@RequestBody ParamBaseCreateDto createDto) {
        return paramsBaseService.saveBase(createDto);
    }

    @Operation(summary = "获取参数集基础信息")
    @GetMapping("/base")
    public Result<List<MoParamsBase>> list(@RequestParam Integer type) {
        return paramsBaseService.listByType(type);
    }

    @Operation(summary = "保存参数集详情并自动生成版本")
    @PostMapping("/detail")
    public Result<Long> saveDetail(@RequestBody ParamDetailCreateDto createDto) {
        return paramsDetailService.saveDetail(createDto);
    }

    @Operation(summary = "获取参数集详情")
    @GetMapping("/detail/{detailId}")
    public Result<MoParamsDetail> getDetail(@PathVariable("detailId") Long detailId) {
        return Result.ok(paramsDetailService.getById(detailId));
    }


}
