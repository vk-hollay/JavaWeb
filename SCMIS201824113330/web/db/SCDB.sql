CREATE DATABASE	SCDB DEFAULT CHARSET utf8;

CREATE TABLE IF NOT EXISTS student (
	`sno` CHAR(15) PRIMARY KEY,
	`sname` NVARCHAR(8),
	`ssex` NCHAR(1),
	`sbirthday` DATE,
	`sdno` CHAR(5)  /*系别编号*/
);

CREATE TABLE IF NOT EXISTS department (
	`dno` CHAR(5) PRIMARY KEY,
	`dname` VARCHAR(30),
	`dmanager` CHAR(8) /*系主任*/
);

-- 为学生信息表系别编号添加外键约束
ALTER TABLE student ADD FOREIGN KEY(`sdno`) REFERENCES department(`dno`);

DESC student;
DESC department;

DESC course;

CREATE TABLE IF NOT EXISTS teacher (
	`tno` CHAR(10) PRIMARY KEY,
	`tname` NVARCHAR(8),
	`tsex` NCHAR(1),
	`tbirthday` DATE,
	`teb` NVARCHAR(10),  /* 学历 */
	`tpt` NVARCHAR(10)   /* 职称 */
);


CREATE TABLE IF NOT EXISTS course (
	`cno` CHAR(6) PRIMARY KEY,
	`cname` NVARCHAR(50),
	`cpno` CHAR(6), /* 先行课编号 */
	`ccredit` SMALLINT(2) 
);


-- 选课信息表
CREATE TABLE IF NOT EXISTS sct (
	`cno` CHAR(6),
	`sno` CHAR(15),
	`tno` CHAR(10),
	`grade` INT(4),
	PRIMARY KEY(`cno`, `sno`),
	FOREIGN KEY(`cno`) REFERENCES course(`cno`),
	FOREIGN KEY(`sno`) REFERENCES student(`sno`),
	FOREIGN KEY(`tno`) REFERENCES teacher(`tno`)
);

CREATE TABLE IF NOT EXISTS `user` (
	`id` VARCHAR(15) PRIMARY KEY,
	`password` VARCHAR(30),
	`userType` NVARCHAR(6)
);


CREATE TABLE IF NOT EXISTS 

SELECT sno,sname,ssex,sbirthday,sdno,dname AS sdname FROM student,department WHERE student.sdno=department.dno;



SELECT a.cno,a.cname,a.cpno,b.cname AS cpname,a.ccredit FROM course a LEFT JOIN course b ON a.cpno=b.cno;



SELECT sc.sno,st.sname,c.cno,c.cname,te.tname,sc.grade FROM sct sc,student st,tcc tc,course c,teacher te WHERE sc.sno=st.sno AND tc.cno=c.cno AND tc.tno=te.tno AND sc.tccno=tc.tccno; 

SELECT c.cname FROM tcc t,course c WHERE t.cno=c.cno AND t.tno="10341";

SELECT sc.tccno,sc.sno,st.sname,c.cno,c.cname,te.tname,sc.grade FROM sct sc,student st,tcc tc,course c,teacher te WHERE sc.sno=st.sno AND tc.cno=c.cno AND tc.tno=te.tno AND sc.tccno=tc.tccno ORDER BY sc.sno;

SELECT sc.tccno,sc.sno,st.sname,c.cno,c.cname,te.tname,sc.grade FROM sct sc,student st,tcc tc,course c,teacher te WHERE sc.sno=st.sno AND tc.cno=c.cno AND tc.tno=te.tno AND sc.tccno=tc.tccno AND sc.sno='201824113326';

