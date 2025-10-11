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
        if (List.of(
                StepMappingEnum.PLASMA.getCode(),
                StepMappingEnum.MATERIAL_BINDING.getCode()
        ).contains(productionRecordDto.getStepTypeNo())) {
            return 0L;
        }
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
                entity = processMapping.getEntityClass().getDeclaredConstructor().newInstance();
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
            Object service = applicationContext.getBean(processMapping.getServiceClass());
            Method saveMethod = null;
            try {
                saveMethod = service.getClass().getMethod("save", Object.class);
                saveMethod.invoke(service, entity);
            } catch (Exception e) {
                e.printStackTrace(); // 打印错误信息到控制台
                throw new MOException("", -1);
            }
        });

//        switch (productionRecordDto.getStepTypeNo()) {
//            case StepMapping.DIRTY_CHECKING -> add_dirty_check(productionRecordDto, moProcessStepProductionResultId);
//            case StepMapping.UV_DISPENSING -> add_uv_dispensing(productionRecordDto, moProcessStepProductionResultId);
//            case StepMapping.AUTO_ADJUST -> add_auto_adjust(productionRecordDto, moProcessStepProductionResultId);
////            case StepMapping.MATERIAL_BINDING -> add_material_binding(productionRecordDto, moProcessStepProductionResultId);
//            case StepMapping.LENS_ASSEMBLY -> add_lens_assembly(productionRecordDto, moProcessStepProductionResultId);
//            case StepMapping.LENS_CAP_FASTENING -> add_lens_cap_fastening(productionRecordDto, moProcessStepProductionResultId);
//            case StepMapping.LENS_DISPENSING -> add_lens_dispensing(productionRecordDto, moProcessStepProductionResultId);
//            case StepMapping.IR_PASTING -> add_ir_pasting(productionRecordDto, moProcessStepProductionResultId);
//            case StepMapping.LENS_BAKING -> add_lens_baking(productionRecordDto, moProcessStepProductionResultId);
//            case StepMapping.LENS_MTF_TESTING -> add_lens_mtf_testing(productionRecordDto, moProcessStepProductionResultId);
//            case StepMapping.BEAM_APPEARANCE_INSPECTION -> add_beam_appearance_inspection(productionRecordDto, moProcessStepProductionResultId);
//            case StepMapping.BEAM_SEALANT_COATING -> add_uv_dispensing(productionRecordDto, moProcessStepProductionResultId);
//            case StepMapping.CMOS_APPEARANCE_INSPECTION -> add_cmos_appearance_inspection(productionRecordDto, moProcessStepProductionResultId);
//            case StepMapping.FILM_REMOVAL_CLEANING -> add_dirty_check(productionRecordDto, moProcessStepProductionResultId);
//            case StepMapping.SCREW_TIGHTENING_INSPECTION -> add_screw_tightening_inspection(productionRecordDto, moProcessStepProductionResultId);
//            case StepMapping.HIGH_TEMP_CURING_RECORD -> add_high_temp_curing_record(productionRecordDto, moProcessStepProductionResultId);
//            case StepMapping.AFTER_AA_FINAL_COMPREHENSIVE_INSPECTION -> add_after_aa_final_comprehensive_inspection(productionRecordDto, moProcessStepProductionResultId);
//            case StepMapping.AFTER_AA_COATING_PROCESS_RECORD -> add_after_aa_coating_process_record(productionRecordDto, moProcessStepProductionResultId);
////            default -> throw new MOException("未找到对应的工序编号：" + productionRecordDto.getStepTypeNo() + "-" + productionRecordDto.getStepType(), 1);
//        }

        return 0L;
    }
