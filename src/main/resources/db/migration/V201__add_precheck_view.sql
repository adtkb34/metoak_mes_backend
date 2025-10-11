CREATE OR REPLACE VIEW view_stereo_precheck_perfc AS
SELECT *
FROM mo_stereo_precheck
WHERE is_clarity_detect_enabled = true
  AND is_lo_detect_enabled = true
  AND is_dirty_detect_enabled = true
  AND is_color_cast_detect_enabled = true
  AND is_cod_detect_enabled = true;

CREATE OR REPLACE VIEW view_stereo_precheck_oqc AS
SELECT *
FROM mo_stereo_precheck
WHERE is_clarity_detect_enabled = true
  AND is_lo_detect_enabled = true
  AND is_dirty_detect_enabled = false
  AND is_color_cast_detect_enabled = false
  AND is_cod_detect_enabled = false;

grant select on table mo_stereo_precheck to user03;
grant select on table view_stereo_precheck_oqc to user03;
grant select on table view_stereo_precheck_perfc to user03;

