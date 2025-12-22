package com.metoak.mes.params.controller;

import com.metoak.mes.common.ResultBean;
import com.metoak.mes.params.dto.ParamBaseCreateDto;
import com.metoak.mes.params.dto.ParamDetailCreateDto;
import com.metoak.mes.params.service.IMoParamsBaseService;
import com.metoak.mes.params.service.IMoParamsDetailService;
import com.metoak.mes.params.vo.MoParamsVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping
    public ResultBean<List<MoParamsVO>> listByType(@RequestParam Integer type) {
        return paramsBaseService.listByType(type);
    }

    @Operation(summary = "保存参数集基础信息")
    @PostMapping("/base")
    public ResultBean<Long> saveBase(@RequestBody ParamBaseCreateDto createDto) {
        return paramsBaseService.saveBase(createDto);
    }

    @Operation(summary = "保存参数集详情并自动生成版本")
    @PostMapping("/detail")
    public ResultBean<Long> saveDetail(@RequestBody ParamDetailCreateDto createDto) {
        return paramsDetailService.saveDetail(createDto);
    }

}
