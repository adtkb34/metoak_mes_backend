ALTER TABLE mo_params_base
    ADD COLUMN flow_no VARCHAR(10) NULL COMMENT '工艺编号' AFTER type,
    ADD COLUMN order_id BIGINT NULL COMMENT '工单ID' AFTER flow_no;

ALTER TABLE mo_params_base
    DROP COLUMN material_code;

ALTER TABLE mo_params_base
    MODIFY COLUMN step_type_no VARCHAR(10) NULL COMMENT '工序类型编号';
