CREATE TABLE IF NOT EXISTS `simor_param_check` (
  `taskid` int NOT NULL,
  `sn` varchar(45) NOT NULL,
  `stepid` int NOT NULL,
  `result` int NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`taskid`,`sn`,`stepid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `serial_number_check` (
  `taskid` int NOT NULL,
  `sn` varchar(32) NOT NULL,
  `result` int NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`taskid`,`sn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `mo_user_operation` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `operation_desc` text COMMENT '操作描述',
  `operation_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4222 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户操作日志表';

CREATE TABLE IF NOT EXISTS `mo_tag_shell_info` (
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

CREATE TABLE IF NOT EXISTS `mo_stage_property` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `stage` varchar(16) NOT NULL,
  `property_name` varchar(45) DEFAULT NULL,
  `property_desc` varchar(45) DEFAULT NULL,
  `is_show` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_2` (`stage`,`property_name`)
) ENGINE=InnoDB AUTO_INCREMENT=294 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `mo_rejection_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `workstation_id` int DEFAULT NULL,
  `description_id` int DEFAULT NULL,
  `produce_order_code` varchar(24) DEFAULT NULL,
  `product_id` varchar(16) DEFAULT NULL,
  `total` int DEFAULT '0',
  `quantity` int DEFAULT '0',
  `rejectdate` date DEFAULT NULL,
  `user_add` varchar(128) DEFAULT NULL,
  `timestamp_add` datetime DEFAULT NULL,
  `rework_description` varchar(512) DEFAULT NULL,
  `repaired` tinyint(1) DEFAULT '0',
  `user_upd` varchar(128) DEFAULT NULL,
  `timestamp_upd` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=395 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `mo_rejection_description` (
  `description_id` int NOT NULL AUTO_INCREMENT,
  `workstation_id` int NOT NULL,
  `description` varchar(512) NOT NULL,
  `valid` tinyint(1) NOT NULL,
  PRIMARY KEY (`description_id`,`workstation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `mo_produce_property` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL COMMENT '工单ID',
  `property_name` varchar(64) DEFAULT NULL,
  `property_value` varchar(64) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `invalid` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `mo_packing_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `packing_code` varchar(32) NOT NULL COMMENT '箱号',
  `camera_sn` varchar(16) NOT NULL,
  `station_number` int NOT NULL COMMENT '工位号',
  `operator` varchar(64) NOT NULL COMMENT '操作人员',
  `start_time` datetime NOT NULL COMMENT '工序开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '工序结束时间',
  `tool_version` varchar(16) DEFAULT NULL,
  `return_repair_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_14` (`operator`),
  KEY `FK_Reference_13` (`camera_sn`),
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`operator`) REFERENCES `mo_user_info` (`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=38496 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='装箱信息表';

CREATE TABLE IF NOT EXISTS `mo_oqc_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `camera_sn` varchar(16) NOT NULL,
  `station_number` int NOT NULL COMMENT '工位号',
  `operator` varchar(64) NOT NULL COMMENT '操作人员',
  `start_time` datetime NOT NULL COMMENT '工序开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '工序结束时间',
  `stepid` int DEFAULT NULL COMMENT '检查步骤序号',
  `check_result` tinyint(1) DEFAULT NULL COMMENT '检测结果',
  `file_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `image_path` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `timestamp_tag` int unsigned NOT NULL COMMENT '时间戳标记',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_5` (`camera_sn`),
  KEY `FK_Reference_10` (`operator`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`operator`) REFERENCES `mo_user_info` (`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=15000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='出货检验信息表';

