CREATE TABLE IF NOT EXISTS `mo_lens_thickness_result` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `batch_no` varchar(255) DEFAULT NULL,
  `ng_reason` varchar(100) DEFAULT NULL,
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `station_num` int DEFAULT NULL,
  `error_code` int DEFAULT NULL,
  `mo_process_step_production_result_id` bigint DEFAULT NULL,
  `lens_type_index` int DEFAULT NULL,
  `height` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
