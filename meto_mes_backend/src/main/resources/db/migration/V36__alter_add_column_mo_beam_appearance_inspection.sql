-- 先为 6 组测量值本身建列（之前已给出）
ALTER TABLE mo_beam_appearance_inspection
  ADD COLUMN aa_hole_side_distance_x      FLOAT NOT NULL COMMENT 'AA孔到页边距离x',
  ADD COLUMN aa_hole_side_distance_y      FLOAT NOT NULL COMMENT 'AA孔到页边距离y',
  ADD COLUMN thread_hole_distance_x_out   FLOAT NOT NULL COMMENT '外侧螺纹孔到内边距横向距离',
  ADD COLUMN thread_hole_distance_y_out   FLOAT NOT NULL COMMENT '外侧螺纹孔到内边距纵向距离',
  ADD COLUMN thread_hole_distance_x_in    FLOAT NOT NULL COMMENT '内侧螺纹孔到内边距横向距离',
  ADD COLUMN thread_hole_distance_y_in    FLOAT NOT NULL COMMENT '内侧螺纹孔到内边距纵向距离',

  -- 再为每组追加 USL/LSL（上限 / 下限）
  ADD COLUMN aa_hole_side_distance_x_usl  FLOAT NULL COMMENT 'AA孔到页边距离x-上限',
  ADD COLUMN aa_hole_side_distance_x_lsl  FLOAT NULL COMMENT 'AA孔到页边距离x-下限',

  ADD COLUMN aa_hole_side_distance_y_usl  FLOAT NULL COMMENT 'AA孔到页边距离y-上限',
  ADD COLUMN aa_hole_side_distance_y_lsl  FLOAT NULL COMMENT 'AA孔到页边距离y-下限',

  ADD COLUMN thread_hole_distance_x_out_usl FLOAT NULL COMMENT '外侧螺纹孔到内边距横向距离-上限',
  ADD COLUMN thread_hole_distance_x_out_lsl FLOAT NULL COMMENT '外侧螺纹孔到内边距横向距离-下限',

  ADD COLUMN thread_hole_distance_y_out_usl FLOAT NULL COMMENT '外侧螺纹孔到内边距纵向距离-上限',
  ADD COLUMN thread_hole_distance_y_out_lsl FLOAT NULL COMMENT '外侧螺纹孔到内边距纵向距离-下限',

  ADD COLUMN thread_hole_distance_x_in_usl  FLOAT NULL COMMENT '内侧螺纹孔到内边距横向距离-上限',
  ADD COLUMN thread_hole_distance_x_in_lsl  FLOAT NULL COMMENT '内侧螺纹孔到内边距横向距离-下限',

  ADD COLUMN thread_hole_distance_y_in_usl  FLOAT NULL COMMENT '内侧螺纹孔到内边距纵向距离-上限',
  ADD COLUMN thread_hole_distance_y_in_lsl  FLOAT NULL COMMENT '内侧螺纹孔到内边距纵向距离-下限';