alter table mo_imu_calib_results change column bag_path refpath_bag varchar(100) comment 'bag相对路径';
alter table mo_imu_calib_results change column imu_yaml_path refpath_yml_imu varchar(100) comment 'imu.yaml相对路径';
alter table mo_imu_calib_results change column imucam_yaml_path refpath_yml_imucam varchar(100) comment 'imucam.yaml相对路径';