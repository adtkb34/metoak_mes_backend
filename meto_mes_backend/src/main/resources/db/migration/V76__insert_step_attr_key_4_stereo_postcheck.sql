SET SQL_SAFE_UPDATES = 0;
DELETE FROM mo_step_attr_keys WHERE step_type_no='024';
INSERT INTO mo_step_attr_keys (step_type_no, attr_key, attr_no) VALUES
('024', 'is_version_ok', '000'),
('024', 'is_image_ok', '001');