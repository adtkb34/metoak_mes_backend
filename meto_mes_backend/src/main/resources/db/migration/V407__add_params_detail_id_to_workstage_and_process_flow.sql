ALTER TABLE mo_workstage
    ADD COLUMN params_detail_id BIGINT COMMENT '参数明细ID（关联 mo_params_detail.id）';

ALTER TABLE mo_process_flow
    ADD COLUMN params_detail_id BIGINT COMMENT '参数明细ID（关联 mo_params_detail.id）';