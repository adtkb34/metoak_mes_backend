ALTER TABLE mo_assemble_info
MODIFY COLUMN pcba_code VARCHAR(25);

ALTER TABLE mo_calibration
MODIFY COLUMN camera_sn VARCHAR(25);

ALTER TABLE mo_final_check
MODIFY COLUMN camera_sn VARCHAR(25);

ALTER TABLE mo_final_result
MODIFY COLUMN camera_sn VARCHAR(25);

ALTER TABLE mo_oqc_info
MODIFY COLUMN camera_sn VARCHAR(25);

ALTER TABLE mo_rejection_info
MODIFY COLUMN product_id VARCHAR(25);