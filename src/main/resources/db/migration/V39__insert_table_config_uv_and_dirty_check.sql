ALTER TABLE mo_dirty_check modify column image_path text COMMENT '图片路径';

DELETE FROM table_config
WHERE table_name = 'mo_dirty_check'
  AND table_field IN ('stains', 'image_path');

INSERT INTO table_config (table_name, table_field, position) VALUES
('mo_dirty_check', 'stains',               '[1,2]'),
('mo_dirty_check', 'image_path',             '[1,2]');

DELETE FROM table_config
WHERE table_name = 'mo_uv_dispensing'
  AND table_field IN ('image_path', 'glue_width', 'circle_center_offset', 'glue_width_min', 'glue_width_max');

INSERT INTO table_config (table_name, table_field, position) VALUES
('mo_uv_dispensing', 'image_path',             '[1,2]'),
('mo_uv_dispensing', 'glue_width',           '[1,2]'),
('mo_uv_dispensing', 'circle_center_offset', '[1,2]');

DELETE FROM table_config
WHERE table_name = 'mo_beam_appearance_inspection'
  AND table_field IN (
    'aa_hole_side_distance_x', 'aa_hole_side_distance_y',
    'thread_hole_distance_x_out', 'thread_hole_distance_y_out',
    'thread_hole_distance_x_in', 'thread_hole_distance_y_in',
    'beam_width', 'thread_hole_distance'
  );

INSERT INTO table_config (table_name, table_field, position) VALUES
('mo_beam_appearance_inspection', 'aa_hole_side_distance_x', '[1,2]'),
('mo_beam_appearance_inspection', 'aa_hole_side_distance_y', '[1,2]'),
('mo_beam_appearance_inspection', 'thread_hole_distance_x_out', '[1,2]'),
('mo_beam_appearance_inspection', 'thread_hole_distance_y_out', '[1,2]'),
('mo_beam_appearance_inspection', 'thread_hole_distance_x_in', '[1,2]'),
('mo_beam_appearance_inspection', 'thread_hole_distance_y_in', '[1,2]');


INSERT INTO table_config (table_name, table_field, position) VALUES
('mo_beam_appearance_inspection', 'beam_width', '[1,2]');

INSERT INTO table_config (table_name, table_field, position) VALUES
('mo_beam_appearance_inspection', 'thread_hole_distance', '[1,2]');

INSERT INTO table_config (table_name, table_field, position) VALUES
('mo_uv_dispensing', 'glue_width_min', '[1,2]');

INSERT INTO table_config (table_name, table_field, position) VALUES
('mo_uv_dispensing', 'glue_width_max', '[1,2]');


ALTER TABLE mo_uv_dispensing
  ADD COLUMN glue_height FLOAT NULL COMMENT '胶高';

INSERT INTO table_config (table_name, table_field, position) VALUES
  ('mo_uv_dispensing', 'glue_height', '[1,2]');