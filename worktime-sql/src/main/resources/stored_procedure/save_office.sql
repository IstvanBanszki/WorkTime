CREATE DEFINER=`root`@`localhost` PROCEDURE `save_office`(
    IN name TINYTEXT,
    IN address TINYTEXT,
    IN date_of_foundation DATE
)
BEGIN

	INSERT INTO `worktime`.`office` (`name`, `address`, `date_of_foundation`)
    VALUES (name, address, date_of_foundation);

	SELECT ROW_COUNT() AS status;

END