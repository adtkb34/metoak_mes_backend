ALTER TABLE mo_ir_pasting
CHANGE COLUMN cure_time cure_time_spec  FLOAT NULL;


ALTER TABLE mo_lens_assembly DROP COLUMN assembly_pressure;
ALTER TABLE mo_lens_assembly DROP COLUMN assembly_pressure_usl;
ALTER TABLE mo_lens_assembly DROP COLUMN assembly_pressure_lsl;
ALTER TABLE mo_lens_assembly DROP COLUMN assembly_height;
ALTER TABLE mo_lens_assembly DROP COLUMN assembly_height_usl;
ALTER TABLE mo_lens_assembly DROP COLUMN assembly_height_lsl;
ALTER TABLE mo_lens_assembly DROP COLUMN assembly_angle_spec;

ALTER TABLE mo_process_step_production_result
ADD COLUMN add_time DATETIME DEFAULT CURRENT_TIMESTAMP;