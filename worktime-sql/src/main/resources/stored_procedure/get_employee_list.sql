CREATE DEFINER=`root`@`localhost` PROCEDURE `get_employee_list`(
	IN worker_id BIGINT
)
BEGIN
	SELECT w.id, w.first_name, w.last_name
	  FROM worker w
	 WHERE w.superior_worker_id = worker_id;
END