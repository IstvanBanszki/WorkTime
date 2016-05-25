CREATE DEFINER=`root`@`localhost` PROCEDURE `get_profile_data`(
	IN  worker_id BIGINT
)
BEGIN
	SELECT u.date_of_registration, 
		   w.first_name, 
		   w.last_name, 
		   w.gender, 
		   w.date_of_birth, 
		   w.nationality, 
		   w.position, 
		   w.email_address, 
		   w.daily_work_hour_total, 
		   d.name AS department_name,
		   o.name AS office_name
	FROM user u 
	INNER JOIN worker w 	ON w.user_id = u.id
	INNER JOIN department d ON w.department_id = d.id
	INNER JOIN office o 	ON d.office_id = o.id
	WHERE w.id = worker_id;
END;