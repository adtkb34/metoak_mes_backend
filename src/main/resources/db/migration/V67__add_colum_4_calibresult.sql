ALTER TABLE calibresult ADD COLUMN mo_process_step_production_result_id BIGINT;
INSERT INTO mo_step_attr_keys (step_type_no, attr_key, attr_no) VALUES
('020', 'TimeStamp', '002');