CREATE TABLE IF NOT EXISTS `mo_operation_record` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `operation_desc` text COMMENT '操作描述',
  `operation_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4425 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户操作日志表';

CREATE TABLE IF NOT EXISTS `mo_imu_results` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `camera_sn` varchar(255) DEFAULT NULL,
  `r_roll` float DEFAULT NULL,
  `r_pitch` float DEFAULT NULL,
  `r_yaw` float DEFAULT NULL,
  `t_x` float DEFAULT NULL,
  `t_y` float DEFAULT NULL,
  `t_z` float DEFAULT NULL,
  `accelerometer_error_mean` float DEFAULT NULL,
  `accelerometer_error_std` float DEFAULT NULL,
  `gyroscope_error_mean` float DEFAULT NULL,
  `gyroscope_error_std` float DEFAULT NULL,
  `reprojection_error_mean` float DEFAULT NULL,
  `reprojection_error_std` float DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_delete` tinyint(1) DEFAULT NULL,
  `calibresult_id` bigint DEFAULT NULL,
  `position` int DEFAULT NULL,
  `accelerometer_noise_density` float DEFAULT NULL,
  `accelerometer_random_walk` float DEFAULT NULL,
  `gyroscope_noise_density` float DEFAULT NULL,
  `gyroscope_random_walk` float DEFAULT NULL,
  `status` int DEFAULT NULL,
  `error_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `mo_imu_calib_results` (
  `id` int NOT NULL AUTO_INCREMENT,
  `case_name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  `reprojection_error_cam0_mean` float DEFAULT NULL,
  `reprojection_error_cam0_median` float DEFAULT NULL,
  `reprojection_error_cam0_std` float DEFAULT NULL,
  `gyroscope_error_imu0_mean` float DEFAULT NULL,
  `gyroscope_error_imu0_median` float DEFAULT NULL,
  `gyroscope_error_imu0_std` float DEFAULT NULL,
  `accelerometer_error_imu0_mean` float DEFAULT NULL,
  `accelerometer_error_imu0_median` float DEFAULT NULL,
  `accelerometer_error_imu0_std` float DEFAULT NULL,
  `tx_mm` float DEFAULT NULL,
  `ty_mm` float DEFAULT NULL,
  `tz_mm` float DEFAULT NULL,
  `timeshift_ms` float DEFAULT NULL,
  `q_angle` float DEFAULT NULL,
  `root` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `mo_final_result` (
  `id` int NOT NULL AUTO_INCREMENT,
  `camera_sn` varchar(16) NOT NULL,
  `station_number` int NOT NULL COMMENT '工位号',
  `operator` varchar(64) NOT NULL COMMENT '操作人员',
  `check_time` datetime NOT NULL COMMENT '检测时间',
  `check_type` varchar(16) NOT NULL COMMENT '检测类型',
  `check_result` tinyint(1) DEFAULT NULL COMMENT '检测结果',
  `taskid` bigint DEFAULT NULL,
  `error_code` int DEFAULT NULL,
  `image_path` varchar(256) DEFAULT NULL,
  `tool_version` varchar(16) DEFAULT NULL,
  `return_repair_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ak_unique` (`camera_sn`,`taskid`)
) ENGINE=InnoDB AUTO_INCREMENT=250825 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='终检和出货检最终检测结果信息表';

