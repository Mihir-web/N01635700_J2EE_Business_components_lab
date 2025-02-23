-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Feb 23, 2025 at 03:18 AM
-- Server version: 9.1.0
-- PHP Version: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `j2ee_assignment2_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `id` bigint NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employee_seq`
--

DROP TABLE IF EXISTS `employee_seq`;
CREATE TABLE IF NOT EXISTS `employee_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `employee_seq`
--

INSERT INTO `employee_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `enrollment`
--

DROP TABLE IF EXISTS `enrollment`;
CREATE TABLE IF NOT EXISTS `enrollment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount_paid` double DEFAULT NULL,
  `program_code` varchar(255) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `enrollment`
--

INSERT INTO `enrollment` (`id`, `amount_paid`, `program_code`, `start_date`, `status`, `student_id`) VALUES
(1, 200, 'ITE2034', '2025-02-21 23:59:33.670000', 'enrolled', 1),
(2, 2000, 'ITE5432', '2025-02-22 13:27:29.333000', 'dropped', 4),
(3, 2000, 'ITE5432', '2025-02-22 22:09:54.370000', 'dropped', 5);

-- --------------------------------------------------------

--
-- Table structure for table `programs`
--

DROP TABLE IF EXISTS `programs`;
CREATE TABLE IF NOT EXISTS `programs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `duration` varchar(125) NOT NULL,
  `fee` decimal(10,0) NOT NULL,
  `duration_fee` double NOT NULL,
  `program_code` varchar(255) NOT NULL,
  `program_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `programs`
--

INSERT INTO `programs` (`id`, `duration`, `fee`, `duration_fee`, `program_code`, `program_name`) VALUES
(1, '2 Month', 200, 200, 'ITE2034', 'Enterprise Application Development'),
(2, '4 Month', 2000, 2000, 'ITE5432', 'J2EE Business Components');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
CREATE TABLE IF NOT EXISTS `students` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `postal_code` varchar(255) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK174kjl69manglnf5695rrame3` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(50) NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `postal_code` varchar(255) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `is_admin` int NOT NULL DEFAULT '0',
  `is_delete` int NOT NULL DEFAULT '0',
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `password`, `address`, `city`, `first_name`, `last_name`, `postal_code`, `user_name`, `is_admin`, `is_delete`, `created_at`, `updated_at`, `email`) VALUES
(1, 'Admin@1234', '93 Courtlands Drive', 'Scarborough', 'Mihirbhai', 'Hirpara', 'M1B4M8', 'mihir_hirpara', 0, 0, '2025-02-06 22:47:03.000000', '2025-02-22 22:11:27.579013', 'mihir@gmail.com'),
(2, 'Admin@1234', '66 clement cresent', 'Missisauga', 'Brijesh', 'Chandgadhiya', 'M1B4M8', 'brijesh', 0, 0, '2025-02-06 22:47:11.000000', '2025-02-22 12:24:08.805948', 'brijesh@gmail.com'),
(3, 'Admin@1234', 'Admin Address', 'Toronto', 'Admin', 'User', 'M1B4M8', 'admin_user', 1, 0, '2025-02-06 22:47:15.000000', '2025-02-06 22:47:26.000000', 'admin@gmaiil.com'),
(5, 'Admin@1234', 'Test Address', 'Toronto', 'John', 'Doe', 'M1B4M8', 'test_user', 0, 1, '2025-02-22 22:08:04.085709', '2025-02-22 22:12:08.652909', 'john@gmail.com');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
