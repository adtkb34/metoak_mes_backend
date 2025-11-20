ALTER TABLE mo_auto_adjust_st08 ADD COLUMN releaseClawMinusUvCuredMtfCenterDiff FLOAT COMMENT '释放爪与UV固化MTF中心差';
ALTER TABLE mo_auto_adjust_st08 ADD COLUMN releaseClawMinusUvCuredMtfTlDiff FLOAT COMMENT '释放爪与UV固化MTF左上差';
ALTER TABLE mo_auto_adjust_st08 ADD COLUMN releaseClawMinusUvCuredMtfTrDiff FLOAT COMMENT '释放爪与UV固化MTF右上差';
ALTER TABLE mo_auto_adjust_st08 ADD COLUMN releaseClawMinusUvCuredMtfBlDiff FLOAT COMMENT '释放爪与UV固化MTF左下差';
ALTER TABLE mo_auto_adjust_st08 ADD COLUMN releaseClawMinusUvCuredMtfBrDiff FLOAT COMMENT '释放爪与UV固化MTF右下差';