DROP DATABASE IF EXISTS book; ## 如果原来的数据库存在，就删除

CREATE DATABASE book;  ## 创建数据库

USE book;   ## 切换到book数据库

##创建用户表t_user
CREATE TABLE IF NOT EXISTS t_user (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`username` VARCHAR(20) NOT NULL UNIQUE,
	`password` VARCHAR(32) NOT NULL,
	`email` VARCHAR(200)
);

INSERT INTO t_user(`username`,`password`,`email`) VALUES('admin','admin','admin@qq.com');

SELECT * FROM t_user;

############################################################################################

#图书模块的数据库表
CREATE TABLE t_book (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(100),
	`price` DECIMAL(11, 2),
	`author` VARCHAR(100),
	`sales` INT,
	`stock` INT, /*库存*/
	`img_path` VARCHAR(200)  /*图书封面路径*/
);

## 插入初始化测试数据
INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
VALUES(NULL , 'java从入门到放弃' , '国哥' , 80 , 9999 , 9 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
VALUES(NULL , '数据结构与算法' , '严敏君' , 78.5 , 6 , 13 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
VALUES(NULL , '怎样拐跑别人的媳妇' , '龙伍' , 68, 99999 , 52 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
VALUES(NULL , '木虚肉盖饭' , '小胖' , 16, 1000 , 50 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
VALUES(NULL , 'C++编程思想' , '刚哥' , 45.5 , 14 , 95 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
VALUES(NULL , '蛋炒饭' , '周星星' , 9.9, 12 , 53 , 'static/img/default.jpg');
 
INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
VALUES(NULL , '赌神' , '龙伍' , 66.5, 125 , 535 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
VALUES(NULL , 'Java编程思想' , '阳哥' , 99.5 , 47 , 36 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
VALUES(NULL , 'JavaScript从入门到精通' , '婷姐' , 9.9 , 85 , 95 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
VALUES(NULL , 'cocos2d-x游戏编程入门' , '国哥' , 49, 52 , 62 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
VALUES(NULL , 'C语言程序设计' , '谭浩强' , 28 , 52 , 74 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
VALUES(NULL , 'Lua语言程序设计' , '雷丰阳' , 51.5 , 48 , 82 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
VALUES(NULL , '西游记' , '罗贯中' , 12, 19 , 9999 , 'static/img/default.jpg');

INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
VALUES(NULL , '水浒传' , '华仔' , 33.05 , 22 , 88 , 'static/img/default.jpg');
 
INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
VALUES(NULL , '操作系统原理' , '刘优' , 133.05 , 122 , 188 , 'static/img/default.jpg');
 
INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
VALUES(NULL , '数据结构 java版' , '封大神' , 173.15 , 21 , 81 , 'static/img/default.jpg');
 
INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
VALUES(NULL , 'UNIX高级环境编程' , '乐天' , 99.15 , 210 , 810 , 'static/img/default.jpg');
 
INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
VALUES(NULL , 'javaScript高级编程' , '国哥' , 69.15 , 210 , 810 , 'static/img/default.jpg');
 
INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
VALUES(NULL , '大话设计模式' , '国哥' , 89.15 , 20 , 10 , 'static/img/default.jpg');
 
INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
VALUES(NULL , '人月神话' , '刚哥' , 88.15 , 20 , 80 , 'static/img/default.jpg');
 	
## 查看表内容
SELECT id,NAME,author,price,sales,stock,img_path FROM t_book;

#分页查询
SELECT * FROM t_book LIMIT 8, 5; # limit bdgin, pageSize

SELECT COUNT(*) FROM t_book;

SELECT COUNT(*) FROM t_book WHERE price BETWEEN 10 AND 50;

SELECT `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` imgPath FROM t_book WHERE price BETWEEN 10 AND 50 ORDER BY price LIMIT 1, 4;

CREATE TABLE t_order(
 `order_id` VARCHAR(50) PRIMARY KEY,
 `create_time` DATETIME,
 `price` DECIMAL(11, 2),
 `status` INT,
 `user_id` INT,
 FOREIGN KEY(`user_id`) REFERENCES t_user(`id`)
 );

 
 CREATE TABLE t_order_item(
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(100),
  `count` INT,
  `price` DECIMAL(11,2),
  `total_price` DECIMAL(11,2),
  `order_id` VARCHAR(50),
   FOREIGN KEY(`order_id`) REFERENCES t_order(`order_id`)
 );
 
