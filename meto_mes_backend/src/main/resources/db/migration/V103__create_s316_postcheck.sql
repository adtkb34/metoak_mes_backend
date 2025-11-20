CREATE TABLE IF NOT EXISTS `mo_stereo_postcheck` (
  `id` int NOT NULL AUTO_INCREMENT,
  `datetime` datetime,
  `sn` varchar(64),
  `error_code` int DEFAULT '0',
  `operator` varchar(64) DEFAULT NULL,
  `station` int DEFAULT NULL,
  `is_version_ok` tinyint(1) DEFAULT NULL,
  `is_image_ok` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci 
