CREATE TABLE IF NOT EXISTS mo_process_step_yield (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键，自增ID',

    mo_process_step_production_result_id BIGINT NOT NULL COMMENT '【过站表】记录关联ID',

    input_num INT NOT NULL DEFAULT 0 COMMENT '检测数量',
    good_num INT NOT NULL DEFAULT 0 COMMENT '良品数量',

    PRIMARY KEY (id)
) COMMENT='工序步骤产量/良率信息表';