/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 8.0.19 : Database - scdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`scdb` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `scdb`;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `cno` char(6) NOT NULL,
  `cname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `cpno` char(6) DEFAULT NULL,
  `ccredit` smallint DEFAULT NULL,
  PRIMARY KEY (`cno`),
  KEY `course_ibfk_1` (`cpno`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`cpno`) REFERENCES `course` (`cno`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`cno`,`cname`,`cpno`,`ccredit`) values 
('10015','高等数学',NULL,2),
('10018','中国书法赏析',NULL,1),
('10021','C语言程序设计',NULL,2),
('10026','数据结构','10021',2),
('10027','Oracle数据库','10026',2),
('10028','计算机组成原理','10021',2),
('10030','操作系统','10028',1),
('10032','Java程序设计','10021',2),
('10035','软件设计模式','10032',2),
('10038','算法设计与分析','10026',3);

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `dno` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '院系编号',
  `dname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '院系名称',
  `dmanager` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '院系主任',
  PRIMARY KEY (`dno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `department` */

insert  into `department`(`dno`,`dname`,`dmanager`) values 
('101','计算机学院','张三'),
('102','音乐学院','李四'),
('103','数学学院','赵六'),
('104','外国语学院','王五'),
('105','文学院','陈二');

/*Table structure for table `sct` */

DROP TABLE IF EXISTS `sct`;

CREATE TABLE `sct` (
  `tccno` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开课编号',
  `sno` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生学号',
  `grade` int DEFAULT NULL COMMENT '成绩',
  PRIMARY KEY (`tccno`,`sno`),
  KEY `sno` (`sno`),
  CONSTRAINT `sct_ibfk_1` FOREIGN KEY (`sno`) REFERENCES `student` (`sno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sct_ibfk_2` FOREIGN KEY (`tccno`) REFERENCES `tcc` (`tccno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sct` */

insert  into `sct`(`tccno`,`sno`,`grade`) values 
('1','201824113330',88),
('10','201824113330',88),
('13','201824113330',NULL),
('3','201824113326',NULL),
('4','201824113330',96),
('6','201824113326',NULL),
('8','201824113330',90),
('9','201824113326',70);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `sno` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生学号',
  `sname` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学生姓名',
  `ssex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别',
  `sbirthday` date DEFAULT NULL COMMENT '出生日期',
  `sdno` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '院系编号',
  PRIMARY KEY (`sno`),
  KEY `sdno` (`sdno`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`sdno`) REFERENCES `department` (`dno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`sno`,`sname`,`ssex`,`sbirthday`,`sdno`) values 
('201824112125','辛德拉','女','2002-06-05','103'),
('201824113303','科加斯','男','2001-02-02','105'),
('201824113304','薇恩','女','1998-07-19','102'),
('201824113306','金克斯','女','2000-07-12','104'),
('201824113325','盖伦','男','1999-11-11','101'),
('201824113326','贾克斯','男','1999-04-01','101'),
('201824113330','黄磊','男','1999-04-20','101'),
('201824113360','拉克丝','女','2001-02-15','102');

/*Table structure for table `tcc` */

DROP TABLE IF EXISTS `tcc`;

CREATE TABLE `tcc` (
  `tccno` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开课编号',
  `cno` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程编号',
  `tno` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '教师工号',
  `limited` int DEFAULT NULL COMMENT '限选人数',
  `selected` int DEFAULT NULL COMMENT '已选人数',
  `classroom` char(7) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '上课教室',
  `classtime` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '上课时间',
  PRIMARY KEY (`tccno`),
  UNIQUE KEY `classroom&classtime` (`classroom`,`classtime`),
  UNIQUE KEY `tno&classtime` (`tno`,`classtime`),
  KEY `cno` (`cno`),
  CONSTRAINT `tcc_ibfk_1` FOREIGN KEY (`cno`) REFERENCES `course` (`cno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tcc_ibfk_2` FOREIGN KEY (`tno`) REFERENCES `teacher` (`tno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tcc` */

insert  into `tcc`(`tccno`,`cno`,`tno`,`limited`,`selected`,`classroom`,`classtime`) values 
('1','10026','10215',100,6,'2-213','周二7-8节'),
('10','10027','10219',10,1,'2-213','周二1-2节'),
('11','10026','10152',30,0,'1-203','周四3-5节'),
('13','10030','10215',25,1,'2-104','周一1-2节'),
('2','10021','10152',100,3,'1-315','周一1-2节'),
('3','10021','10341',100,3,'1-315','周一3-4节'),
('4','10028','10341',100,4,'1-411','周四3-4节'),
('5','10035','10215',3,3,'2-203','周三3-5节'),
('6','10018','10231',60,3,'2-202','周二11-12节'),
('7','10032','10219',60,0,'1-103','周五7-8节'),
('8','10038','10152',40,1,'2-510','周三1-2节'),
('9','10015','10152',20,1,'1-215','周四1-2节');

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `tno` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师工号',
  `tname` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '教师姓名',
  `tsex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别',
  `tbirthday` date DEFAULT NULL COMMENT '出生日期',
  `teb` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学历',
  `tpt` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职称',
  PRIMARY KEY (`tno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

insert  into `teacher`(`tno`,`tname`,`tsex`,`tbirthday`,`teb`,`tpt`) values 
('10152','钟鏸','男','1976-07-14','硕士','讲师'),
('10215','许盛贵','男','1975-06-12','博士','副教授'),
('10219','申伟','男','1983-06-17','硕士','讲师'),
('10231','丁楹','男','1978-07-14','博士','教授'),
('10341','彭士荣','男','1977-07-07','硕士','助教');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户登录账号',
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户登录密码',
  `userType` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`password`,`userType`) values 
('201824112125','2125','学生'),
('201824113303','3303','学生'),
('201824113304','3304','学生'),
('201824113306','3306','学生'),
('201824113325','3325','学生'),
('201824113326','3326','学生'),
('201824113330','3330','学生'),
('201824113360','3360','学生'),
('admin','123','管理员');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
