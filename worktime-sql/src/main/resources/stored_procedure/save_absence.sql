CREATE DEFINER=`root`@`localhost` PROCEDURE `save_absence`(
	IN begin_date DATE,
	IN end_date DATE,
	IN worker_id BIGINT,
	IN absence_type_id BIGINT
)
BEGIN
	INSERT INTO `worktime`.`absence` (`begin_date`, `end_date`, `status`, `absence_type_id`, `worker_id`, `note`) 
	VALUES (begin_date, end_date, 1, absence_type_id, worker_id, '');

	SELECT ROW_COUNT() AS status, LAST_INSERT_ID() new_id;

	CASE absence_type_id
	WHEN 1 THEN 
		UPDATE `worktime`.`worker_holiday_number` 
		SET `not_set_absence_number` = `not_set_absence_number` + 1 
		WHERE worker_id = worker_id;
	WHEN 2 THEN 
		UPDATE `worktime`.`worker_holiday_number`
		SET `payed_absence_number` = `payed_absence_number` + 1
		WHERE worker_id = worker_id;
	WHEN 3 THEN 
		UPDATE `worktime`.`worker_holiday_number`
		SET `unpayed_absence_number` = `unpayed_absence_number` + 1
		WHERE worker_id = worker_id;
	WHEN 4 THEN 
		UPDATE `worktime`.`worker_holiday_number`
		SET `sickpayed_absence_number` = `sickpayed_absence_number` + 1
		WHERE worker_id = worker_id;
	WHEN 5 THEN 
		UPDATE `worktime`.`worker_holiday_number`
		SET `verified_absence_number` = `verified_absence_number` + 1
		WHERE worker_id = worker_id;
	END CASE;
END