
CREATE TABLE IF NOT EXISTS mo_packing_base (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    packing_code VARCHAR(50) NOT NULL COMMENT '箱号',
    weight_rule_id BIGINT NULL COMMENT '关联的重量规则',
    gross_weight DECIMAL(10,3) NULL COMMENT '毛重',
    monitor_path VARCHAR(255) NULL COMMENT '监控路径',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    created_by VARCHAR(50) NULL COMMENT '创建者',
    PRIMARY KEY (id)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COMMENT='打包基础信息表';
