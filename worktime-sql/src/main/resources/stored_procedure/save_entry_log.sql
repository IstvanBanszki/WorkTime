CREATE DEFINER=`root`@`localhost` PROCEDURE `save_entry_log`(
	IN int_out 			TINYINT,
	IN log_timestamp	TIMESTAMP,
	IN worker_id 		BIGINT

)
BEGIN
	INSERT INTO `worktime`.`entry` (`int_out`, `log_timestamp`, `worker_id`)
	VALUES (int_out, log_timestamp, worker_id);

	SELECT ROW_COUNT() AS status, LAST_INSERT_ID() new_id;
END