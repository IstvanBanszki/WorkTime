CREATE DEFINER=`root`@`localhost` PROCEDURE `save_user`(
	IN login_name 	TINYTEXT,
	IN password 	VARCHAR(128),
	IN role_id 		BIGINT
)
BEGIN

	INSERT INTO `worktime`.`user` (`login_name`, `password`, `role_id`)
	VALUES (login_name, password, role_id);

	SELECT ROW_COUNT() AS status, LAST_INSERT_ID() new_id;

END