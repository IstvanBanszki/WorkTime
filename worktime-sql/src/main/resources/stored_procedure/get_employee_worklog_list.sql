CREATE DEFINER=`root`@`localhost` PROCEDURE `get_employee_worklog_list`(
	IN first_name  TINYTEXT,
	IN last_name   TINYTEXT,
	IN date_filter TINYTEXT,
	IN listDailyWorkHour TINYINT
)
BEGIN

    SET @dinQuery = CONCAT('SELECT wg.id, wg.begin_date, wg.work_hour, wg.note ',
					   'FROM worklog wg INNER JOIN worker w ON w.id = wg.worker_id ',
					   'AND w.first_name = \'', first_name ,
					   '\' AND w.last_name = \'', last_name, '\' ' );

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

	IF(listDailyWorkHour = 1) THEN

		SET @dinQuery = CONCAT(@dinQuery,
							IF(date_filter = 'ALL', ' WHERE ', ' AND '),
							' wg.work_hour = w.daily_work_hour_total ');

	END IF;

    PREPARE stmt FROM @dinQuery;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END