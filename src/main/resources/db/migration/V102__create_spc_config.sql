CREATE TABLE IF NOT EXISTS `mo_spc_config` (
  `id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '自增 ID（主键）',
  `user_id` INT COMMENT '用户 ID',
  `datetime` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `station` INT COMMENT '工站',
  `table_name` VARCHAR(64) COMMENT '表名',
  `field_name` VARCHAR(64) COMMENT '字段名',
  `usl` FLOAT COMMENT '控制上限',
  `lsl` FLOAT COMMENT '控制下限',
  `subgroup_length` INT DEFAULT 1 COMMENT '子组长度',
  `rules` INT DEFAULT 0 COMMENT '判异规则',
  `is_real_time` BOOLEAN DEFAULT FALSE COMMENT '是否实时更新数据',
  `statistics_length` INT DEFAULT 0 COMMENT '统计长度',
  `position` INT DEFAULT 0 COMMENT '左右目',
  INDEX idx_user_station (`user_id`, `station`),
  INDEX idx_table_field (`table_name`, `field_name`)
) COMMENT='SPC 配置表';

