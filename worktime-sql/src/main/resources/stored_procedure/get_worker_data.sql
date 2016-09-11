CREATE DEFINER=`root`@`localhost` PROCEDURE `get_worker_data`(
	IN worker_id BIGINT
)
BEGIN

	SELECT w.first_name,
		   w.last_name,
		   w.position,
		   w.email_address,
		   w.daily_work_hour_total
	  FROM worker w
	 WHERE w.id = worker_id;

END