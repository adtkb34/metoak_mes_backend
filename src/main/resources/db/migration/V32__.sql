UPDATE table_config
SET    stage = '[1,2,3]'
WHERE  table_name = 'mo_auto_adjust_st08'
  AND  table_field IN (
        'mtf_center',
        'mtf_tl',
        'mtf_tr',
        'mtf_bl',
        'mtf_br',
        'mtf_range_offset'
      );
INSERT INTO table_config (table_name, table_field, stage) VALUES
 ('mo_auto_adjust_st08', 'mtf_center_lsl',      '[1,2,3]'),
 ('mo_auto_adjust_st08', 'mtf_tl_lsl',      '[1,2,3]'),
 ('mo_auto_adjust_st08', 'mtf_tr_lsl',     '[1,2,3]'),
 ('mo_auto_adjust_st08', 'mtf_bl_lsl',     '[1,2,3]'),
 ('mo_auto_adjust_st08', 'mtf_br_lsl',       '[1,2,3]'),
 ('mo_auto_adjust_st08', 'mtf_range_offset_lsl',           '[1,2,3]');