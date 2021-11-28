-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 22, 2021 at 04:20 PM
-- Server version: 8.0.17
-- PHP Version: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `turner`
--

/* Create database */
CREATE DATABASE turner;

/* Select database */
USE turner;

-- --------------------------------------------------------

--
-- Table structure for table `barco`
--

CREATE TABLE `barco` (
  `registro` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `id_categoriabarco` int(11) NOT NULL,
  `nombre` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tripulantes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `barco`
--

INSERT INTO `barco` (`registro`, `id_categoriabarco`, `nombre`, `tripulantes`) VALUES
('123123123', 1, 'Manoloo', 123123123),
('378274GHA', 2, 'Manolo', 155),
('A45JG8W', 3, 'Roberto', 20000);

-- --------------------------------------------------------

--
-- Table structure for table `categoriabarco`
--

CREATE TABLE `categoriabarco` (
  `id` int(1) NOT NULL,
  `nombre` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `categoriabarco`
--

INSERT INTO `categoriabarco` (`id`, `nombre`) VALUES
(1, 'galera'),
(2, 'bote'),
(3, 'galeon');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barco`
--
ALTER TABLE `barco`
  ADD PRIMARY KEY (`registro`),
  ADD UNIQUE KEY `codRegistro` (`registro`),
  ADD KEY `id_categoriabarco` (`id_categoriabarco`);

--
-- Indexes for table `categoriabarco`
--
ALTER TABLE `categoriabarco`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `barco`
--
ALTER TABLE `barco`
  ADD CONSTRAINT `barco_ibfk_1` FOREIGN KEY (`id_categoriabarco`) REFERENCES `categoriabarco` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
