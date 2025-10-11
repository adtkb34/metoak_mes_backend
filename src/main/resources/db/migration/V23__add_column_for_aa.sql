-- 追加到表 mo_aa_final_comprehensive_inspection
ALTER TABLE mo_auto_adjust_st08
    ADD COLUMN uv_exposure_time_spec DECIMAL(10,2) NULL COMMENT 'UV曝光时间规格(s)',
    ADD COLUMN uv_energy_spec DECIMAL(10,2) NULL COMMENT 'UV能量规格(mJ/cm²)',
    ADD COLUMN check_glue_weight_spec DECIMAL(10,3) NULL COMMENT '胶水重量规格(mg)',
    ADD COLUMN axis_movement_distance_spec DECIMAL(10,2) NULL COMMENT '轴移动距离规格(mm)',
    ADD COLUMN test_card_illuminance_spec DECIMAL(10,2) NULL COMMENT '测试卡照度规格(lux)',
    ADD COLUMN test_card_height_spec DECIMAL(10,2) NULL COMMENT '测试卡高度规格(mm)';