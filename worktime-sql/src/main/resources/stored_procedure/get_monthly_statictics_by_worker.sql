CREATE DEFINER=`root`@`localhost` PROCEDURE `get_monthly_statictics_by_worker`(
    IN worker_id INT,
    IN years   	 INT,
    IN months 	 INT
)
BEGIN

    SELECT SUM(`work_hour`) AS sum, COUNT(`work_hour`) AS count, AVG(`work_hour`) AS avarage
      FROM `worktime`.`worklog`
     WHERE MONTH(`begin_date`) = months 
       AND YEAR(`begin_date`) = years
       AND `worker_id` = worker_id;

END