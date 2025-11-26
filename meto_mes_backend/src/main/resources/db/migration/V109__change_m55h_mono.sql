ALTER TABLE final_check_mono_m55h
-- 删除旧字段
DROP COLUMN datetime,
DROP COLUMN version_adas,
DROP COLUMN version_spi,
DROP COLUMN focus,
DROP COLUMN baseline,
DROP COLUMN pack_version;

-- 修改类型（BOOL -> INT）
ALTER TABLE final_check_mono_m55h
MODIFY can0_ok INT DEFAULT -1 COMMENT '失败:0；成功:1；未知:-1',
MODIFY can1_ok INT DEFAULT -1 COMMENT '失败:0；成功:1；未知:-1';

-- 字段重命名
ALTER TABLE final_check_mono_m55h
CHANGE COLUMN product_version version_product VARCHAR(64) COMMENT '产品版本';

-- 新增字段
ALTER TABLE final_check_mono_m55h
ADD COLUMN mo_process_step_production_result_id BIGINT COMMENT '过站记录关联ID' AFTER id,
ADD COLUMN start_time DATETIME COMMENT '开始时间' AFTER mo_process_step_production_result_id,
ADD COLUMN end_time DATETIME COMMENT '结束时间' AFTER start_time,
ADD COLUMN camera_type INT COMMENT '相机类型: 0竖版;1横版';

-- 检测功能相关新增字段
ALTER TABLE final_check_mono_m55h
ADD COLUMN is_image_dirty_detect_enabled BOOL AFTER version_product,
ADD COLUMN image_dirty_count INT AFTER is_image_dirty_detect_enabled,

ADD COLUMN is_board_clarity_detect_enabled BOOL AFTER image_dirty_count,
ADD COLUMN board_clarity FLOAT AFTER is_board_clarity_detect_enabled,
ADD COLUMN board_clarity_ref_min FLOAT AFTER board_clarity,

ADD COLUMN is_board_color_cast_detect_enabled BOOL AFTER board_clarity_ref_min,
ADD COLUMN board_color_cast_r_mean FLOAT AFTER is_board_color_cast_detect_enabled,
ADD COLUMN board_color_cast_g_mean FLOAT AFTER board_color_cast_r_mean,
ADD COLUMN board_color_cast_b_mean FLOAT AFTER board_color_cast_g_mean,
ADD COLUMN board_color_cast_r_stddev FLOAT AFTER board_color_cast_b_mean,
ADD COLUMN board_color_cast_g_stddev FLOAT AFTER board_color_cast_r_stddev,
ADD COLUMN board_color_cast_b_stddev FLOAT AFTER board_color_cast_g_stddev,
ADD COLUMN board_color_cast_maxdiff_ratio_ref FLOAT AFTER board_color_cast_b_stddev,
ADD COLUMN board_color_cast_maxdiff_value_ref FLOAT AFTER board_color_cast_maxdiff_ratio_ref,

ADD COLUMN is_board_cod_detect_enabled BOOL AFTER board_color_cast_maxdiff_value_ref,
ADD COLUMN board_cod_x FLOAT AFTER is_board_cod_detect_enabled,
ADD COLUMN board_cod_y FLOAT AFTER board_cod_x,
ADD COLUMN board_cod_x_ref FLOAT AFTER board_cod_y,
ADD COLUMN board_cod_x_tolerance FLOAT AFTER board_cod_x_ref,
ADD COLUMN board_cod_y_ref FLOAT AFTER board_cod_x_tolerance,
ADD COLUMN board_cod_y_tolerance FLOAT AFTER board_cod_y_ref;

