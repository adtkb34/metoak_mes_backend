ALTER TABLE mo_step_attr_keys MODIFY COLUMN mo_workstage_id INT COMMENT '工序外键';

ALTER TABLE mo_step_attr_keys
ADD CONSTRAINT fk_mo_workstage_id
FOREIGN KEY (mo_workstage_id) REFERENCES mo_workstage(id)
ON DELETE CASCADE
ON UPDATE CASCADE;