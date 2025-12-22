package com.metoak.mes.params.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.params.dto.ParamBaseCreateDto;
import com.metoak.mes.params.entity.MoParamsBase;
import com.metoak.mes.params.entity.MoParamsDetail;
import com.metoak.mes.params.enums.ParamTypeEnum;
import com.metoak.mes.params.mapper.MoParamsBaseMapper;
import com.metoak.mes.params.service.IMoParamsBaseService;
import com.metoak.mes.params.service.IMoParamsDetailService;
import com.metoak.mes.params.vo.MoParamsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 参数集基础信息表 服务实现类
 * </p>
 *
 * @author Qoder
 * @since 2025-11-18
 */
@Service
public class MoParamsBaseServiceImpl extends ServiceImpl<MoParamsBaseMapper, MoParamsBase> implements IMoParamsBaseService {

    private static final int DEFAULT_VERSION_SEQUENCE = 0;
    private static final int DEFAULT_VERSION_MAJOR = 1;

    @Autowired
    private IMoParamsDetailService paramsDetailService;

    @Override
    public Result<Long> saveBase(ParamBaseCreateDto createDto) {
        MoParamsBase paramsBase = new MoParamsBase();
        BeanUtils.copyProperties(createDto, paramsBase);
        paramsBase.setCreatedAt(LocalDateTime.now());
        save(paramsBase);
        return Result.ok(paramsBase.getId());
    }

    @Override
    public Result<List<MoParamsVO>> listByType(Integer type) {
        List<MoParamsBase> paramsBases = list(new LambdaQueryWrapper<MoParamsBase>()
                .eq(MoParamsBase::getType, type));
        if (paramsBases.isEmpty()) {
            return Result.ok(Collections.emptyList());
        }

        List<Long> baseIds = paramsBases.stream()
                .map(MoParamsBase::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (baseIds.isEmpty()) {
            return Result.ok(Collections.emptyList());
        }

        Map<Long, MoParamsDetail> detailMap = paramsDetailService.list(new LambdaQueryWrapper<MoParamsDetail>()
                        .in(MoParamsDetail::getBaseId, baseIds))
                .stream()
                .collect(Collectors.groupingBy(MoParamsDetail::getBaseId))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> selectDetail(entry.getValue())));

        List<MoParamsVO> result = paramsBases.stream()
                .map(base -> buildParamsVO(base, detailMap.get(base.getId())))
                .collect(Collectors.toList());

        return Result.ok(result);
    }

    private MoParamsVO buildParamsVO(MoParamsBase base, MoParamsDetail detail) {
        return MoParamsVO.builder()
                .type(base.getType())
                .relation(resolveRelation(base))
                .name(base.getName())
                .description(detail != null ? detail.getDescription() : null)
                .version(detail != null ? formatVersion(detail) : null)
                .createdBy(detail != null ? detail.getCreatedBy() : base.getCreatedBy())
                .createdAt(detail != null ? detail.getCreatedAt() : base.getCreatedAt())
                .build();
    }

    private String resolveRelation(MoParamsBase base) {
        if (ParamTypeEnum.STEP.getCode().equals(base.getType())) {
            return base.getStepTypeNo();
        }
        if (ParamTypeEnum.PROCESS.getCode().equals(base.getType())) {
            return base.getFlowNo();
        }
        if (ParamTypeEnum.WORK_ORDER.getCode().equals(base.getType())) {
            return base.getOrderId() != null ? base.getOrderId().toString() : null;
        }
        return null;
    }

    private String formatVersion(MoParamsDetail detail) {
        return String.format("%d.%d.%d",
                detail.getVersionMajor() == null ? DEFAULT_VERSION_MAJOR : detail.getVersionMajor(),
                detail.getVersionMinor() == null ? DEFAULT_VERSION_SEQUENCE : detail.getVersionMinor(),
                detail.getVersionPatch() == null ? DEFAULT_VERSION_SEQUENCE : detail.getVersionPatch());
    }

    private MoParamsDetail selectDetail(List<MoParamsDetail> details) {
        Comparator<MoParamsDetail> comparator = Comparator
                .comparing((MoParamsDetail detail) -> detail.getIsActive() != null ? detail.getIsActive() : -1, Comparator.reverseOrder())
                .thenComparing(MoParamsDetail::getVersionMajor, Comparator.nullsFirst(Integer::compareTo))
                .thenComparing(MoParamsDetail::getVersionMinor, Comparator.nullsFirst(Integer::compareTo))
                .thenComparing(MoParamsDetail::getVersionPatch, Comparator.nullsFirst(Integer::compareTo))
                .thenComparing(MoParamsDetail::getCreatedAt, Comparator.nullsLast(LocalDateTime::compareTo));

        return details.stream()
                .filter(Objects::nonNull)
                .max(comparator)
                .orElse(null);
    }
}
