CREATE DEFINER=`root`@`localhost` PROCEDURE `save_worklog`(
	IN begin TIMESTAMP,
	IN end TIMESTAMP,
	IN worker_id BIGINT,
    IN absence_type_id BIGINT
)
BEGIN

	INSERT INTO `worktime`.`worklog` (`status`, `begin`, `end`, `absence_type_id`, `worker_id`)
	VALUES (1, begin, end, absence_type_id, worker_id);
    
    SELECT ROW_COUNT() AS status;
END