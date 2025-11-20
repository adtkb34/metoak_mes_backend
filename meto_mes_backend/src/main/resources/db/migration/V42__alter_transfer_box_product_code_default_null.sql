ALTER TABLE transfer_box
    modify column batch_number varchar(50) default NULL comment '批次号',
    modify column product_code varchar(50) default NULL comment '产品料号',
    modify column work_order varchar(50) default NULL comment '工单号',
    modify column box_number varchar(50) default NULL comment '箱号';