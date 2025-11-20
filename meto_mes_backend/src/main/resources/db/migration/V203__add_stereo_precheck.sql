ALTER TABLE mo_stereo_precheck
ADD COLUMN board_color_cast_maxdiff_ratio_ref FLOAT NULL AFTER color_cast_standard_b_tolerance,
ADD COLUMN board_color_cast_maxdiff_value_ref FLOAT NULL AFTER board_color_cast_maxdiff_ratio_ref,
ADD COLUMN tool_version VARCHAR(64) NULL,
ADD COLUMN station INT NULL;
