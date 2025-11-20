CREATE TABLE IF NOT EXISTS  `mo_tag_shell_info` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `camera_sn` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '横梁SN',
  `shell_sn` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '外壳SN',
  `station_number` int NOT NULL COMMENT '工位号',
  `operator` varchar(64) NOT NULL COMMENT '操作人员',
  `operation_time` datetime NOT NULL COMMENT '操作时间',
  `invalid` tinyint(1) DEFAULT '0' COMMENT '解绑时置为true',
  `order_id` int DEFAULT NULL,
  `order_code` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_15` (`operator`),
  CONSTRAINT `FK_Reference_15` FOREIGN KEY (`operator`) REFERENCES `mo_user_info` (`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=88158 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='横梁SN和外壳SN对应信息表';

 CREATE TABLE IF NOT EXISTS  `mo_after_aa_coating_process_record` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `spray_pressure` decimal(10,2) DEFAULT NULL COMMENT '喷涂压力 (bar 或 MPa)',
  `spray_program_code` varchar(50) DEFAULT NULL COMMENT '喷涂轨迹程序编号',
  `mo_process_step_production_result_id` bigint DEFAULT NULL,
  `error_code` int DEFAULT NULL,
  `station_num` int DEFAULT NULL,
  `ng_reason` varchar(100) DEFAULT NULL,
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `beam_sn` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='涂布工序记录表';