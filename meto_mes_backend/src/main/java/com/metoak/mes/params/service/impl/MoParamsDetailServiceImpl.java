package com.metoak.mes.params.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.common.result.ResultCodeEnum;
import com.metoak.mes.params.dto.ParamDetailCreateDto;
import com.metoak.mes.params.dto.ParamsUploadRequest;
import com.metoak.mes.params.entity.MoParamsBase;
import com.metoak.mes.params.entity.MoParamsDetail;
import com.metoak.mes.params.enums.ParamTypeEnum;
import com.metoak.mes.params.mapper.MoParamsDetailMapper;
import com.metoak.mes.params.service.IMoParamsBaseService;
import com.metoak.mes.params.service.IMoParamsDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 参数集详情表 服务实现类
 * </p>
 *
 * @author Qoder
 * @since 2025-11-18
 */
@Service
public class MoParamsDetailServiceImpl extends ServiceImpl<MoParamsDetailMapper, MoParamsDetail> implements IMoParamsDetailService {

    @Autowired
    private IMoParamsBaseService paramsBaseService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Result<Long> saveDetail(ParamDetailCreateDto createDto) {
        MoParamsBase paramsBase = paramsBaseService.getById(createDto.getBaseId());
        if (paramsBase == null) {
            return Result.fail(ResultCodeEnum.PARAM_BASE_NOT_FOUND);
        }
        JsonNode currentParams;
        try {
            currentParams = objectMapper.readTree(createDto.getParams());
        } catch (JsonProcessingException e) {
            return Result.fail(ResultCodeEnum.PARAMS_INVALID_JSON);
        }
        List<MoParamsDetail> detailList = lambdaQuery()
                .eq(MoParamsDetail::getBaseId, paramsBase.getId())
                .list();
        VersionResult versionResult = determineVersion(detailList, currentParams);

        MoParamsDetail paramsDetail = new MoParamsDetail();
        paramsDetail.setBaseId(createDto.getBaseId());
        paramsDetail.setDescription(createDto.getDescription());
        paramsDetail.setVersionMajor(versionResult.major);
        paramsDetail.setVersionMinor(versionResult.minor);
        paramsDetail.setVersionPatch(versionResult.patch);
        paramsDetail.setParams(createDto.getParams());
        paramsDetail.setIsActive(ACTIVE_FLAG);
        paramsDetail.setCreatedAt(LocalDateTime.now());
        save(paramsDetail);
        return Result.ok(paramsDetail.getId());
    }

    @Override
    public Result<Long> uploadParams(ParamsUploadRequest request) {
        JsonNode currentParams;
        try {
            currentParams = objectMapper.readTree(request.getParams());
        } catch (JsonProcessingException e) {
            return Result.fail(ResultCodeEnum.PARAMS_INVALID_JSON);
        }

        MoParamsBase paramsBase = findOrCreateParamsBase(request);

        List<MoParamsDetail> detailList = lambdaQuery()
                .eq(MoParamsDetail::getBaseId, paramsBase.getId())
                .list();

        VersionResult versionResult = determineVersion(detailList, currentParams);

        // 如果存在完全匹配的版本，直接返回现有版本的ID
        if (versionResult.existingDetail != null) {
            return Result.ok(versionResult.existingDetail.getId());
        }

        // 创建新版本记录
        MoParamsDetail newDetail = new MoParamsDetail();
        newDetail.setBaseId(paramsBase.getId());
        newDetail.setDescription(request.getDescription());
        newDetail.setVersionMajor(versionResult.major);
        newDetail.setVersionMinor(versionResult.minor);
        newDetail.setVersionPatch(versionResult.patch);
        newDetail.setParams(request.getParams());
        if (request.getType()== null || request.getType().equals(ParamTypeEnum.ENGINEERING.getCode())) {
            newDetail.setIsActive(1);
        }
        newDetail.setCreatedBy(request.getUsername());
        newDetail.setCreatedAt(LocalDateTime.now());

        save(newDetail);

        // 返回新创建记录的ID
        return Result.ok(newDetail.getId());
    }

