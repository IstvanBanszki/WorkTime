CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_worklog`(
	IN worklog_id BIGINT
)
BEGIN

	DELETE FROM `worktime`.`worklog`
	WHERE `id` = worklog_id;

	SELECT ROW_COUNT() AS status;
END