//
//    public Boolean add_after_aa_coating_process_record(ProductionRecordDto productionRecordDto, Long moProcessStepProductionResultId) {
//        MoAfterAaCoatingProcessRecord moAfterAaCoatingProcessRecord = new MoAfterAaCoatingProcessRecord();
//        moAfterAaCoatingProcessRecord.setBeamSn(productionRecordDto.getProductSn());
//        moAfterAaCoatingProcessRecord.setErrorCode(Integer.parseInt(productionRecordDto.getErrorNo()));
//        moAfterAaCoatingProcessRecord.setNgReason(productionRecordDto.getError());
//        moAfterAaCoatingProcessRecord.setStationNum(Integer.parseInt(productionRecordDto.getDeviceNo()));
//        moAfterAaCoatingProcessRecord.setMoProcessStepProductionResultId(moProcessStepProductionResultId);
//        GroupingUtil.group(productionRecordDto.getAttrKeyVals()).forEach(items -> {
//            moAfterAaCoatingProcessRecord.setSprayPressure(null);
//            moAfterAaCoatingProcessRecord.setSprayProgramCode(null);
//            items.forEach(item -> {
//                moAfterAaCoatingProcessRecord.setPosition(item.getPosition());
//                switch (item.getNo()) {
//                    case AfterAaCoatingProcessRecordAttrMapping.SPRAY_PRESSURE ->
//                            moAfterAaCoatingProcessRecord.setSprayPressure(new BigDecimal(item.getVal()));
//                    case AfterAaCoatingProcessRecordAttrMapping.SPRAY_PROGRAM_CODE ->
//                            moAfterAaCoatingProcessRecord.setSprayProgramCode(item.getVal());
//                }
//            });
//            moAfterAaCoatingProcessRecord.setId(null);
//            moAfterAaCoatingProcessRecordService.save(moAfterAaCoatingProcessRecord);
//        });
//
//        return true;
//    }
//
//    public Boolean add_after_aa_final_comprehensive_inspection(ProductionRecordDto productionRecordDto, Long moProcessStepProductionResultId) {
//        MoAaFinalComprehensiveInspection moAaFinalComprehensiveInspection = new MoAaFinalComprehensiveInspection();
//        moAaFinalComprehensiveInspection.setBeamSn(productionRecordDto.getProductSn());
//        moAaFinalComprehensiveInspection.setErrorCode(Integer.parseInt(productionRecordDto.getErrorNo()));
//        moAaFinalComprehensiveInspection.setNgReason(productionRecordDto.getError());
//        moAaFinalComprehensiveInspection.setStationNum(Integer.parseInt(productionRecordDto.getDeviceNo()));
//        moAaFinalComprehensiveInspection.setMoProcessStepProductionResultId(moProcessStepProductionResultId);
//        GroupingUtil.group(productionRecordDto.getAttrKeyVals()).forEach(items -> {
//            moAaFinalComprehensiveInspection.setCurrentConsumption(null);
//            moAaFinalComprehensiveInspection.setFrameRate(null);
//            moAaFinalComprehensiveInspection.setBadPixels(null);
//            moAaFinalComprehensiveInspection.setContaminationLevel(null);
//            moAaFinalComprehensiveInspection.setClarityScore(null);
//            moAaFinalComprehensiveInspection.setImageQualityColor(null);
//            moAaFinalComprehensiveInspection.setImageQualityGray(null);
//            moAaFinalComprehensiveInspection.setFlareLevel(null);
//            moAaFinalComprehensiveInspection.setOcXOffset(null);
//            moAaFinalComprehensiveInspection.setOcYOffset(null);
//            items.forEach(item -> {
//                moAaFinalComprehensiveInspection.setPosition(item.getPosition());
//                switch (item.getNo()) {
//                    case AfterAaFinalComprehensiveInspectionAttrMapping.CURRENT_CONSUMPTION ->
//                            moAaFinalComprehensiveInspection.setCurrentConsumption(new BigDecimal(item.getVal()));
//                    case AfterAaFinalComprehensiveInspectionAttrMapping.FRAME_RATE ->
//                            moAaFinalComprehensiveInspection.setFrameRate(new BigDecimal(item.getVal()));
//                    case AfterAaFinalComprehensiveInspectionAttrMapping.BAD_PIXELS ->
//                            moAaFinalComprehensiveInspection.setBadPixels(Integer.parseInt(item.getVal()));
//                    case AfterAaFinalComprehensiveInspectionAttrMapping.CONTAMINATION_LEVEL ->
//                            moAaFinalComprehensiveInspection.setContaminationLevel(item.getVal());
//                    case AfterAaFinalComprehensiveInspectionAttrMapping.CLARITY_SCORE ->
//                            moAaFinalComprehensiveInspection.setClarityScore(new BigDecimal(item.getVal()));
//                    case AfterAaFinalComprehensiveInspectionAttrMapping.IMAGE_QUALITY_COLOR ->
//                            moAaFinalComprehensiveInspection.setImageQualityColor(item.getVal());
//                    case AfterAaFinalComprehensiveInspectionAttrMapping.IMAGE_QUALITY_GRAY ->
//                            moAaFinalComprehensiveInspection.setImageQualityGray(item.getVal());
//                    case AfterAaFinalComprehensiveInspectionAttrMapping.FLARE_LEVEL ->
//                            moAaFinalComprehensiveInspection.setFlareLevel(item.getVal());
//                    case AfterAaFinalComprehensiveInspectionAttrMapping.OC_X_OFFSET ->
//                            moAaFinalComprehensiveInspection.setOcXOffset(new BigDecimal(item.getVal()));
//                    case AfterAaFinalComprehensiveInspectionAttrMapping.OC_Y_OFFSET ->
//                            moAaFinalComprehensiveInspection.setOcYOffset(new BigDecimal(item.getVal()));
//                }
//            });
//            moAaFinalComprehensiveInspection.setId(null);
//            moAaFinalComprehensiveInspectionService.save(moAaFinalComprehensiveInspection);
//        });
//
//        return true;
//    }
//
//    public Boolean add_high_temp_curing_record(ProductionRecordDto productionRecordDto, Long moProcessStepProductionResultId) {
//        MoHighTempCuringRecord moHighTempCuringRecord = new MoHighTempCuringRecord();
//        moHighTempCuringRecord.setBeamSn(productionRecordDto.getProductSn());
//        moHighTempCuringRecord.setErrorCode(Integer.parseInt(productionRecordDto.getErrorNo()));
//        moHighTempCuringRecord.setNgReason(productionRecordDto.getError());
//        moHighTempCuringRecord.setStationNum(Integer.parseInt(productionRecordDto.getDeviceNo()));
//        moHighTempCuringRecord.setMoProcessStepProductionResultId(moProcessStepProductionResultId);
//        GroupingUtil.group(productionRecordDto.getAttrKeyVals()).forEach(items -> {
//            moHighTempCuringRecord.setTrayNo(null);
//            moHighTempCuringRecord.setOvenNo(null);
//            moHighTempCuringRecord.setMinTemp(null);
//            moHighTempCuringRecord.setMaxTemp(null);
//            moHighTempCuringRecord.setBakeDurationMinutes(null);
//            items.forEach(item -> {
//                moHighTempCuringRecord.setPosition(item.getPosition());
//                switch (item.getNo()) {
//                    case HighTempCuringRecordAttrMapping.TRAY_NO ->
//                            moHighTempCuringRecord.setTrayNo(item.getVal());
//                    case HighTempCuringRecordAttrMapping.OVEN_NO ->
//                            moHighTempCuringRecord.setOvenNo(item.getVal());
//                    case HighTempCuringRecordAttrMapping.MIN_TEMP ->
//                            moHighTempCuringRecord.setMinTemp(new BigDecimal(item.getVal()));
//                    case HighTempCuringRecordAttrMapping.MAX_TEMP ->
//                            moHighTempCuringRecord.setMaxTemp(new BigDecimal(item.getVal()));
//                    case HighTempCuringRecordAttrMapping.BAKE_DURATION ->
//                            moHighTempCuringRecord.setBakeDurationMinutes(Integer.parseInt(item.getVal()));
//                }
//            });
//            moHighTempCuringRecord.setId(null);
//            highTempCuringRecordService.save(moHighTempCuringRecord);
//        });
//
//        return true;
//    }
//
//    public Boolean add_screw_tightening_inspection(ProductionRecordDto productionRecordDto, Long moProcessStepProductionResultId) {
//        MoScrewTighteningInspection moScrewTighteningInspection = new MoScrewTighteningInspection();
//        moScrewTighteningInspection.setBeamSn(productionRecordDto.getProductSn());
//        moScrewTighteningInspection.setErrorCode(Integer.parseInt(productionRecordDto.getErrorNo()));
//        moScrewTighteningInspection.setNgReason(productionRecordDto.getError());
//        moScrewTighteningInspection.setStationNum(Integer.parseInt(productionRecordDto.getDeviceNo()));
//        moScrewTighteningInspection.setMoProcessStepProductionResultId(moProcessStepProductionResultId);
//        GroupingUtil.group(productionRecordDto.getAttrKeyVals()).forEach(items -> {
//            moScrewTighteningInspection.setTorque(null);
//            moScrewTighteningInspection.setAngle(null);
//            moScrewTighteningInspection.setTurns(null);
//            moScrewTighteningInspection.setScrewHeight(null);
//            items.forEach(item -> {
//                moScrewTighteningInspection.setPosition(item.getPosition());
//                switch (item.getNo()) {
//                    case ScrewTighteningInspectionAttrMapping.TORQUE ->
//                            moScrewTighteningInspection.setTorque(new BigDecimal(item.getVal()));
//                    case ScrewTighteningInspectionAttrMapping.ANGLE ->
//                            moScrewTighteningInspection.setAngle(new BigDecimal(item.getVal()));
//                    case ScrewTighteningInspectionAttrMapping.TURNS ->
//                            moScrewTighteningInspection.setTurns(new BigDecimal(item.getVal()));
//                    case ScrewTighteningInspectionAttrMapping.SCREW_HEIGHT ->
//                            moScrewTighteningInspection.setScrewHeight(new BigDecimal(item.getVal()));
//                }
//            });
//            moScrewTighteningInspection.setId(null);
//            screwTighteningInspectionService.save(moScrewTighteningInspection);
//        });
//
//        return true;
//    }
//
//    public Boolean add_cmos_appearance_inspection(ProductionRecordDto productionRecordDto, Long moProcessStepProductionResultId) {
//        MoCmosAppearanceInspection moCmosAppearanceInspection = new MoCmosAppearanceInspection();
//        moCmosAppearanceInspection.setBeamSn(productionRecordDto.getProductSn());
//        moCmosAppearanceInspection.setErrorCode(Integer.parseInt(productionRecordDto.getErrorNo()));
//        moCmosAppearanceInspection.setNgReason(productionRecordDto.getError());
//        moCmosAppearanceInspection.setStationNum(Integer.parseInt(productionRecordDto.getDeviceNo()));
//        moCmosAppearanceInspection.setMoProcessStepProductionResultId(moProcessStepProductionResultId);
//        GroupingUtil.group(productionRecordDto.getAttrKeyVals()).forEach(items -> {
//            moCmosAppearanceInspection.setSensorParallelism(null);
//            moCmosAppearanceInspection.setHoleDistance(null);
//            items.forEach(item -> {
//                moCmosAppearanceInspection.setPosition(item.getPosition());
//                switch (item.getNo()) {
//                    case CmosAppearanceInspectionAttrMapping.SENSOR_PARALLELISM ->
//                            moCmosAppearanceInspection.setSensorParallelism(BigDecimal.valueOf(Long.parseLong(item.getVal())));
//                    case CmosAppearanceInspectionAttrMapping.HOLE_DISTANCE ->
//                            moCmosAppearanceInspection.setHoleDistance(BigDecimal.valueOf(Long.parseLong(item.getVal())));
//
//                }
//            });
//            moCmosAppearanceInspection.setId(null);
//            moCmosAppearanceInspectionService.save(moCmosAppearanceInspection);
//        });
//
//        return true;
//    }
//
//    public Boolean add_beam_appearance_inspection(ProductionRecordDto productionRecordDto, Long moProcessStepProductionResultId) {
//        MoBeamAppearanceInspection moBeamAppearanceInspection = new MoBeamAppearanceInspection();
//        moBeamAppearanceInspection.setBeamSn(productionRecordDto.getProductSn());
//        moBeamAppearanceInspection.setErrorCode(Integer.parseInt(productionRecordDto.getErrorNo()));
//        moBeamAppearanceInspection.setNgReason(productionRecordDto.getError());
//        moBeamAppearanceInspection.setStationNum(Integer.parseInt(productionRecordDto.getDeviceNo()));
//        moBeamAppearanceInspection.setMoProcessStepProductionResultId(moProcessStepProductionResultId);
//        GroupingUtil.group(productionRecordDto.getAttrKeyVals()).forEach(items -> {
//            moBeamAppearanceInspection.setAaHoleDiameter(null);
//            moBeamAppearanceInspection.setAaHoleDistance(null);
//            moBeamAppearanceInspection.setAaHoleCenterX(null);
//            moBeamAppearanceInspection.setAaHoleCenterY(null);
//            moBeamAppearanceInspection.setThreadHoleDistance(null);
//            moBeamAppearanceInspection.setThreadHoleCenterX(null);
//            moBeamAppearanceInspection.setThreadHoleCenterY(null);
//            moBeamAppearanceInspection.setSurfaceHeightDiff(null);
//            moBeamAppearanceInspection.setSurfaceToBeamDiff(null);
//            moBeamAppearanceInspection.setBeamLength(null);
//            moBeamAppearanceInspection.setBeamWidth(null);
//            items.forEach(item -> {
//                moBeamAppearanceInspection.setPosition(item.getPosition());
//                switch (item.getNo()) {
//                    case BeamAppearanceInspectionAttrMapping.AA_HOLE_DIAMETER ->
//                            moBeamAppearanceInspection.setAaHoleDiameter(BigDecimal.valueOf(Long.parseLong(item.getVal())));
//                    case BeamAppearanceInspectionAttrMapping.AA_HOLE_DISTANCE ->
//                            moBeamAppearanceInspection.setAaHoleDistance(BigDecimal.valueOf(Long.parseLong(item.getVal())));
//                    case BeamAppearanceInspectionAttrMapping.AA_HOLE_CENTER_X ->
//                            moBeamAppearanceInspection.setAaHoleCenterX(BigDecimal.valueOf(Long.parseLong(item.getVal())));
//                    case BeamAppearanceInspectionAttrMapping.AA_HOLE_CENTER_Y ->
//                            moBeamAppearanceInspection.setAaHoleCenterY(BigDecimal.valueOf(Long.parseLong(item.getVal())));
//                    case BeamAppearanceInspectionAttrMapping.THREAD_HOLE_DISTANCE ->
//                            moBeamAppearanceInspection.setThreadHoleDistance(BigDecimal.valueOf(Long.parseLong(item.getVal())));
//                    case BeamAppearanceInspectionAttrMapping.THREAD_HOLE_CENTER_X ->
//                            moBeamAppearanceInspection.setThreadHoleCenterX(BigDecimal.valueOf(Long.parseLong(item.getVal())));
//                    case BeamAppearanceInspectionAttrMapping.THREAD_HOLE_CENTER_Y ->
//                            moBeamAppearanceInspection.setThreadHoleCenterY(BigDecimal.valueOf(Long.parseLong(item.getVal())));
//                    case BeamAppearanceInspectionAttrMapping.SURFACE_HEIGHT_DIFF ->
//                            moBeamAppearanceInspection.setSurfaceHeightDiff(BigDecimal.valueOf(Long.parseLong(item.getVal())));
//                    case BeamAppearanceInspectionAttrMapping.SURFACE_TO_BEAM_DIFF ->
//                            moBeamAppearanceInspection.setSurfaceToBeamDiff(BigDecimal.valueOf(Long.parseLong(item.getVal())));
//                    case BeamAppearanceInspectionAttrMapping.BEAM_LENGTH ->
//                            moBeamAppearanceInspection.setBeamLength(BigDecimal.valueOf(Long.parseLong(item.getVal())));
//                    case BeamAppearanceInspectionAttrMapping.BEAM_WIDTH ->
//                            moBeamAppearanceInspection.setBeamWidth(BigDecimal.valueOf(Long.parseLong(item.getVal())));
//                }
//            });
//            moBeamAppearanceInspection.setId(null);
//            moBeamAppearanceInspectionService.save(moBeamAppearanceInspection);
//        });
//
//        return true;
//    }
//
//    public Boolean add_lens_mtf_testing(ProductionRecordDto productionRecordDto, Long moProcessStepProductionResultId) {
    ////        GroupingUtil.group(productionRecordDto.getAttrKeyVals()).forEach(items -> {
    ////            ProcessMappingRegistry.ProcessMapping processMapping = ProcessMappingRegistry.get("007");
    ////            Object entity = null;
    ////            try {
    ////                entity = processMapping.getEntityClass().getDeclaredConstructor().newInstance();
    ////            } catch (Exception e) {
    ////                throw new MOException("", -1);
    ////            }
    ////            // 设置静态字段（每个 group 相同）
    ////            ReflectionMapper.setField(entity, "moProcessStepProductionResultId", moProcessStepProductionResultId.toString());
    ////
    ////            CommonAttrMapping.mapDtoFields(entity, productionRecordDto, CommonAttrMapping.FIELD_TO_FIELD);
    ////            CommonAttrMapping.mapDtoFields(entity, items.get(0), CommonAttrMapping.FIELD_TO_FIELD2);
    ////
    ////            Class<?> constantClass = processMapping.getConstantClass();
    ////            Field field = null;
    ////            try {
    ////                field = constantClass.getDeclaredField("NO_TO_FIELD");
    ////            } catch (Exception e) {
    ////                throw new MOException("", -1);
    ////            }
    ////            field.setAccessible(true);
    ////            Map<String, String> noToFieldMap = null;
    ////            try {
    ////                noToFieldMap = (Map<String, String>) field.get(null);
    ////            } catch (Exception e) {
    ////                throw new MOException("", -1);
    ////            }
    ////            // 设置每个编号字段的值
    ////            DtoEntityMapper.applyDtoToEntity(items, entity, noToFieldMap);
    ////
    ////            // 保存
    ////            Object service = applicationContext.getBean(processMapping.getServiceClass());
    ////            Method saveMethod = null;
    ////            try {
    ////                saveMethod = service.getClass().getMethod("save", Object.class);
    ////            } catch (Exception e) {
    ////                throw new MOException("", -1);
    ////            }
    ////            try {
    ////                saveMethod.invoke(service, entity);
    ////            } catch (Exception e) {
    ////                throw new MOException("", -1);
    ////            }
    ////        });
