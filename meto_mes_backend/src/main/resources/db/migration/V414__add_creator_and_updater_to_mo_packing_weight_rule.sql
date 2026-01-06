ALTER TABLE mo_packing_weight_rule
    ADD COLUMN create_by VARCHAR(64) NULL COMMENT '创建人',
    ADD COLUMN update_by VARCHAR(64) NULL COMMENT '更新人';
