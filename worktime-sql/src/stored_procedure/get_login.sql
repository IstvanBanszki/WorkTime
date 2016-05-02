
CREATE DEFINER=`root`@`localhost` 
PROCEDURE `get_login`(IN login_name TINYTEXT, IN password VARCHAR(32), OUT status INT(1))
BEGIN
	SELECT status = IF(EXISTS(
						SELECT *
						FROM user
						WHERE name     = login_name
						  AND password = password  ), 1, 0);
END