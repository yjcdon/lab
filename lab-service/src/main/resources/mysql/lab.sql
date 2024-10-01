-- --------------------------------------------------------
-- 主机:                           192.168.88.137
-- 服务器版本:                        5.7.44 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 lab 的数据库结构
CREATE DATABASE IF NOT EXISTS `lab` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `lab`;

-- 导出  表 lab.notify 结构
CREATE TABLE IF NOT EXISTS `notify` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `content` varchar(100) DEFAULT NULL COMMENT '通知内容',
  `user_id` int(11) NOT NULL COMMENT '被通知人的id',
  `is_look` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户是否查看了该通知，0未查看，1已查看',
  `notify_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '通知类型，1新增任务，2删除任务，3修改任务',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除，0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8mb4 COMMENT='任务通知表';

-- 正在导出表  lab.notify 的数据：~36 rows (大约)
INSERT INTO `notify` (`id`, `content`, `user_id`, `is_look`, `notify_type`, `create_time`, `is_delete`) VALUES
	(91, '张三 你好，你的导师修改了任务：发送邮件测试', 4, 0, 3, '2024-09-30 13:49:24', 0),
	(92, '李四 你好，你的导师修改了任务：发送邮件测试', 5, 0, 3, '2024-09-30 13:49:24', 0),
	(93, '王五 你好，你的导师修改了任务：发送邮件测试', 6, 0, 3, '2024-09-30 13:49:24', 0),
	(94, '王五 你好，你的导师将你移除出了任务：发送邮件测试', 6, 0, 3, '2024-09-30 13:50:21', 0),
	(95, '赵六 你好，你的导师将你添加到了任务：发送邮件测试', 7, 0, 3, '2024-09-30 13:50:21', 0),
	(96, '张三 你好，你的导师修改了任务：发送邮件测试', 4, 0, 3, '2024-09-30 13:50:21', 0),
	(97, '李四 你好，你的导师修改了任务：发送邮件测试', 5, 0, 3, '2024-09-30 13:50:21', 0),
	(98, 'admin 你好，你的导师删除了 2 个任务：string，发送邮件测试', 1, 0, 2, '2024-09-30 14:09:13', 0),
	(99, '小号 你好，你的导师删除了 2 个任务：string，发送邮件测试', 2, 0, 2, '2024-09-30 14:09:13', 0),
	(100, '小号 你好，你的导师删除了 2 个任务：string，发送邮件测试', 3, 0, 2, '2024-09-30 14:09:13', 0),
	(101, '张三 你好，你的导师删除了 2 个任务：string，发送邮件测试', 4, 0, 2, '2024-09-30 14:09:13', 0),
	(102, '李四 你好，你的导师删除了 2 个任务：string，发送邮件测试', 5, 0, 2, '2024-09-30 14:09:13', 0),
	(103, '赵六 你好，你的导师删除了 2 个任务：string，发送邮件测试', 7, 0, 2, '2024-09-30 14:09:13', 0),
	(104, 'admin 你好，你的导师删除了 2 个任务：string，发送邮件测试', 1, 0, 2, '2024-09-30 14:12:47', 0),
	(105, '小号 你好，你的导师删除了 2 个任务：string，发送邮件测试', 2, 0, 2, '2024-09-30 14:12:47', 0),
	(106, '小号 你好，你的导师删除了 2 个任务：string，发送邮件测试', 3, 0, 2, '2024-09-30 14:12:47', 0),
	(107, '张三 你好，你的导师删除了 2 个任务：string，发送邮件测试', 4, 0, 2, '2024-09-30 14:12:47', 0),
	(108, '李四 你好，你的导师删除了 2 个任务：string，发送邮件测试', 5, 0, 2, '2024-09-30 14:12:47', 0),
	(109, '赵六 你好，你的导师删除了 2 个任务：string，发送邮件测试', 7, 0, 2, '2024-09-30 14:12:47', 0),
	(110, 'admin 你好，你的导师删除了 2 个任务：string，发送邮件测试', 1, 0, 2, '2024-09-30 14:14:17', 0),
	(111, '小号 你好，你的导师删除了 2 个任务：string，发送邮件测试', 2, 0, 2, '2024-09-30 14:14:17', 0),
	(112, '小号 你好，你的导师删除了 2 个任务：string，发送邮件测试', 3, 0, 2, '2024-09-30 14:14:17', 0),
	(113, '张三 你好，你的导师删除了 2 个任务：string，发送邮件测试', 4, 0, 2, '2024-09-30 14:14:17', 0),
	(114, '李四 你好，你的导师删除了 2 个任务：string，发送邮件测试', 5, 0, 2, '2024-09-30 14:14:17', 0),
	(115, '赵六 你好，你的导师删除了 2 个任务：string，发送邮件测试', 7, 0, 2, '2024-09-30 14:14:17', 0),
	(116, 'admin 你好，你的导师删除了 2 个任务：string，发送邮件测试', 1, 0, 2, '2024-09-30 14:16:49', 0),
	(117, '小号 你好，你的导师删除了 2 个任务：string，发送邮件测试', 2, 0, 2, '2024-09-30 14:16:49', 0),
	(118, '张三 你好，你的导师删除了 2 个任务：string，发送邮件测试', 4, 0, 2, '2024-09-30 14:16:49', 0),
	(119, '李四 你好，你的导师删除了 2 个任务：string，发送邮件测试', 5, 0, 2, '2024-09-30 14:16:49', 0),
	(120, '赵六 你好，你的导师删除了 2 个任务：string，发送邮件测试', 7, 0, 2, '2024-09-30 14:16:49', 0),
	(121, '李四 你好，你的导师将你移除出了任务：修改邮件测试', 5, 0, 3, '2024-10-01 01:43:42', 0),
	(122, '赵六 你好，你的导师将你移除出了任务：修改邮件测试', 7, 0, 3, '2024-10-01 01:43:42', 0),
	(123, 'admin 你好，你的导师将你添加到了任务：修改邮件测试', 1, 0, 3, '2024-10-01 01:43:42', 0),
	(124, '小号 你好，你的导师将你添加到了任务：修改邮件测试', 2, 0, 3, '2024-10-01 01:43:42', 0),
	(125, '小号 你好，你的导师将你添加到了任务：修改邮件测试', 3, 0, 3, '2024-10-01 01:43:42', 0),
	(126, '张三 你好，你的导师修改了任务：修改邮件测试', 4, 0, 3, '2024-10-01 01:43:42', 0),
	(127, 'admin 你好，你的导师删除了 1 个任务：修改邮件测试', 1, 0, 2, '2024-10-01 09:49:37', 0),
	(128, '小号 你好，你的导师删除了 1 个任务：修改邮件测试', 2, 0, 2, '2024-10-01 09:49:37', 0),
	(129, '小号 你好，你的导师删除了 1 个任务：修改邮件测试', 3, 0, 2, '2024-10-01 09:49:37', 0),
	(130, '张三 你好，你的导师删除了 1 个任务：修改邮件测试', 4, 0, 2, '2024-10-01 09:49:37', 0);

-- 导出  表 lab.task 结构
CREATE TABLE IF NOT EXISTS `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `task_name` varchar(100) NOT NULL DEFAULT '' COMMENT '任务名字',
  `task_assigned_user_id` varchar(100) NOT NULL DEFAULT '' COMMENT '任务已分配的学生id，通过英文逗号分隔',
  `task_type_id` int(11) NOT NULL DEFAULT '0' COMMENT '任务分类id',
  `task_tag_id` int(11) NOT NULL DEFAULT '0' COMMENT '任务标签id',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新人id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '任务创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '任务更新时间',
  `begin_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '任务开始时间，可指定；默认为now()',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '任务结束时间，可指定；默认为null',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '任务是否删除，0未删除，1已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `task_name` (`task_name`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COMMENT='任务表';

-- 正在导出表  lab.task 的数据：~14 rows (大约)
INSERT INTO `task` (`id`, `task_name`, `task_assigned_user_id`, `task_type_id`, `task_tag_id`, `create_user_id`, `update_user_id`, `create_time`, `update_time`, `begin_time`, `end_time`, `is_delete`) VALUES
	(1, '完成期末论文', '1,2,3', 1, 1, 1, 2, '2024-09-28 06:58:04', '2024-09-28 07:47:31', '2024-09-28 08:35:13', '2023-12-31 15:59:59', 0),
	(2, '实现Web项目新功能', '4,5,6', 2, 2, 2, 2, '2024-09-28 06:58:04', '2024-09-28 06:58:04', '2024-09-28 08:35:13', '2023-11-30 15:59:59', 0),
	(3, '优化数据库性能', '7,8', 3, 3, 3, 3, '2024-09-28 06:58:04', '2024-09-28 06:58:04', '2024-09-28 08:35:13', '2023-10-31 15:59:59', 0),
	(4, '撰写技术博客', '9,10', 4, 4, 4, 4, '2024-09-28 06:58:04', '2024-09-28 06:58:04', '2024-09-28 08:35:13', '2023-09-30 15:59:59', 0),
	(5, '开发移动端APP', '1,4,7', 2, 5, 1, 1, '2024-09-28 06:58:04', '2024-09-28 06:58:04', '2024-09-28 08:35:13', '2023-12-31 15:59:59', 0),
	(6, '学习新技术', '2,5,8', 4, 6, 2, 2, '2024-09-28 06:58:04', '2024-09-28 06:58:04', '2024-09-28 08:35:13', '2023-11-30 15:59:59', 0),
	(7, '修复线上BUG', '3,6,9', 3, 1, 3, 3, '2024-09-28 06:58:04', '2024-09-28 06:58:04', '2024-09-28 08:35:13', '2023-10-31 15:59:59', 0),
	(8, '完成系统架构文档', '4,7,10', 1, 2, 4, 4, '2024-09-28 06:58:04', '2024-09-28 08:45:54', '2024-09-28 08:35:13', '2023-09-30 15:59:59', 0),
	(9, '参与项目需求讨论', '1,5,9', 2, 3, 1, 1, '2024-09-28 06:58:04', '2024-09-28 06:58:04', '2024-09-28 08:35:13', '2023-12-31 15:59:59', 0),
	(10, '整理知识体系', '2,6,10', 4, 4, 2, 2, '2024-09-28 06:58:04', '2024-09-28 06:58:04', '2024-09-28 08:35:13', '2023-11-30 15:59:59', 0),
	(11, 'string', '1,2,4', 1, 1, 1, 1, '2024-09-28 07:23:38', '2024-09-30 14:16:49', '2024-09-28 08:35:13', '2024-12-31 16:00:00', 1),
	(13, 'test', '1,2,3', 1, 1, 1, 1, '2024-09-28 08:41:02', '2024-09-28 08:42:15', '2024-09-28 08:41:02', '2024-11-10 16:00:00', 1),
	(14, '666', '1,2,3,4,5,6', 2, 2, 1, 2, '2024-09-28 08:41:21', '2024-10-01 06:01:46', '2024-10-31 16:00:00', '2024-12-31 16:00:00', 0),
	(46, '修改邮件测试', '1,2,3,4', 1, 1, 1, 1, '2024-09-29 11:35:20', '2024-10-01 09:50:35', '2023-12-31 16:00:00', '2024-12-31 16:00:00', 0);

-- 导出  表 lab.task_tag 结构
CREATE TABLE IF NOT EXISTS `task_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `task_tag_name` varchar(50) NOT NULL DEFAULT '' COMMENT '任务标签名字',
  PRIMARY KEY (`id`),
  UNIQUE KEY `task_tag_name` (`task_tag_name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='任务标签表';

-- 正在导出表  lab.task_tag 的数据：~7 rows (大约)
INSERT INTO `task_tag` (`id`, `task_tag_name`) VALUES
	(14, '666'),
	(2, 'Web开发'),
	(4, '技术分享'),
	(6, '技术学习'),
	(3, '数据库优化'),
	(5, '移动开发'),
	(1, '论文');

-- 导出  表 lab.task_type 结构
CREATE TABLE IF NOT EXISTS `task_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `task_type_name` varchar(50) DEFAULT NULL COMMENT '任务分类名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `task_type_name` (`task_type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='任务类型表';

-- 正在导出表  lab.task_type 的数据：~5 rows (大约)
INSERT INTO `task_type` (`id`, `task_type_name`) VALUES
	(6, '888'),
	(2, 'Web开发'),
	(1, '文档编写'),
	(4, '知识管理'),
	(3, '系统维护');

-- 导出  表 lab.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `account` varchar(20) NOT NULL DEFAULT '' COMMENT '学号',
  `password` varchar(30) NOT NULL DEFAULT '' COMMENT '密码',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT '电子邮箱地址',
  `sex` char(1) NOT NULL DEFAULT '' COMMENT '性别，只填男女',
  `is_tutor` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否为导师，0不是，1是',
  PRIMARY KEY (`id`),
  KEY `idx_name_password` (`name`,`password`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 正在导出表  lab.user 的数据：~13 rows (大约)
INSERT INTO `user` (`id`, `name`, `account`, `password`, `email`, `sex`, `is_tutor`) VALUES
	(1, 'admin', 'admin', 'admin', 'liangyujia1203@qq.com', '男', 1),
	(2, '小号', 'xiaohao', 'test', '2844921429@qq.com', '男', 0),
	(3, '小号', 'xiaohao', 'test', '2844921429@qq.com', '女', 1),
	(4, '张三', '2023001', 'password1', 'liangyujia1203@qq.com', '男', 0),
	(5, '李四', '2023002', 'password2', 'lisi@example.com', '男', 0),
	(6, '王五', '2023003', 'password3', 'wangwu@example.com', '男', 0),
	(7, '赵六', '2023004', 'password4', 'zhaoliu@example.com', '男', 1),
	(8, '孙七', '2023005', 'password5', 'sunqi@example.com', '女', 0),
	(9, '周八', '2023006', 'password6', 'zhouba@example.com', '女', 0),
	(10, '吴九', '2023007', 'password7', 'wujiu@example.com', '男', 1),
	(11, '郑十', '2023008', 'password8', 'zhengshi@example.com', '女', 0),
	(12, '陈十一', '2023009', 'password9', 'chenshiyi@example.com', '男', 0),
	(13, '林十二', '2023010', 'password10', 'linshier@example.com', '女', 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
