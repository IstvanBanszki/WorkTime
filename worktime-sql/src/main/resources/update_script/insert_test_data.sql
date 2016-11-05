-- role table test data
INSERT INTO `worktime`.`role` (`role`) VALUES ('NOT-SET');
INSERT INTO `worktime`.`role` (`role`) VALUES ('COMPANY-ADMIN-ROLE');
INSERT INTO `worktime`.`role` (`role`) VALUES ('SUPERIOR-ROLE');
INSERT INTO `worktime`.`role` (`role`) VALUES ('WORKER-ROLE');

-- user table test data
INSERT INTO `worktime`.`user` (`login_name`, `password`, `role_id`) VALUES ('root', 'AD3BwdmBpksCUTCj7cdYTnzyqavtcO+ZMhum6A==', 2); -- root
INSERT INTO `worktime`.`user` (`login_name`, `password`, `role_id`) VALUES ('alajos', 'P1pcDYM6N5A3AY30tJgTi3d6HbPQEPUu5dmdPQ==', 4); -- szalonna
INSERT INTO `worktime`.`user` (`login_name`, `password`, `role_id`) VALUES ('orc_worker', 'Wk9bcfJU036YdiSCD6xALHZjzKt7Ne0LvynG3A==', 3); -- test_password
INSERT INTO `worktime`.`user` (`login_name`, `password`, `role_id`) VALUES ('angel', 'OD5QEBn2+aZhaGb9TGIBs4ENbYjaMRUIDE5r4g==', 4); -- palinka
INSERT INTO `worktime`.`user` (`login_name`, `password`, `role_id`) VALUES ('joseph', 'onqj5XFLgXVsjLx6H9lLYSnHVMnCQTKaS/HlCg==', 3); -- stalin
INSERT INTO `worktime`.`user` (`login_name`, `password`, `role_id`) VALUES ('rambo', 'GAE0V+VEW0153J8GWTEr6W1qrcJgn5mnEpGOTA==', 4); -- thunder
INSERT INTO `worktime`.`user` (`login_name`, `password`, `role_id`) VALUES ('terminator', 'seLwFCP0j9VkEppU2i+S/41PJ7oVhC6f0vn9/A==', 4); -- zacsko

-- office table test data
INSERT INTO `worktime`.`office` (`name`, `address`, `date_of_foundation`) VALUES ('Example Company', '4023 Debrecen Csapo utca 23.', '2004.07.16');
INSERT INTO `worktime`.`office` (`name`, `address`, `date_of_foundation`) VALUES ('Test Company', 'Budapest Vamhaz korut 9.', '2008.02.21');

-- department table test data
INSERT INTO `worktime`.`department` (`name`, `office_id`, `date_of_foundation`) VALUES ('IT Operation Department', 1, '2004.07.16');
INSERT INTO `worktime`.`department` (`name`, `office_id`, `date_of_foundation`) VALUES ('HR Resources Department', 1, '2004.08.25');
INSERT INTO `worktime`.`department` (`name`, `office_id`, `date_of_foundation`) VALUES ('Sales Department', 2, '2008.02.21');
INSERT INTO `worktime`.`department` (`name`, `office_id`, `date_of_foundation`) VALUES ('Management Department', 1, '2005.01.25');

-- absence_type table test data
INSERT INTO `worktime`.`absence_type` (`name`) VALUES ('NOT-SET');
INSERT INTO `worktime`.`absence_type` (`name`) VALUES ('PAYED');
INSERT INTO `worktime`.`absence_type` (`name`) VALUES ('UNPAYED');
INSERT INTO `worktime`.`absence_type` (`name`) VALUES ('SICK-PAY');
INSERT INTO `worktime`.`absence_type` (`name`) VALUES ('VERIFIED');

