ALTER TABLE `mo_params_detail`
    ADD COLUMN `param_hash` VARCHAR(128) NULL COMMENT '参数内容哈希';

CREATE INDEX idx_mo_params_detail_param_hash ON mo_params_detail (`param_hash`);
