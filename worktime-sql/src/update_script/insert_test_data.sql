-- role table test data
INSERT INTO `worktime`.`role` (`role`) VALUES ('COMPANY-ADMIN-ROLE');
INSERT INTO `worktime`.`role` (`role`) VALUES ('SUPERIOR-ROLE');
INSERT INTO `worktime`.`role` (`role`) VALUES ('WORKER-ROLE');

-- user table test data
INSERT INTO `worktime`.`user` (`login_name`, `password`, `role_id`) VALUES ('root', 'AD3BwdmBpksCUTCj7cdYTnzyqavtcO+ZMhum6A==', 1);

-- office table test data
INSERT INTO `worktime`.`office` (`name`, `address`) VALUES ('Example Company', '4023 Debrecen Csapo utca 23.');

-- office table test data
INSERT INTO `worktime`.`department` (`name`, `office_id`) VALUES ('IT Operation Department', 1);

-- worker table test data
INSERT INTO `worktime`.`worker`
(`first_name`, `last_name`, `gender`, `date_of_birth`, `nationality`, `position`, `email_address`, `daily_work_hour_total`, `superior_work_id`, `department_id`, `user_id`)
VALUES 
('Elemer', 'Nagy', 1, '1990.04.04', 'Hungarian', 'Admin', 'elemer.nagy@example.com', 8, 0, 1, 1);



