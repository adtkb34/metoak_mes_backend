ALTER TABLE mo_uv_dispensing
ADD COLUMN check_glue_weight Float COMMENT '胶重';

ALTER TABLE mo_dirty_check
ADD COLUMN plasma_power_spec Float COMMENT 'Plasma设定功率',
ADD COLUMN lens_gripper_spec Float COMMENT '镜头夹爪设定值';