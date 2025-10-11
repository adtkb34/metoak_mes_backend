ALTER TABLE mo_auto_adjust_st08
ADD COLUMN mtf_center_value_diff DOUBLE COMMENT '中心MTF两目差值，实际测试值',
ADD COLUMN mtf_center_diff_usl DOUBLE COMMENT '中心MTF两目差值，规格上限',
ADD COLUMN mtf_leftup_value_diff DOUBLE COMMENT '左上MTF两目差值',
ADD COLUMN mtf_leftup_diff_usl DOUBLE COMMENT '左上MTF两目差值，规格上限',
ADD COLUMN mtf_rightup_value_diff DOUBLE COMMENT '右上MTF两目差值',
ADD COLUMN mtf_rightup_diff_usl DOUBLE COMMENT '右上MTF两目差值，规格上限',
ADD COLUMN mtf_leftdown_value_diff DOUBLE COMMENT '左下MTF两目差值',
ADD COLUMN mtf_leftdown_diff_usl DOUBLE COMMENT '左下MTF两目差值，规格上限',
ADD COLUMN mtf_rightdown_value_diff DOUBLE COMMENT '右下MTF两目差值',
ADD COLUMN mtf_rightdown_diff_usl DOUBLE COMMENT '右下MTF两目差值，规格上限';