BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `foresters` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`first_name`	TEXT NOT NULL,
	`last_name`	TEXT NOT NULL,
	`middle_name`	TEXT NOT NULL,
	`phone_number`	TEXT NOT NULL,
	`date_of_birth`	TEXT NOT NULL
);
INSERT INTO `foresters` (id,first_name,last_name,middle_name,phone_number,date_of_birth) VALUES (1,'Stepan','Philippovich','Dubov','+79601236547','01.04.1990');
INSERT INTO `foresters` (id,first_name,last_name,middle_name,phone_number,date_of_birth) VALUES (2,'Philipp','Markovich','Kot','+79991234567','08.08.2000');
INSERT INTO `foresters` (id,first_name,last_name,middle_name,phone_number,date_of_birth) VALUES (3,'Evgenii','Vladimirovich','Nikitin','+79849874561','17.06.1984');
COMMIT;
