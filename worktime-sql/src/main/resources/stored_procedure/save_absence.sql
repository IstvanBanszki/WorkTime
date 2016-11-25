CREATE DEFINER=`root`@`localhost` PROCEDURE `save_absence`(
	IN begin_date DATE,
	IN end_date DATE,
	IN worker_id BIGINT,
	IN absence_type_id BIGINT
)
BEGIN
	DECLARE day_number_to_take TINYINT;

	SET day_number_to_take = TO_DAYS(end_date) - TO_DAYS(begin_date) + 1;

	IF ( (SELECT COUNT(*) FROM absence a WHERE a.worker_id = worker_id) >= ((SELECT whn.holiday_number_total FROM worker_holiday_number whn WHERE whn.worker_id = worker_id) + day_number_to_take) ) THEN

		SELECT 0 AS status, 0 AS new_id;

	ELSE

		INSERT INTO `worktime`.`absence` (`begin_date`, `end_date`, `status`, `absence_type_id`, `worker_id`, `note`) 
		VALUES (begin_date, end_date, 2, absence_type_id, worker_id, '');

		SELECT ROW_COUNT() AS status, LAST_INSERT_ID() AS new_id;

		CASE absence_type_id
		WHEN 1 THEN 
			UPDATE `worktime`.`worker_holiday_number` 
			SET `not_set_absence_number` = `not_set_absence_number` + day_number_to_take
			WHERE worker_id = worker_id;
		WHEN 2 THEN 
			UPDATE `worktime`.`worker_holiday_number`
			SET `payed_absence_number` = `payed_absence_number` + day_number_to_take
			WHERE worker_id = worker_id;
		WHEN 3 THEN 
			UPDATE `worktime`.`worker_holiday_number`
			SET `unpayed_absence_number` = `unpayed_absence_number` + day_number_to_take
			WHERE worker_id = worker_id;
		WHEN 4 THEN 
			UPDATE `worktime`.`worker_holiday_number`
			SET `sickpayed_absence_number` = `sickpayed_absence_number` + day_number_to_take
			WHERE worker_id = worker_id;
		WHEN 5 THEN 
			UPDATE `worktime`.`worker_holiday_number`
			SET `verified_absence_number` = `verified_absence_number` + day_number_to_take
			WHERE worker_id = worker_id;
		ELSE 
			BEGIN END;
		END CASE;
	END IF;
END