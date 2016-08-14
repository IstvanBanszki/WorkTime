CREATE DEFINER=`root`@`localhost` PROCEDURE `get_absence_data`(
	IN worker_id BIGINT
)
BEGIN

	SELECT whn.year, whn.holiday_number_total, (SELECT COUNT(*) FROM absence a WHERE a.worker_id = worker_id) AS absence_number
	FROM worker_holiday_number whn
	WHERE whn.worker_id = worker_id;

END