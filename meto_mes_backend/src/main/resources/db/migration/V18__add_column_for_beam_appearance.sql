ALTER TABLE `mo_beam_appearance_inspection`
  -- aa_hole_diameter
  ADD COLUMN `aa_hole_diameter_usl` decimal(10,4),
  ADD COLUMN `aa_hole_diameter_lsl` decimal(10,4),

  -- aa_hole_distance
  ADD COLUMN `aa_hole_distance_usl` decimal(10,4),
  ADD COLUMN `aa_hole_distance_lsl` decimal(10,4),

  -- thread_hole_distance / center_x / center_y
  ADD COLUMN `thread_hole_distance_usl` decimal(10,4),
  ADD COLUMN `thread_hole_distance_lsl` decimal(10,4),

  -- bonding surfaces
  ADD COLUMN `bonding_surface_height_diff_usl` decimal(10,4),
  ADD COLUMN `bonding_surface_to_beam_diff_usl` decimal(10,4),

  -- beam_length / width
  ADD COLUMN `beam_length_usl` decimal(10,4),
  ADD COLUMN `beam_length_lsl` decimal(10,4),
  ADD COLUMN `beam_width_usl` decimal(10,4),
  ADD COLUMN `beam_width_lsl` decimal(10,4);


CREATE TABLE IF NOT EXISTS mo_laser_marking_inspection (
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    laser_power        FLOAT            DEFAULT NULL,
    laser_template_no  VARCHAR(255)     DEFAULT NULL,
  `mo_process_step_production_result_id` bigint DEFAULT NULL,
  `error_code` int DEFAULT NULL,
  `station_num` int DEFAULT NULL,
  `ng_reason` varchar(100) DEFAULT NULL,
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `beam_sn` varchar(255) DEFAULT NULL
);