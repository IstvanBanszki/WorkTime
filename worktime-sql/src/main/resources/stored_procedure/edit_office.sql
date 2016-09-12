CREATE DEFINER=`root`@`localhost` PROCEDURE `edit_office`(
    IN name TINYTEXT,
    IN address TINYTEXT,
    IN date_of_foundation DATE,
    IN office_id BIGINT
)
BEGIN

	UPDATE office o
       SET o.name = name,
		   o.address = address,
		   o.date_of_foundation = date_of_foundation
	 WHERE o.id =office_id;

	SELECT ROW_COUNT() AS status;

END