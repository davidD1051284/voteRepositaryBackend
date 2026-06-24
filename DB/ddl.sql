CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `vote` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `vote_name` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `vote_option` (
  `option_id` bigint NOT NULL AUTO_INCREMENT,
  `option_name` varchar(255) NOT NULL,
  `total_count` int NOT NULL DEFAULT '0',
  `vote_id` bigint NOT NULL,
  PRIMARY KEY (`option_id`),
  KEY `fk_vote_option_vote` (`vote_id`),
  CONSTRAINT `fk_vote_option_vote` FOREIGN KEY (`vote_id`) REFERENCES `vote` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `vote_record` (
  `record_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `vote_id` bigint NOT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`record_id`),
  UNIQUE KEY `uk_user_vote` (`user_id`,`vote_id`),
  KEY `fk_vote_record_vote` (`vote_id`),
  CONSTRAINT `fk_vote_record_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_vote_record_vote` FOREIGN KEY (`vote_id`) REFERENCES `vote` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `vote_record_option` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `vote_record_id` bigint NOT NULL,
  `option_id` bigint NOT NULL,
  `vote_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_vote_record_id` (`vote_record_id`),
  KEY `idx_option_id` (`option_id`),
  KEY `IDXf38u3qexonlxkf1vj7i68vcfh` (`vote_record_id`),
  KEY `IDXj44352b1s0jg5er13p00k0rwv` (`option_id`),
  CONSTRAINT `fk_vro_option` FOREIGN KEY (`option_id`) REFERENCES `vote_option` (`option_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_vro_record` FOREIGN KEY (`vote_record_id`) REFERENCES `vote_record` (`record_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci