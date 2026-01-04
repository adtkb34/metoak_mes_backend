CREATE TABLE IF NOT EXISTS packing_weight_rule (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键，自增ID',
    product_model VARCHAR(64) NOT NULL COMMENT '产品型号',
    full_box_quantity INT NOT NULL COMMENT '整箱数量',
    single_product_weight DECIMAL(10,3) NOT NULL COMMENT '单个产品重量',
    full_box_package_weight DECIMAL(10,3) NOT NULL COMMENT '整箱时包裹重量',
    allowed_deviation DECIMAL(10,3) NOT NULL COMMENT '允许的误差',
    unit VARCHAR(16) NOT NULL COMMENT '重量单位',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_packing_weight_rule_model (product_model)
) COMMENT='装箱重量规则';
