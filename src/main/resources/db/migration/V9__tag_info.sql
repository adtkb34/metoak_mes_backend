CREATE TABLE IF NOT EXISTS `mo_tag_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tag_sn` varchar(20) NOT NULL,
  `work_order_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `front_section` varchar(16) DEFAULT NULL,
  `serial_number` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `produce_order_id` int DEFAULT NULL,
  `operator` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_uq_product_sn` (`tag_sn`)
) ENGINE=InnoDB AUTO_INCREMENT=13274 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='标签序列号表';