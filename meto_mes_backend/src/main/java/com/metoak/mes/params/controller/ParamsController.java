package com.metoak.mes.params.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.metoak.mes.common.ResultBean;
import com.metoak.mes.common.result.Result;
import com.metoak.mes.dto.ParamsBaseDto;
import com.metoak.mes.dto.ParamsDetailDto;
import com.metoak.mes.entity.MoBeamInfo;
import com.metoak.mes.entity.MoProduceOrder;
import com.metoak.mes.entity.MoTagInfo;
import com.metoak.mes.params.dto.ParamsUploadRequest;
import com.metoak.mes.params.entity.MoParamsBase;
import com.metoak.mes.params.entity.MoParamsDetail;
import com.metoak.mes.params.enums.ParamTypeEnum;
import com.metoak.mes.params.service.IMoParamsBaseService;
import com.metoak.mes.params.service.IMoParamsDetailService;
import com.metoak.mes.params.vo.MoParamsVO;
import com.metoak.mes.service.IMoBeamInfoService;
import com.metoak.mes.service.IMoProduceOrderService;
import com.metoak.mes.service.IMoTagInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
@RequestMapping("/api/mes/v1/params")
@Tag(name = "参数管理", description = "参数集基础信息和详情管理")
public class ParamsController {

    @Autowired
    private IMoParamsBaseService paramsBaseService;

    @Autowired
    private IMoParamsDetailService paramsDetailService;

    @Autowired
    private IMoTagInfoService tagInfoService;

    @Autowired
    private IMoBeamInfoService beamInfoService;

    @Autowired
    private IMoProduceOrderService produceOrderService;

//    @Operation(summary = "参数集上传并自动管理版本")
//    @PostMapping
//    public Result<Long> uploadParams(@RequestBody ParamsUploadRequest request) {
//        return paramsDetailService.uploadParams(request);
//    }



