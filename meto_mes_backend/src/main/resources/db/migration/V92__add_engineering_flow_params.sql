ALTER TABLE mo_process_step_production_result
ADD COLUMN engineering_params_id BIGINT COMMENT '工程参数ID';

-- 添加 flow_params_id 字段
ALTER TABLE mo_process_step_production_result
ADD COLUMN flow_params_id BIGINT COMMENT '流程参数ID';