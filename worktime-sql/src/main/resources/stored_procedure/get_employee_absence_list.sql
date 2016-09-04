CREATE DEFINER=`root`@`localhost` PROCEDURE `get_employee_absence_list`(
	IN first_name  TINYTEXT,
	IN last_name   TINYTEXT,
	IN date_filter TINYTEXT
)
BEGIN

    IF(date_filter = 'This Year') THEN

		SELECT a.id, a.begin_date, a.end_date, a.absence_type_id, a.status, a.note
		  FROM absence a
	INNER JOIN worker w ON w.id = a.worker_id
					   AND w.first_name = first_name
					   AND w.last_name = last_name
		 WHERE (`begin_date` BETWEEN  DATE_FORMAT(NOW() ,'%Y') AND NOW());

    ELSEIF(date_filter = 'This Month') THEN
		
		SELECT a.id, a.begin_date, a.end_date, a.absence_type_id, a.status, a.note
		  FROM absence a
	INNER JOIN worker w ON w.id = a.worker_id
					   AND w.first_name = first_name
					   AND w.last_name = last_name
		 WHERE (`begin_date` BETWEEN  DATE_FORMAT(NOW() ,'%Y-%m-01') AND NOW());

    ELSEIF(date_filter = 'Last Week') THEN

		SELECT a.id, a.begin_date, a.end_date, a.absence_type_id, a.status, a.note
		  FROM absence a
	INNER JOIN worker w ON w.id = a.worker_id
					   AND w.first_name = first_name
					   AND w.last_name = last_name
		 WHERE (`begin_date` BETWEEN  DATE_FORMAT(DATE_ADD(NOW(), INTERVAL(-WEEKDAY(NOW())-7) DAY),'%Y-%m-%d') 
								  AND DATE_FORMAT(DATE_ADD(NOW(), INTERVAL(-WEEKDAY(NOW())) DAY),'%Y-%m-%d') );

    ELSEIF(date_filter = 'This Week') THEN
		
		SELECT a.id, a.begin_date, a.end_date, a.absence_type_id, a.status, a.note
		  FROM absence a
	INNER JOIN worker w ON w.id = a.worker_id
					   AND w.first_name = first_name
					   AND w.last_name = last_name
		 WHERE (`begin_date` BETWEEN  DATE_FORMAT(DATE_ADD(NOW(), INTERVAL(-WEEKDAY(NOW())) DAY),'%Y-%m-%d') AND NOW());

	ELSE
		SELECT a.id, a.begin_date, a.end_date, a.absence_type_id, a.status, a.note
		  FROM absence a
	INNER JOIN worker w ON w.id = a.worker_id
					   AND w.first_name = first_name
					   AND w.last_name = last_name;
    END IF;

END