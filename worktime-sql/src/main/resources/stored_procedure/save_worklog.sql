CREATE DEFINER=`root`@`localhost` PROCEDURE `save_worklog`(
	IN begin_date DATE,
	IN work_hour TINYINT,
	IN worker_id BIGINT
)
BEGIN
	INSERT INTO `worktime`.`worklog` (`begin_date`, `work_hour`, `worker_id`, `note`)
	VALUES (begin_date, work_hour, worker_id, '');

	SELECT ROW_COUNT() AS status, LAST_INSERT_ID() new_id;  
END