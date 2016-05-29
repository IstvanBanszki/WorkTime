CREATE DEFINER=`root`@`localhost` PROCEDURE `save_worklog`(
	IN description TINYTEXT,
	IN begin TIMESTAMP,
	IN end TIMESTAMP,
	IN worker_id BIGINT
)
BEGIN

	INSERT INTO `worktime`.`worklog` (`description`, `status`, `begin`, `end`, `absence_type_id`, `worker_id`)
	VALUES (description, 0, begin, end, 1, worker_id);
    
    SELECT ROW_COUNT() AS status;
END;