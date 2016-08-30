CREATE DEFINER=`root`@`localhost` PROCEDURE `edit_worklog`(
	IN worklog_id BIGINT,
	IN begin_date TIMESTAMP,
	IN work_hour TINYINT
)
BEGIN

	IF(work_hour = 0) THEN
    
		UPDATE worklog w
		SET w.work_hour = work_hour 
		WHERE w.id = worklog_id;
        
	ELSEIF(begin_date = null) THEN
    
		UPDATE worklog w
		SET w.begin_date = begin_date 
		WHERE w.id = worklog_id;
        
    END IF;

	SELECT ROW_COUNT() AS status;
END