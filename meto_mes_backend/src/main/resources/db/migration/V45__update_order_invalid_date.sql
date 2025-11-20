SET SQL_SAFE_UPDATES = 0;

DELETE FROM table_config
WHERE table_name = 'mo_screw_tightening_inspection'
  AND table_field IN ('screw_height');

INSERT INTO table_config (table_name, table_field, position) VALUES
('mo_screw_tightening_inspection', 'screw_height',           '[1,2,3,4]');

SET SQL_SAFE_UPDATES = 1;