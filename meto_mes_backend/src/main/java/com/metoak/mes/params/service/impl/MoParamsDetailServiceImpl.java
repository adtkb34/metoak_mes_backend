package com.metoak.mes.params.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metoak.mes.common.ResultBean;
import com.metoak.mes.dto.ParamsDetailDto;
import com.metoak.mes.dto.ParamsUploadRequest;
import com.metoak.mes.params.entity.MoParamsBase;
import com.metoak.mes.params.entity.MoParamsDetail;
import com.metoak.mes.params.mapper.MoParamsDetailMapper;
import com.metoak.mes.params.service.IMoParamsBaseService;
import com.metoak.mes.params.service.IMoParamsDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
    public ResultBean<ParamsDetailDto> uploadParams(ParamsUploadRequest request) {
        JsonNode currentParams;
        try {
            currentParams = objectMapper.readTree(request.getParams());
        } catch (JsonProcessingException e) {
            return ResultBean.fail(201, "params 不是有效的 JSON");
        }

        MoParamsBase paramsBase = findOrCreateParamsBase(request);

        List<MoParamsDetail> detailList = lambdaQuery()
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

        save(newDetail);

        ParamsDetailDto dto = new ParamsDetailDto();
        BeanUtils.copyProperties(newDetail, dto);
        return ResultBean.ok(dto);
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

        int maxMajor = history.stream()
                .map(MoParamsDetail::getVersionMajor)
                .max(Integer::compareTo)
                .orElse(1);

        List<MoParamsDetail> compatibleMajor = history.stream()
                .filter(item -> containsAllFields(currentParams, parse(item.getParams())))
                .collect(Collectors.toList());

        int major;
        if (compatibleMajor.isEmpty()) {
            major = maxMajor + 1;
            return new VersionResult(major, 0, 0, null);
        } else {
            major = compatibleMajor.stream()
                    .map(MoParamsDetail::getVersionMajor)
                    .max(Integer::compareTo)
                    .orElse(maxMajor + 1);
        }

        List<MoParamsDetail> sameMajor = history.stream()
                .filter(x -> x.getVersionMajor() == major)
                .collect(Collectors.toList());

        int maxMinor = sameMajor.stream()
                .map(MoParamsDetail::getVersionMinor)
                .max(Integer::compareTo)
                .orElse(0);

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
