CREATE DEFINER=`root`@`localhost` PROCEDURE `edit_department`(
    IN name TINYTEXT,
    IN date_of_foundation DATE,
    IN office_id BIGINT,
    IN deparment_id BIGINT
)
BEGIN

	UPDATE department d
       SET d.name = name,
		   d.date_of_foundation = date_of_foundation,
		   d.office_id = office_id
	 WHERE d.id =deparment_id;

	SELECT ROW_COUNT() AS status;

END