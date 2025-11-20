set sql_safe_updates = 0;

delete from mo_step_attr_keys where step_type_no = '032';

insert into mo_step_attr_keys (step_type_no, attr_key, attr_no) values
('032', 'start_time', '000'),
('032', 'end_time', '001'),
('032', 'test_result', '002'),
('032', 'test_result_can0', '003'),
('032', 'test_result_can1', '004'),
('032', 'test_result_led', '005'),
('032', 'test_result_ahd', '006'),
('032', 'test_result_disparity_density', '007'),
('032', 'test_result_accuracy', '008'),
('032', 'test_result_firmware_version', '009'),
('032', 'disp_image_path', '010'),
('032', 'density', '011'),
('032', 'density_ref', '012'),
('032', 'distance_gt', '013'),
('032', 'distance_measure', '014'),
('032', 'accuracy_ref', '015'),
('032', 'multifirmware_version_ref', '016'),
('032', 'multifirmware_version', '017'),
('032', 'error_code', '018'),
('032', 'ecu_firmware_writen_version', '019'),
('032', 'ecu_production_time_writen', '020');