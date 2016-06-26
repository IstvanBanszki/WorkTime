CREATE DEFINER=`root`@`localhost` PROCEDURE `get_all_absence_by_worker`(
	IN worker_id BIGINT
)
BEGIN
	SELECT a.status, a.absence_type_id, a.begin, a.end
	FROM absence a
	WHERE a.worker_id = worker_id;
END