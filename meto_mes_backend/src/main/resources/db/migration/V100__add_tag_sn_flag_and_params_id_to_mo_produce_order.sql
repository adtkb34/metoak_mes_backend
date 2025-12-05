ALTER TABLE mo_produce_order
ADD COLUMN tag_sn_can_exceed_planned_count TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否允许 SN 超过计划数量：0=否，1=是',
ADD COLUMN params_detail_id BIGINT NULL COMMENT '参数集ID';
