CREATE DEFINER=`root`@`localhost` PROCEDURE `save_worklog_by_entry`(
	IN worker_id BIGINT
)
BEGIN

	DECLARE begin_time 		TIMESTAMP;
	DECLARE end_time 		TIMESTAMP;
	DECLARE sub_result_hour	TINYINT;
	DECLARE sub_result_min	TINYINT;
	DECLARE timestamp_num	INT;
	DECLARE worklog_id		INT;
	DECLARE worklog_min		TINYINT;

	SELECT MAX(`log_timestamp`)
	  INTO begin_time
	  FROM `entry`
	 WHERE `worker_id` = worker_id
	   AND `in_out` = 1;

	SELECT MAX(`log_timestamp`)
	  INTO end_time
	  FROM `entry`
	 WHERE `worker_id` = worker_id
	   AND `in_out` = 0;

	SET sub_result_hour = TIMESTAMPDIFF(HOUR, begin_time, end_time);
	SET sub_result_min = TIMESTAMPDIFF(MINUTE, begin_time, end_time) - (sub_result_hour * 60);

	SELECT COUNT(`log_timestamp`)
      INTO timestamp_num
	  FROM `entry`
	 WHERE `worker_id` = worker_id
       AND DATE(`log_timestamp`) LIKE DATE_FORMAT(begin_time, '%Y-%m-%d');

	IF (timestamp_num > 1) THEN
		BEGIN
			SELECT `id`, `work_min`
			  INTO worklog_id, worklog_min
              FROM `worklog`
             WHERE DATE(`begin_date`) = DATE_FORMAT(begin_time, '%Y-%m-%d');

			SET sub_result_min = worklog_min + sub_result_min;
			IF (sub_result_min >= 60) THEN
				SET sub_result_min = sub_result_min - 60;
				SET sub_result_hour = sub_result_hour + 1;
			END IF;

			UPDATE `worktime`.`worklog`
			SET `work_hour` = `work_hour` + sub_result_hour,
				`date_of_modification` = NOW()
			WHERE `id` = worklog_id;

			SELECT ROW_COUNT() AS status, 0 AS new_id;
		END;
    ELSE
		BEGIN
			INSERT INTO `worktime`.`worklog` (`begin_date`, `work_hour`, `work_min`, `worker_id`, `note`)
			VALUES (DATE_FORMAT(begin_time, '%Y-%m-%d'), sub_result_hour, sub_result_min, worker_id, '');

			SELECT ROW_COUNT() AS status, LAST_INSERT_ID() AS new_id;
		END;
	END IF;
END