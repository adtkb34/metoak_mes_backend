DELETE FROM table_config
WHERE table_name = 'mo_aa_final_comprehensive_inspection'
  AND table_field IN ('image_quality_color', 'flare_level', 'oc_x_offset', 'oc_y_offset', 'sfr_center', 'sfr_top_left', 'sfr_top_right', 'sfr_bottom_left', 'sfr_bottom_right', 'current_consumption');


INSERT INTO table_config (table_name, table_field, position) VALUES
('mo_aa_final_comprehensive_inspection', 'image_quality_color', '[1, 2]'),
('mo_aa_final_comprehensive_inspection', 'flare_level', '[1, 2]'),
('mo_aa_final_comprehensive_inspection', 'oc_x_offset', '[1, 2]'),
('mo_aa_final_comprehensive_inspection', 'oc_y_offset', '[1, 2]'),
('mo_aa_final_comprehensive_inspection', 'sfr_center', '[1, 2]'),
('mo_aa_final_comprehensive_inspection', 'sfr_top_left', '[1, 2]'),
('mo_aa_final_comprehensive_inspection', 'sfr_top_right', '[1, 2]'),
('mo_aa_final_comprehensive_inspection', 'sfr_bottom_left', '[1, 2]'),
('mo_aa_final_comprehensive_inspection', 'sfr_bottom_right', '[1, 2]'),
('mo_aa_final_comprehensive_inspection', 'current_consumption', '[1, 2]');