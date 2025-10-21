package com.metoak.mes.enums;

public enum StepMappingEnum {
    DIRTY_CHECKING("000", "脏污检测"),
    UV_DISPENSING("001", "UV点胶"),
    AUTO_ADJUST("002", "自动调节"),
    PLASMA("003", "等离子"),
    MATERIAL_BINDING("004", "材料绑定"),
    LENS_ASSEMBLY("005", "镜头组装"),
    LENS_CAP_FASTENING("006", "镜头盖固定"),
    LENS_MTF_TESTING("007", "MTF测试"),
    LENS_DISPENSING("008", "镜头点胶"),
    IR_PASTING("009", "IR贴合"),
    LENS_BAKING("010", "镜头烘烤"),
    BEAM_APPEARANCE_INSPECTION("011", "横梁外观检测"),
    LASER_MARKING_INSPECTION("012", "镭雕与检查"),
    BEAM_SEALANT_COATING("013", "横梁密封胶涂布"),
    CMOS_APPEARANCE_INSPECTION("014", "CMOS外观检测"),
    FILM_REMOVAL_CLEANING("015", "撕膜清洁"),
    SCREW_TIGHTENING_INSPECTION("016", "螺丝锁付"),
    HIGH_TEMP_CURING_RECORD("017", "高温固化"),
    AFTER_AA_FINAL_COMPREHENSIVE_INSPECTION("018", "AA后综合检测"),
    AFTER_AA_COATING_PROCESS_RECORD("019", "AA后涂布"),
    DUAL_TARGET_CALIB("020", "双目标定"),
    REAR_LENS_ASSEMBLY("022", "后群组装"),
    REAR_LENS_CAP_FASTENING("023", "后镜头盖固定"),
    S315_FINAL_CHECK("027", "S315终检");

    private final String code;
    private final String description;

    StepMappingEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    // 根据 code 获取枚举实例
    public static StepMappingEnum fromCode(String code) {
        for (StepMappingEnum step : StepMappingEnum.values()) {
            if (step.code.equals(code)) {
                return step;
            }
        }
        return null;
    }
}