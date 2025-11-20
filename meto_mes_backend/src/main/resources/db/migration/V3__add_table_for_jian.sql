CREATE TABLE IF NOT EXISTS `mo_lens_dispensing` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `batch_no` varchar(255) DEFAULT NULL,
  `front_cover_dispense_time` float DEFAULT NULL,
  `front_cover_cure_time` float DEFAULT NULL,
  `rear_cover_dispense_time` float DEFAULT NULL,
  `rear_cover_cure_time` float DEFAULT NULL,
  `ng_reason` varchar(100) DEFAULT NULL,
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `station_num` int DEFAULT NULL,
  `error_code` int DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `mo_process_step_production_result_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `mo_lens_cap_fastening` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `batch_no` varchar(255) DEFAULT NULL,
  `screw_height` float DEFAULT NULL,
  `screw_height_usl` float DEFAULT NULL,
  `screw_height_lsl` float DEFAULT NULL,
  `screw_torque` float DEFAULT NULL,
  `screw_torque_usl` float DEFAULT NULL,
  `screw_torque_lsl` float DEFAULT NULL,
  `screw_pressure` float DEFAULT NULL,
  `screw_pressure_usl` float DEFAULT NULL,
  `screw_pressure_lsl` float DEFAULT NULL,
  `ng_reason` varchar(100) DEFAULT NULL,
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `station_num` int DEFAULT NULL,
  `error_code` int DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `mo_process_step_production_result_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `mo_lens_baking` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `batch_no` varchar(255) DEFAULT NULL,
  `cure_time` float DEFAULT NULL,
  `cure_power` float DEFAULT NULL,
  `cure_temperature` float DEFAULT NULL,
  `ng_reason` varchar(100) DEFAULT NULL,
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `station_num` int DEFAULT NULL,
  `error_code` int DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `mo_process_step_production_result_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `mo_lens_assembly` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `batch_no` varchar(255) DEFAULT NULL,
  `assembly_pressure` float DEFAULT NULL,
  `assembly_pressure_usl` float DEFAULT NULL,
  `assembly_pressure_lsl` float DEFAULT NULL,
  `assembly_height` float DEFAULT NULL,
  `assembly_height_usl` float DEFAULT NULL,
  `assembly_height_lsl` float DEFAULT NULL,
  `ng_reason` varchar(100) DEFAULT NULL,
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `station_num` int DEFAULT NULL,
  `error_code` int DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `mo_process_step_production_result_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `mo_ir_pasting` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `batch_no` varchar(255) DEFAULT NULL,
  `dispense_method` varchar(255) DEFAULT NULL,
  `dispense_time` float DEFAULT NULL,
  `cure_time` float DEFAULT NULL,
  `ng_reason` varchar(100) DEFAULT NULL,
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `station_num` int DEFAULT NULL,
  `error_code` int DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `mo_process_step_production_result_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;