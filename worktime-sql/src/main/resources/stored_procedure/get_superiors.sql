CREATE DEFINER=`root`@`localhost` PROCEDURE `get_superiors`(
)
BEGIN

	SELECT w.first_name, w.last_name, w.id
	  FROM Worker w
INNER JOIN User u ON u.id = w.user_id
INNER JOIN Role r ON r.id = u.role_id
	 WHERE r.id = 3;

END