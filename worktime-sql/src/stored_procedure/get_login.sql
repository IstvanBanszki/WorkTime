DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_login`(
	IN  login_name TINYTEXT,
    IN  password VARCHAR(32)
)
BEGIN
	SELECT w.id as worker_id, r.role as role_name 
	FROM worker w, user u, role r 
	WHERE r.id = u.role_id
	  AND u.id = w.user_id
	  AND u.login_name = login_name
	  AND u.password = password;
	/*
	SELECT status = IF(EXISTS(
						SELECT *
						FROM user
						WHERE name     = login_name
						  AND password = password  ), 1, 0);
						  */
END$$
DELIMITER ;
