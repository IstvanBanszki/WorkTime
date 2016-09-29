CREATE DEFINER=`root`@`localhost` PROCEDURE `update_worklog_note`(
	IN worklog_id BIGINT,
	IN note TINYTEXT
)
BEGIN

	UPDATE worklog w
	SET w.note = note,
        w.date_of_modification = NOW()
	WHERE w.id = worklog_id;

	SELECT ROW_COUNT() AS status;
END
