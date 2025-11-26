package com.metoak.mes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metoak.mes.common.annotate.FieldCodeMapper;
import com.metoak.mes.common.MOException;
import com.metoak.mes.common.mapping.*;
import com.metoak.mes.common.util.GroupingUtil;
import com.metoak.mes.dto.*;
import com.metoak.mes.entity.*;
import com.metoak.mes.enums.StepMappingEnum;
import com.metoak.mes.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.metoak.mes.common.util.TimestampUtils.convertToDateTime;


@Service
public class ProcessStepProductionRecordsServiceImpl implements IProcessStepProductionRecordsService {


    @Autowired
    private IMoBeamInfoService moBeamInfoService;

    @Autowired
    private IMoAutoAdjustInfoService moAutoAdjustInfoService;

    @Autowired
    private IMoAutoAdjustSt08Service moAutoAdjustSt08Service;

    @Autowired
    private IMoDeviceStatusService deviceStatusService;

    @Autowired
    private IMoDirtyCheckService moDirtyCheckService;

    @Autowired
    private IUvDispensingService uvDispensingService;

    @Autowired
    private IMoLensAssemblyService moLensAssemblyService;

    @Autowired
    private IMoLensCapFasteningService moLensCapFasteningService;

    @Autowired
    private IMoIrPastingService moIrPastingService;

    @Autowired
    private IMoLensDispensingService moLensDispensingService;

    @Autowired
    private IMoLensBakingService moLensBakingService;

    @Autowired
    private IMoLensMtfCheckingService moLensMtfCheckingService;

    @Autowired
    private IMoMaterialBindingService moMaterialBindingService;

    @Autowired
    private IMoProcessStepProductionResultService moProcessStepProductionResultService;

    @Autowired
    private IMoBeamAppearanceInspectionService moBeamAppearanceInspectionService;

    @Autowired
    private IMoCmosAppearanceInspectionService moCmosAppearanceInspectionService;

    @Autowired
    private IMoScrewTighteningInspectionService screwTighteningInspectionService;

    @Autowired
    private IMoHighTempCuringRecordService highTempCuringRecordService;

    @Autowired
    private IMoAaFinalComprehensiveInspectionService moAaFinalComprehensiveInspectionService;

    @Autowired
    private IMoAfterAaCoatingProcessRecordService moAfterAaCoatingProcessRecordService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private IMoProduceOrderService moProduceOrderService;

    @Autowired
    private IMoTagInfoService moTagInfoService;

    @Autowired
    private IMoWorkstageService moWorkstageService;

    @Autowired
    private IMoProcessFlowService moProcessFlowService;

    @Override
    public Long add(ProductionRecordDto productionRecordDto) {
        System.out.println(productionRecordDto);
        MoProcessStepProductionResult moProcessStepProductionResult = MoProcessStepProductionResult.builder().
                stepType(productionRecordDto.getStepType()).
                stepTypeNo(productionRecordDto.getStepTypeNo()).
                productSn(productionRecordDto.getProductSn()).
                productBatchNo(productionRecordDto.getProductBatchNo()).
                startTime(convertToDateTime(productionRecordDto.getStartTime())).
                endTime(convertToDateTime(productionRecordDto.getEndTime())).
                errorCode(Integer.parseInt(productionRecordDto.getErrorNo())).
                ngReason(productionRecordDto.getError()).
                softwareTool(productionRecordDto.getSoftwareTool()).
                softwareToolVersion(productionRecordDto.getSoftwareToolVersion()).
                stationNum(Integer.parseInt(productionRecordDto.getDeviceNo())).
                operator(productionRecordDto.getOperator()).
                build();
        moProcessStepProductionResultService.save(moProcessStepProductionResult);
        Long moProcessStepProductionResultId = moProcessStepProductionResult.getId();
        add_material_binding(productionRecordDto, moProcessStepProductionResultId);
        if (StepMappingEnum.AUTO_ADJUST.getCode().equals(productionRecordDto.getStepTypeNo())) add_auto_adjust(productionRecordDto);
        if (StepMappingEnum.DUAL_TARGET_CALIB.getCode().equals(productionRecordDto.getStepTypeNo())) add_2_mo_calibration(productionRecordDto, moProcessStepProductionResultId);
        if (List.of(
                StepMappingEnum.PLASMA.getCode(),
                StepMappingEnum.MATERIAL_BINDING.getCode()
        ).contains(productionRecordDto.getStepTypeNo())) {
            return 0L;
        }
        save_attr_vals(productionRecordDto, moProcessStepProductionResultId, null, null);


        return 0L;
    }

