CREATE DEFINER=`root`@`localhost` PROCEDURE `get_all_deparments`()
BEGIN

	SELECT d.id, d.name, d.date_of_foundation, d.office_id, (SELECT COUNT(*) FROM worker w WHERE w.department_id = d.id) AS worker_number
	  FROM department d;

END