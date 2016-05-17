-- role table test data
INSERT INTO `worktime`.`role` (`role`) VALUES ('COMPANY-ADMIN-ROLE');
INSERT INTO `worktime`.`role` (`role`) VALUES ('SUPERIOR-ROLE');
INSERT INTO `worktime`.`role` (`role`) VALUES ('WORKER-ROLE');

-- user table test data
INSERT INTO `worktime`.`user` (`login_name`, `password`, `role_id`) VALUES ('root', 'AD3BwdmBpksCUTCj7cdYTnzyqavtcO+ZMhum6A==', 1); -- root
INSERT INTO `worktime`.`user` (`login_name`, `password`, `role_id`) VALUES ('alajos', 'P1pcDYM6N5A3AY30tJgTi3d6HbPQEPUu5dmdPQ==', 3); -- szalonna
INSERT INTO `worktime`.`user` (`login_name`, `password`, `role_id`) VALUES ('orc_worker', 'Wk9bcfJU036YdiSCD6xALHZjzKt7Ne0LvynG3A==', 2); -- test_password
INSERT INTO `worktime`.`user` (`login_name`, `password`, `role_id`) VALUES ('angel', 'OD5QEBn2+aZhaGb9TGIBs4ENbYjaMRUIDE5r4g==', 3); -- palinka
INSERT INTO `worktime`.`user` (`login_name`, `password`, `role_id`) VALUES ('joseph', 'onqj5XFLgXVsjLx6H9lLYSnHVMnCQTKaS/HlCg==', 2); -- stalin
INSERT INTO `worktime`.`user` (`login_name`, `password`, `role_id`) VALUES ('rambo', 'GAE0V+VEW0153J8GWTEr6W1qrcJgn5mnEpGOTA==', 3); -- thunder
INSERT INTO `worktime`.`user` (`login_name`, `password`, `role_id`) VALUES ('terminator', 'seLwFCP0j9VkEppU2i+S/41PJ7oVhC6f0vn9/A==', 3); -- zacsko

-- office table test data
INSERT INTO `worktime`.`office` (`name`, `address`) VALUES ('Example Company', '4023 Debrecen Csapo utca 23.');
INSERT INTO `worktime`.`office` (`name`, `address`) VALUES ('Test Company', 'Budapest Vamhaz korut 9.');

-- department table test data
INSERT INTO `worktime`.`department` (`name`, `office_id`) VALUES ('IT Operation Department', 1);
INSERT INTO `worktime`.`department` (`name`, `office_id`) VALUES ('HR Resources Department', 1);
INSERT INTO `worktime`.`department` (`name`, `office_id`) VALUES ('Sales Department', 2);
INSERT INTO `worktime`.`department` (`name`, `office_id`) VALUES ('Management Department', 1);

-- absence_type table test data
INSERT INTO `worktime`.`absence_type_id` (`name`) VALUES ('PAYED');
INSERT INTO `worktime`.`absence_type_id` (`name`) VALUES ('UNPAYED');
INSERT INTO `worktime`.`absence_type_id` (`name`) VALUES ('SICK-PAY');
INSERT INTO `worktime`.`absence_type_id` (`name`) VALUES ('VERIFIED');

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
INSERT INTO `worktime`.`worker_holiday_number` (`year`, `worker_holiday_number_total`, `worker_id`) VALUES (2016, 25, 1);
INSERT INTO `worktime`.`worker_holiday_number` (`year`, `worker_holiday_number_total`, `worker_id`) VALUES (2016, 28, 2);
INSERT INTO `worktime`.`worker_holiday_number` (`year`, `worker_holiday_number_total`, `worker_id`) VALUES (2016, 24, 3);
INSERT INTO `worktime`.`worker_holiday_number` (`year`, `worker_holiday_number_total`, `worker_id`) VALUES (2016, 24, 4);
INSERT INTO `worktime`.`worker_holiday_number` (`year`, `worker_holiday_number_total`, `worker_id`) VALUES (2016, 27, 5);
INSERT INTO `worktime`.`worker_holiday_number` (`year`, `worker_holiday_number_total`, `worker_id`) VALUES (2016, 28, 6);
INSERT INTO `worktime`.`worker_holiday_number` (`year`, `worker_holiday_number_total`, `worker_id`) VALUES (2016, 28, 7);


