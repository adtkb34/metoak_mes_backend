ALTER TABLE mo_beam_appearance_inspection
  MODIFY COLUMN aa_hole_side_distance_x      FLOAT NULL COMMENT 'AA孔到页边距离x',
  MODIFY COLUMN aa_hole_side_distance_y      FLOAT NULL COMMENT 'AA孔到页边距离y',
  MODIFY COLUMN thread_hole_distance_x_out   FLOAT NULL COMMENT '外侧螺纹孔到内边距横向距离',
  MODIFY COLUMN thread_hole_distance_y_out   FLOAT NULL COMMENT '外侧螺纹孔到内边距纵向距离',
  MODIFY COLUMN thread_hole_distance_x_in    FLOAT NULL COMMENT '内侧螺纹孔到内边距横向距离',
  MODIFY COLUMN thread_hole_distance_y_in    FLOAT NULL COMMENT '内侧螺纹孔到内边距纵向距离';


ALTER TABLE mo_uv_dispensing
  ADD COLUMN glue_width_min FLOAT NULL COMMENT '胶宽最小值',
  ADD COLUMN glue_width_max FLOAT NULL COMMENT '胶宽最大值';

ALTER TABLE calibresult
   MODIFY COLUMN id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID';

ALTER TABLE mo_auto_adjust_st08
  -- 白场脏污
  ADD COLUMN white_field_dirt      FLOAT NULL COMMENT '白场脏污',
  ADD COLUMN white_field_dirt_usl  FLOAT NULL COMMENT '白场脏污-上限',

  -- 黑场暗点
  ADD COLUMN black_field_dark_spot      FLOAT NULL COMMENT '黑场暗点',
  ADD COLUMN black_field_dark_spot_usl  FLOAT NULL COMMENT '黑场暗点-上限';

-- 005 温度达到时间
ALTER TABLE mo_high_temp_curing_record
  ADD COLUMN temperature_reaching_time      varchar(100) NULL COMMENT '温度达到时间';

-- 006 烘烤完成时间
ALTER TABLE mo_high_temp_curing_record
  ADD COLUMN gripper_number  varchar(30) NULL COMMENT '夹爪编号',
  ADD COLUMN baking_end_time  varchar(100) NULL COMMENT '烘烤完成时间';