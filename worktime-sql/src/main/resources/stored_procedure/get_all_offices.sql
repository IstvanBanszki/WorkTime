CREATE DEFINER=`root`@`localhost` PROCEDURE `get_all_offices`(
)
BEGIN

	SELECT id, name, address, date_of_foundation
	  FROM office;

END