-- 消费电流上下限
ALTER TABLE mo_aa_final_comprehensive_inspection
    ADD COLUMN current_consumption_usl DECIMAL(10,2) NULL COMMENT '消费电流上限(mA)',
    ADD COLUMN current_consumption_lsl DECIMAL(10,2) NULL COMMENT '消费电流下限(mA)';

-- 帧率上下限
ALTER TABLE mo_aa_final_comprehensive_inspection
    ADD COLUMN frame_rate_usl DECIMAL(10,2) NULL COMMENT '帧率上限(fps)',
    ADD COLUMN frame_rate_lsl DECIMAL(10,2) NULL COMMENT '帧率下限(fps)';

-- 清晰度评分上下限
ALTER TABLE mo_aa_final_comprehensive_inspection
    ADD COLUMN clarity_score_usl DECIMAL(10,2) NULL COMMENT '清晰度评分上限',
    ADD COLUMN clarity_score_lsl DECIMAL(10,2) NULL COMMENT '清晰度评分下限';

-- OC X 偏差上下限
ALTER TABLE mo_aa_final_comprehensive_inspection
    ADD COLUMN oc_x_offset_usl DECIMAL(10,3) NULL COMMENT 'OC X偏差上限(mm)',
    ADD COLUMN oc_x_offset_lsl DECIMAL(10,3) NULL COMMENT 'OC X偏差下限(mm)';

-- OC Y 偏差上下限
ALTER TABLE mo_aa_final_comprehensive_inspection
    ADD COLUMN oc_y_offset_usl DECIMAL(10,3) NULL COMMENT 'OC Y偏差上限(mm)',
    ADD COLUMN oc_y_offset_lsl DECIMAL(10,3) NULL COMMENT 'OC Y偏差下限(mm)';

-- SFR 中心上下限
ALTER TABLE mo_aa_final_comprehensive_inspection
    ADD COLUMN sfr_center DECIMAL(10,2) NULL COMMENT 'SFR中心清晰度',
    ADD COLUMN sfr_center_usl DECIMAL(10,2) NULL COMMENT 'SFR中心清晰度上限',
    ADD COLUMN sfr_center_lsl DECIMAL(10,2) NULL COMMENT 'SFR中心清晰度下限';

-- SFR 左上上下限
ALTER TABLE mo_aa_final_comprehensive_inspection
    ADD COLUMN sfr_top_left DECIMAL(10,2) NULL COMMENT 'SFR左上清晰度',
    ADD COLUMN sfr_top_left_usl DECIMAL(10,2) NULL COMMENT 'SFR左上清晰度上限',
    ADD COLUMN sfr_top_left_lsl DECIMAL(10,2) NULL COMMENT 'SFR左上清晰度下限';

-- SFR 右上上下限
ALTER TABLE mo_aa_final_comprehensive_inspection
    ADD COLUMN sfr_top_right DECIMAL(10,2) NULL COMMENT 'SFR右上清晰度',
    ADD COLUMN sfr_top_right_usl DECIMAL(10,2) NULL COMMENT 'SFR右上清晰度上限',
    ADD COLUMN sfr_top_right_lsl DECIMAL(10,2) NULL COMMENT 'SFR右上清晰度下限';

-- SFR 左下上下限
ALTER TABLE mo_aa_final_comprehensive_inspection
    ADD COLUMN sfr_bottom_left DECIMAL(10,2) NULL COMMENT 'SFR左下清晰度',
    ADD COLUMN sfr_bottom_left_usl DECIMAL(10,2) NULL COMMENT 'SFR左下清晰度上限',
    ADD COLUMN sfr_bottom_left_lsl DECIMAL(10,2) NULL COMMENT 'SFR左下清晰度下限';

-- SFR 左下上下限
ALTER TABLE mo_aa_final_comprehensive_inspection
    ADD COLUMN sfr_bottom_right DECIMAL(10,2) NULL COMMENT 'SFR右下清晰度',
    ADD COLUMN sfr_bottom_right_usl DECIMAL(10,2) NULL COMMENT 'SFR右下清晰度上限',
    ADD COLUMN sfr_bottom_right_lsl DECIMAL(10,2) NULL COMMENT 'SFR右下清晰度下限';

-- 在表 mo_aa_final_comprehensive_inspection 末尾一次性追加 5 个 _spec 字段
ALTER TABLE mo_aa_final_comprehensive_inspection
    ADD COLUMN custom_chart_spec VARCHAR(255) NULL COMMENT '自定义图表规格',
    ADD COLUMN color_board_spec VARCHAR(255) NULL COMMENT '色板规格',
    ADD COLUMN fixture_no_spec VARCHAR(255) NULL COMMENT '夹具编号规格',
    ADD COLUMN illumination_spec VARCHAR(255) NULL COMMENT '光源规格',
    ADD COLUMN height_spec DECIMAL(10,2) NULL COMMENT '高度规格 (mm)';