-- worker table test data
INSERT INTO `worktime`.`worker` (`first_name`, `last_name`, `gender`, `date_of_birth`, `nationality`, `position`, `email_address`, `daily_work_hour_total`, `superior_work_id`, `department_id`, `user_id`)
VALUES ('Elemer', 'Nagy', 1, '1990.04.04', 'Hungarian', 'Admin', 'elemer.nagy@example.com', 8, 0, 1, 1);
INSERT INTO `worktime`.`worker` (`first_name`, `last_name`, `gender`, `date_of_birth`, `nationality`, `position`, `email_address`, `daily_work_hour_total`, `superior_work_id`, `department_id`, `user_id`)
VALUES ('Elek', 'Teszt', 1, '1984.10.01', 'Hungarian', 'Coffee maker', 'elek.teszt@example.com', 8, 1, 1, 2);
INSERT INTO `worktime`.`worker` (`first_name`, `last_name`, `gender`, `date_of_birth`, `nationality`, `position`, `email_address`, `daily_work_hour_total`, `superior_work_id`, `department_id`, `user_id`)
VALUES ('Linda', 'Teszt', 2, '1989.08.12', 'Hungarian', 'HR Generalist', 'nora.teszt@example.com', 8, 0, 2, 3);
INSERT INTO `worktime`.`worker` (`first_name`, `last_name`, `gender`, `date_of_birth`, `nationality`, `position`, `email_address`, `daily_work_hour_total`, `superior_work_id`, `department_id`, `user_id`)
VALUES ('Maria', 'Teszt', 2, '1990.02.21', 'Hungarian', 'HR Specialist', 'maria.teszt@example.com', 8, 3, 2, 4);
INSERT INTO `worktime`.`worker` (`first_name`, `last_name`, `gender`, `date_of_birth`, `nationality`, `position`, `email_address`, `daily_work_hour_total`, `superior_work_id`, `department_id`, `user_id`)
VALUES ('Joseph', 'Teszt', 1, '1984.03.16', 'Hungarian', 'Manager', 'joseph.teszt@example.com', 8, 0, 4, 5);
INSERT INTO `worktime`.`worker` (`first_name`, `last_name`, `gender`, `date_of_birth`, `nationality`, `position`, `email_address`, `daily_work_hour_total`, `superior_work_id`, `department_id`, `user_id`)
VALUES ('Villam', 'Teszt', 1, '1985.06.07', 'Hungarian', 'Office Manager', 'villam.teszt@example.com', 8, 5, 4, 6);
INSERT INTO `worktime`.`worker` (`first_name`, `last_name`, `gender`, `date_of_birth`, `nationality`, `position`, `email_address`, `daily_work_hour_total`, `superior_work_id`, `department_id`, `user_id`)
VALUES ('Miklos', 'Teszt', 1, '1988.01.04', 'Hungarian', 'Programmer', 'miklos.teszt@example.com', 8, 0, 1, 7);

-- worker_holiday_number table test data
INSERT INTO `worktime`.`worker_holiday_number` (`year`,`holiday_number_total`,`not_set_absence_number`,`payed_absence_number`,`unpayed_absence_number`,`sickpayed_absence_number`,`verified_absence_number`,`date_of_registration`,`worker_id`) VALUES (2016,25,1,0,0,0,0,0);
INSERT INTO `worktime`.`worker_holiday_number` (`year`,`holiday_number_total`,`not_set_absence_number`,`payed_absence_number`,`unpayed_absence_number`,`sickpayed_absence_number`,`verified_absence_number`,`date_of_registration`,`worker_id`) VALUES (2016,28,2,0,0,0,0,0);
INSERT INTO `worktime`.`worker_holiday_number` (`year`,`holiday_number_total`,`not_set_absence_number`,`payed_absence_number`,`unpayed_absence_number`,`sickpayed_absence_number`,`verified_absence_number`,`date_of_registration`,`worker_id`) VALUES (2016,24,3,0,0,0,0,0);
INSERT INTO `worktime`.`worker_holiday_number` (`year`,`holiday_number_total`,`not_set_absence_number`,`payed_absence_number`,`unpayed_absence_number`,`sickpayed_absence_number`,`verified_absence_number`,`date_of_registration`,`worker_id`) VALUES (2016,24,4,0,0,0,0,0);
INSERT INTO `worktime`.`worker_holiday_number` (`year`,`holiday_number_total`,`not_set_absence_number`,`payed_absence_number`,`unpayed_absence_number`,`sickpayed_absence_number`,`verified_absence_number`,`date_of_registration`,`worker_id`) VALUES (2016,27,5,0,0,0,0,0);
INSERT INTO `worktime`.`worker_holiday_number` (`year`,`holiday_number_total`,`not_set_absence_number`,`payed_absence_number`,`unpayed_absence_number`,`sickpayed_absence_number`,`verified_absence_number`,`date_of_registration`,`worker_id`) VALUES (2016,28,6,0,0,0,0,0);
INSERT INTO `worktime`.`worker_holiday_number` (`year`,`holiday_number_total`,`not_set_absence_number`,`payed_absence_number`,`unpayed_absence_number`,`sickpayed_absence_number`,`verified_absence_number`,`date_of_registration`,`worker_id`) VALUES (2016,28,7,0,0,0,0,0);


