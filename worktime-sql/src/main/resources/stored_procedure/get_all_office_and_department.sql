CREATE DEFINER=`root`@`localhost` PROCEDURE `get_all_office_and_department`()
BEGIN

	SELECT o.name, o.address, o.date_of_foundation, d.name AS department_name, d.date_of_foundation AS department_foundation_date
	  FROM office o
INNER JOIN department d ON d.office_id = o.id
  GROUP BY department_name;

END