    private VersionResult generateNextVersion(Long baseId) {
        List<MoParamsDetail> details = lambdaQuery().eq(MoParamsDetail::getBaseId, baseId).list();
        if (details.isEmpty()) {
            return new VersionResult(INITIAL_VERSION_MAJOR, INITIAL_VERSION_MINOR, INITIAL_VERSION_PATCH, null);
        }
        MoParamsDetail latest = details.stream()
                .filter(Objects::nonNull)
                .max(Comparator
                        .comparing(MoParamsDetail::getVersionMajor, Comparator.nullsFirst(Integer::compareTo))
                        .thenComparing(MoParamsDetail::getVersionMinor, Comparator.nullsFirst(Integer::compareTo))
                        .thenComparing(MoParamsDetail::getVersionPatch, Comparator.nullsFirst(Integer::compareTo))
                        .thenComparing(MoParamsDetail::getCreatedAt, Comparator.nullsLast(LocalDateTime::compareTo)))
                .orElse(null);
        if (latest == null) {
            return new VersionResult(INITIAL_VERSION_MAJOR, INITIAL_VERSION_MINOR, INITIAL_VERSION_PATCH, null);
        }
        int nextPatch = latest.getVersionPatch() == null ? 1 : latest.getVersionPatch() + 1;
        int major = latest.getVersionMajor() == null ? INITIAL_VERSION_MAJOR : latest.getVersionMajor();
        int minor = latest.getVersionMinor() == null ? INITIAL_VERSION_MINOR : latest.getVersionMinor();
        return new VersionResult(major, minor, nextPatch, null);
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

    private VersionResult determineVersion(List<MoParamsDetail> history, JsonNode currentParams) {

        if (history == null || history.isEmpty()) {
            return new VersionResult(1, 0, 0, null);
        }

        // ====== 第 1 层：匹配 major ======
        // 按 major 分组
        Map<Integer, List<MoParamsDetail>> majorGroups = history.stream()
                .collect(Collectors.groupingBy(MoParamsDetail::getVersionMajor));

        int maxMajor = majorGroups.keySet().stream()
                .max(Integer::compareTo)
                .orElse(1);

        // 找出“整个 major 组完全兼容 current”的 major
        List<Integer> compatibleMajors = majorGroups.entrySet().stream()
                .filter(entry ->
                        entry.getValue().stream()
                                .allMatch(item -> containsAllFields(currentParams, parse(item.getParams())))
                )
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        int major;
        if (compatibleMajors.isEmpty()) {
            // 没有 major 完全兼容 → major + 1
            major = maxMajor + 1;
            return new VersionResult(major, 0, 0, null);
        } else {
            // 选择最大号的 major
            major = compatibleMajors.stream()
                    .max(Integer::compareTo)
                    .orElse(maxMajor + 1);
        }


        // ====== 提取此 major 下所有版本 ======
        List<MoParamsDetail> sameMajor = majorGroups.get(major);


        // ====== 第 2 层：匹配 minor ======
        int maxMinor = sameMajor.stream()
                .map(MoParamsDetail::getVersionMinor)
                .max(Integer::compareTo)
                .orElse(0);

        // 判断 history 是否包含 current（按你的最新规则）
        List<MoParamsDetail> compatibleMinor = sameMajor.stream()
                .filter(item -> containsAllFields(parse(item.getParams()), currentParams))
                .collect(Collectors.toList());

        int minor;
        if (compatibleMinor.isEmpty()) {
            minor = maxMinor + 1;
            return new VersionResult(major, minor, 0, null);
        } else {
            minor = compatibleMinor.stream()
                    .map(MoParamsDetail::getVersionMinor)
                    .max(Integer::compareTo)
                    .orElse(0);
        }


        // ====== 第 3 层：匹配 patch ======
        List<MoParamsDetail> sameMinor = sameMajor.stream()
                .filter(x -> x.getVersionMinor() == minor)
                .collect(Collectors.toList());

        int maxPatch = sameMinor.stream()
                .map(MoParamsDetail::getVersionPatch)
                .max(Integer::compareTo)
                .orElse(0);

        for (MoParamsDetail detail : sameMinor) {
            if (jsonEquals(parse(detail.getParams()), currentParams)) {
                return new VersionResult(major, minor, detail.getVersionPatch(), detail);
            }
        }

        return new VersionResult(major, minor, maxPatch + 1, null);
    }

    private boolean jsonEquals(JsonNode a, JsonNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.equals(b);
    }

    private boolean containsAllFields(JsonNode container, JsonNode containee) {
        if (containee == null || containee.isMissingNode()) return true;

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

        if (containee.isArray()) {
            if (!container.isArray()) return false;
            if (containee.size() > container.size()) return false;

            for (int i = 0; i < containee.size(); i++) {
                if (!containsAllFields(container.get(i), containee.get(i))) return false;
            }
            return true;
        }

        return true;
    }

    private JsonNode parse(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (Exception e) {
            return objectMapper.createObjectNode();
        }
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
