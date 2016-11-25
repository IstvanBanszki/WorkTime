CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_absence`(
	IN absence_id BIGINT
)
BEGIN
	DECLARE absence_type_id BIGINT;
	DECLARE day_number_to_take TINYINT;

	SELECT `absence_type_id`, 
			TO_DAYS(`end_date`) - TO_DAYS(`begin_date`) + 1 
	  INTO absence_type_id, 
		   day_number_to_take
	  FROM `worktime`.`absence` 
	 WHERE `id` = absence_id;

	DELETE FROM `worktime`.`absence`
	 WHERE `id` = absence_id;

	SELECT ROW_COUNT() AS status;

	CASE absence_type_id
	WHEN 1 THEN 
		UPDATE `worktime`.`worker_holiday_number` 
		SET `not_set_absence_number` = `not_set_absence_number` - day_number_to_take,
			`date_of_modification` = NOW()
		WHERE worker_id = worker_id;
	WHEN 2 THEN 
		UPDATE `worktime`.`worker_holiday_number`
		SET `payed_absence_number` = `payed_absence_number` - day_number_to_take,
			`date_of_modification` = NOW()
		WHERE worker_id = worker_id;
	WHEN 3 THEN 
		UPDATE `worktime`.`worker_holiday_number`
		SET `unpayed_absence_number` = `unpayed_absence_number` - day_number_to_take,
			`date_of_modification` = NOW()
		WHERE worker_id = worker_id;
	WHEN 4 THEN 
		UPDATE `worktime`.`worker_holiday_number`
		SET `sickpayed_absence_number` = `sickpayed_absence_number` - day_number_to_take,
			`date_of_modification` = NOW()
		WHERE worker_id = worker_id;
	WHEN 5 THEN 
		UPDATE `worktime`.`worker_holiday_number`
		SET `verified_absence_number` = `verified_absence_number` - day_number_to_take,
			`date_of_modification` = NOW()
		WHERE worker_id = worker_id;
	ELSE BEGIN END;
	END CASE;
END