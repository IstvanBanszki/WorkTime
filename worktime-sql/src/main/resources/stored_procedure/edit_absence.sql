CREATE DEFINER=`root`@`localhost` PROCEDURE `edit_absence`(
	IN absence_id BIGINT,
	IN begin_date TIMESTAMP,
	IN end_date TIMESTAMP,
	IN absence_type_id BIGINT
)
BEGIN
    
	UPDATE `absence`
	SET `end_date` = end_date, 
	    `begin_date` = begin_date, 
        `date_of_modification` = NOW()
	WHERE `id` = absence_id;

	SELECT ROW_COUNT() AS status, begin_date;
END