CREATE DEFINER=`root`@`localhost` PROCEDURE `get_worker_holidays`(
	IN  worker_id BIGINT
)
BEGIN
	
    DECLARE year INT;
	DECLARE holiday_number_total INT;
	DECLARE holiday_number_used INT;
    
	SELECT year = whn.year, holiday_number_total = whn.holiday_number_total
	FROM worker_holiday_number whn
	INNER JOIN worker w ON whn.worker_id = w.id
	WHERE w.id = worker_id;
    
    SELECT holiday_number_used = COUNT(*)
	FROM absence a
	INNER JOIN worker w ON a.worker_id = w.id
	WHERE a.worker_id = 1
      AND a.absence_type_id = 2;
      
    SELECT year, holiday_number_total, holiday_number_used   
END;