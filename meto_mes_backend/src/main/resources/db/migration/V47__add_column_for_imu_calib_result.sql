alter table mo_imu_calib_results add column create_time datetime DEFAULT CURRENT_TIMESTAMP comment '创建时间';
alter table mo_imu_calib_results add column software_tool varchar(50) comment '软件名';
alter table mo_imu_calib_results add column software_tool_version varchar(50) comment '软件版本';
alter table mo_imu_result add column software_tool varchar(50) comment '软件名';
alter table mo_imu_result add column software_tool_version varchar(50) comment '软件版本';
DROP TABLE IF EXISTS mo_imu_results;