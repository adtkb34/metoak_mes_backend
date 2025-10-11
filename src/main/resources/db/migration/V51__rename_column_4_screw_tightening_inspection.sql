alter table mo_screw_tightening_inspection change column `time` duration float comment '用时';
alter table mo_screw_tightening_inspection change column time_usl duration_usl float comment '用时上限';
alter table mo_screw_tightening_inspection change column time_lsl duration_lsl float comment '用时下限';