CREATE DEFINER=`root`@`localhost` PROCEDURE `get_all_deparments`()
BEGIN

	SELECT name, date_of_foundation, office_id
	  FROM department;

END