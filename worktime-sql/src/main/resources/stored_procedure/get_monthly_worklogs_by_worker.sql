CREATE DEFINER=`root`@`localhost` PROCEDURE `get_monthly_worklogs_by_worker`(
    IN worker_id INT,
    IN years   	 INT,
    IN months 	 INT
)
BEGIN

    SELECT `id`, `begin_date`, `work_hour`
      FROM `worktime`.`worklog`
     WHERE MONTH(`begin_date`) = months 
       AND YEAR(`begin_date`) = years
       AND `worker_id` = worker_id;

END