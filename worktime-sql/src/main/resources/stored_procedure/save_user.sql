CREATE DEFINER=`root`@`localhost` PROCEDURE `save_user`(
	login_name 	TINYTEXT,
	password 	VARCHAR(128),
	role_id 	BIGINT
)
BEGIN

	INSERT INTO `worktime`.`worklog` (`login_name`, `password`, `role_id`)
	VALUES (login_name, password, role_id);

	SELECT ROW_COUNT() AS status, LAST_INSERT_ID() new_id;

END