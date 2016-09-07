CREATE DEFINER=`root`@`localhost` PROCEDURE `approve_employee_absence`(
	IN absence_id BIGINT
)
BEGIN

	UPDATE `absence`
	SET `status` = 3,
        `date_of_modification` = NOW()
	WHERE `id` = absence_id;

	SELECT ROW_COUNT() AS status;
END