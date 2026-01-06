CREATE TABLE IF NOT EXISTS `mo_program_record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `error_code` int DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `params_id` bigint DEFAULT NULL,
  `software_tool` varchar(255) DEFAULT NULL,
  `software_tool_version` varchar(255) DEFAULT NULL,
  `firmware_name` varchar(64) DEFAULT NULL,
  `firmware_type` tinyint DEFAULT NULL,
  `firmware_version` varchar(64) DEFAULT NULL,
  `sn_type` int DEFAULT NULL,
  `work_order_id` varchar(64) DEFAULT NULL,
  `material_code` varchar(64) DEFAULT NULL,
  `sn` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Program record table';
