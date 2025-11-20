ALTER TABLE mo_imu_calib_results MODIFY COLUMN root varchar(512) COMMENT '文件根路径';
ALTER TABLE mo_imu_calib_results CHANGE COLUMN refpath_bag relative_path_bag varchar(256) COMMENT 'bag相对路径';
ALTER TABLE mo_imu_calib_results CHANGE COLUMN refpath_yml_imu relative_path_yml_imu varchar(256) COMMENT 'imu.yaml相对路径';
ALTER TABLE mo_imu_calib_results CHANGE COLUMN refpath_yml_imucam relative_path_yml_imucam varchar(256) COMMENT 'imucam.yaml相对路径';