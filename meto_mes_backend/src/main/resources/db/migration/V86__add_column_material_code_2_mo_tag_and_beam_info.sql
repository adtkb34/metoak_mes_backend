ALTER TABLE mo_tag_info
ADD COLUMN material_code VARCHAR(16) AFTER work_order_code;
ALTER TABLE mo_beam_info
ADD COLUMN material_code VARCHAR(16) AFTER work_order_code;