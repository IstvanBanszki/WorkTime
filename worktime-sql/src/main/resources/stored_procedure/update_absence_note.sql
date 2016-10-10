CREATE DEFINER=`root`@`localhost` PROCEDURE `update_absence_note`(
	IN absence_id BIGINT,
	IN note TINYTEXT
)
BEGIN

	UPDATE absence a
	SET a.note = note,
        a.date_of_modification = NOW()
	WHERE a.id = absence_id;

	SELECT ROW_COUNT() AS status;
END