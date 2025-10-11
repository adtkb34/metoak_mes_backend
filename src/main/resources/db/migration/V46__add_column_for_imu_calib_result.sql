alter table mo_imu_calib_results add column bag_path varchar(100) comment 'bag相对路径';
alter table mo_imu_calib_results add column imu_yaml_path varchar(100) comment 'imu.yaml相对路径';
alter table mo_imu_calib_results add column imucam_yaml_path varchar(100) comment 'imucam.yaml相对路径';