CREATE DEFINER=`root`@`localhost` PROCEDURE `save_worklog`(
	IN begin_date TIMESTAMP,
	IN work_hour TINYINT,
	IN worker_id BIGINT
)
BEGIN

	IF ((SELECT COUNT(*) FROM `worktime`.`worklog` WHERE `begin` = begin_date) = 0) THEN

		INSERT INTO `worktime`.`worklog` (`begin`, `work_hour`, `worker_id`)
		VALUES (begin, work_hour, worker_id);
        
		SELECT ROW_COUNT() AS status;
    ELSE
		SELECT -1 AS status;
    END IF;
  
END