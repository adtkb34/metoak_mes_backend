package com.metoak.mes.params.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.metoak.mes.common.ResultBean;
import com.metoak.mes.dto.ParamsBaseDto;
import com.metoak.mes.dto.ParamsDetailDto;
import com.metoak.mes.dto.ParamsUploadRequest;
import com.metoak.mes.params.entity.MoParamsBase;
import com.metoak.mes.params.entity.MoParamsDetail;
import com.metoak.mes.params.service.IMoParamsBaseService;
import com.metoak.mes.params.service.IMoParamsDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 参数管理 前端控制器
 * </p>
 *
 * @author Qoder
 * @since 2025-11-18
 */
@RestController
@RequestMapping("/api/v1/params")
@Tag(name = "参数管理", description = "参数集基础信息和详情管理")
public class ParamsController {

    @Autowired
    private IMoParamsBaseService paramsBaseService;

    @Autowired
    private IMoParamsDetailService paramsDetailService;

    @Operation(summary = "参数集上传并自动管理版本")
    @PostMapping("/upload")
    public ResultBean<ParamsDetailDto> uploadParams(@RequestBody ParamsUploadRequest request) {
        return paramsDetailService.uploadParams(request);
    }

    @Operation(summary = "创建参数集基础信息")
    @PostMapping("/base")
    public ResultBean<ParamsBaseDto> createParamsBase(@RequestBody ParamsBaseDto paramsBaseDto) {
        MoParamsBase paramsBase = new MoParamsBase();
        BeanUtils.copyProperties(paramsBaseDto, paramsBase);
        paramsBase.setCreatedAt(LocalDateTime.now());
        paramsBaseService.save(paramsBase);
        
        ParamsBaseDto result = new ParamsBaseDto();
        BeanUtils.copyProperties(paramsBase, result);
        return ResultBean.ok(result);
    }

    @Operation(summary = "获取参数集基础信息列表")
    @GetMapping("/base")
    public ResultBean<List<ParamsBaseDto>> getParamsBaseList(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer type,
            @RequestParam(required = false) String materialCode) {
        
        LambdaQueryWrapper<MoParamsBase> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, MoParamsBase::getName, name);
        queryWrapper.eq(type != null, MoParamsBase::getType, type);
        queryWrapper.like(materialCode != null, MoParamsBase::getMaterialCode, materialCode);
        
        List<MoParamsBase> list = paramsBaseService.list(queryWrapper);
        List<ParamsBaseDto> result = list.stream().map(item -> {
            ParamsBaseDto dto = new ParamsBaseDto();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).collect(Collectors.toList());
        
        return ResultBean.ok(result);
    }

    @Operation(summary = "根据ID获取参数集基础信息")
    @GetMapping("/base/{id}")
    public ResultBean<ParamsBaseDto> getParamsBaseById(@PathVariable Long id) {
        MoParamsBase paramsBase = paramsBaseService.getById(id);
        if (paramsBase == null) {
            return ResultBean.fail(201, "参数集基础信息不存在");
        }
        
        ParamsBaseDto result = new ParamsBaseDto();
        BeanUtils.copyProperties(paramsBase, result);
        return ResultBean.ok(result);
    }

    @Operation(summary = "更新参数集基础信息")
    @PutMapping("/base/{id}")
    public ResultBean<ParamsBaseDto> updateParamsBase(@PathVariable Long id, @RequestBody ParamsBaseDto paramsBaseDto) {
        MoParamsBase paramsBase = paramsBaseService.getById(id);
        if (paramsBase == null) {
            return ResultBean.fail(201, "参数集基础信息不存在");
        }
        
        BeanUtils.copyProperties(paramsBaseDto, paramsBase, "id", "createdAt");
        paramsBaseService.updateById(paramsBase);
        
        ParamsBaseDto result = new ParamsBaseDto();
        BeanUtils.copyProperties(paramsBase, result);
        return ResultBean.ok(result);
    }

    @Operation(summary = "删除参数集基础信息")
    @DeleteMapping("/base/{id}")
    public ResultBean<Void> deleteParamsBase(@PathVariable Long id) {
        boolean result = paramsBaseService.removeById(id);
        if (!result) {
            return ResultBean.fail(201, "参数集基础信息不存在或删除失败");
        }
        return ResultBean.ok();
    }

    @Operation(summary = "创建参数集详情")
    @PostMapping("/detail")
    public ResultBean<ParamsDetailDto> createParamsDetail(@RequestBody ParamsDetailDto paramsDetailDto) {
        MoParamsDetail paramsDetail = new MoParamsDetail();
        BeanUtils.copyProperties(paramsDetailDto, paramsDetail);
        paramsDetail.setCreatedAt(LocalDateTime.now());
        paramsDetailService.save(paramsDetail);
        
        ParamsDetailDto result = new ParamsDetailDto();
        BeanUtils.copyProperties(paramsDetail, result);
        return ResultBean.ok(result);
    }

    @Operation(summary = "获取参数集详情列表")
    @GetMapping("/detail")
    public ResultBean<List<ParamsDetailDto>> getParamsDetailList(
            @RequestParam(required = false) Long baseId,
            @RequestParam(required = false) Integer isActive) {
        
        LambdaQueryWrapper<MoParamsDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(baseId != null, MoParamsDetail::getBaseId, baseId);
        queryWrapper.eq(isActive != null, MoParamsDetail::getIsActive, isActive);
        
        List<MoParamsDetail> list = paramsDetailService.list(queryWrapper);
        List<ParamsDetailDto> result = list.stream().map(item -> {
            ParamsDetailDto dto = new ParamsDetailDto();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).collect(Collectors.toList());
        
        return ResultBean.ok(result);
    }

    @Operation(summary = "根据ID获取参数集详情")
    @GetMapping("/detail/{id}")
    public ResultBean<ParamsDetailDto> getParamsDetailById(@PathVariable Long id) {
        MoParamsDetail paramsDetail = paramsDetailService.getById(id);
        if (paramsDetail == null) {
            return ResultBean.fail(201, "参数集详情不存在");
        }
        
        ParamsDetailDto result = new ParamsDetailDto();
        BeanUtils.copyProperties(paramsDetail, result);
        return ResultBean.ok(result);
    }

    @Operation(summary = "更新参数集详情")
    @PutMapping("/detail/{id}")
    public ResultBean<ParamsDetailDto> updateParamsDetail(@PathVariable Long id, @RequestBody ParamsDetailDto paramsDetailDto) {
        MoParamsDetail paramsDetail = paramsDetailService.getById(id);
        if (paramsDetail == null) {
            return ResultBean.fail(201, "参数集详情不存在");
        }
        
        BeanUtils.copyProperties(paramsDetailDto, paramsDetail, "id", "createdAt");
        paramsDetailService.updateById(paramsDetail);
        
        ParamsDetailDto result = new ParamsDetailDto();
        BeanUtils.copyProperties(paramsDetail, result);
        return ResultBean.ok(result);
    }

    @Operation(summary = "删除参数集详情")
    @DeleteMapping("/detail/{id}")
    public ResultBean<Void> deleteParamsDetail(@PathVariable Long id) {
        boolean result = paramsDetailService.removeById(id);
        if (!result) {
            return ResultBean.fail(201, "参数集详情不存在或删除失败");
        }
        return ResultBean.ok();
    }

}