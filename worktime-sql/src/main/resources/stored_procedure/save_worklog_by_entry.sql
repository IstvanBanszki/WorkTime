CREATE DEFINER=`root`@`localhost` PROCEDURE `save_worklog_by_entry`(
	IN worker_id BIGINT
)
BEGIN

	DECLARE begin_time 		TIMESTAMP;
	DECLARE end_time 		TIMESTAMP;
	DECLARE date_of_work 	DATE;
	DECLARE sub_result_hour	INT;
	DECLARE timestamp_num	INT;
	DECLARE worklog_id		INT;

	SELECT Max(`log_timestamp`)
	  INTO begin_time
	  FROM `entry`
	 WHERE `worker_id` = worker_id
	   AND `in_out` = 1;

	SELECT Max(`log_timestamp`)
	  INTO end_time
	  FROM `entry`
	 WHERE `worker_id` = worker_id
	   AND `in_out` = 0;

	SELECT TIMESTAMPDIFF(HOUR, begin_time, end_time)
	  INTO sub_result_hour;

	SELECT COUNT(`log_timestamp`)
      INTO timestamp_num
	  FROM `entry`
	 WHERE `worker_id` = worker_id
       AND DATE(`log_timestamp`) LIKE DATE_FORMAT(begin_time, '%Y-%m-%d');

	IF (timestamp_num > 1) THEN
		BEGIN
			SELECT `id`
			  INTO worklog_id
              FROM `worklog`
             WHERE DATE(`begin_date`) = DATE_FORMAT(begin_time, '%Y-%m-%d');
        
			UPDATE `worktime`.`worklog`
			SET `work_hour` = `work_hour` + sub_result_hour,
				`date_of_modification` = NOW()
			WHERE `id` = worklog_id;

			SELECT ROW_COUNT() AS status, 0 AS new_id;
		END;
    ELSE
		BEGIN
			INSERT INTO `worktime`.`worklog` (`begin_date`, `work_hour`, `worker_id`, `note`)
			VALUES (DATE_FORMAT(begin_time, '%Y-%m-%d'), sub_result_hour, worker_id, '');

			SELECT ROW_COUNT() AS status, LAST_INSERT_ID() AS new_id;
		END;
	END IF;
END