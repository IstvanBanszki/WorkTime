CREATE DEFINER=`root`@`localhost` PROCEDURE `get_all_absence_by_worker`(
	IN worker_id BIGINT
)
BEGIN
	SELECT w.description, w.status, w.absence_type_id, w.begin, w.end
	FROM worklog w
	WHERE w.worker_id = worker_id
	  AND w.absence_type_id != 1;
END