//
//        return true;
//    }

//    public Boolean add_lens_baking(ProductionRecordDto productionRecordDto, Long moProcessStepProductionResultId) {
//        MoLensBaking moLensBaking = new MoLensBaking();
//        moLensBaking.setBatchNo(productionRecordDto.getProductBatchNo());
//        moLensBaking.setErrorCode(Integer.parseInt(productionRecordDto.getErrorNo()));
//        moLensBaking.setNgReason(productionRecordDto.getError());
//        moLensBaking.setStationNum(Integer.parseInt(productionRecordDto.getDeviceNo()));
//        moLensBaking.setOperator(productionRecordDto.getOperator());
//        moLensBaking.setMoProcessStepProductionResultId(moProcessStepProductionResultId);
//        GroupingUtil.group(productionRecordDto.getAttrKeyVals()).forEach(items -> {
//            moLensBaking.setCureTime(null);
//            moLensBaking.setCurePower(null);
//            moLensBaking.setCureTemperature(null);
//            items.forEach(item -> {
//                switch (item.getNo()) {
//                    case LensBakingAttrMapping.CURE_TIME -> moLensBaking.setCureTime(Float.parseFloat(item.getVal()));
//                    case LensBakingAttrMapping.CURE_POWER -> moLensBaking.setCurePower(Float.parseFloat(item.getVal()));
//                    case LensBakingAttrMapping.CURE_TEMPERATURE -> moLensBaking.setCureTemperature(Float.parseFloat(item.getVal()));
//                }
//            });
//            moLensBaking.setId(null);
//            moLensBakingService.save(moLensBaking);
//        });
//
//        return true;
//    }
//
//    public Boolean add_ir_pasting(ProductionRecordDto productionRecordDto, Long moProcessStepProductionResultId) {
//        MoIrPasting moIrPasting = new MoIrPasting();
//        moIrPasting.setBatchNo(productionRecordDto.getProductBatchNo());
//        moIrPasting.setErrorCode(Integer.parseInt(productionRecordDto.getErrorNo()));
//        moIrPasting.setNgReason(productionRecordDto.getError());
//        moIrPasting.setStationNum(Integer.parseInt(productionRecordDto.getDeviceNo()));
//        moIrPasting.setOperator(productionRecordDto.getOperator());
//        moIrPasting.setMoProcessStepProductionResultId(moProcessStepProductionResultId);
//        GroupingUtil.group(productionRecordDto.getAttrKeyVals()).forEach(items -> {
//            moIrPasting.setDispenseMethod(null);
//            moIrPasting.setDispenseTime(null);
//            moIrPasting.setCureTime(null);
//            items.forEach(item -> {
//                switch (item.getNo()) {
//                    case IrPastingAttrMapping.DISPENSE_METHOD -> moIrPasting.setDispenseMethod(item.getVal());
//                    case IrPastingAttrMapping.DISPENSE_TIME -> moIrPasting.setDispenseTime(Float.parseFloat(item.getVal()));
//                    case IrPastingAttrMapping.CURE_TIME -> moIrPasting.setCureTime(Float.parseFloat(item.getVal()));
//                }
//            });
//            moIrPasting.setId(null);
//            moIrPastingService.save(moIrPasting);
//        });
//        return true;
//    }
//
//    public Boolean add_lens_dispensing(ProductionRecordDto productionRecordDto, Long moProcessStepProductionResultId) {
//        MoLensDispensing moLensDispensing = new MoLensDispensing();
//        moLensDispensing.setBatchNo(productionRecordDto.getProductBatchNo());
//        moLensDispensing.setErrorCode(Integer.parseInt(productionRecordDto.getErrorNo()));
//        moLensDispensing.setNgReason(productionRecordDto.getError());
//        moLensDispensing.setStationNum(Integer.parseInt(productionRecordDto.getDeviceNo()));
//        moLensDispensing.setOperator(productionRecordDto.getOperator());
//        moLensDispensing.setMoProcessStepProductionResultId(moProcessStepProductionResultId);
//        GroupingUtil.group(productionRecordDto.getAttrKeyVals()).forEach(items -> {
//            moLensDispensing.setFrontCoverDispenseTime(null);
//            moLensDispensing.setFrontCoverCureTime(null);
//            moLensDispensing.setRearCoverDispenseTime(null);
//            moLensDispensing.setRearCoverCureTime(null);
//            items.forEach(item -> {
//                switch (item.getNo()) {
//                    case LensDispensingAttrMapping.FRONT_COVER_DISPENSE_TIME -> moLensDispensing.setFrontCoverDispenseTime(Float.parseFloat(item.getVal()));
//                    case LensDispensingAttrMapping.FRONT_COVER_CURE_TIME -> moLensDispensing.setFrontCoverCureTime(Float.parseFloat(item.getVal()));
//                    case LensDispensingAttrMapping.REAR_COVER_DISPENSE_TIME -> moLensDispensing.setRearCoverDispenseTime(Float.parseFloat(item.getVal()));
//                    case LensDispensingAttrMapping.REAR_COVER_CURE_TIME -> moLensDispensing.setRearCoverCureTime(Float.parseFloat(item.getVal()));
//                }
//            });
//            moLensDispensing.setId(null);
//            moLensDispensingService.save(moLensDispensing);
//        });
//        return true;
//    }
//
//    public Boolean add_lens_cap_fastening(ProductionRecordDto productionRecordDto, Long moProcessStepProductionResultId) {
//        MoLensCapFastening moLensCapFastening = new MoLensCapFastening();
//        moLensCapFastening.setBatchNo(productionRecordDto.getProductBatchNo());
//        moLensCapFastening.setErrorCode(Integer.parseInt(productionRecordDto.getErrorNo()));
//        moLensCapFastening.setNgReason(productionRecordDto.getError());
//        moLensCapFastening.setStationNum(Integer.parseInt(productionRecordDto.getDeviceNo()));
//        moLensCapFastening.setOperator(productionRecordDto.getOperator());
//        moLensCapFastening.setMoProcessStepProductionResultId(moProcessStepProductionResultId);
//        GroupingUtil.group(productionRecordDto.getAttrKeyVals()).forEach(items -> {
//            moLensCapFastening.setScrewHeight(null);
//            moLensCapFastening.setScrewHeightUsl(null);
//            moLensCapFastening.setScrewHeightLsl(null);
//
//            moLensCapFastening.setScrewTorque(null);
//            moLensCapFastening.setScrewTorqueUsl(null);
//            moLensCapFastening.setScrewTorqueLsl(null);
//
//            moLensCapFastening.setScrewPressure(null);
//            moLensCapFastening.setScrewPressureUsl(null);
//            moLensCapFastening.setScrewPressureLsl(null);
//            items.forEach(item -> {
//                switch (item.getNo()) {
//                    case LensCapFasteningAttrMapping.SCREW_HEIGHT -> {
//                        moLensCapFastening.setScrewHeight(Float.parseFloat(item.getVal()));
//                        moLensCapFastening.setScrewHeightUsl(Float.parseFloat(item.getUsl()));
//                        moLensCapFastening.setScrewHeightLsl(Float.parseFloat(item.getLsl()));
//                    }
//                    case LensCapFasteningAttrMapping.SCREW_TORQUE -> {
//                        moLensCapFastening.setScrewTorque(Float.parseFloat(item.getVal()));
//                        moLensCapFastening.setScrewTorqueUsl(Float.parseFloat(item.getUsl()));
//                        moLensCapFastening.setScrewTorqueLsl(Float.parseFloat(item.getLsl()));
//                    }
//                    case LensCapFasteningAttrMapping.SCREW_PRESSURE -> {
//                        moLensCapFastening.setScrewPressure(Float.parseFloat(item.getVal()));
//                        moLensCapFastening.setScrewPressureUsl(Float.parseFloat(item.getUsl()));
//                        moLensCapFastening.setScrewPressureLsl(Float.parseFloat(item.getLsl()));
//                    }
//                }
//            });
//            moLensCapFastening.setId(null);
//            moLensCapFasteningService.save(moLensCapFastening);
//        });
//
//        return true;
//    }
//
//    public Boolean add_lens_assembly(ProductionRecordDto productionRecordDto, Long moProcessStepProductionResultId) {
//        MoLensAssembly moLensAssembly = new MoLensAssembly();
//        moLensAssembly.setBatchNo(productionRecordDto.getProductBatchNo());
//        moLensAssembly.setErrorCode(Integer.parseInt(productionRecordDto.getErrorNo()));
//        moLensAssembly.setNgReason(productionRecordDto.getError());
//        moLensAssembly.setStationNum(Integer.parseInt(productionRecordDto.getDeviceNo()));
//        moLensAssembly.setOperator(productionRecordDto.getOperator());
//        moLensAssembly.setMoProcessStepProductionResultId(moProcessStepProductionResultId);
//        GroupingUtil.group(productionRecordDto.getAttrKeyVals()).forEach(items -> {
//            moLensAssembly.setAssemblyPressure(null);
//            moLensAssembly.setAssemblyPressureUsl(null);
//            moLensAssembly.setAssemblyPressureLsl(null);
//            moLensAssembly.setAssemblyHeight(null);
//            moLensAssembly.setAssemblyHeightUsl(null);
//            moLensAssembly.setAssemblyHeightLsl(null);
//            items.forEach(item -> {
//                switch (item.getNo()) {
//                    case LensAssemblyAttrMapping.ASSEMBLY_PRESSURE -> {
//                        moLensAssembly.setAssemblyPressure(Float.parseFloat(item.getVal()));
//                        moLensAssembly.setAssemblyPressureUsl(Float.parseFloat(item.getUsl()));
//                        moLensAssembly.setAssemblyPressureLsl(Float.parseFloat(item.getLsl()));
//                    }
//                    case LensAssemblyAttrMapping.ASSEMBLY_HEIGHT -> {
//                        moLensAssembly.setAssemblyHeight(Float.parseFloat(item.getVal()));
//                        moLensAssembly.setAssemblyHeightUsl(Float.parseFloat(item.getUsl()));
//                        moLensAssembly.setAssemblyHeightLsl(Float.parseFloat(item.getLsl()));
//                    }
//                }
//            });
//
//            moLensAssemblyService.save(moLensAssembly);
//        });
//
//        return true;
//    }
//
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
//
//        GroupingUtil.group(productionRecordDto.getAttrKeyVals()).forEach(items -> {
//            MoAutoAdjustSt08 moAutoAdjustSt08 = new MoAutoAdjustSt08();
//            moAutoAdjustSt08.setBeamSn(productionRecordDto.getProductSn());
//            moAutoAdjustSt08.setMoProcessStepProductionResultId(moProcessStepProductionResultId);
//            moAutoAdjustSt08.setStage(null);
//            moAutoAdjustSt08.setPosition(null);
//            moAutoAdjustSt08.setImagePath(null);
//            moAutoAdjustSt08.setOcXOffset(null);
//            moAutoAdjustSt08.setOcXOffsetUsl(null);
//            moAutoAdjustSt08.setOcYOffset(null);
//            moAutoAdjustSt08.setOcYOffsetUsl(null);
//            moAutoAdjustSt08.setCodXOffset(null);
//            moAutoAdjustSt08.setCodXOffsetUsl(null);
//            moAutoAdjustSt08.setCodYOffset(null);
//            moAutoAdjustSt08.setCodYOffsetUsl(null);
//            moAutoAdjustSt08.setMtfCenterValue(null);
//            moAutoAdjustSt08.setMtfCenterLsl(null);
//            moAutoAdjustSt08.setMtfLeftupValue(null);
//            moAutoAdjustSt08.setMtfLeftupLsl(null);
//            moAutoAdjustSt08.setMtfRightupValue(null);
//            moAutoAdjustSt08.setMtfRightupLsl(null);
//            moAutoAdjustSt08.setMtfLeftdownValue(null);
//            moAutoAdjustSt08.setMtfLeftdownLsl(null);
//            moAutoAdjustSt08.setMtfRightdownValue(null);
//            moAutoAdjustSt08.setMtfRightdownLsl(null);
//            moAutoAdjustSt08.setMtfRangeOffset(null);
//            moAutoAdjustSt08.setMtfRangeOffsetUsl(null);
//            moAutoAdjustSt08.setAaClawGripPressure(null);
//            moAutoAdjustSt08.setAaClawGripPressureSpec(null);
//            moAutoAdjustSt08.setReleaseClawMinusUvCuredMtfCenterDiffUsl(null);
//            moAutoAdjustSt08.setReleaseClawMinusUvCuredMtfBlDiffUsl(null);
//            moAutoAdjustSt08.setReleaseClawMinusUvCuredMtfBrDiffUsl(null);
//            moAutoAdjustSt08.setReleaseClawMinusUvCuredMtfTrDiffUsl(null);
//            moAutoAdjustSt08.setReleaseClawMinusUvCuredMtfTlDiffUsl(null);
//            items.forEach(item -> {
//                moAutoAdjustSt08.setStage(item.getStage());
//                moAutoAdjustSt08.setPosition(item.getPosition());
//                if (item.getNo().equals(AutoAdjustAttrMapping.IMG_PATH)) {
//                    moAutoAdjustSt08.setImagePath(item.getVal());
//                } else {
//                    double val = parseDouble(item.getVal());
//                    switch (item.getNo()) {
//                        case AutoAdjustAttrMapping.OC_X_OFFSET -> {
//                            moAutoAdjustSt08.setOcXOffset(Float.parseFloat(item.getVal()));
//                            moAutoAdjustSt08.setOcXOffsetUsl(Float.parseFloat(item.getUsl()));
//                        }
//                        case AutoAdjustAttrMapping.OC_Y_OFFSET -> {
//                            moAutoAdjustSt08.setOcYOffset(Float.parseFloat(item.getVal()));
//                            moAutoAdjustSt08.setOcYOffsetUsl(Float.parseFloat(item.getUsl()));
//                        }
//                        case AutoAdjustAttrMapping.COD_X_OFFSET -> {
//                            moAutoAdjustSt08.setCodXOffset(Float.parseFloat(item.getVal()));
//                            moAutoAdjustSt08.setCodXOffsetUsl(Float.parseFloat(item.getUsl()));
//                        }
//                        case AutoAdjustAttrMapping.COD_Y_OFFSET -> {
//                            moAutoAdjustSt08.setCodYOffset(Float.parseFloat(item.getVal()));
//                            moAutoAdjustSt08.setCodYOffsetUsl(Float.parseFloat(item.getUsl()));
//                        }
//
//                        case AutoAdjustAttrMapping.MTF_CENTER -> {
//                            moAutoAdjustSt08.setMtfCenterValue(val);
//                            moAutoAdjustSt08.setMtfCenterLsl(parseDouble(item.getLsl()));
//                        }
//                        case AutoAdjustAttrMapping.MTF_TL -> {
//                            moAutoAdjustSt08.setMtfLeftupValue(val);
//                            moAutoAdjustSt08.setMtfLeftupLsl(parseDouble(item.getLsl()));
//                        }
//                        case AutoAdjustAttrMapping.MTF_TR -> {
//                            moAutoAdjustSt08.setMtfRightupValue(val);
//                            moAutoAdjustSt08.setMtfRightupLsl(parseDouble(item.getLsl()));
//                        }
//
//                        case AutoAdjustAttrMapping.MTF_BL -> {
//                            moAutoAdjustSt08.setMtfLeftdownValue(val);
//                            moAutoAdjustSt08.setMtfLeftdownLsl(parseDouble(item.getLsl()));
//                        }
//                        case AutoAdjustAttrMapping.MTF_BR -> {
//                            moAutoAdjustSt08.setMtfRightdownValue(val);
//                            moAutoAdjustSt08.setMtfRightdownLsl(parseDouble(item.getLsl()));
//                        }
//
//                        case AutoAdjustAttrMapping.MTF_RANGE_OFFSET -> {
//                            moAutoAdjustSt08.setMtfRangeOffset(Float.parseFloat(item.getVal()));
//                            moAutoAdjustSt08.setMtfRangeOffsetUsl(Float.parseFloat(item.getUsl()));
//                        }
//
//                        case AutoAdjustAttrMapping.AA_CLAW_GRIP_PRESSURE -> {
//                            moAutoAdjustSt08.setAaClawGripPressure(Float.parseFloat(item.getVal()));
//                            moAutoAdjustSt08.setAaClawGripPressureSpec(Float.parseFloat(item.getSpec()));
//                        }
//
//                        case AutoAdjustAttrMapping.RELEASE_CLAW_MINUS_UV_CURED_MTF_CENTER_DIFF -> {
//                            moAutoAdjustSt08.setReleaseClawMinusUvCuredMtfCenterDiffUsl(Float.parseFloat(item.getUsl()));
//                        }
//
//                        case AutoAdjustAttrMapping.RELEASE_CLAW_MINUS_UV_CURED_MTF_BL_DIFF -> {
//                            moAutoAdjustSt08.setReleaseClawMinusUvCuredMtfBlDiffUsl(Float.parseFloat(item.getUsl()));
//                        }
//
//                        case AutoAdjustAttrMapping.RELEASE_CLAW_MINUS_UV_CURED_MTF_BR_DIFF -> {
//                            moAutoAdjustSt08.setReleaseClawMinusUvCuredMtfBrDiffUsl(Float.parseFloat(item.getUsl()));
//                        }
//
//                        case AutoAdjustAttrMapping.RELEASE_CLAW_MINUS_UV_CURED_MTF_TR_DIFF -> {
//                            moAutoAdjustSt08.setReleaseClawMinusUvCuredMtfTrDiffUsl(Float.parseFloat(item.getUsl()));
//                        }
//
//                        case AutoAdjustAttrMapping.RELEASE_CLAW_MINUS_UV_CURED_MTF_TL_DIFF -> {
//                            moAutoAdjustSt08.setReleaseClawMinusUvCuredMtfTlDiffUsl(Float.parseFloat(item.getUsl()));
//                        }
//                    }
//                }
//            });
//            moAutoAdjustSt08.setId(null);
//            moAutoAdjustSt08Service.save(moAutoAdjustSt08);
//        });
//
//        return true;
//    }
//
//    public Boolean add_uv_dispensing(ProductionRecordDto productionRecordDto, Long moProcessStepProductionResultId) {
//        UvDispensing uvDispensing = new UvDispensing();
//        uvDispensing.setBeamSn(productionRecordDto.getProductSn());
//        uvDispensing.setErrorCode(Integer.parseInt(productionRecordDto.getErrorNo()));
//        uvDispensing.setNgReason(productionRecordDto.getError());
//        uvDispensing.setStationNum(Integer.parseInt(productionRecordDto.getDeviceNo()));
//        uvDispensing.setMoProcessStepProductionResultId(moProcessStepProductionResultId);
//        GroupingUtil.group(productionRecordDto.getAttrKeyVals()).forEach(items -> {
//            uvDispensing.setGlueWidth(null);
//            uvDispensing.setGlueWidthLsl(null);
//            uvDispensing.setGlueWidthUsl(null);
//            uvDispensing.setCircleCenterOffset(null);
//            uvDispensing.setCircleCenterOffsetUsl(null);
//            uvDispensing.setImagePath(null);
//            items.forEach(item -> {
//                uvDispensing.setPosition(item.getPosition());
//                switch (item.getNo()) {
//                    case UvDispensingAttrMapping.GLUE_WIDTH -> {
//                        uvDispensing.setGlueWidth(Float.parseFloat(item.getVal()));
//                        uvDispensing.setGlueWidthLsl(Float.parseFloat(item.getLsl()));
//                        uvDispensing.setGlueWidthUsl(Float.parseFloat(item.getUsl()));
//                    }
//                    case UvDispensingAttrMapping.CIRCLE_CENTER_OFFSET -> {
//                        uvDispensing.setCircleCenterOffset(Float.parseFloat(item.getVal()));
//                        uvDispensing.setCircleCenterOffsetUsl(Float.parseFloat(item.getUsl()));
//                    }
//                    case UvDispensingAttrMapping.IMG_PATH -> uvDispensing.setImagePath(item.getVal());
//                }
//            });
//            uvDispensing.setId(null);
//            uvDispensingService.save(uvDispensing);
//        });
//
//        return true;
//    }
//
//    public Boolean add_dirty_check(ProductionRecordDto productionRecordDto, Long moProcessStepProductionResultId) {
//        MoDirtyCheck moDirtyCheck = new MoDirtyCheck();
//        moDirtyCheck.setBeamSn(productionRecordDto.getProductSn());
//        moDirtyCheck.setErrorCode(Integer.parseInt(productionRecordDto.getErrorNo()));
//        moDirtyCheck.setNgReason(productionRecordDto.getError());
//        moDirtyCheck.setStationNum(Integer.parseInt(productionRecordDto.getDeviceNo()));
//        moDirtyCheck.setMoProcessStepProductionResultId(moProcessStepProductionResultId);
//        GroupingUtil.group(productionRecordDto.getAttrKeyVals()).forEach(items -> {
//            moDirtyCheck.setStainSizeUsl(null);
//            moDirtyCheck.setStainCountUsl(null);
//            moDirtyCheck.setStains(null);
//            moDirtyCheck.setImagePath(null);
//            items.forEach(item -> {
//                moDirtyCheck.setPosition(item.getPosition());
//                switch (item.getNo()) {
//                    case DirtyCheckAttrMapping.STAIN_SIZE_USL -> moDirtyCheck.setStainSizeUsl(Float.parseFloat(item.getVal()));
//                    case DirtyCheckAttrMapping.STAIN_COUNT_USL -> moDirtyCheck.setStainCountUsl(Float.parseFloat(item.getVal()));
//                    case DirtyCheckAttrMapping.STAINS -> moDirtyCheck.setStains(item.getVal());
//                    case DirtyCheckAttrMapping.IMG_PATH -> moDirtyCheck.setImagePath(item.getVal());
//                }
//            });
//            moDirtyCheck.setId(null);
//            moDirtyCheckService.save(moDirtyCheck);
//        });
//
//        return true;
//    }

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
