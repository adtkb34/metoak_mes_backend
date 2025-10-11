ALTER TABLE mo_workstage
ADD COLUMN sys_step_type_no VARCHAR(10)
COMMENT '系统自身维护的一份编号，保证所有场地此编号都对应同一工序';