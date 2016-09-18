CREATE DEFINER=`root`@`localhost` PROCEDURE `get_employee_worklog_list`(
	IN employee_id 			TINYINT,
	IN date_filter 			TINYTEXT,
	IN list_daily_work_hour TINYINT
)
BEGIN

    SET @dinQuery = CONCAT('SELECT wg.id, wg.begin_date, wg.work_hour, wg.date_of_registration, wg.date_of_modification, wg.note ',
					   'FROM worklog wg INNER JOIN worker w ON w.id = wg.worker_id ',
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

	IF(list_daily_work_hour = 1) THEN

		SET @dinQuery = CONCAT(@dinQuery,
							IF(date_filter = 'ALL', ' WHERE ', ' AND '),
							' wg.work_hour = w.daily_work_hour_total ');

	END IF;

    PREPARE stmt FROM @dinQuery;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END