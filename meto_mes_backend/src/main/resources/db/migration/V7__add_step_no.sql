
CREATE TABLE IF NOT EXISTS `mo_workstage` (
  `id` int NOT NULL AUTO_INCREMENT,
  `stage_code` varchar(32) NOT NULL,
  `stage_name` varchar(64) NOT NULL,
  `stage_desc` varchar(256) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `show_report` int DEFAULT '0',
  `show_ptype` tinyint(1) DEFAULT '0',
  `manual_result` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `AK_uq_stage_code` (`stage_code`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='工序表';


CREATE TABLE IF NOT EXISTS `mo_process_flow` (
  `id` int NOT NULL AUTO_INCREMENT,
  `process_code` varchar(32) DEFAULT NULL COMMENT '流程编号',
  `process_name` varchar(64) DEFAULT NULL COMMENT '流程名称',
  `stage_code` varchar(32) NOT NULL COMMENT '工序代号',
  `process_desc` varchar(256) DEFAULT NULL COMMENT '流程说明',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `is_required` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_31` (`stage_code`) USING BTREE,
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`stage_code`) REFERENCES `mo_workstage` (`stage_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=362 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='生产流程/工艺流程';