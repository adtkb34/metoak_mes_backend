ALTER TABLE mo_lens_cap_fastening
DROP COLUMN screw_pressure_lsl,
DROP COLUMN screw_pressure_usl,
DROP COLUMN screw_pressure;

ALTER TABLE mo_lens_cap_fastening
CHANGE COLUMN screw_pressure_spec screw_pressure float;