    @Operation(summary = "根据类型获取参数集列表")
    @GetMapping
    public ResultBean<List<MoParamsVO>> listByType(@RequestParam Integer type) {
        List<MoParamsBase> paramsBases = paramsBaseService.list(new LambdaQueryWrapper<MoParamsBase>()
                .eq(MoParamsBase::getType, type));
        if (paramsBases.isEmpty()) {
            return ResultBean.ok(Collections.emptyList());
        }

        List<Long> baseIds = paramsBases.stream()
                .map(MoParamsBase::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (baseIds.isEmpty()) {
            return ResultBean.ok(Collections.emptyList());
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

        return ResultBean.ok(result);
    }
//
//    @Operation(Operationsummary = "创建参数集基础信息")
//    @PostMapping("/base")
//    public ResultBean<ParamsBaseDto> createParamsBase(@RequestBody ParamsBaseDto paramsBaseDto) {
//        MoParamsBase paramsBase = new MoParamsBase();
//        BeanUtils.copyProperties(paramsBaseDto, paramsBase);
//        paramsBase.setCreatedAt(LocalDateTime.now());
//        paramsBaseService.save(paramsBase);
//
//        ParamsBaseDto result = new ParamsBaseDto();
//        BeanUtils.copyProperties(paramsBase, result);
//        return ResultBean.ok(result);
//    }

//    @Operation(summary = "获取参数集基础信息列表")
//    @GetMapping("/base")
//    public ResultBean<List<ParamsBaseDto>> getParamsBaseList(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) Integer type,
//            @RequestParam(required = false) String materialCode) {
//
//        LambdaQueryWrapper<MoParamsBase> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.like(name != null, MoParamsBase::getName, name);
//        queryWrapper.eq(type != null, MoParamsBase::getType, type);
////        queryWrapper.like(materialCode != null, MoParamsBase::getMaterialCode, materialCode);
//
//        List<MoParamsBase> list = paramsBaseService.list(queryWrapper);
//        List<ParamsBaseDto> result = list.stream().map(item -> {
//            ParamsBaseDto dto = new ParamsBaseDto();
//            BeanUtils.copyProperties(item, dto);
//            return dto;
//        }).collect(Collectors.toList());
//
//        return ResultBean.ok(result);
//    }
//
//    @Operation(summary = "根据ID获取参数集基础信息")
//    @GetMapping("/base/{id}")
//    public ResultBean<ParamsBaseDto> getParamsBaseById(@PathVariable Long id) {
//        MoParamsBase paramsBase = paramsBaseService.getById(id);
//        if (paramsBase == null) {
//            return ResultBean.fail(201, "参数集基础信息不存在");
//        }
//
//        ParamsBaseDto result = new ParamsBaseDto();
//        BeanUtils.copyProperties(paramsBase, result);
//        return ResultBean.ok(result);
//    }
//
//    @Operation(summary = "更新参数集基础信息")
//    @PutMapping("/base/{id}")
//    public ResultBean<ParamsBaseDto> updateParamsBase(@PathVariable Long id, @RequestBody ParamsBaseDto paramsBaseDto) {
//        MoParamsBase paramsBase = paramsBaseService.getById(id);
//        if (paramsBase == null) {
//            return ResultBean.fail(201, "参数集基础信息不存在");
//        }
//
//        BeanUtils.copyProperties(paramsBaseDto, paramsBase, "id", "createdAt");
//        paramsBaseService.updateById(paramsBase);
//
//        ParamsBaseDto result = new ParamsBaseDto();
//        BeanUtils.copyProperties(paramsBase, result);
//        return ResultBean.ok(result);
//    }
//
//    @Operation(summary = "删除参数集基础信息")
//    @DeleteMapping("/base/{id}")
//    public ResultBean<Void> deleteParamsBase(@PathVariable Long id) {
//        boolean result = paramsBaseService.removeById(id);
//        if (!result) {
//            return ResultBean.fail(201, "参数集基础信息不存在或删除失败");
//        }
//        return ResultBean.ok();
//    }
//
//    @Operation(summary = "创建参数集详情")
//    @PostMapping("/detail")
//    public ResultBean<ParamsDetailDto> createParamsDetail(@RequestBody ParamsDetailDto paramsDetailDto) {
//        MoParamsDetail paramsDetail = new MoParamsDetail();
//        BeanUtils.copyProperties(paramsDetailDto, paramsDetail);
//        paramsDetail.setCreatedAt(LocalDateTime.now());
//        paramsDetailService.save(paramsDetail);
//
//        ParamsDetailDto result = new ParamsDetailDto();
//        BeanUtils.copyProperties(paramsDetail, result);
//        return ResultBean.ok(result);
//    }
//
//    @Operation(summary = "获取参数集详情列表")
//    @GetMapping("/detail")
//    public ResultBean<List<ParamsDetailDto>> getParamsDetailList(
//            @RequestParam(required = false) Long baseId,
//            @RequestParam(required = false) Integer isActive) {
//
//        LambdaQueryWrapper<MoParamsDetail> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(baseId != null, MoParamsDetail::getBaseId, baseId);
//        queryWrapper.eq(isActive != null, MoParamsDetail::getIsActive, isActive);
//
//        List<MoParamsDetail> list = paramsDetailService.list(queryWrapper);
//        List<ParamsDetailDto> result = list.stream().map(item -> {
//            ParamsDetailDto dto = new ParamsDetailDto();
//            MoParamsBase moParamsBase = paramsBaseService.getById(item.getBaseId());
//            BeanUtils.copyProperties(item, dto);
//            dto.setName(moParamsBase.getName());
//            return dto;
//        }).collect(Collectors.toList());
//        System.out.println(list.size());
//        return ResultBean.ok(result);
//    }
//
//    @Operation(summary = "根据ID获取参数集详情")
//    @GetMapping("/detail/{id}")
//    public ResultBean<ParamsDetailDto> getParamsDetailById(@PathVariable Long id) {
//        MoParamsDetail paramsDetail = paramsDetailService.getById(id);
//        if (paramsDetail == null) {
//            return ResultBean.fail(201, "参数集详情不存在");
//        }
//
//        ParamsDetailDto result = new ParamsDetailDto();
//        MoParamsBase moParamsBase = paramsBaseService.getById(paramsDetail.getBaseId());
//        BeanUtils.copyProperties(paramsDetail, result);
//        result.setName(moParamsBase.getName());
//        System.out.println(result);
//        return ResultBean.ok(result);
//    }
//
//    @Operation(summary = "更新参数集详情")
//    @PutMapping("/detail/{id}")
//    public ResultBean<ParamsDetailDto> updateParamsDetail(@PathVariable Long id, @RequestBody ParamsDetailDto paramsDetailDto) {
//        MoParamsDetail paramsDetail = paramsDetailService.getById(id);
//        if (paramsDetail == null) {
//            return ResultBean.fail(201, "参数集详情不存在");
//        }
//
//        BeanUtils.copyProperties(paramsDetailDto, paramsDetail, "id", "createdAt");
//        paramsDetailService.updateById(paramsDetail);
//
//        ParamsDetailDto result = new ParamsDetailDto();
//        BeanUtils.copyProperties(paramsDetail, result);
//        return ResultBean.ok(result);
//    }
//
//    @Operation(summary = "删除参数集详情")
//    @DeleteMapping("/detail/{id}")
//    public ResultBean<Void> deleteParamsDetail(@PathVariable Long id) {
//        boolean result = paramsDetailService.removeById(id);
//        if (!result) {
//            return ResultBean.fail(201, "参数集详情不存在或删除失败");
//        }
//        return ResultBean.ok();
//    }
//
//    @Operation(summary = "根据产品序列号获取参数集内容")
//    @GetMapping("/detail/params/bySn")
//    public ResultBean<String> getParamsBySerialNumber(@RequestParam String sn) {
//        WorkOrderInfo workOrderInfo = fetchWorkOrderInfo(sn);
//        if (workOrderInfo == null) {
//            return ResultBean.fail(201, "SN 未绑定工单");
//        }
//
//        LambdaQueryWrapper<MoProduceOrder> orderQueryWrapper = new LambdaQueryWrapper<>();
//        orderQueryWrapper.eq(MoProduceOrder::getWorkOrderCode, workOrderInfo.getWorkOrderCode());
//        orderQueryWrapper.eq(MoProduceOrder::getMaterialCode, workOrderInfo.getMaterialCode());
//        MoProduceOrder produceOrder = produceOrderService.getOne(orderQueryWrapper);
//        if (produceOrder == null) {
//            return ResultBean.fail(201, "工单不存在或已关闭");
//        }
//
//        Long paramsDetailId = produceOrder.getParamsDetailId();
//        if (paramsDetailId == null) {
//            return ResultBean.fail(201, "工单未绑定参数集");
//        }
//
//        MoParamsDetail paramsDetail = paramsDetailService.getById(paramsDetailId);
//        if (paramsDetail == null) {
//            return ResultBean.fail(201, "参数集详情不存在");
//        }
//
//        return ResultBean.ok(paramsDetail.getParams());
//    }
//
//    private WorkOrderInfo fetchWorkOrderInfo(String sn) {
//        LambdaQueryWrapper<MoTagInfo> tagInfoWrapper = new LambdaQueryWrapper<>();
//        tagInfoWrapper.eq(MoTagInfo::getTagSn, sn);
//        MoTagInfo tagInfo = tagInfoService.getOne(tagInfoWrapper, false);
//        if (tagInfo != null) {
//            MoProduceOrder produceOrder = getProduceOrder(tagInfo.getProduceOrderId(), tagInfo.getWorkOrderCode());
//            if (produceOrder != null) {
//                return new WorkOrderInfo(produceOrder.getWorkOrderCode(), produceOrder.getMaterialCode());
//            }
//        }
//
//        LambdaQueryWrapper<MoBeamInfo> beamInfoWrapper = new LambdaQueryWrapper<>();
//        beamInfoWrapper.eq(MoBeamInfo::getBeamSn, sn);
//        MoBeamInfo beamInfo = beamInfoService.getOne(beamInfoWrapper, false);
//        if (beamInfo != null) {
//            MoProduceOrder produceOrder = getProduceOrder(beamInfo.getProduceOrderId(), beamInfo.getWorkOrderCode());
//            if (produceOrder != null) {
//                return new WorkOrderInfo(produceOrder.getWorkOrderCode(), produceOrder.getMaterialCode());
//            }
//        }
//        return null;
//    }
//
//    private MoProduceOrder getProduceOrder(Integer produceOrderId, String workOrderCode) {
//        MoProduceOrder produceOrder = null;
//        if (produceOrderId != null) {
//            produceOrder = produceOrderService.getById(produceOrderId);
//        }
//        if (produceOrder == null && workOrderCode != null) {
//            LambdaQueryWrapper<MoProduceOrder> orderQueryWrapper = new LambdaQueryWrapper<>();
//            orderQueryWrapper.eq(MoProduceOrder::getWorkOrderCode, workOrderCode);
//            produceOrder = produceOrderService.getOne(orderQueryWrapper, false);
//        }
//        return produceOrder;
//    }
//
//    private MoParamsVO buildParamsVO(MoParamsBase base, MoParamsDetail detail) {
//        return MoParamsVO.builder()
//                .type(base.getType())
//                .relation(resolveRelation(base))
//                .name(base.getName())
//                .description(detail != null ? detail.getDescription() : null)
//                .version(detail != null ? formatVersion(detail) : null)
//                .createdBy(detail != null ? detail.getCreatedBy() : base.getCreatedBy())
//                .createdAt(detail != null ? detail.getCreatedAt() : base.getCreatedAt())
//                .build();
//    }
//
//    private String resolveRelation(MoParamsBase base) {
//        if (ParamTypeEnum.STEP.getCode().equals(base.getType())) {
//            return base.getStepTypeNo();
//        }
//        if (ParamTypeEnum.PROCESS.getCode().equals(base.getType())) {
//            return base.getFlowNo();
//        }
//        if (ParamTypeEnum.WORK_ORDER.getCode().equals(base.getType())) {
//            return base.getOrderId() != null ? base.getOrderId().toString() : null;
//        }
//        return null;
//    }
//
//    private String formatVersion(MoParamsDetail detail) {
//        return String.format("%d.%d.%d",
//                detail.getVersionMajor() == null ? 0 : detail.getVersionMajor(),
//                detail.getVersionMinor() == null ? 0 : detail.getVersionMinor(),
//                detail.getVersionPatch() == null ? 0 : detail.getVersionPatch());
//    }
//
//    private MoParamsDetail selectDetail(List<MoParamsDetail> details) {
//        Comparator<MoParamsDetail> comparator = Comparator
//                .comparing((MoParamsDetail detail) -> detail.getIsActive() != null ? detail.getIsActive() : -1, Comparator.reverseOrder())
//                .thenComparing(MoParamsDetail::getVersionMajor, Comparator.nullsFirst(Integer::compareTo))
//                .thenComparing(MoParamsDetail::getVersionMinor, Comparator.nullsFirst(Integer::compareTo))
//                .thenComparing(MoParamsDetail::getVersionPatch, Comparator.nullsFirst(Integer::compareTo))
//                .thenComparing(MoParamsDetail::getCreatedAt, Comparator.nullsLast(LocalDateTime::compareTo));
//
//        return details.stream()
//                .filter(Objects::nonNull)
//                .max(comparator)
//                .orElse(null);
//    }
//
//    private static class WorkOrderInfo {
//        private final String workOrderCode;
//        private final String materialCode;
//
//        public WorkOrderInfo(String workOrderCode, String materialCode) {
//            this.workOrderCode = workOrderCode;
//            this.materialCode = materialCode;
//        }
//
//        public String getWorkOrderCode() {
//            return workOrderCode;
//        }
//
//        public String getMaterialCode() {
//            return materialCode;
//        }
//    }

}
