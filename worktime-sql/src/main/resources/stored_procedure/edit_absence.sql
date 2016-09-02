CREATE DEFINER=`root`@`localhost` PROCEDURE `edit_absence`(
	IN absence_id BIGINT,
	IN begin_date TIMESTAMP,
	IN end_date TIMESTAMP,
	IN absence_type_id BIGINT
)
BEGIN
	DECLARE old_absence_type_id BIGINT;
	DECLARE worker_id 			BIGINT;
	
	SELECT `absence_type_id`,
		   `worker_id`
      INTO old_absence_type_id,
		   worker_id
	  FROM `absence`
	 WHERE `id` = absence_id;
    
	UPDATE `absence`
	SET `end_date` = end_date, 
	    `begin_date` = begin_date, 
        `absence_type_id` = absence_type_id,
        `date_of_modification` = NOW()
	WHERE `id` = absence_id;

	SELECT ROW_COUNT() AS status;

	CASE old_absence_type_id
	WHEN 1 THEN 
		UPDATE `worktime`.`worker_holiday_number` 
		SET `not_set_absence_number` = `not_set_absence_number` - 1 
		WHERE `worker_id` = worker_id;
	WHEN 2 THEN 
		UPDATE `worktime`.`worker_holiday_number`
		SET `payed_absence_number` = `payed_absence_number` - 1
		WHERE `worker_id` = worker_id;
	WHEN 3 THEN 
		UPDATE `worktime`.`worker_holiday_number`
		SET `unpayed_absence_number` = `unpayed_absence_number` - 1
		WHERE `worker_id` = worker_id;
	WHEN 4 THEN 
		UPDATE `worktime`.`worker_holiday_number`
		SET `sickpayed_absence_number` = `sickpayed_absence_number` - 1
		WHERE `worker_id` = worker_id;
	WHEN 5 THEN 
		UPDATE `worktime`.`worker_holiday_number`
		SET `verified_absence_number` = `verified_absence_number` - 1
		WHERE `worker_id` = worker_id;
    ELSE
		BEGIN
        END;
	END CASE;


	CASE absence_type_id
	WHEN 1 THEN 
		UPDATE `worktime`.`worker_holiday_number` 
		SET `not_set_absence_number` = `not_set_absence_number` + 1 
		WHERE `worker_id` = worker_id;
	WHEN 2 THEN 
		UPDATE `worktime`.`worker_holiday_number`
		SET `payed_absence_number` = `payed_absence_number` + 1
		WHERE `worker_id` = worker_id;
	WHEN 3 THEN 
		UPDATE `worktime`.`worker_holiday_number`
		SET `unpayed_absence_number` = `unpayed_absence_number` + 1
		WHERE `worker_id` = worker_id;
	WHEN 4 THEN 
		UPDATE `worktime`.`worker_holiday_number`
		SET `sickpayed_absence_number` = `sickpayed_absence_number` + 1
		WHERE `worker_id` = worker_id;
	WHEN 5 THEN 
		UPDATE `worktime`.`worker_holiday_number`
		SET `verified_absence_number` = `verified_absence_number` + 1
		WHERE `worker_id` = worker_id;
    ELSE
		BEGIN
        END;
	END CASE;
END