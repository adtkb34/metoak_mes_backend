ALTER TABLE packing_weight_rule
    CHANGE COLUMN product_model product_code VARCHAR(64) NOT NULL COMMENT '产品编码';

ALTER TABLE packing_weight_rule
    RENAME INDEX uk_packing_weight_rule_model TO uk_packing_weight_rule_code;
