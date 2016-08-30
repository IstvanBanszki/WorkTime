CREATE DEFINER=`root`@`localhost` PROCEDURE `get_all_worklog_by_worker`(
	IN worker_id BIGINT
)
BEGIN
	SELECT w.id, w.begin_date, w.work_hour
	FROM worklog w
	WHERE w.worker_id = worker_id;
END