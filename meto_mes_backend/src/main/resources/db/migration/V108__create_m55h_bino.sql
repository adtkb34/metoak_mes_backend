 CREATE TABLE IF NOT EXISTS `final_check_m55h_stereo` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',

  `mo_process_step_production_result_id` BIGINT NOT NULL COMMENT '关联生产结果ID',
  `camera_sn` VARCHAR(64) NOT NULL COMMENT '相机序列号',

  `start_time` DATETIME COMMENT '检测开始时间',
  `end_time` DATETIME COMMENT '检测结束时间',

  `test_result` TINYINT COMMENT '整体测试结果 0 NG / 1 OK',
  `test_result_can0` TINYINT COMMENT 'CAN0 测试结果',
  `test_result_can1` TINYINT COMMENT 'CAN1 测试结果',
  `test_result_led` TINYINT COMMENT 'LED 测试结果',
  `test_result_ahd` TINYINT COMMENT 'AHD 测试结果',
  `test_result_disparity_density` TINYINT COMMENT '视差稠密度测试结果',
  `test_result_accuracy` TINYINT COMMENT '视差精度测试结果',
  `test_result_firmware_version` TINYINT COMMENT '固件版本测试结果',

  `disp_image_path` VARCHAR(256) COMMENT '视差图保存路径',

  `density` FLOAT COMMENT '视差稠密度结果值',
  `density_ref` FLOAT COMMENT '稠密度目标值',
  `distance_gt` FLOAT COMMENT '真值距离 (GT)',
  `distance_measure` FLOAT COMMENT '测量距离值',
  `accuracy_ref` FLOAT COMMENT '精度指标阈值',

  `multifirmware_version_ref` VARCHAR(256) COMMENT '固件版本基准值',
  `multifirmware_version` VARCHAR(256) COMMENT '实际固件版本',

  `err_code` INT COMMENT '错误码',

  `ecu_firmware_writen_version` VARCHAR(64) COMMENT '写入的 ECU 固件版本',
  `ecu_production_time_writen` VARCHAR(64) COMMENT '写入的 ECU 生产时间',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='距离测量结果记录';

