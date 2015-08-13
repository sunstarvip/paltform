/*
SQLyog Ultimate v9.62 
MySQL - 5.6.17-log : Database - platform
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE IF NOT EXISTS `platform` DEFAULT CHARACTER SET utf8;

USE `platform`;

/*Table structure for table `t_platform_menu` */
CREATE TABLE IF NOT EXISTS `t_platform_menu` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `enable_tag` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `visible_tag` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `url_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ej9sogs6oqdbjxu6o62vyoj2f` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_platform_menu` */

insert  into `t_platform_menu`(`id`,`create_time`,`enable_tag`,`sort`,`update_time`,`visible_tag`,`name`,`parent_id`,`url_path`) values ('297e72df4c7471dd014c7475c8550000','2015-04-01 18:10:18','YES',0,'2015-04-07 23:01:51','YES','用户管理',NULL,'/platform/account/user/esayuiPage');
insert  into `t_platform_menu`(`id`,`create_time`,`enable_tag`,`sort`,`update_time`,`visible_tag`,`name`,`parent_id`,`url_path`) values ('297e72df4c7471dd014c747600a40001','2015-04-01 18:10:32','YES',0,'2015-04-07 23:12:01','YES','角色管理',NULL,'/platform/account/role/esayuiPage');
insert  into `t_platform_menu`(`id`,`create_time`,`enable_tag`,`sort`,`update_time`,`visible_tag`,`name`,`parent_id`,`url_path`) values ('297e72df4c7471dd014c74762fdb0002','2015-04-01 18:10:44','YES',0,'2015-04-07 23:11:41','YES','权限管理',NULL,'/platform/account/permission/esayuiPage');
insert  into `t_platform_menu`(`id`,`create_time`,`enable_tag`,`sort`,`update_time`,`visible_tag`,`name`,`parent_id`,`url_path`) values ('297e72df4c7471dd014c747657a10003','2015-04-01 18:10:55','YES',0,NULL,'YES','菜单管理',NULL,'/platform/system/menu/esayuiPage');

/*Table structure for table `t_platform_permission` */
CREATE TABLE IF NOT EXISTS `t_platform_permission` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `enable_tag` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `visible_tag` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lwp137xwbep48n81e0rys9gth` (`parent_id`),
  CONSTRAINT `FK_lwp137xwbep48n81e0rys9gth` FOREIGN KEY (`parent_id`) REFERENCES `t_platform_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_platform_permission` */

insert  into `t_platform_permission`(`id`,`create_time`,`enable_tag`,`sort`,`update_time`,`visible_tag`,`description`,`name`,`parent_id`) values ('297e72df4c417dbf014c419943fa0000','2015-03-22 21:08:12','YES',0,NULL,'YES','系统管理员','系统管理员',NULL);

/*Data for the table `t_platform_permission_role` */

/*Table structure for table `t_platform_role` */
CREATE TABLE IF NOT EXISTS `t_platform_role` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `enable_tag` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `visible_tag` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_platform_role` */

insert  into `t_platform_role`(`id`,`create_time`,`enable_tag`,`sort`,`update_time`,`visible_tag`,`description`,`name`) values ('297e72df4c2804fb014c281669e10000','2015-03-17 22:14:59','YES',0,'2015-04-14 00:21:28','YES','对系统进行管理的角色','管理员');

/*Data for the table `t_platform_role_user` */
CREATE TABLE IF NOT EXISTS `t_platform_user` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `enable_tag` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `visible_tag` varchar(255) DEFAULT NULL,
  `account_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `mail_address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_num` varchar(11) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_platform_user` */

insert  into `t_platform_user`(`id`,`create_time`,`enable_tag`,`sort`,`update_time`,`visible_tag`,`account_name`,`address`,`mail_address`,`name`,`password`,`phone_num`,`salt`) values ('402883824b8e1d89014b8e1e636d0000','2015-02-16 00:42:04','YES',0,'2015-04-14 16:43:39','YES','admin',NULL,NULL,'admin','c24df1d38a50ea076950897876e4407d',NULL,'VCVmTsXqns+uSRBUOwe6gg==');

/*Table structure for table `t_platform_permission_role` */
CREATE TABLE IF NOT EXISTS `t_platform_permission_role` (
  `permission_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  KEY `FK_l5efj0fmngkcjtdnejb0d94u` (`role_id`),
  KEY `FK_c0s5projb08pxek3r1l5gqrh1` (`permission_id`),
  CONSTRAINT `FK_c0s5projb08pxek3r1l5gqrh1` FOREIGN KEY (`permission_id`) REFERENCES `t_platform_permission` (`id`),
  CONSTRAINT `FK_l5efj0fmngkcjtdnejb0d94u` FOREIGN KEY (`role_id`) REFERENCES `t_platform_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_platform_role_user` */
CREATE TABLE IF NOT EXISTS `t_platform_role_user` (
  `role_id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  KEY `FK_nen90j3ancd8t5cql2ywqeyuq` (`user_id`),
  KEY `FK_5525txkeb9j0igfg75k3gk1j9` (`role_id`),
  CONSTRAINT `FK_5525txkeb9j0igfg75k3gk1j9` FOREIGN KEY (`role_id`) REFERENCES `t_platform_role` (`id`),
  CONSTRAINT `FK_nen90j3ancd8t5cql2ywqeyuq` FOREIGN KEY (`user_id`) REFERENCES `t_platform_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
