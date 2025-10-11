SET SQL_SAFE_UPDATES = 0;
DELETE FROM mo_step_attr_keys where attr_key = 'ISP_right_k5';
INSERT INTO mo_step_attr_keys (step_type_no, attr_key, attr_no) VALUES
('020', 'ISP_right_k5', '136'),
('020', 'ISP_right_k6', '137');

INSERT INTO mo_step_attr_keys (step_type_no, attr_key, attr_no) VALUES
('020', 'isp_roi_x', '114');

INSERT INTO mo_step_attr_keys (step_type_no, attr_key, attr_no) VALUES
('020', 'station', '131');
