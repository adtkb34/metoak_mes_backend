delete from table_config where table_name = 'mo_auto_adjust_st08';


INSERT INTO table_config (table_name, table_field, position) VALUES
('mo_auto_adjust_st08', 'oc_x_offset',      '[1,2]'),
('mo_auto_adjust_st08', 'oc_y_offset',      '[1,2]'),
('mo_auto_adjust_st08', 'cod_x_offset',     '[1,2]'),
('mo_auto_adjust_st08', 'cod_y_offset',     '[1,2]'),
('mo_auto_adjust_st08', 'mtf_center_value',       '[1,2]'),
('mo_auto_adjust_st08', 'mtf_leftup_value',           '[1,2]'),
('mo_auto_adjust_st08', 'mtf_rightup_value',           '[1,2]'),
('mo_auto_adjust_st08', 'mtf_leftdown_value',           '[1,2]'),
('mo_auto_adjust_st08', 'mtf_rightdown_value',           '[1,2]'),
('mo_auto_adjust_st08', 'mtf_range_offset', '[1,2]');

UPDATE table_config
SET    stage = '[1,2,3]'
WHERE  table_name = 'mo_auto_adjust_st08'
  AND  table_field IN (
        'mtf_center_value',
        'mtf_leftup_value',
        'mtf_rightup_value',
        'mtf_leftdown_value',
        'mtf_rightdown_value',
        'mtf_range_offset'
      );

INSERT INTO table_config (table_name, table_field, stage) VALUES
 ('mo_auto_adjust_st08', 'mtf_center_lsl',      '[1,2,3]'),
 ('mo_auto_adjust_st08', 'mtf_leftup_lsl',      '[1,2,3]'),
 ('mo_auto_adjust_st08', 'mtf_rightup_lsl',     '[1,2,3]'),
 ('mo_auto_adjust_st08', 'mtf_leftdown_lsl',     '[1,2,3]'),
 ('mo_auto_adjust_st08', 'mtf_rightdown_lsl',       '[1,2,3]'),
 ('mo_auto_adjust_st08', 'mtf_range_offset_usl',           '[1,2,3]');