    public <T> void save_attr_vals(ProductionRecordDto productionRecordDto, Long moProcessStepProductionResultId, T entity_, Class<?> service_) {
        GroupingUtil.group(productionRecordDto.getAttrKeyVals()).forEach(items -> {
            if (productionRecordDto.getStepTypeNo().equals(StepMappingEnum.AFTER_AA_FINAL_COMPREHENSIVE_INSPECTION.getCode())) {
                String imgQualityColors = items.stream().filter(item -> item.getNo().equals("005")).map(AttrKeyValDto::getVal).collect(Collectors.joining(","));
                String imgQualityColorUsls = items.stream().filter(item -> item.getNo().equals("005")).map(AttrKeyValDto::getUsl).collect(Collectors.joining(","));
                items.removeIf(i -> i.getNo().equals("005"));
                items.add(AttrKeyValDto.builder().no("005").val(imgQualityColors).usl(imgQualityColorUsls).build());
            }

            ProcessMappingRegistry.ProcessMapping processMapping = ProcessMappingRegistry.get(productionRecordDto.getStepTypeNo());
            Object entity = null;
            try {
                entity = entity_ != null ? entity_ : processMapping.getEntityClass().getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                throw new MOException("", -1);
            }
            // 设置静态字段（每个 group 相同）
            ReflectionMapper.setField(entity, "moProcessStepProductionResultId", moProcessStepProductionResultId.toString());
            CommonAttrMapping.mapDtoFields(entity, productionRecordDto, CommonAttrMapping.FIELD_TO_FIELD);
            CommonAttrMapping.mapDtoFields(entity, items.get(0), CommonAttrMapping.FIELD_TO_FIELD2);
            FieldCodeMapper.applyAttrListToObject(entity, items);
            // 保存
            Object service = service_ != null ? applicationContext.getBean(service_) : applicationContext.getBean(processMapping.getServiceClass());
            Method saveMethod = null;
            try {
                saveMethod = service.getClass().getMethod("save", Object.class);
                saveMethod.invoke(service, entity);
            } catch (Exception e) {
                e.printStackTrace(); // 打印错误信息到控制台
                throw new MOException("", -1);
            }
        });
    }

    public Boolean add_auto_adjust(ProductionRecordDto productionRecordDto) {
        MoAutoAdjustInfo moAutoAdjustInfo = MoAutoAdjustInfo.builder().
                beamSn(productionRecordDto.getProductSn()).
                stationNum(Integer.parseInt(productionRecordDto.getDeviceNo())).
                operationTime(convertToDateTime(productionRecordDto.getEndTime())).
                addTime(convertToDateTime(productionRecordDto.getStartTime())).
                ngReason(productionRecordDto.getError()).
                errorCode(Integer.parseInt(productionRecordDto.getErrorNo())).
                build();
        moAutoAdjustInfoService.save(moAutoAdjustInfo);

        return true;
    }

    public Boolean add_2_mo_calibration(ProductionRecordDto productionRecordDto, Long moProcessStepProductionResultId) {
        MoCalibration moCalibration = MoCalibration.builder().
                cameraSn(productionRecordDto.getProductSn()).
                stationNumber(Integer.parseInt(productionRecordDto.getDeviceNo())).
                endTime(convertToDateTime(productionRecordDto.getEndTime())).
                startTime(convertToDateTime(productionRecordDto.getStartTime())).
                errorCode(Integer.parseInt(productionRecordDto.getErrorNo())).
                build();
        save_attr_vals(productionRecordDto, moProcessStepProductionResultId, moCalibration, IMoCalibrationService.class);

        return true;
    }

    public Boolean add_material_binding(ProductionRecordDto productionRecordDto, Long moProcessStepProductionResultId) {
        if (productionRecordDto.getMaterials() != null) {
            productionRecordDto.getMaterials().forEach(item -> {
                moMaterialBindingService.save(MoMaterialBinding.builder().
                        cameraSn(productionRecordDto.getProductSn()).
                        cameraBatch(productionRecordDto.getProductBatchNo()).
                        category(item.getCategory()).
                        categoryNo(item.getCategoryNo()).
                        materialBatchNo(item.getBatchNo()).
                        materialSerialNo(item.getSerialNo()).
                        moProcessStepProductionResultId(moProcessStepProductionResultId).
                        position(item.getPosition()).
                        build());
            });
        }
        return true;
    }

    @Override
    public Boolean executable(ExecutableDto executableDto) {
//        return true;
//        String curStepTypeNo = executableDto.getStepTypeNo();
//        if (List.of(
//                StepMapping.MATERIAL_BINDING
//        ).contains(curStepTypeNo)) {
//            return true;
//        }
//        if (hasCurrentStepBeenCompleted(executableDto)) {
////            ResultCodeEnum.
//            throw new MOException("", -1);
//        }
//        if (!isPreviousStepPassed(executableDto)) {
//            throw new MOException("", -1);
//        }
//
//        String productSn = executableDto.getProductSn();
//        List<String> requiredSysStepTypeNos = fetchRequiredSysStepTypeNos(productSn);
//        List<String> completedSysStepTypeNos = fetchCompletedSuccessSysStepTypeNos(productSn);
//        if (hasStepBeenSkipped(curStepTypeNo, requiredSysStepTypeNos, completedSysStepTypeNos)) {
//            throw new MOException("", -1);
//        }

        return true;
    }

