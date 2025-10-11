ALTER TABLE transfer_box
    modify column is_finished tinyint(1) default 0 comment '批次号';