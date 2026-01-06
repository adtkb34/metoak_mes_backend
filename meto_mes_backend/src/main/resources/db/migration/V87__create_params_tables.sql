CREATE TABLE IF NOT EXISTS `mo_params_base` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` VARCHAR(100) NOT NULL COMMENT '参数集名称，含目的信息',
  `type` TINYINT NOT NULL COMMENT '参数集类型：0-工程，1-工艺',
  `material_code` VARCHAR(20) NULL COMMENT '产品物料编号',
  `step_type_no` VARCHAR(10) NULL COMMENT '工序编号，参数集归属的工序',
  `created_by` VARCHAR(50) NOT NULL COMMENT '创建人，用于审计',
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='参数集基础信息表';

CREATE TABLE IF NOT EXISTS `mo_params_detail` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键，同时用作token',
  `base_id` BIGINT NOT NULL COMMENT '关联 param_base.id，多对一',
  `description` TEXT NOT NULL COMMENT '版本说明，可记录变更内容',
  `version_major` INT NOT NULL COMMENT '版本：主号',
  `version_minor` INT NOT NULL COMMENT '版本：副号',
  `version_patch` INT NOT NULL COMMENT '版本：修订号',
  `param_hash` VARCHAR(128) NULL COMMENT '参数内容哈希',
  `params` JSON NOT NULL COMMENT '参数内容（JSON），完整参数',
  `is_active` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否是当前启用版本，工艺参数集对每个 name 仅允许一个 active',
  `created_by` VARCHAR(200) NOT NULL COMMENT '创建人，用于审计',
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_base_id` (`base_id`),
  KEY `idx_is_active` (`is_active`),
  KEY `idx_param_hash` (`param_hash`),
  CONSTRAINT `fk_params_detail_base_id` FOREIGN KEY (`base_id`) REFERENCES `mo_params_base` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='参数集详情表';