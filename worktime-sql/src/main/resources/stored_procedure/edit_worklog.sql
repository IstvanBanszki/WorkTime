CREATE DEFINER=`root`@`localhost` PROCEDURE `edit_worklog`(
	IN worklog_id BIGINT,
	IN begin_date DATE,
	IN work_hour TINYINT
)
BEGIN
    
	UPDATE worklog w
	SET w.work_hour = work_hour, 
	    w.begin_date = begin_date, 
        w.date_of_modification = NOW()
	WHERE w.id = worklog_id;

	SELECT ROW_COUNT() AS status;
END