    // 当前工序是否已刚刚成功做过
    public Boolean hasCurrentStepBeenCompleted(ExecutableDto executableDto) {
        LambdaQueryWrapper<MoProcessStepProductionResult> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoProcessStepProductionResult::getProductSn, executableDto.getProductSn()).
                eq(MoProcessStepProductionResult::getErrorCode, 0).
                orderByDesc(MoProcessStepProductionResult::getId);
        List<MoProcessStepProductionResult> moProcessStepProductionResults = moProcessStepProductionResultService.list(wrapper);
        if (moProcessStepProductionResults.isEmpty()) {
            return false;
        } else return moProcessStepProductionResults.get(0).getStepTypeNo().equals(executableDto.getStepTypeNo());
    }

    // 上一步是否通过
    public Boolean isPreviousStepPassed(ExecutableDto executableDto) {
        LambdaQueryWrapper<MoProcessStepProductionResult> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoProcessStepProductionResult::getProductSn, executableDto.getProductSn()).
                ne(MoProcessStepProductionResult::getStepTypeNo, executableDto.getStepTypeNo()).
                orderByDesc(MoProcessStepProductionResult::getId);
        List<MoProcessStepProductionResult> moProcessStepProductionResults = moProcessStepProductionResultService.list(wrapper);
        if (moProcessStepProductionResults.isEmpty()) {
            return true;
        } else return moProcessStepProductionResults.get(0).getErrorCode() == 0;

    }

    // 是否有跳过工序
