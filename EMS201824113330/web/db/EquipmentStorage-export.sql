/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 8.0.19 : Database - equipmentstorage
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`equipmentstorage` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `equipmentstorage`;

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `manager` char(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `manager` (`manager`),
  CONSTRAINT `department_ibfk_1` FOREIGN KEY (`manager`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `department` */

insert  into `department`(`id`,`name`,`manager`) values 
('101','财务部','11439'),
('102','销售部','15233'),
('103','运营部','14362'),
('104','策划部','11511'),
('105','公关部','21824'),
('106','人事部','admin');

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` char(10) NOT NULL,
  `password` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `phone` char(13) DEFAULT NULL,
  `usertype` char(8) DEFAULT NULL,
  `department` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `department` (`department`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`department`) REFERENCES `department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `employee` */

insert  into `employee`(`id`,`password`,`name`,`phone`,`usertype`,`department`) values 
('11439','123','李四','15842632569','普通员工','101'),
('11511','123','张三','1423652452','系统管理员','104'),
('14362','123','李白','15489632369','普通员工','103'),
('15233','123','薇恩','15362547166','系统管理员','102'),
('17362','123','科加斯','14362596325','普通员工','104'),
('21824','000','贾克斯','14523695266','普通员工','105'),
('admin','123','黄磊','15046253961','系统管理员','106');

/*Table structure for table `equipment` */

DROP TABLE IF EXISTS `equipment`;

CREATE TABLE `equipment` (
  `id` varchar(30) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `specification` varchar(120) DEFAULT NULL,
  `price` decimal(11,2) DEFAULT NULL,
  `buydate` date DEFAULT NULL,
  `position` varchar(120) DEFAULT NULL,
  `imgpath` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `manager` char(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `manager` (`manager`),
  CONSTRAINT `equipment_ibfk_1` FOREIGN KEY (`manager`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `equipment` */

insert  into `equipment`(`id`,`name`,`specification`,`price`,`buydate`,`position`,`imgpath`,`manager`) values 
('0001','键盘','70*30',265.00,'2020-12-10','仓库A','upload/1607936194127_键盘.png','admin'),
('0002','硬盘','50*50',566.50,'2020-11-05','仓库B','upload/1607936211727_硬盘.png','15233'),
('0003','鼠标','15*9',76.48,'2020-12-02','仓库A','upload/1607936228651_鼠标.png','21824'),
('0004','音响','70*100',235.00,'2020-12-12','仓库C','upload/1607936238405_音响.png','admin'),
('0005','打印机','60*40',524.00,'2020-07-04','仓库A','upload/1607936259373_打印机.png','11511');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