CREATE TABLE IF NOT EXISTS `mo_final_check` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `camera_sn` varchar(16) NOT NULL,
  `station_number` int NOT NULL COMMENT '工位号',
  `operator` varchar(64) NOT NULL COMMENT '操作人员',
  `start_time` datetime NOT NULL COMMENT '工序开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '工序结束时间',
  `stepid` int DEFAULT NULL COMMENT '检查步骤序号',
  `check_result` tinyint(1) DEFAULT NULL COMMENT '检查结果',
  `image_path` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `file_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `taskid` bigint DEFAULT NULL,
  `timestamp_tag` bigint DEFAULT NULL,
  `return_repair_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_4` (`camera_sn`),
  KEY `FK_Reference_9` (`operator`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`operator`) REFERENCES `mo_user_info` (`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=642537 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='终检信息表';

 CREATE TABLE IF NOT EXISTS `mo_error_desc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `stage` varchar(24) NOT NULL COMMENT '工序名称',
  `pos_index` int DEFAULT '1',
  `error_code` int DEFAULT NULL COMMENT '错误码',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=213 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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

CREATE TABLE IF NOT EXISTS `mo_customer_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_code` varchar(16) NOT NULL COMMENT '单据编号',
  `order_date` date DEFAULT NULL COMMENT '单据日期',
  `material_code` varchar(16) DEFAULT NULL COMMENT '物料编号',
  `material_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '物料名称',
  `model_type` varchar(64) DEFAULT NULL COMMENT '规格型号',
  `workshop` varchar(32) DEFAULT NULL COMMENT '生产车间',
  `product_count` int DEFAULT NULL COMMENT '产品数量',
  `product_unit` varchar(16) DEFAULT NULL COMMENT '单位',
  `business_state` varchar(16) DEFAULT NULL COMMENT '业务状态',
  `material_state` varchar(16) DEFAULT NULL COMMENT '领料状态',
  `plan_begin` datetime DEFAULT NULL,
  `plan_end` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `AK_uq_order_code` (`order_code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='客户订单信息表(From ERP)';

CREATE TABLE IF NOT EXISTS `mo_beam_material_code` (
  `id` int NOT NULL AUTO_INCREMENT,
  `project_name` varchar(64) DEFAULT NULL,
  `material_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `material_letter` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `product_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `mo_assemble_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `camera_sn` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '横梁SN',
  `pcba_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `station_number` int NOT NULL COMMENT '工位号',
  `operator` varchar(64) NOT NULL COMMENT '操作人员',
  `start_time` datetime NOT NULL COMMENT '工序开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '工序结束时间',
  `invalid` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_12` (`operator`),
  KEY `FK_Reference_7` (`camera_sn`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`operator`) REFERENCES `mo_user_info` (`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=88868 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='组装工具信息表';

 CREATE TABLE IF NOT EXISTS `mo_adjust_focus` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `camera_sn` varchar(16) NOT NULL,
  `station_number` int NOT NULL COMMENT '工位号',
  `operator` varchar(64) NOT NULL COMMENT '操作人员',
  `start_time` datetime NOT NULL COMMENT '工序开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '工序结束时间',
  `side` enum('left','right','mid','') DEFAULT NULL COMMENT '左侧镜头或右侧镜头',
  `sharpness_center` float(10,4) DEFAULT NULL COMMENT '中心清晰度',
  `sharpness_center_max` float(10,4) DEFAULT NULL COMMENT '中心清晰度最大值',
  `sharpness_leftup` float(10,4) DEFAULT NULL COMMENT '左上角清晰度',
  `sharpness_leftup_max` float(10,4) DEFAULT NULL COMMENT '左上角清晰度最大值',
  `sharpness_rightup` float(10,4) DEFAULT NULL,
  `sharpness_rightup_max` float(10,4) DEFAULT NULL,
  `sharpness_leftdown` float(10,4) DEFAULT NULL,
  `sharpness_leftdown_max` float(10,4) DEFAULT NULL,
  `sharpness_rightdown` float(10,4) DEFAULT NULL,
  `sharpness_rightdown_max` float(10,4) DEFAULT NULL,
  `lens_sn` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '镜头sn号',
  `cmos_sn` char(30) DEFAULT NULL,
  `image_path` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图像路径',
  `error_code` int DEFAULT '0' COMMENT '调焦综合结果',
  `mtf_center_lsl` float(10,4) DEFAULT NULL COMMENT '中心值最低规格线',
  `mtf_center_usl` float(10,4) DEFAULT NULL COMMENT '中心值最高规格线',
  `mtf_leftup_lsl` float(10,4) DEFAULT NULL COMMENT '左上角值低高规格线',
  `mtf_leftup_usl` float(10,4) DEFAULT NULL COMMENT '左上角值最高规格线',
  `mtf_rightup_lsl` float(10,4) DEFAULT NULL,
  `mtf_rightup_usl` float(10,4) DEFAULT NULL,
  `mtf_leftdown_lsl` float(10,4) DEFAULT NULL,
  `mtf_leftdown_usl` float(10,4) DEFAULT NULL,
  `mtf_rightdown_lsl` float(10,4) DEFAULT NULL,
  `mtf_rightdown_usl` float(10,4) DEFAULT NULL,
  `tool_version` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '工具软件版本号',
  `light_field_black_point` int DEFAULT NULL COMMENT '明场黑点数量',
  `light_field_black_threshold` int DEFAULT NULL COMMENT '明场判断为黑点的像素阈值',
  `dark_field_white_point` int DEFAULT NULL COMMENT '暗场白点数量',
  `dark_field_white_threshold` int DEFAULT NULL COMMENT '暗场判断为白点的像素阈值',
  `abnormal_point_threshold` int DEFAULT NULL COMMENT '异常点数量阈值',
  `dirty_count` int DEFAULT NULL COMMENT '脏污区域数量',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_8` (`operator`),
  KEY `Index_3` (`start_time`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`operator`) REFERENCES `mo_user_info` (`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=14882 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='调焦工具数据表';

CREATE TABLE IF NOT EXISTS `mo_aaqc_config` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `type_num1` int unsigned NOT NULL,
  `type_num2` int unsigned NOT NULL,
  `threshold_diff` decimal(6,2) NOT NULL,
  `threshold_count` int unsigned NOT NULL,
  `operation_time` datetime DEFAULT NULL,
  `operator` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `isp_param_check` (
  `taskid` bigint NOT NULL,
  `sn` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `stepid` bigint NOT NULL,
  `result` bigint DEFAULT NULL,
  `lo` float(12,6) DEFAULT NULL,
  `disp` float(12,6) DEFAULT NULL,
  `kpi_lo` float(12,6) DEFAULT NULL,
  `kpi_disp` float(12,6) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`taskid`,`sn`,`stepid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `fov_fadezone_check` (
  `taskid` bigint NOT NULL,
  `sn` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `stepid` bigint NOT NULL,
  `result` bigint DEFAULT NULL,
  `fov` bigint DEFAULT NULL,
  `density_fadezone` float(10,6) DEFAULT NULL,
  `kpi_fadezone` float(10,6) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`taskid`,`sn`,`stepid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 CREATE TABLE IF NOT EXISTS `firmware_check` (
  `taskid` bigint NOT NULL,
  `sn` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `stepid` bigint NOT NULL,
  `result` bigint DEFAULT NULL,
  `usb_version` longtext,
  `fpga_version` longtext,
  `usb_version_standard` longtext,
  `fpga_version_standard` longtext,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`taskid`,`sn`,`stepid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `filllight_check` (
  `taskid` bigint NOT NULL,
  `sn` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `stepid` bigint NOT NULL,
  `result` bigint DEFAULT NULL,
  `fill_light_status` bigint DEFAULT NULL,
  `density_target` float(10,6) DEFAULT NULL,
  `kpi_density_target` float(10,6) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`taskid`,`sn`,`stepid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `error_descriptions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `message` varchar(255) NOT NULL,
  `procedure_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `dist_measuring_check` (
  `taskid` bigint NOT NULL,
  `sn` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `stepid` bigint NOT NULL,
  `result` bigint DEFAULT NULL,
  `target_left` bigint DEFAULT NULL,
  `target_top` bigint DEFAULT NULL,
  `target_width` bigint DEFAULT NULL,
  `target_height` bigint DEFAULT NULL,
  `areaid` bigint NOT NULL,
  `distance_truth` float(10,6) DEFAULT NULL,
  `distance_measured` float(10,6) DEFAULT NULL,
  `density` float(10,6) DEFAULT NULL,
  `precision1` float(10,6) DEFAULT NULL,
  `kpi_precision` float(10,6) DEFAULT NULL,
  `kpi_density` float(10,6) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`taskid`,`sn`,`stepid`,`areaid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `delivery_check` (
  `taskid` bigint NOT NULL,
  `sn` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `stepid` bigint NOT NULL,
  `distance_truth` float(10,6) DEFAULT NULL,
  `distance_measured` float(10,6) DEFAULT NULL,
  `density` float(10,6) DEFAULT NULL,
  `precision1` float(10,6) DEFAULT NULL,
  `kpi_precision` float(10,6) DEFAULT NULL,
  `kpi_density` float(10,6) DEFAULT NULL,
  `lineoffset` float(10,6) DEFAULT NULL,
  `dispoffset` float(10,6) DEFAULT NULL,
  `result` bigint DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`taskid`,`sn`,`stepid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;