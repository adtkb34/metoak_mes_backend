package com.metoak.mes.common.mapping;

import com.metoak.mes.entity.*;
import com.metoak.mes.enums.StepMappingEnum;
import com.metoak.mes.service.*;

import java.util.HashMap;
import java.util.Map;

public class ProcessMappingRegistry {

    public static class ProcessMapping {
        private final Class<?> entityClass;
        private final Class<?> serviceClass;

        public ProcessMapping(Class<?> entityClass, Class<?> serviceClass) {
            this.entityClass = entityClass;
            this.serviceClass = serviceClass;
        }


        public Class<?> getEntityClass() {
            return entityClass;
        }


        public Class<?> getServiceClass() {
            return serviceClass;
        }
    }

    // 工序编号 → 映射关系
    private static final Map<String, ProcessMapping> registry = new HashMap<>();

    static {
        put(StepMappingEnum.DIRTY_CHECKING,              MoDirtyCheck.class,                 IMoDirtyCheckService.class);
        put(StepMappingEnum.UV_DISPENSING,               UvDispensing.class,                 IUvDispensingService.class);
        put(StepMappingEnum.BEAM_SEALANT_COATING,        UvDispensing.class,                 IUvDispensingService.class);
        put(StepMappingEnum.AUTO_ADJUST,                 MoAutoAdjustSt08.class,             IMoAutoAdjustSt08Service.class);

        put(StepMappingEnum.LENS_ASSEMBLY,               MoLensAssembly.class,               IMoLensAssemblyService.class);
        put(StepMappingEnum.LENS_CAP_FASTENING,          MoLensCapFastening.class,           IMoLensCapFasteningService.class);
        put(StepMappingEnum.REAR_LENS_ASSEMBLY,          MoLensAssembly.class,               IMoLensAssemblyService.class);
        put(StepMappingEnum.REAR_LENS_CAP_FASTENING,     MoLensCapFastening.class,           IMoLensCapFasteningService.class);
        put(StepMappingEnum.LENS_MTF_TESTING,            MoLensMtfChecking.class,            IMoLensMtfCheckingService.class);
        put(StepMappingEnum.LENS_DISPENSING,             MoLensDispensing.class,             IMoLensDispensingService.class);
        put(StepMappingEnum.IR_PASTING,                  MoIrPasting.class,                  IMoIrPastingService.class);
        put(StepMappingEnum.LENS_BAKING,                 MoLensBaking.class,                 IMoLensBakingService.class);

        put(StepMappingEnum.BEAM_APPEARANCE_INSPECTION,  MoBeamAppearanceInspection.class,   IMoBeamAppearanceInspectionService.class);
        put(StepMappingEnum.LASER_MARKING_INSPECTION,    MoLaserMarkingInspection.class,     IMoLaserMarkingInspectionService.class);
        put(StepMappingEnum.CMOS_APPEARANCE_INSPECTION,  MoCmosAppearanceInspection.class,   IMoCmosAppearanceInspectionService.class);
        put(StepMappingEnum.FILM_REMOVAL_CLEANING,       MoDirtyCheck.class,                 IMoDirtyCheckService.class);
        put(StepMappingEnum.SCREW_TIGHTENING_INSPECTION, MoScrewTighteningInspection.class,  IMoScrewTighteningInspectionService.class);
        put(StepMappingEnum.HIGH_TEMP_CURING_RECORD,     MoHighTempCuringRecord.class,       IMoHighTempCuringRecordService.class);
        put(StepMappingEnum.AFTER_AA_FINAL_COMPREHENSIVE_INSPECTION,
                MoAaFinalComprehensiveInspection.class, IMoAaFinalComprehensiveInspectionService.class);
        put(StepMappingEnum.AFTER_AA_COATING_PROCESS_RECORD,
                MoAfterAaCoatingProcessRecord.class,    IMoAfterAaCoatingProcessRecordService.class);
        put(StepMappingEnum.DUAL_TARGET_CALIB,
                Calibresult.class,    ICalibresultService.class);
    }

    private static void put(StepMappingEnum step, Class<?> entity, Class<?> service) {
        registry.put(step.getCode(), new ProcessMapping(entity, service));
    }

    public static ProcessMapping get(String processNo) {
        return registry.get(processNo);
    }
}