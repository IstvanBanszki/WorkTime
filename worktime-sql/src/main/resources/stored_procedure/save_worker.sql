CREATE DEFINER=`root`@`localhost` PROCEDURE `save_worker`(
	IN first_name 				TINYTEXT,
	IN last_name 				TINYTEXT,
	IN gender					INT(1),
	IN date_of_birth 			DATE,
	IN nationality 				TINYTEXT,
	IN position 				TINYTEXT,
	IN email_address 			TINYTEXT,
	IN daily_work_hour_total 	INT(4),
	IN superior_worker_id		BIGINT,
	IN department_id			BIGINT,
	IN user_id 					BIGINT
)
BEGIN

	INSERT INTO `worktime`.`worker` (`first_name`, `last_name`, `gender`, `date_of_birth`, `nationality`, `position`, `email_address`, `daily_work_hour_total`, `superior_worker_id`, `department_id`, `user_id`)
	VALUES (first_name, last_name, gender, date_of_birth, nationality, position, email_address, daily_work_hour_total, superior_worker_id, department_id, user_id);

	SELECT ROW_COUNT() AS status, LAST_INSERT_ID() new_id; 

END