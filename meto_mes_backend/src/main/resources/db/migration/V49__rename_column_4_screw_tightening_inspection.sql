alter table mo_screw_tightening_inspection change column turns `time` float comment '用时';
alter table mo_screw_tightening_inspection change column turns_usl time_usl float comment '用时上限';
alter table mo_screw_tightening_inspection change column turns_lsl time_lsl float comment '用时下限';