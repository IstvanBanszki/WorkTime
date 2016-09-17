CREATE DEFINER=`root`@`localhost` PROCEDURE `save_worker`(
	first_name 				TINYTEXT,
	last_name 				TINYTEXT,
	gender					INT(1),
	date_of_birth 			DATE,
	nationality 			TINYTEXT,
	position 				TINYTEXT,
	email_address 			TINYTEXT,
	daily_work_hour_total 	INT(4),
	superior_worker_id		BIGINT,
	department_id			BIGINT,
	user_id 				BIGINT
)
BEGIN

	INSERT INTO `worktime`.`worker` (`first_name`, `last_name`, `gender`, `date_of_birth`, `nationality`, `position`, `email_address`, `daily_work_hour_total`, `superior_worker_id`, `department_id`, `user_id`)
	VALUES (first_name, last_name, date_of_birth, nationality, position, email_address, daily_work_hour_total, superior_worker_id, department_id, user_id);

	SELECT ROW_COUNT() AS status, LAST_INSERT_ID() new_id; 

END