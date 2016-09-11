CREATE DEFINER=`root`@`localhost` PROCEDURE `edit_worker_data`(
	IN first_name TINYTEXT,
    IN last_name TINYTEXT,
    IN position TINYTEXT,
    IN email_address TINYTEXT,
    IN daily_work_hour_total TINYINT,
    IN worker_id BIGINT
)
BEGIN

	UPDATE worker w
       SET w.first_name = first_name,
		   w.last_name = last_name,
		   w.position = position,
		   w.email_address = email_address,
		   w.daily_work_hour_total = daily_work_hour_total
	 WHERE w.id = worker_id;

	SELECT ROW_COUNT() AS status;

END