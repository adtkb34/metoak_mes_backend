package com.metoak.mes.params.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.metoak.mes.common.ResultBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.*;
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

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Operation(summary = "参数集上传并自动管理版本")
    @PostMapping("/upload")
    public ResultBean<ParamsDetailDto> uploadParams(@RequestBody ParamsUploadRequest request) {
        JsonNode currentParams;
        try {
            currentParams = objectMapper.readTree(request.getParams());
        } catch (JsonProcessingException e) {
            return ResultBean.fail(201, "params 不是有效的 JSON");
        }

        MoParamsBase paramsBase = findOrCreateParamsBase(request);

        List<MoParamsDetail> detailList = paramsDetailService.lambdaQuery()
                .eq(MoParamsDetail::getBaseId, paramsBase.getId())
                .list();

        VersionResult versionResult = determineVersion(detailList, currentParams);

        if (versionResult.existingDetail != null) {
            ParamsDetailDto dto = new ParamsDetailDto();
            BeanUtils.copyProperties(versionResult.existingDetail, dto);
            return ResultBean.ok(dto);
        }

        MoParamsDetail newDetail = new MoParamsDetail();
        newDetail.setBaseId(paramsBase.getId());
        newDetail.setDescription(request.getDescription());
        newDetail.setVersionMajor(versionResult.major);
        newDetail.setVersionMinor(versionResult.minor);
        newDetail.setVersionPatch(versionResult.patch);
        newDetail.setParams(request.getParams());
        newDetail.setIsActive(1);
        newDetail.setCreatedBy(request.getUsername());
        newDetail.setCreatedAt(LocalDateTime.now());

        paramsDetailService.save(newDetail);

        ParamsDetailDto dto = new ParamsDetailDto();
        BeanUtils.copyProperties(newDetail, dto);
        return ResultBean.ok(dto);
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

    private MoParamsBase findOrCreateParamsBase(ParamsUploadRequest request) {
        MoParamsBase paramsBase = paramsBaseService.getOne(new LambdaQueryWrapper<MoParamsBase>()
                .eq(MoParamsBase::getName, request.getName())
                .eq(request.getType() != null, MoParamsBase::getType, request.getType()));

        if (paramsBase != null) {
            return paramsBase;
        }

        MoParamsBase newBase = new MoParamsBase();
        newBase.setName(request.getName());
        newBase.setType(request.getType());
        newBase.setCreatedBy(request.getUsername());
        newBase.setCreatedAt(LocalDateTime.now());
        paramsBaseService.save(newBase);
        return newBase;
    }

    /**
     * 按 major → minor → patch 三层匹配规则决定版本号
     */
    private VersionResult determineVersion(List<MoParamsDetail> history, JsonNode currentParams) {

        if (history == null || history.isEmpty()) {
            // 没有任何历史 → 从 1.0.0 开始
            return new VersionResult(1, 0, 0, null);
        }

        // ====== 第 1 层：匹配 major ======
        int maxMajor = history.stream()
                .map(MoParamsDetail::getVersionMajor)
                .max(Integer::compareTo)
                .orElse(1);

        // 找所有“字段结构包含在当前 params 中”的版本
        List<MoParamsDetail> compatibleMajor = history.stream()
                .filter(item -> containsAllFields(currentParams, parse(item.getParams())))
                .collect(Collectors.toList());

        int major;
        if (compatibleMajor.isEmpty()) {
            // 无兼容 → major + 1
            major = maxMajor + 1;
            return new VersionResult(major, 0, 0, null);
        } else {
            // 取兼容的最大 major（不是 findFirst）
            major = compatibleMajor.stream()
                    .map(MoParamsDetail::getVersionMajor)
                    .max(Integer::compareTo)
                    .orElse(maxMajor + 1);
        }

        // 提取此 major 下的所有版本
        List<MoParamsDetail> sameMajor = history.stream()
                .filter(x -> x.getVersionMajor() == major)
                .collect(Collectors.toList());


        // ====== 第 2 层：匹配 minor ======
        int maxMinor = sameMajor.stream()
                .map(MoParamsDetail::getVersionMinor)
                .max(Integer::compareTo)
                .orElse(0);

        // 找所有 minor 中“字段结构完全包含在当前 params”的版本
        List<MoParamsDetail> compatibleMinor = sameMajor.stream()
                .filter(item -> containsAllFields(parse(item.getParams()), currentParams))
                .collect(Collectors.toList());

        int minor;
        if (compatibleMinor.isEmpty()) {
            minor = maxMinor + 1;
            return new VersionResult(major, minor, 0, null);
        } else {
            // 取兼容的最大 minor
            minor = compatibleMinor.stream()
                    .map(MoParamsDetail::getVersionMinor)
                    .max(Integer::compareTo)
                    .orElse(0);
        }

        List<MoParamsDetail> sameMinor = sameMajor.stream()
                .filter(x -> x.getVersionMinor() == minor)
                .collect(Collectors.toList());


        // ====== 第 3 层：匹配 patch ======
        int maxPatch = sameMinor.stream()
                .map(MoParamsDetail::getVersionPatch)
                .max(Integer::compareTo)
                .orElse(0);

        // 是否存在完全相同的 params？
        for (MoParamsDetail detail : sameMinor) {
            if (jsonEquals(parse(detail.getParams()), currentParams)) {
                // 完全相同 → 不创建版本
                return new VersionResult(major, minor, detail.getVersionPatch(), detail);
            }
        }

        // 未找到完全相同 → patch + 1
        return new VersionResult(major, minor, maxPatch + 1, null);
    }

    /**
     * 判断 JSON 是否完全一致（用于 patch 判断）
     * 更安全，避免 int/long、顺序问题
     */
    private boolean jsonEquals(JsonNode a, JsonNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.equals(b);
    }


    private JsonNode parseParams(String params) {
        try {
            return objectMapper.readTree(params);
        } catch (JsonProcessingException e) {
            return objectMapper.createObjectNode();
        }
    }

    /**
     * 判断 containee 的字段结构是否完全包含在 container 中
     * 用于 major/minor 的结构匹配
     */
    private boolean containsAllFields(JsonNode container, JsonNode containee) {
        if (containee == null || containee.isMissingNode()) return true;

        // -------- Object 处理 --------
        if (containee.isObject()) {
            if (!container.isObject()) return false;

            Iterator<Map.Entry<String, JsonNode>> fields = containee.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> e = fields.next();
                JsonNode child = container.get(e.getKey());

                if (child == null) return false;
                if (!containsAllFields(child, e.getValue())) return false;
            }
            return true;
        }

        // -------- Array 处理 --------
        if (containee.isArray()) {
            if (!container.isArray()) return false;
            if (containee.size() > container.size()) return false;

            for (int i = 0; i < containee.size(); i++) {
                if (!containsAllFields(container.get(i), containee.get(i))) return false;
            }
            return true;
        }

        // 基础类型不比较值，只比较是否存在结构
        return true;
    }

    private JsonNode parse(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (Exception e) {
            return objectMapper.createObjectNode();
        }
    }


    private <T> Iterable<T> iterable(java.util.Iterator<T> iterator) {
        return () -> iterator;
    }

    private static class VersionResult {
        private final int major;
        private final int minor;
        private final int patch;
        private final MoParamsDetail existingDetail;

        VersionResult(int major, int minor, int patch, MoParamsDetail existingDetail) {
            this.major = major;
            this.minor = minor;
            this.patch = patch;
            this.existingDetail = existingDetail;
        }
    }
}