CREATE TABLE IF NOT EXISTS `mo_user_info` (
  `user_name` varchar(128) NOT NULL COMMENT '用户名',
  `user_password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'password' COMMENT '密码',
  `user_level` int DEFAULT NULL COMMENT '用户级别',
  `work_code` varchar(16) DEFAULT NULL COMMENT '员工号',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `create_time` datetime DEFAULT NULL,
  `user_state` int DEFAULT '0',
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';


 CREATE TABLE IF NOT EXISTS `mo_beam_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `beam_sn` varchar(20) NOT NULL,
  `work_order_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `serial_number` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_produced` tinyint(1) DEFAULT '0',
  `produce_order_id` int DEFAULT NULL,
  `is_used` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_uq_product_sn` (`beam_sn`)
) ENGINE=InnoDB AUTO_INCREMENT=146424 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='横梁序列号信息表';


CREATE TABLE IF NOT EXISTS `mo_auto_adjust_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `beam_sn` varchar(32) NOT NULL COMMENT 'sn号',
  `station_num` int DEFAULT NULL COMMENT '工站编号',
  `operation_time` datetime DEFAULT NULL COMMENT '操作时间',
  `operation_result` int DEFAULT NULL COMMENT '操作结果',
  `add_time` datetime DEFAULT NULL,
  `ng_reason` varchar(160) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `error_code` int DEFAULT '0',
  `lens_sn` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `tool_version` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `lens_batch` varchar(255) DEFAULT NULL,
  `cmos_batch` varchar(255) DEFAULT NULL,
  `beam_batch` varchar(255) DEFAULT NULL,
  `device_status` json DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mo_aa_station_info_1` (`beam_sn`)
) ENGINE=InnoDB AUTO_INCREMENT=219914 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `mo_auto_adjust_st08` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `beam_sn` varchar(20) NOT NULL COMMENT '横梁SN',
  `side` enum('left','right','') DEFAULT NULL COMMENT '左侧镜头或右侧镜头',
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `mtf_center_value` float(10,4) DEFAULT NULL COMMENT '中心MTF平均数值，实际测试值',
  `mtf_center_lsl` float(10,4) DEFAULT NULL COMMENT '规格下限',
  `mtf_leftup_value` float(10,4) DEFAULT NULL COMMENT '左上MTF平均数值',
  `mtf_leftup_lsl` float(10,4) DEFAULT NULL,
  `mtf_rightup_value` float(10,4) DEFAULT NULL,
  `mtf_rightup_lsl` float(10,4) DEFAULT NULL,
  `mtf_leftdown_value` float(10,4) DEFAULT NULL,
  `mtf_leftdown_lsl` float(10,4) DEFAULT NULL,
  `mtf_rightdown_value` float(10,4) DEFAULT NULL,
  `mtf_rightdown_lsl` float(10,4) DEFAULT NULL,
  `image_path` varchar(256) DEFAULT NULL,
  `oc_x_offset` float DEFAULT NULL,
  `oc_x_offset_usl` float DEFAULT NULL,
  `oc_y_offset_usl` float DEFAULT NULL,
  `oc_y_offset` float DEFAULT NULL,
  `cod_x_offset` float DEFAULT NULL,
  `cod_y_offset` float DEFAULT NULL,
  `cod_x_offset_usl` float DEFAULT NULL,
  `cod_y_offset_usl` float DEFAULT NULL,
  `mtf_range_offset` float DEFAULT NULL,
  `mtf_range_offset_usl` float DEFAULT NULL,
  `mo_process_step_production_result_id` bigint DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `stage` varchar(255) DEFAULT NULL,
  `aa_claw_grip_pressure` float DEFAULT NULL,
  `aa_claw_grip_pressure_spec` float DEFAULT NULL,
  `release_claw_minus_uv_cured_mtf_center_diff_usl` float DEFAULT NULL,
  `release_claw_minus_uv_cured_mtf_tl_diff_usl` float DEFAULT NULL,
  `release_claw_minus_uv_cured_mtf_tr_diff_usl` float DEFAULT NULL,
  `release_claw_minus_uv_cured_mtf_bl_diff_usl` float DEFAULT NULL,
  `release_claw_minus_uv_cured_mtf_br_diff_usl` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=684760 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AA调焦数据表';

 CREATE TABLE IF NOT EXISTS `mo_lens_mtf_checking` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `batch_no` varchar(255) DEFAULT NULL,
  `ng_reason` varchar(100) DEFAULT NULL,
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `station_num` int DEFAULT NULL,
  `error_code` int DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `mo_process_step_production_result_id` bigint DEFAULT NULL,
  `job_id` varchar(255) DEFAULT NULL,
  `frequency` int DEFAULT NULL,
  `block` int DEFAULT NULL,
  `position` int DEFAULT NULL,
  `t_mtf` float DEFAULT NULL,
  `s_mtf` float DEFAULT NULL,
  `t_peak` float DEFAULT NULL,
  `s_peak` float DEFAULT NULL,
  `t_fs` float DEFAULT NULL,
  `s_fs` float DEFAULT NULL,
  `astigmatism` float DEFAULT NULL,
  `field_curvature_t` float DEFAULT NULL,
  `field_curvature_s` float DEFAULT NULL,
  `offset_t` float DEFAULT NULL,
  `offset_s` float DEFAULT NULL,
  `uniformity_t` float DEFAULT NULL,
  `uniformity_s` float DEFAULT NULL,
  `depth_of_focus` float DEFAULT NULL,
  `back_focal_distance` float DEFAULT NULL,
  `focal_length` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65889 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `mo_process_step_production_result` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_sn` varchar(255) DEFAULT NULL,
  `product_batch_no` varchar(255) DEFAULT NULL,
  `step_type` varchar(255) DEFAULT NULL,
  `step_type_no` varchar(255) DEFAULT NULL,
  `error_code` int DEFAULT NULL,
  `ng_reason` varchar(100) DEFAULT NULL,
  `station_num` int DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `software_tool` varchar(255) DEFAULT NULL,
  `software_tool_version` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4260 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `mo_uv_dispensing` (
  `id` int NOT NULL AUTO_INCREMENT,
  `beam_sn` varchar(255) DEFAULT NULL,
  `glue_width` float DEFAULT NULL,
  `glue_width_usl` float DEFAULT NULL,
  `circle_center_offset` float DEFAULT NULL,
  `circle_center_offset_usl` float DEFAULT NULL,
  `image_path` varchar(100) DEFAULT NULL,
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `error_code` int DEFAULT NULL,
  `ng_reason` varchar(100) DEFAULT NULL,
  `station_num` int DEFAULT NULL,
  `side` enum('left','right','') DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `mo_process_step_production_result_id` bigint DEFAULT NULL,
  `glue_width_lsl` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `mo_dirty_check` (
  `id` int NOT NULL AUTO_INCREMENT,
  `stain_size_usl` float DEFAULT NULL,
  `stain_count_usl` float DEFAULT NULL,
  `stains` varchar(255) DEFAULT NULL,
  `image_path` varchar(100) DEFAULT NULL,
  `error_code` int DEFAULT NULL,
  `beam_sn` varchar(255) DEFAULT NULL,
  `ng_reason` varchar(100) DEFAULT NULL,
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `station_num` int DEFAULT NULL,
  `side` enum('left','right','') DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `mo_process_step_production_result_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `mo_material_binding` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `camera_sn` varchar(255) DEFAULT NULL,
  `camera_batch` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `category_no` varchar(255) DEFAULT NULL,
  `material_batch_no` varchar(255) DEFAULT NULL,
  `material_serial_no` varchar(255) DEFAULT NULL,
  `is_deleted` bigint DEFAULT '0',
  `create_time` datetime DEFAULT (now()),
  `update_time` datetime DEFAULT NULL,
  `mo_process_step_production_result_id` bigint DEFAULT NULL,
  `position` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=235 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;