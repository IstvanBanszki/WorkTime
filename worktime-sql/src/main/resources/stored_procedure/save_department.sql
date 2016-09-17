CREATE DEFINER=`root`@`localhost` PROCEDURE `save_department`(
    IN name TINYTEXT,
    IN date_of_foundation DATE,
    IN office_id BIGINT
)
BEGIN

	INSERT INTO `worktime`.`department` (`name`, `date_of_foundation`, `office_id`)
    VALUES (name, date_of_foundation, office_id);

	SELECT ROW_COUNT() AS status, LAST_INSERT_ID() new_id;

END