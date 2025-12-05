set sql_safe_updates = 0;

delete from mo_step_attr_keys where step_type_no = '034';

INSERT INTO mo_step_attr_keys (step_type_no, attr_key, attr_no) VALUES
('034', 'startTime', '000'),
('034', 'endTime', '001'),
('034', 'errcode', '002'),
('034', 'isOqc', '003'),
('034', 'productType', '004'),
('034', 'isSigned', '005'),
('034', 'versionMcu', '006'),
('034', 'imuTx', '007'),
('034', 'imuTy', '008'),
('034', 'imuTz', '009'),
('034', 'imuQangleDeg', '010'),
('034', 'distance1Error', '011'),
('034', 'distance1Density', '012'),
('034', 'distance2Error', '013'),
('034', 'distance2Density', '014'),
('034', 'materialRoot', '015'),
('034', 'moProcessStepProductionResultId', '016');