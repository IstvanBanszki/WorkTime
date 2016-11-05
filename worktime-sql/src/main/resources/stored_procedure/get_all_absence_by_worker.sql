CREATE DEFINER=`root`@`localhost` PROCEDURE `get_all_absence_by_worker`(
	IN worker_id	BIGINT,
    IN date_filter	TINYTEXT
)
BEGIN
    SET @dinQuery = CONCAT('SELECT a.id, a.status, a.absence_type_id, a.begin_date, a.end_date ',
						   'FROM absence a ',
						   'WHERE a.worker_id = ', worker_id );

    IF(date_filter = 'This Year') THEN

		SET @dinQuery = CONCAT(@dinQuery, ' AND (`begin_date` BETWEEN DATE_FORMAT(NOW() ,\'%Y\') AND NOW()) ');

    ELSEIF(date_filter = 'This Month') THEN

		SET @dinQuery = CONCAT(@dinQuery, ' AND (`begin_date` BETWEEN DATE_FORMAT(NOW() ,\'%Y-%m-01\') AND NOW()) ');

    ELSEIF(date_filter = 'Last Week') THEN

		SET @dinQuery = CONCAT(@dinQuery, 
		' AND (`begin_date` BETWEEN DATE_FORMAT(DATE_ADD(NOW(), INTERVAL(-WEEKDAY(NOW())-7) DAY),\'%Y-%m-%d\') ',
								'AND DATE_FORMAT(DATE_ADD(NOW(), INTERVAL(-WEEKDAY(NOW())) DAY),\'%Y-%m-%d\') ) ' );

    ELSEIF(date_filter = 'This Week') THEN

		SET @dinQuery = CONCAT(@dinQuery, ' AND (`begin_date` BETWEEN DATE_FORMAT(DATE_ADD(NOW(), INTERVAL(-WEEKDAY(NOW())) DAY),\'%Y-%m-%d\') AND NOW()) ');

    END IF;

    PREPARE stmt FROM @dinQuery;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END