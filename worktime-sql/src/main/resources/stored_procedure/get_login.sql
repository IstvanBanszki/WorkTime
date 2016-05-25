CREATE DEFINER=`root`@`localhost` PROCEDURE `get_login`(
	IN  login_name TINYTEXT
)
BEGIN
	SELECT w.id as worker_id, r.role as role_name, u.password as password
	FROM worker w, user u, role r 
	WHERE r.id = u.role_id
	  AND u.id = w.user_id
	  AND u.login_name = login_name;
END
