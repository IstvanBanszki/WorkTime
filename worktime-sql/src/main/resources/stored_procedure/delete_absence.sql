CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_absence`(
	IN absence_id BIGINT
)
BEGIN
	DECLARE absence_type_id BIGINT;

	SELECT `absence_type_id` INTO absence_type_id
	  FROM `worktime`.`absence` 
	 WHERE `id` = absence_id;

	DELETE FROM `worktime`.`absence`
	 WHERE `id` = absence_id;

	SELECT ROW_COUNT() AS status;

	CASE absence_type_id
	WHEN 1 THEN 
		UPDATE `worktime`.`worker_holiday_number` 
		SET `not_set_absence_number` = `not_set_absence_number` - 1 
		WHERE worker_id = worker_id;
	WHEN 2 THEN 
		UPDATE `worktime`.`worker_holiday_number`
		SET `payed_absence_number` = `payed_absence_number` - 1
		WHERE worker_id = worker_id;
	WHEN 3 THEN 
		UPDATE `worktime`.`worker_holiday_number`
		SET `unpayed_absence_number` = `unpayed_absence_number` - 1
		WHERE worker_id = worker_id;
	WHEN 4 THEN 
		UPDATE `worktime`.`worker_holiday_number`
		SET `sickpayed_absence_number` = `sickpayed_absence_number` - 1
		WHERE worker_id = worker_id;
	WHEN 5 THEN 
		UPDATE `worktime`.`worker_holiday_number`
		SET `verified_absence_number` = `verified_absence_number` - 1
		WHERE worker_id = worker_id;
	ELSE BEGIN END;
	END CASE;
END