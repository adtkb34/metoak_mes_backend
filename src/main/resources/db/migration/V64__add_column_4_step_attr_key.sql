ALTER TABLE mo_step_attr_keys DROP FOREIGN KEY fk_mo_workstage_id;
alter table mo_step_attr_keys drop column mo_workstage_id;
alter table mo_step_attr_keys add column step_type_no varchar(10) comment '工序编号';