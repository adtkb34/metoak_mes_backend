set sql_safe_updates = 0;

delete from mo_step_attr_keys where step_type_no = '038';

INSERT INTO mo_step_attr_keys (step_type_no, attr_key, attr_no) VALUES
('038', 'params_id', '001'),
('038', 'firmware_name', '002'),
('038', 'firmware_type', '003'),
('038', 'firmware_version', '004'),
('038', 'sn_type', '005'),
('038', 'work_order_id', '006'),
('038', 'material_code', '007'),
('038', 'sn', '008');
