CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_absence`(
	IN absence_id BIGINT
)
BEGIN

	DELETE FROM `worktime`.`absence`
	WHERE `id` = absence_id;

	SELECT ROW_COUNT() AS status;
END