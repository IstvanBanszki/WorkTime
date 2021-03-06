USE worktime;

/* ***************************************************
					role table creation
   *************************************************** */
   
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` tinytext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* ***************************************************
					user table creation
   *************************************************** */
   
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` tinytext NOT NULL,
  `date_of_registration` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `password` varchar(128) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_u_role_id_idx` (`role_id`),
  CONSTRAINT `FK_u_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* ***************************************************
					office table creation
   *************************************************** */
   
DROP TABLE IF EXISTS `office`;
CREATE TABLE `office` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` tinytext NOT NULL,
  `address` tinytext NOT NULL,
  `date_of_registration` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_of_foundation` date NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* ***************************************************
				department table creation
   *************************************************** */
   
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` tinytext NOT NULL,
  `date_of_registration` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_of_foundation` date NOT NULL DEFAULT '0000-00-00 00:00:00',
  `office_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_d_office_id_idx` (`office_id`),
  CONSTRAINT `FK_d_office_id` FOREIGN KEY (`office_id`) REFERENCES `office` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* ***************************************************
				absence_type table creation
   *************************************************** */
   
DROP TABLE IF EXISTS `absence_type`;
CREATE TABLE `absence_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` tinytext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* ***************************************************
				worker table creation
   *************************************************** */

DROP TABLE IF EXISTS `worker`;
CREATE TABLE `worker` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` tinytext NOT NULL,
  `last_name` tinytext NOT NULL,
  `gender` int(1) NOT NULL,
  `date_of_birth` date NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `nationality` tinytext NOT NULL,
  `position` tinytext NOT NULL,
  `email_address` tinytext NOT NULL,
  `daily_work_hour_total` tinyint(4) NOT NULL,
  `superior_work_id` bigint(20) NOT NULL,
  `department_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_w_user_id_idx` (`user_id`),
  KEY `FK_w_department_id_idx` (`department_id`),
  CONSTRAINT `FK_w_department_id` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_w_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* ***************************************************
			worker_holiday_number table creation
   *************************************************** */
CREATE TABLE `worker_holiday_number` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `year` int(4) NOT NULL,
  `holiday_number_total` int(2) NOT NULL,
  `not_set_absence_number` int(2) NOT NULL,
  `payed_absence_number` int(2) NOT NULL,
  `unpayed_absence_number` int(2) NOT NULL,
  `sickpayed_absence_number` int(2) NOT NULL,
  `verified_absence_number` int(2) NOT NULL,
  `date_of_registration` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_of_modification` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `worker_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_whn_worker_id_idx` (`worker_id`),
  CONSTRAINT `FK_whn_worker_id` FOREIGN KEY (`worker_id`) REFERENCES `worker` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* ***************************************************
				worklog table creation
   *************************************************** */
   
CREATE TABLE `worklog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `begin_date` date NOT NULL,
  `work_hour` tinyint(4) NOT NULL,
  `work_min` tinyint(4) NOT NULL DEFAULT '0',
  `date_of_registration` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_of_modification` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `note` tinytext NOT NULL,
  `worker_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_wl_worker_idx` (`worker_id`),
  CONSTRAINT `FK_wl_worker_id` FOREIGN KEY (`worker_id`) REFERENCES `worker` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* ***************************************************
				absence table creation
   *************************************************** */
   
CREATE TABLE `absence` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `begin_date` date NOT NULL,
  `end_date` date NOT NULL,
  `date_of_registration` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_of_modification` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL,
  `note` tinytext NOT NULL,
  `absence_type_id` bigint(20) NOT NULL,
  `worker_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_a_worker_id_idx` (`worker_id`),
  KEY `FK_a_absence_type_id_idx` (`absence_type_id`),
  CONSTRAINT `FK_a_absence_type_id` FOREIGN KEY (`absence_type_id`) REFERENCES `absence_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_a_worker_id` FOREIGN KEY (`worker_id`) REFERENCES `worker` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* ***************************************************
				entry table creation
   *************************************************** */

CREATE TABLE `entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `int_out` tinyint(4) NOT NULL,
  `log_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `worker_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_e_worker_id_idx` (`worker_id`),
  CONSTRAINT `FK_e_worker_id` FOREIGN KEY (`worker_id`) REFERENCES `worker` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
