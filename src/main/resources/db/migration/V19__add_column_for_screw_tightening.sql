ALTER TABLE mo_screw_tightening_inspection
ADD COLUMN torque_usl DECIMAL(10, 2) COMMENT '扭力上限值 (N·m)',
ADD COLUMN torque_lsl DECIMAL(10, 2) COMMENT '扭力下限值 (N·m)',
ADD COLUMN angle_usl DECIMAL(10, 2) COMMENT '角度上限值 (°)',
ADD COLUMN angle_lsl DECIMAL(10, 2) COMMENT '角度下限值 (°)',
ADD COLUMN turns_usl DECIMAL(10, 2) COMMENT '圈数上限值',
ADD COLUMN turns_lsl DECIMAL(10, 2) COMMENT '圈数下限值',
ADD COLUMN screw_height_usl DECIMAL(10, 2) COMMENT '锁付高度上限值 (mm)';

ALTER TABLE mo_cmos_appearance_inspection
ADD COLUMN hole_distance_usl DECIMAL(10, 2) COMMENT '两孔间距上限值',
ADD COLUMN hole_distance_lsl DECIMAL(10, 2) COMMENT '两孔间距下限值';

