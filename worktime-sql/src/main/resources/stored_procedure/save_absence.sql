CREATE DEFINER=`root`@`localhost` PROCEDURE `save_absence`(
	IN begin_date TIMESTAMP,
	IN end_date TIMESTAMP,
	IN worker_id BIGINT,
	IN absence_type_id BIGINT
)
BEGIN

	IF ((SELECT COUNT(*) FROM `worktime`.`absence` WHERE `begin` = begin_date) != 0) THEN
		SELECT -1 AS status;
    ELSEIF ((SELECT COUNT(*) FROM `worktime`.`absence` WHERE `end` = end_date) != 0) THEN
		SELECT -2 AS status;
    ELSEIF (((SELECT COUNT(*) FROM `worktime`.`absence` WHERE `begin` BETWEEN begin_date AND end_date) != 0) OR
			 ((SELECT COUNT(*) FROM `worktime`.`absence` WHERE `end`   BETWEEN begin_date AND end_date) != 0)) THEN
		SELECT -3 AS status;
    ELSE
		INSERT INTO `worktime`.`absence` (`begin`, `end`, `status`, `absence_type_id`, `worker_id`) 
		VALUES (begin, end, 1, absence_type_id, worker_id);
		
		SELECT ROW_COUNT() AS status;
		
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
    END IF;

END