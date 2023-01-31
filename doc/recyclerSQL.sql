-- table for user
CREATE TABLE `user` (
  `id` varchar(100) COLLATE utf8_estonian_ci NOT NULL COMMENT 'unique',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `username` varchar(60) COLLATE utf8_estonian_ci DEFAULT NULL,
  `password` varchar(60) COLLATE utf8_estonian_ci DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  `email` varchar(45) COLLATE utf8_estonian_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_estonian_ci ROW_FORMAT=DYNAMIC;
-- table for role
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8_estonian_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_estonian_ci;
-- middle table for user and role
CREATE TABLE `user_role` (
  `user_id` varchar(100) COLLATE utf8_estonian_ci NOT NULL,
  `role_id` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_user` (`user_id`),
  KEY `fk_role` (`role_id`),
  CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_estonian_ci;

-- table for record
CREATE TABLE `record` (
  `record_id` varchar(100) COLLATE utf8_estonian_ci NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(11) NOT NULL DEFAULT '1',
  `user_id` varchar(100) COLLATE utf8_estonian_ci NOT NULL,
  `description` varchar(45) COLLATE utf8_estonian_ci DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `fk_user` (`user_id`),
  CONSTRAINT `record_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_estonian_ci;

-- table for record details
CREATE TABLE `record_details` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `record_id` varchar(100) COLLATE utf8_estonian_ci NOT NULL,
  `detail_name` varchar(30) COLLATE utf8_estonian_ci NOT NULL,
  `detail_qty` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`detail_id`),
  KEY `fk_record` (`record_id`),
  CONSTRAINT `fk_record` FOREIGN KEY (`record_id`) REFERENCES `record` (`record_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_estonian_ci;