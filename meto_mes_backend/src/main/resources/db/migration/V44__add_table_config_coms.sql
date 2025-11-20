DELETE FROM table_config
WHERE table_name = 'mo_cmos_appearance_inspection'
  AND table_field IN ('sensor_parallelism', 'hole_distance');

INSERT INTO table_config (table_name, table_field, position) VALUES
('mo_cmos_appearance_inspection', 'sensor_parallelism',             '[1,2]'),
('mo_cmos_appearance_inspection', 'hole_distance',           '[1,2]');


DELETE FROM table_config
WHERE table_name = 'mo_screw_tightening_inspection'
  AND table_field IN ('torque', 'angle', 'turns', 'screwHeight');

INSERT INTO table_config (table_name, table_field, position) VALUES
('mo_screw_tightening_inspection', 'torque',             '[1,2,3,4]'),
('mo_screw_tightening_inspection', 'angle',           '[1,2,3,4]'),
('mo_screw_tightening_inspection', 'turns',             '[1,2,3,4]'),
('mo_screw_tightening_inspection', 'screwHeight',           '[1,2,3,4]');