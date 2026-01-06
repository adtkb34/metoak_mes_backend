package com.metoak.mes.params.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metoak.mes.common.MOException;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.entity.*;
import com.metoak.mes.common.result.ResultCodeEnum;
import com.metoak.mes.params.dto.ParamBaseCreateDto;
import com.metoak.mes.params.dto.ParamsQueryDto;
import com.metoak.mes.params.entity.MoParamsBase;
import com.metoak.mes.params.entity.MoParamsDetail;
import com.metoak.mes.params.enums.ParamTypeEnum;
import com.metoak.mes.params.mapper.MoParamsBaseMapper;
import com.metoak.mes.params.mapper.ParamsDtoMapper;
import com.metoak.mes.params.service.IMoParamsBaseService;
import com.metoak.mes.params.service.IMoParamsDetailService;
import com.metoak.mes.params.vo.MoParamsVo;
import com.metoak.mes.params.vo.ParamsContainer;
import com.metoak.mes.params.vo.ParamsNode;
import com.metoak.mes.params.vo.ParamsPayload;
import com.metoak.mes.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
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

    @Lazy
    @Autowired
    private IMoParamsDetailService paramsDetailService;

    @Autowired
    private IMoWorkstageService moWorkstageService;

    @Autowired
    private IMoProcessFlowService moProcessFlowService;

    @Autowired
    private IMoProduceOrderService moProduceOrderService;

    @Autowired
    private IMoBeamInfoService moBeamInfoService;

    @Autowired
    private IMoTagInfoService moTagInfoService;

    @Override
    public Result<Long> saveBase(ParamBaseCreateDto createDto) {
        LambdaQueryWrapper<MoParamsBase> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoParamsBase::getType, createDto.getType());
        if (Objects.equals(createDto.getType(), ParamTypeEnum.STEP.getCode())) {
            wrapper.eq(MoParamsBase::getStepTypeNo, createDto.getStepTypeNo());
        } else if (Objects.equals(createDto.getType(), ParamTypeEnum.FLOW.getCode())) {
            wrapper.eq(MoParamsBase::getFlowNo, createDto.getFlowNo());
        } else if (Objects.equals(createDto.getType(), ParamTypeEnum.WORK_ORDER.getCode())) {
            wrapper.eq(MoParamsBase::getOrderId, createDto.getOrderId());
        } else if (Objects.equals(createDto.getType(), ParamTypeEnum.ENGINEERING.getCode())) {
            wrapper.eq(MoParamsBase::getName, createDto.getName());
        }
        List<MoParamsBase> list = list(wrapper);
        if (!list.isEmpty()) return Result.ok(list.get(0).getId());

        MoParamsBase paramsBase = new MoParamsBase();
        BeanUtils.copyProperties(createDto, paramsBase);
        paramsBase.setCreatedAt(LocalDateTime.now());
        save(paramsBase);
        return Result.ok(paramsBase.getId());
    }

    @Override
    public Result<List<MoParamsVo>> list(ParamsQueryDto paramsQueryDto) {

        List<MoParamsBase> bases = list(
                new LambdaQueryWrapper<MoParamsBase>()
                        .eq(MoParamsBase::getType, paramsQueryDto.getType())
                        .eq(paramsQueryDto.getName() !=null, MoParamsBase::getName, paramsQueryDto.getName())
                        .eq(paramsQueryDto.getOrderId() !=null, MoParamsBase::getOrderId, paramsQueryDto.getOrderId())
                        .eq(paramsQueryDto.getFlowNo() !=null, MoParamsBase::getFlowNo, paramsQueryDto.getFlowNo())
                        .eq(paramsQueryDto.getStepTypeNo() !=null, MoParamsBase::getStepTypeNo, paramsQueryDto.getStepTypeNo())
        );

        if (bases.isEmpty()) {
            return Result.ok(Collections.emptyList());
        }

        Map<Long, MoParamsBase> baseMap = bases.stream()
                .filter(b -> b.getId() != null)
                .collect(Collectors.toMap(MoParamsBase::getId, Function.identity()));

        List<Long> detailIds = getDetailIds(paramsQueryDto.getType());
        if (baseMap.isEmpty()) {
            return Result.ok(Collections.emptyList());
        }
        LambdaQueryWrapper<MoParamsDetail> wrapper = new LambdaQueryWrapper<MoParamsDetail>()
                .in(MoParamsDetail::getBaseId, baseMap.keySet());
        if (!ParamTypeEnum.ENGINEERING.getCode().equals(paramsQueryDto.getType())) {
            wrapper.in(!detailIds.isEmpty(), MoParamsDetail::getId, detailIds);
        }
        List<MoParamsDetail> details = paramsDetailService.list(wrapper);

        if (details.isEmpty()) {
            return Result.ok(Collections.emptyList());
        }

        List<MoParamsVo> result = details.stream()
                .map(detail -> buildParamsVO(baseMap.get(detail.getBaseId()), detail))
                .collect(Collectors.toList());

        return Result.ok(result);
    }

    private List<Long> getDetailIds(Integer type) {
        if (type.equals(ParamTypeEnum.STEP.getCode())) {
            LambdaQueryWrapper<MoWorkstage> wrapper = new LambdaQueryWrapper<>();
            wrapper.isNotNull(MoWorkstage::getParamsDetailId);
            return moWorkstageService.list(wrapper)
                    .stream()
                    .map(MoWorkstage::getParamsDetailId)
                    .distinct()
                    .collect(Collectors.toList());
        } else if (type.equals(ParamTypeEnum.FLOW.getCode())) {
            LambdaQueryWrapper<MoProcessFlow> wrapper = new LambdaQueryWrapper<>();
            wrapper.isNotNull(MoProcessFlow::getParamsDetailId);
            return moProcessFlowService.list(wrapper)
                    .stream()
                    .map(MoProcessFlow::getParamsDetailId)
                    .distinct()
                    .collect(Collectors.toList());
        } else if (type.equals(ParamTypeEnum.WORK_ORDER.getCode())) {
            LambdaQueryWrapper<MoProduceOrder> wrapper = new LambdaQueryWrapper<>();
            wrapper.isNotNull(MoProduceOrder::getParamsDetailId);
            return moProduceOrderService.list(wrapper)
                    .stream()
                    .map(MoProduceOrder::getParamsDetailId)
                    .distinct()
                    .collect(Collectors.toList());
        }

        return List.of();
    }
    @Override
    public Result<List<MoParamsBase>> listByType(Integer type) {
        LambdaQueryWrapper<MoParamsBase> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoParamsBase::getType, type);
        return Result.ok(list(wrapper));
    }

    @Override
    public Result<ParamsPayload> getBySn(String sn) {

        // 1️⃣ 订单
        Long orderId = getOrderId(sn);
        MoProduceOrder order = moProduceOrderService.getById(orderId);
        if (order == null) {
            throw new MOException(ResultCodeEnum.WORK_ORDER_QUERY_BY_ID_FAILED);
        }

        ParamsContainer container = new ParamsContainer();

        // =====================
        // ORDER PARAMS
        // =====================
        if (order.getParamsDetailId() != null) {
            container.setOrder(
                    buildNodeByDetailId(order.getParamsDetailId())
            );
        }

        // =====================
        // FLOW PARAMS
        // =====================
        String flowCode = order.getFlowCode();
        Long flowDetailId = getDetailIdByFlowNo(flowCode);
        if (flowDetailId != null) {
            container.setFlow(
                    buildNodeByDetailId(flowDetailId)
            );
        }

        // =====================
        // STEP PARAMS
        // =====================
        container.setSteps(buildStepParamsNodes(flowCode));

        ParamsPayload payload = new ParamsPayload();
        payload.setParams(container);

        return Result.ok(payload);
    }

    @Override
    public Result<ParamsPayload> getByOrderId(Long orderId) {
        MoProduceOrder order = moProduceOrderService.getById(orderId);
        if (order == null) {
            throw new MOException(ResultCodeEnum.WORK_ORDER_QUERY_BY_ID_FAILED);
        }

        ParamsContainer container = new ParamsContainer();

        // =====================
        // ORDER PARAMS
        // =====================
        if (order.getParamsDetailId() != null) {
            container.setOrder(
                    buildNodeByDetailId(order.getParamsDetailId())
            );
        }

        ParamsPayload payload = new ParamsPayload();
        payload.setParams(container);

        return Result.ok(payload);
    }

    @Override
    public Result<ParamsPayload> getByFlowNo(String flowNo) {
        ParamsContainer container = new ParamsContainer();

        Long flowDetailId = getDetailIdByFlowNo(flowNo);
        if (flowDetailId != null) {
            container.setFlow(
                    buildNodeByDetailId(flowDetailId)
            );
        }

        ParamsPayload payload = new ParamsPayload();
        payload.setParams(container);
        return Result.ok(payload);
    }

    @Override
    public Result<ParamsPayload> getByStepNo(String stepNo) {
        ParamsContainer container = new ParamsContainer();

        List<ParamsNode> stepNodes = new ArrayList<>();
        MoWorkstage stage = moWorkstageService.getByNo(stepNo);
        if (stage == null) {
            throw new MOException(ResultCodeEnum.STEP_NOT_FOUND_BY_NO);
        }

        if (stage.getParamsDetailId() != null) {
            ParamsNode node = buildNodeByDetailId(stage.getParamsDetailId());
            if (node != null) {
                stepNodes.add(node);
            }
        }

        container.setSteps(stepNodes);


        ParamsPayload payload = new ParamsPayload();
        payload.setParams(container);

        return Result.ok(payload);
    }

    @Override
    public Result<ParamsPayload> getByDetailId(Long id) {
        ParamsContainer container = new ParamsContainer();

        if (id != null) {
            container.setFlow(
                    buildNodeByDetailId(id)
            );
        }

        ParamsPayload payload = new ParamsPayload();
        payload.setParams(container);
        return Result.ok(payload);
    }

    public ParamsNode buildNodeByDetailId(Long detailId) {
        MoParamsDetail detail = paramsDetailService.getById(detailId);
        if (detail == null) {
            throw new MOException(ResultCodeEnum.PARAMS_DETAIL_QUERY_BY_ID_FAILED);
        }

        MoParamsBase base = getById(detail.getBaseId());
        if (base == null) {
            throw new MOException(ResultCodeEnum.PARAMS_BASE_QUERY_BY_ID_FAILED);
        }

        return ParamsDtoMapper.toNode(base, detail);
    }

    public List<ParamsNode> buildStepParamsNodes(String flowCode) {
        List<ParamsNode> stepNodes = new ArrayList<>();
        if (flowCode == null) return stepNodes;
        List<MoProcessFlow> flows = moProcessFlowService.fetchByCode(flowCode);
        if (flows == null || flows.isEmpty()) {
            return stepNodes;
        }

        for (MoProcessFlow flow : flows) {
            MoWorkstage stage = moWorkstageService.getByCode(flow.getStageCode());
            if (stage == null || stage.getParamsDetailId() == null) {
                continue;
            }

            ParamsNode node = buildNodeByDetailId(stage.getParamsDetailId());
            if (node != null) {
                stepNodes.add(node);
            }
        }

        return stepNodes;
    }

    public Long getOrderId(String sn) {
        LambdaQueryWrapper<MoBeamInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoBeamInfo::getBeamSn, sn);
        List<MoBeamInfo> moBeamInfos = moBeamInfoService.list(wrapper);
        LambdaQueryWrapper<MoTagInfo> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(MoTagInfo::getTagSn, sn);
        List<MoTagInfo> moTagInfos = moTagInfoService.list(wrapper2);
        if (moBeamInfos.size() + moTagInfos.size() == 0) throw new MOException(ResultCodeEnum.SN_NOT_FOUND);
        if (moBeamInfos.size() + moTagInfos.size() > 1) throw new MOException(ResultCodeEnum.SN_DUPLICATED);
        String workOrderCode;
        String materialCode;
        if (!moBeamInfos.isEmpty()) {
            workOrderCode = moBeamInfos.get(0).getWorkOrderCode();
            materialCode = moBeamInfos.get(0).getMaterialCode();
        } else {
            workOrderCode = moTagInfos.get(0).getWorkOrderCode();
            materialCode = moTagInfos.get(0).getMaterialCode();
        }
        LambdaQueryWrapper<MoProduceOrder> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(MoProduceOrder::getWorkOrderCode, workOrderCode);
        wrapper1.eq(MoProduceOrder::getMaterialCode, materialCode);
        List<MoProduceOrder> moProduceOrders = moProduceOrderService.list(wrapper1);
        if (moProduceOrders.isEmpty()) throw new MOException(ResultCodeEnum.WORK_ORDER_NOT_FOUND);
        if (moProduceOrders.size() > 1) throw new MOException(ResultCodeEnum.MULTIPLE_WORK_ORDERS_FOUND);

        return moProduceOrders.get(0).getId();
    }

    public Long getDetailIdByFlowNo(String flowNo) {
        if (flowNo == null) return null;
        LambdaQueryWrapper<MoProcessFlow> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoProcessFlow::getProcessCode, flowNo);
        List<MoProcessFlow> moProcessFlows = moProcessFlowService.list(wrapper);
        if (moProcessFlows.isEmpty()) throw new MOException(ResultCodeEnum.FAILED_TO_FIND_PROCESS_BY_PROCESS_ID);

        return moProcessFlows.get(0).getParamsDetailId();
    }

    private MoParamsVo buildParamsVO(MoParamsBase base, MoParamsDetail detail) {
        return MoParamsVo.builder()
                .baseId(base.getId())
                .type(base.getType())
                .relation(resolveRelation(base))
                .flowNo(base.getFlowNo())
                .orderId(base.getOrderId())
                .stepTypeNo(base.getStepTypeNo())
                .name(base.getName())
                .detailId(detail != null ? detail.getId() : null)
                .description(detail != null ? detail.getDescription() : null)
                .version(detail != null ? formatVersion(detail) : null)
                .createdBy(detail != null ? detail.getCreatedBy() : base.getCreatedBy())
                .createdAt(detail != null ? detail.getCreatedAt() : base.getCreatedAt())
                .build();
    }

    private String resolveRelation(MoParamsBase base) {
        if (ParamTypeEnum.STEP.getCode().equals(base.getType())) {
            LambdaQueryWrapper<MoWorkstage> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(MoWorkstage::getStepTypeNo, base.getStepTypeNo());
            List<MoWorkstage> moWorkstages = moWorkstageService.list(wrapper);
            if (moWorkstages.isEmpty()) {
                return base.getStepTypeNo() + ": 此工序编号不存在";
            } else if (moWorkstages.size() == 1) {
                MoWorkstage workstage = moWorkstages.get(0);
                return workstage.getStepTypeNo() + ": " + workstage.getStageName();
            } else {
                return base.getStepTypeNo() + ": 此工序编号重复";
            }
        }
        if (ParamTypeEnum.FLOW.getCode().equals(base.getType())) {
            LambdaQueryWrapper<MoProcessFlow> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(MoProcessFlow::getProcessCode, base.getFlowNo());
            List<MoProcessFlow> moProcessFlows = moProcessFlowService.list(wrapper);
            if (moProcessFlows.isEmpty()) {
                return base.getFlowNo() + ": 此工艺编号不存在";
            } else if (moProcessFlows.size() == 1) {
                MoProcessFlow moProcessFlow = moProcessFlows.get(0);
                return moProcessFlow.getProcessCode() + ": " + moProcessFlow.getProcessName();
            }
        }
        if (ParamTypeEnum.WORK_ORDER.getCode().equals(base.getType())) {
            if (base.getOrderId() == null) return null;
            MoProduceOrder moProduceOrder = moProduceOrderService.getById(base.getOrderId());
            if (moProduceOrder == null) return null;

            return moProduceOrder.getWorkOrderCode() + ": " + moProduceOrder.getMaterialName() + "-" + moProduceOrder.getMaterialCode();
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
