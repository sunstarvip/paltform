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
CREATE DATABASE /*!32312 IF NOT EXISTS*/`platform` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `platform`;

/*插入初始化管理员 admin/admin */
/*Table structure for table `t_platform_permission` */

DROP TABLE IF EXISTS `t_platform_permission`;

CREATE TABLE `t_platform_permission` (
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

/*Data for the table `t_platform_permission` */

/*Table structure for table `t_platform_permission_role` */

DROP TABLE IF EXISTS `t_platform_permission_role`;

CREATE TABLE `t_platform_permission_role` (
  `permission_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  KEY `FK_l5efj0fmngkcjtdnejb0d94u` (`role_id`),
  KEY `FK_c0s5projb08pxek3r1l5gqrh1` (`permission_id`),
  CONSTRAINT `FK_c0s5projb08pxek3r1l5gqrh1` FOREIGN KEY (`permission_id`) REFERENCES `t_platform_permission` (`id`),
  CONSTRAINT `FK_l5efj0fmngkcjtdnejb0d94u` FOREIGN KEY (`role_id`) REFERENCES `t_platform_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_platform_permission_role` */

/*Table structure for table `t_platform_role` */

DROP TABLE IF EXISTS `t_platform_role`;

CREATE TABLE `t_platform_role` (
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

/*Table structure for table `t_platform_role_user` */

DROP TABLE IF EXISTS `t_platform_role_user`;

CREATE TABLE `t_platform_role_user` (
  `role_id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  KEY `FK_nen90j3ancd8t5cql2ywqeyuq` (`user_id`),
  KEY `FK_5525txkeb9j0igfg75k3gk1j9` (`role_id`),
  CONSTRAINT `FK_5525txkeb9j0igfg75k3gk1j9` FOREIGN KEY (`role_id`) REFERENCES `t_platform_role` (`id`),
  CONSTRAINT `FK_nen90j3ancd8t5cql2ywqeyuq` FOREIGN KEY (`user_id`) REFERENCES `t_platform_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_platform_role_user` */

/*Table structure for table `t_platform_user` */

DROP TABLE IF EXISTS `t_platform_user`;

CREATE TABLE `t_platform_user` (
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
  `phone_num` int(11) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_platform_user` */

insert  into `t_platform_user`(`id`,`create_time`,`enable_tag`,`sort`,`update_time`,`visible_tag`,`account_name`,`address`,`mail_address`,`name`,`password`,`phone_num`,`salt`) values ('4028838249cd6e7c0149cd737f5a0000','2014-11-20 21:45:38','YES',0,NULL,'YES','admin',NULL,NULL,'管理员','842e4570ac984ab1241dafd5939b5552',NULL,'dXoITfj8TWvXmL6VmSq3uA==');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
