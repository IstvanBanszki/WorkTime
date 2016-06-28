CREATE DEFINER=`root`@`localhost` PROCEDURE `save_absence`(
	IN begin TIMESTAMP,
	IN end TIMESTAMP,
	IN worker_id BIGINT,
	IN absence_type_id BIGINT
)
BEGIN

	INSERT INTO `worktime`.`absence` (`begin`, `end`, `status`, `absence_type_id`, `worker_id`) 
	VALUES (begin, end, 1, absence_type_id, worker_id);
    
    SELECT ROW_COUNT() AS status;
END