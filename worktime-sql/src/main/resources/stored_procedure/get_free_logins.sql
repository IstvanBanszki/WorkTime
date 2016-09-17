CREATE DEFINER=`root`@`localhost` PROCEDURE `get_free_logins`(
)
BEGIN

	SELECT u.id, u.login_name FROM User u WHERE u.id NOT IN (SELECT w.user_id FROM Worker w);

END