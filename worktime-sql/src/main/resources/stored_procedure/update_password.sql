CREATE DEFINER=`root`@`localhost` PROCEDURE `update_password`(
	IN login_name TINYTEXT,
    IN old_password VARCHAR(128),
    IN new_password VARCHAR(128)
)
BEGIN

	IF ((SELECT COUNT(*) FROM user u WHERE u.password = old_password AND u.login_name = login_name) = 1) THEN

		UPDATE user u
		   SET u.password = new_password 
		 WHERE u.login_name = login_name;

		SELECT ROW_COUNT() AS status;
	ELSE
	    SELECT -1 AS status;
	END IF;
END