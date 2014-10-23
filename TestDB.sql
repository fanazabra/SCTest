-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.0.21-community-nt - MySQL Community Edition (GPL)
-- ОС Сервера:                   Win32
-- HeidiSQL Версия:              8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных testdb
CREATE DATABASE IF NOT EXISTS `testdb` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `testdb`;


-- Дамп структуры для таблица testdb.account
CREATE TABLE IF NOT EXISTS `account` (
  `AccountId` int(11) NOT NULL auto_increment,
  `Name` varchar(50) collate cp1251_bin NOT NULL,
  `Total` decimal(10,0) NOT NULL,
  `Open_Date` date NOT NULL,
  `Close_Date` date NOT NULL,
  `State` varchar(50) collate cp1251_bin NOT NULL,
  `ClientId` int(11) NOT NULL,
  PRIMARY KEY  (`AccountId`),
  KEY `FK_account_client` (`ClientId`),
  CONSTRAINT `FK_account_client` FOREIGN KEY (`ClientId`) REFERENCES `client` (`ClientId`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251 COLLATE=cp1251_bin;

-- Экспортируемые данные не выделены.


-- Дамп структуры для таблица testdb.client
CREATE TABLE IF NOT EXISTS `client` (
  `ClientId` int(11) NOT NULL default '0',
  `Second_Name` varchar(50) collate cp1251_bin NOT NULL,
  `First_Name` varchar(50) collate cp1251_bin NOT NULL,
  `Third_Name` varchar(50) collate cp1251_bin NOT NULL,
  PRIMARY KEY  (`ClientId`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251 COLLATE=cp1251_bin;

-- Экспортируемые данные не выделены.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