-- worklog table test data
INSERT INTO `worktime`.`worklog` (`begin_date`, `work_hour`, `worker_id`, `note`) VALUES ('2016.05.27', 8, 1, '');
INSERT INTO `worktime`.`worklog` (`begin_date`, `work_hour`, `worker_id`, `note`) VALUES ('2016.05.24', 8, 1, '');
INSERT INTO `worktime`.`worklog` (`begin_date`, `work_hour`, `worker_id`, `note`) VALUES ('2016.05.22', 8, 1, '');
INSERT INTO `worktime`.`worklog` (`begin_date`, `work_hour`, `worker_id`, `note`) VALUES ('2016.05.25', 8, 2, '');
INSERT INTO `worktime`.`worklog` (`begin_date`, `work_hour`, `worker_id`, `note`) VALUES ('2016.05.26', 8, 2, '');
INSERT INTO `worktime`.`worklog` (`begin_date`, `work_hour`, `worker_id`, `note`) VALUES ('2016.05.20', 8, 3, '');
INSERT INTO `worktime`.`worklog` (`begin_date`, `work_hour`, `worker_id`, `note`) VALUES ('2016.05.19', 8, 3, '');
INSERT INTO `worktime`.`worklog` (`begin_date`, `work_hour`, `worker_id`, `note`) VALUES ('2016.05.20', 8, 4, '');
INSERT INTO `worktime`.`worklog` (`begin_date`, `work_hour`, `worker_id`, `note`) VALUES ('2016.05.18', 8, 4, '');

-- absence table test data
INSERT INTO `worktime`.`absence` (`begin_date`, `end_date`, `status`, `absence_type_id`, `worker_id`, `note`) 
VALUES ('2016.05.20', '2016.05.20', 2, 2, 1, '');
INSERT INTO `worktime`.`absence` (`begin_date`, `end_date`, `status`, `absence_type_id`, `worker_id`, `note`) 
VALUES ('2016.05.13', '2016.05.13', 3, 4, 1, '');
INSERT INTO `worktime`.`absence` (`begin_date`, `end_date`, `status`, `absence_type_id`, `worker_id`, `note`) 
VALUES ('2016.05.10', '2016.05.10', 3, 4, 1, '');

INSERT INTO `worktime`.`entry` (`in_out`, `log_timestamp`, `worker_id`) VALUES (1, '2016-11-04 08:00:00', 1);
INSERT INTO `worktime`.`entry` (`in_out`, `log_timestamp`, `worker_id`) VALUES (0, '2016-11-04 16:00:00', 1);
INSERT INTO `worktime`.`entry` (`in_out`, `log_timestamp`, `worker_id`) VALUES (1, '2016-11-05 08:00:00', 1);
INSERT INTO `worktime`.`entry` (`in_out`, `log_timestamp`, `worker_id`) VALUES (0, '2016-11-05 16:00:00', 1);
