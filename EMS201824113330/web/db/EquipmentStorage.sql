CREATE DATABASE EquipmentStorage DEFAULT CHARSET utf8mb4;

CREATE TABLE IF NOT EXISTS employee (
	`id` CHAR(10) PRIMARY KEY,
	`password` VARCHAR(32) NOT NULL,
	`name` VARCHAR(50),
	`phone` CHAR(13),
	`usertype` CHAR(8),
	`department` VARCHAR(20)
) 

CREATE TABLE IF NOT EXISTS department (
	`id` VARCHAR(20) PRIMARY KEY,
	`name` VARCHAR(50) NOT NULL,
	`manager` CHAR(10),
	 FOREIGN KEY(`manager`) REFERENCES employee(`id`)
)

ALTER TABLE employee ADD FOREIGN KEY(department) REFERENCES department(`id`);

CREATE TABLE IF NOT EXISTS equipment (
	`id` VARCHAR(30) PRIMARY KEY,
	`name` VARCHAR(50),
	`specification` VARCHAR(120),
	`price` DECIMAL(11, 2),
	`buydate` DATE,
	`position` VARCHAR(120),
	`img_path` VARCHAR(200),
	`manager` CHAR(10),
	 FOREIGN KEY(`manager`) REFERENCES employee(`id`)
)

SHOW COLUMNS FROM employee;

SHOW COLUMNS FROM department;

SHOW COLUMNS FROM equipment;

SHOW CREATE TABLE employee;

INSERT INTO department(`id`,`name`,`manager`) VALUES("101","财务部","152336");
