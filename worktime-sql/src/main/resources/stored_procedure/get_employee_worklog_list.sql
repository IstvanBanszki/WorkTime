CREATE DEFINER=`root`@`localhost` PROCEDURE `get_employee_worklog_list`(
	IN first_name TINYTEXT,
	IN last_name  TINYTEXT
)
BEGIN
	SELECT wg.begin, wg.work_hour
      FROM worklog wg
INNER JOIN worker w ON w.id = wg.worker_id
				   AND w.first_name = first_name
				   AND w.last_name = last_name;
END