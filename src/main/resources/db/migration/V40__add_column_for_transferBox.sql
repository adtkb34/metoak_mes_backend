CREATE TABLE IF NOT EXISTS `transfer_box` (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `batch_number` varchar(50) DEFAULT NULL COMMENT '批次号',
   `product_code` varchar(50) DEFAULT NULL COMMENT '产品料号',
   `work_order` varchar(50) DEFAULT NULL COMMENT '工单号',
   `box_number` varchar(50) DEFAULT NULL COMMENT '箱号',
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE transfer_box add column is_finished tinyint(1) comment '是否结束';