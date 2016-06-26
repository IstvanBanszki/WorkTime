CREATE DEFINER=`root`@`localhost` PROCEDURE `save_worklog`(
	IN begin TIMESTAMP,
	IN work_hour TINYINT,
	IN worker_id BIGINT
)
BEGIN

	INSERT INTO `worktime`.`worklog` (`begin`, `work_hour`, `worker_id`)
	VALUES (begin, work_hour, worker_id);
    
    SELECT ROW_COUNT() AS status;
END