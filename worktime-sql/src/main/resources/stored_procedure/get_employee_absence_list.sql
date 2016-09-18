CREATE DEFINER=`root`@`localhost` PROCEDURE `get_employee_absence_list`(
	IN employee_id 		 TINYINT,
	IN date_filter 		 TINYTEXT,
	IN show_not_approved TINYINT
)
BEGIN

    SET @dinQuery = CONCAT('SELECT a.id, a.begin_date, a.end_date, a.absence_type_id, a.status, a.note ',
					   'FROM absence a ',
					   'INNER JOIN worker w ON w.id = a.worker_id ',
					   'AND w.id = ', employee_id, ' ');

    IF(date_filter = 'This Year') THEN

		SET @dinQuery = CONCAT(@dinQuery, 'WHERE (`begin_date` BETWEEN  DATE_FORMAT(NOW() ,\'%Y\') AND NOW()) ');

    ELSEIF(date_filter = 'This Month') THEN

		SET @dinQuery = CONCAT(@dinQuery, 'WHERE (`begin_date` BETWEEN  DATE_FORMAT(NOW() ,\'%Y-%m-01\') AND NOW()) ');

    ELSEIF(date_filter = 'Last Week') THEN

		SET @dinQuery = CONCAT(@dinQuery, 
		'WHERE (`begin_date` BETWEEN DATE_FORMAT(DATE_ADD(NOW(), INTERVAL(-WEEKDAY(NOW())-7) DAY),\'%Y-%m-%d\') ',
								'AND DATE_FORMAT(DATE_ADD(NOW(), INTERVAL(-WEEKDAY(NOW())) DAY),\'%Y-%m-%d\') )' );

    ELSEIF(date_filter = 'This Week') THEN
		
		SET @dinQuery = CONCAT(@dinQuery, 'WHERE (`begin_date` BETWEEN  DATE_FORMAT(DATE_ADD(NOW(), INTERVAL(-WEEKDAY(NOW())) DAY),\'%Y-%m-%d\') AND NOW()) ');

    END IF;

	IF(show_not_approved = 1) THEN

		SET @dinQuery = CONCAT(@dinQuery,
							IF(date_filter = 'ALL', ' WHERE ', ' AND '),
							' a.status = 1 ');

	END IF;

    PREPARE stmt FROM @dinQuery;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END