-- 删除 rear_cover 相关字段
ALTER TABLE mo_lens_dispensing
DROP COLUMN rear_cover_dispense_time,
DROP COLUMN rear_cover_cure_time;

-- 重命名 front_cover 字段为 cover
ALTER TABLE mo_lens_dispensing
CHANGE COLUMN front_cover_dispense_time cover_dispense_time FLOAT COMMENT '盖板点胶时间',
CHANGE COLUMN front_cover_cure_time cover_cure_time FLOAT COMMENT '盖板固化时间';

-- 添加 position 字段
ALTER TABLE mo_lens_dispensing
ADD COLUMN position INT NULL COMMENT '位置信息';