//    public Boolean hasStepBeenSkipped(ExecutableDto executableDto) {
//        String productSn = executableDto.getProductSn();
//        List<String> requiredSysStepTypeNos = fetchRequiredSysStepTypeNos(productSn);
//        List<String> completedSysStepTypeNos = fetchCompletedSuccessSysStepTypeNos(productSn);
//        return hasStepBeenSkipped(executableDto.getStepTypeNo(), requiredSysStepTypeNos, completedSysStepTypeNos);
//    }

    public String fetchWorkOrderNoByProductSn(String productSn) {
        LambdaQueryWrapper<MoTagInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoTagInfo::getTagSn, productSn);
        List<MoTagInfo> moTagInfos = moTagInfoService.list(wrapper);
        String workOrderNo = null;
        if (moTagInfos.isEmpty()) {
            LambdaQueryWrapper<MoBeamInfo> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(MoBeamInfo::getBeamSn, productSn);
            List<MoBeamInfo> moBeamInfos = moBeamInfoService.list(wrapper1);
            if (!moBeamInfos.isEmpty()) {
                workOrderNo = moBeamInfos.get(0).getWorkOrderCode();
            }
        } else {
            workOrderNo = moTagInfos.get(0).getWorkOrderCode();
        }

        return workOrderNo;
    }

    public List<MoProcessFlow> fetchProcessFlowNoByWorkOrderNo(String workOrderNo) {
        List<MoProcessFlow> moProcessFlows = new ArrayList<>();
        LambdaQueryWrapper<MoProduceOrder> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(MoProduceOrder::getWorkOrderCode, workOrderNo);
        List<MoProduceOrder> moProduceOrders = moProduceOrderService.list(wrapper2);
        if (!moProduceOrders.isEmpty()) {
            String flowCode = moProduceOrders.get(0).getFlowCode();
            LambdaQueryWrapper<MoProcessFlow> wrapper3 = new LambdaQueryWrapper<>();
            wrapper3.eq(MoProcessFlow::getProcessCode, flowCode);
            moProcessFlows = moProcessFlowService.list(wrapper3);
        }

        return moProcessFlows;
    }

    public List<String> fetchRequiredSysStepTypeNos(String productSn) {
        String workOrderNo = fetchWorkOrderNoByProductSn(productSn);
        List<MoProcessFlow> moProcessFlows = fetchProcessFlowNoByWorkOrderNo(workOrderNo);
        List<String> sysStepTypeNos = new ArrayList<>();
        moProcessFlows.forEach(item -> {
            LambdaQueryWrapper<MoWorkstage> wrapper4 = new LambdaQueryWrapper<>();
            wrapper4.eq(MoWorkstage::getStageCode, item.getStageCode());
            List<MoWorkstage> moWorkstages = moWorkstageService.list(wrapper4);
            if (!moWorkstages.isEmpty()) {
                MoWorkstage workstage = moWorkstages.get(0);
                String sysStepTypeNo = workstage.getStepTypeNo();
                if (sysStepTypeNo != null) {
                    sysStepTypeNos.add(sysStepTypeNo);
                }
            }
        });

        return sysStepTypeNos;
    }

    public List<String> fetchCompletedSuccessSysStepTypeNos(String productSn) {
        LambdaQueryWrapper<MoProcessStepProductionResult> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoProcessStepProductionResult::getProductSn, productSn).
                eq(MoProcessStepProductionResult::getErrorCode, 0);
        List<MoProcessStepProductionResult> moProcessStepProductionResults = moProcessStepProductionResultService.list(wrapper);
        ArrayList<String> completedSysStepTypeNos = new ArrayList<>();
        moProcessStepProductionResults.forEach(item -> {
            completedSysStepTypeNos.add(item.getStepTypeNo());
        });

        return completedSysStepTypeNos;
    }

    public Boolean hasStepBeenSkipped(String curStepTypeNo, List<String> requiredSysStepTypeNos, List<String> completedSysStepTypeNos) {
        int i = 0;
        int j = 0;
        for (; i < requiredSysStepTypeNos.size(); i++) {
            for (; j < completedSysStepTypeNos.size(); j++) {
                if (completedSysStepTypeNos.get(j).equals(requiredSysStepTypeNos.get(i))) {
                    j++;
                    break;
                };
            }
            if (j == completedSysStepTypeNos.size()) {
                i++;
                break;
            }
        }

        return requiredSysStepTypeNos.isEmpty() || i >= requiredSysStepTypeNos.size() || requiredSysStepTypeNos.get(i).equals(curStepTypeNo) || !requiredSysStepTypeNos.subList(i, requiredSysStepTypeNos.size()).contains(curStepTypeNo);
    }

    @Override
    public GetProductSnResponseDto fetchProductSn(GetProductSnDto getProductSnDto) {
        LambdaQueryWrapper<MoBeamInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MoBeamInfo::getWorkOrderCode, getProductSnDto.getWorkOrderNo()).
                eq(MoBeamInfo::getIsUsed, 0);

        List<MoBeamInfo> moBeamInfos = moBeamInfoService.list(wrapper);
        if (moBeamInfos.isEmpty()) {
            return null;
        }
        MoBeamInfo moBeamInfo = moBeamInfos.get(0);
        LambdaUpdateWrapper<MoBeamInfo> wrapper2 = new LambdaUpdateWrapper<>();
        wrapper2.eq(MoBeamInfo::getId, moBeamInfo.getId()).set(MoBeamInfo::getIsUsed, 1);
        moBeamInfoService.update(wrapper2);

        return GetProductSnResponseDto.builder().sn(moBeamInfo.getBeamSn()).build();
    }

    @Override
    public Boolean judgement(JudgementDto judgementDto) {
        return true;
    }

    @Override
    public AttrSpecDto fetchAttrSpec(FetchAttrSpecDto fetchAttrSpecDto) {
        return null;
    }

    @Override
    public Boolean device(DeviceStatusDto deviceStatusDto) throws JsonProcessingException {
        MoDeviceStatus moDeviceStatus = new MoDeviceStatus();
        moDeviceStatus.setDeviceNo(deviceStatusDto.getDeviceNo());
        moDeviceStatus.setProductMaterialNo(deviceStatusDto.getProductMaterialNo());
        ObjectMapper objectMapper = new ObjectMapper();
        deviceStatusDto.getAttrKeyVals().forEach(item -> {
            switch (item.getNo()) {
                case DeviceStatusAttrMapping.DEVICE_STATUS -> {
                    moDeviceStatus.setStatus(Integer.parseInt(item.getVal()));
                }
                case DeviceStatusAttrMapping.PRODUCTION_COUNT -> {
                    moDeviceStatus.setProductionCount(Integer.parseInt(item.getVal()));
                }
                case DeviceStatusAttrMapping.DEFECT_COUNT -> {
                    moDeviceStatus.setDefectCount(Integer.parseInt(item.getVal()));
                }
                case DeviceStatusAttrMapping.UPH -> {
                    moDeviceStatus.setUph(Integer.parseInt(item.getVal()));
                }
                case DeviceStatusAttrMapping.OEE -> {
                    moDeviceStatus.setOee(Float.parseFloat(item.getVal()));
                }
            }
        });
        HashMap<String, String> map = new HashMap<>();
        deviceStatusDto.getAttrKeyVals().forEach(item -> {
            map.put(item.getNo(), item.getVal());
        });
        moDeviceStatus.setOthers(objectMapper.writeValueAsString(map));
        deviceStatusService.save(moDeviceStatus);

        return true;
    }
}
