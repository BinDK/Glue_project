-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 06, 2021 at 02:16 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Inventory`
--

-- --------------------------------------------------------

--
-- Table structure for table `db_bill`
--

CREATE TABLE `db_bill` (
  `bill_id` int(11) NOT NULL,
  `date_print` date NOT NULL,
  `bill_status` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `payment` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `total_price` double NOT NULL,
  `customer_id` int(11) NOT NULL,
  `emp_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `db_bill`
--

INSERT INTO `db_bill` (`bill_id`, `date_print`, `bill_status`, `payment`, `total_price`, `customer_id`, `emp_id`) VALUES
(1, '2021-03-26', 'Approved', 'CASH', 17.23, 3, 4),
(2, '2021-03-26', 'DENNY', 'CASH', 17.23, 3, 4),
(3, '2021-03-26', 'Approved', 'CASH', 17.23, 3, 4),
(5, '2021-03-27', 'APPROVED', 'VISA', 36.72, 3, 1),
(6, '2021-03-27', 'APPROVED', 'MASTERCARD', 42.2, 3, 1),
(8, '2021-03-30', 'APPROVED', 'CASH', 44.97, 1, 1),
(9, '2021-03-30', 'APPROVED', 'CASH', 44.97, 1, 1),
(10, '2021-03-30', 'APPROVED', 'CASH', 47.17, 1, 1),
(11, '2021-03-30', 'APPROVED', 'CASH', 118.41, 1, 1),
(12, '2021-03-30', 'APPROVED', 'CASH', 93.93, 1, 1),
(13, '2021-03-30', 'APPROVED', 'CASH', 132.38, 1, 1),
(14, '2021-03-30', 'APPROVED', 'CASH', 306, 5, 1),
(15, '2021-03-30', 'APPROVED', 'CASH', 61.2, 7, 1),
(16, '2021-03-30', 'APPROVED', 'CASH', 48.96, 8, 1),
(17, '2021-03-30', 'APPROVED', 'CASH', 36.72, 1, 1),
(18, '2021-03-31', 'APPROVED', 'MASTERCARD', 138.9, 1, 1),
(19, '2021-04-01', 'APPROVED', 'MASTERCARD', 108.92, 5, 1),
(20, '2021-04-02', 'APPROVED', 'CASH', 12.24, 1, 1),
(21, '2021-04-03', 'APPROVED', 'MASTERCARD', 516.4, 4, 1),
(22, '2021-04-06', 'APPROVED', 'MASTERCARD', 74.85000000000001, 4, 4),
(23, '2021-04-06', 'APPROVED', 'VISA', 791.72, 5, 4),
(24, '2021-04-06', 'APPROVED', 'CASH', 94.91, 4, 4),
(25, '2021-04-06', 'APPROVED', 'MASTERCARD', 74.93, 5, 4),
(26, '2021-04-06', 'APPROVED', 'MASTERCARD', 74.93, 5, 4);

-- --------------------------------------------------------

--
-- Table structure for table `db_bill_detail`
--

CREATE TABLE `db_bill_detail` (
  `bill_detail_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `store_price` double NOT NULL,
  `item_unit` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `item_quantity` int(11) NOT NULL,
  `bill_status` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `bill_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `db_bill_detail`
--

INSERT INTO `db_bill_detail` (`bill_detail_id`, `item_id`, `store_price`, `item_unit`, `item_quantity`, `bill_status`, `bill_id`) VALUES
(1, 2, 4.99, 'pcs', 10, 'APPROVED', 1),
(6, 3, 4.99, 'pcs', 77, '', 3),
(7, 1, 1.99, 'pcs', 14, '', 3),
(11, 3, 14.99, 'pcs', 1, '', 8),
(12, 3, 14.99, 'pcs', 1, '', 8),
(13, 3, 14.99, 'pcs', 1, '', 8),
(14, 3, 14.99, 'pcs', 1, '', 9),
(15, 3, 14.99, 'pcs', 1, '', 9),
(16, 3, 14.99, 'pcs', 0, 'RETURNED', 9),
(17, 2, 4.99, 'pcs', 7, '', 10),
(18, 1, 12.24, 'pcs', 1, '', 10),
(19, 3, 14.99, 'pcs', 3, '', 11),
(20, 1, 12.24, 'pcs', 6, '', 11),
(21, 3, 14.99, 'pcs', 3, '', 12),
(22, 1, 12.24, 'pcs', 4, '', 12),
(23, 1, 12.24, 'pcs', 3, 'APPROVED', 17),
(24, 3, 14.99, 'pcs', 6, 'APPROVED', 18),
(25, 1, 12.24, 'pcs', 0, 'RETURNED', 18),
(26, 3, 14.99, 'pcs', 4, 'APPROVED', 19),
(27, 1, 12.24, 'pcs', 0, 'RETURNED', 19),
(28, 1, 12.24, 'pcs', 1, 'APPROVED', 20),
(29, 3, 14.99, 'pcs', 32, 'APPROVED', 21),
(30, 1, 12.24, 'pcs', 0, 'RETURNED', 21),
(31, 8, 4.99, 'bottle', 3, 'APPROVED', 22),
(32, 2, 4.99, 'pcs', 0, 'RETURNED', 22),
(33, 3, 14.99, 'pcs', 52, 'APPROVED', 23),
(34, 1, 12.24, 'pcs', 1, 'APPROVED', 23);

-- --------------------------------------------------------

--
-- Table structure for table `db_bill_import`
--

CREATE TABLE `db_bill_import` (
  `bill_import_id` int(11) NOT NULL,
  `supplier_id` int(11) NOT NULL,
  `total_price` double NOT NULL,
  `emp_id` int(11) NOT NULL,
  `date_import` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `db_bill_import_detail`
--

CREATE TABLE `db_bill_import_detail` (
  `import_detail_id` int(11) NOT NULL,
  `item_name` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `import_price` double NOT NULL,
  `store_price` double NOT NULL,
  `item_quantity` int(11) NOT NULL,
  `item_unit` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `bill_status` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `bill_import_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `db_category`
--

CREATE TABLE `db_category` (
  `id` int(11) NOT NULL,
  `cate_name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `desciption` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `db_category`
--

INSERT INTO `db_category` (`id`, `cate_name`, `desciption`) VALUES
(1, 'Vegetable', ''),
(2, 'Fruit', ''),
(3, 'Milk', ''),
(4, 'Raincoat', ''),
(5, 'Mask', ''),
(6, 'Phone Accessories', '');

-- --------------------------------------------------------

--
-- Table structure for table `db_customer`
--

CREATE TABLE `db_customer` (
  `customer_id` int(11) NOT NULL,
  `customer_name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `customer_phone` int(11) NOT NULL,
  `description` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `db_customer`
--

INSERT INTO `db_customer` (`customer_id`, `customer_name`, `customer_phone`, `description`) VALUES
(1, 'customer', 0, ''),
(2, 'customer', 0, ''),
(3, 'customer1', 123123, ''),
(4, 'POP', 9202393, ''),
(5, 'POP2', 1912123, ''),
(6, 'asdas', 12121, ''),
(7, '1212', 34114, ''),
(8, 'pop', 1212, '');

-- --------------------------------------------------------

--
-- Table structure for table `db_item`
--

CREATE TABLE `db_item` (
  `item_id` int(11) NOT NULL,
  `item_name` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `item_store_price` double NOT NULL,
  `item_import_price` double NOT NULL,
  `store_quantity` int(11) NOT NULL,
  `item_unit` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `db_item`
--

INSERT INTO `db_item` (`item_id`, `item_name`, `item_store_price`, `item_import_price`, `store_quantity`, `item_unit`, `status`, `category_id`) VALUES
(1, 'Fabric Mask', 12.24, 5, 100, 'pcs', 'FULL', 5),
(2, 'Medical Mask', 4.99, 2, 100, 'pcs', 'FULL', 5),
(3, '3D Mask', 14.99, 9.99, 100, 'pcs', 'FULL', 5),
(8, 'Dalat Milk Mild', 4.99, 5.99, 100, 'bottle', 'FULL', 3);

-- --------------------------------------------------------

--
-- Table structure for table `db_supplier`
--

CREATE TABLE `db_supplier` (
  `id` int(11) NOT NULL,
  `supplier_name` varchar(25) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `db_supplier`
--

INSERT INTO `db_supplier` (`id`, `supplier_name`) VALUES
(1, 'Big Market'),
(2, 'CODEX'),
(3, 'DALAT FARM'),
(4, 'FRESH FRUIT'),
(5, 'LG'),
(6, 'SG FARM');

-- --------------------------------------------------------

--
-- Table structure for table `db_user`
--

CREATE TABLE `db_user` (
  `id` int(11) NOT NULL,
  `username` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `emp_name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `emp_role` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `db_user`
--

INSERT INTO `db_user` (`id`, `username`, `password`, `emp_name`, `emp_role`) VALUES
(1, 'admin', '$2a$10$T3F5sxE3NzA5Aj4Kixsml.3dFQMddcvPViP/3YYAh28rzQ6LnGQXG', 'Administrator', 'ADMIN'),
(2, 'sm', '$2a$10$1n2ZQHMq3EehpND3EMuyk.rOfNJQK6IqITY8UAQ9A6dmcmhYuQWyy', 'Sale Manager', 'SM'),
(3, 'im123', '$2a$10$KNTsQLUdTHPy.kxIBmXLweF6w0YwBGguOKdPd9odY6ogKu.bUH0qa', 'Inventory Manager', 'IM'),
(4, 'sale123', '$2a$10$JDv/DiW.rbLpmqh7edflSe7diHRVrDLAnS/AF8fJZNb7sKffzF1Aa', 'Saleman', 'sale'),
(5, 'test', '$2a$10$j5BaUtw/7YsEj8/cGXoYf.PJOtjvYRSsQB226Fqc6Tyb.hRaY5UJ2', 'test', 'SALE');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `db_bill`
--
ALTER TABLE `db_bill`
  ADD PRIMARY KEY (`bill_id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `emp_id` (`emp_id`);

--
-- Indexes for table `db_bill_detail`
--
ALTER TABLE `db_bill_detail`
  ADD PRIMARY KEY (`bill_detail_id`),
  ADD KEY `bill_id` (`bill_id`),
  ADD KEY `item_id` (`item_id`);

--
-- Indexes for table `db_bill_import`
--
ALTER TABLE `db_bill_import`
  ADD PRIMARY KEY (`bill_import_id`),
  ADD KEY `supplier_id` (`supplier_id`),
  ADD KEY `emp_name` (`emp_id`);

--
-- Indexes for table `db_bill_import_detail`
--
ALTER TABLE `db_bill_import_detail`
  ADD PRIMARY KEY (`import_detail_id`),
  ADD KEY `bill_import_id` (`bill_import_id`);

--
-- Indexes for table `db_category`
--
ALTER TABLE `db_category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cate_name` (`cate_name`);

--
-- Indexes for table `db_customer`
--
ALTER TABLE `db_customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `db_item`
--
ALTER TABLE `db_item`
  ADD PRIMARY KEY (`item_id`),
  ADD UNIQUE KEY `item_name` (`item_name`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `db_supplier`
--
ALTER TABLE `db_supplier`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `supplier_name` (`supplier_name`);

--
-- Indexes for table `db_user`
--
ALTER TABLE `db_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `db_bill`
--
ALTER TABLE `db_bill`
  MODIFY `bill_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `db_bill_detail`
--
ALTER TABLE `db_bill_detail`
  MODIFY `bill_detail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `db_bill_import`
--
ALTER TABLE `db_bill_import`
  MODIFY `bill_import_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `db_bill_import_detail`
--
ALTER TABLE `db_bill_import_detail`
  MODIFY `import_detail_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `db_category`
--
ALTER TABLE `db_category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `db_customer`
--
ALTER TABLE `db_customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `db_item`
--
ALTER TABLE `db_item`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `db_supplier`
--
ALTER TABLE `db_supplier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `db_user`
--
ALTER TABLE `db_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `db_bill`
--
ALTER TABLE `db_bill`
  ADD CONSTRAINT `db_bill_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `db_customer` (`customer_id`),
  ADD CONSTRAINT `db_bill_ibfk_2` FOREIGN KEY (`emp_id`) REFERENCES `db_user` (`id`);

--
-- Constraints for table `db_bill_detail`
--
ALTER TABLE `db_bill_detail`
  ADD CONSTRAINT `db_bill_detail_ibfk_1` FOREIGN KEY (`bill_id`) REFERENCES `db_bill` (`bill_id`),
  ADD CONSTRAINT `db_bill_detail_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `db_item` (`item_id`);

--
-- Constraints for table `db_bill_import`
--
ALTER TABLE `db_bill_import`
  ADD CONSTRAINT `db_bill_import_ibfk_2` FOREIGN KEY (`emp_id`) REFERENCES `db_user` (`id`),
  ADD CONSTRAINT `db_bill_import_ibfk_3` FOREIGN KEY (`supplier_id`) REFERENCES `db_supplier` (`id`);

--
-- Constraints for table `db_bill_import_detail`
--
ALTER TABLE `db_bill_import_detail`
  ADD CONSTRAINT `db_bill_import_detail_ibfk_1` FOREIGN KEY (`bill_import_id`) REFERENCES `db_bill_import` (`bill_import_id`);

--
-- Constraints for table `db_item`
--
ALTER TABLE `db_item`
  ADD CONSTRAINT `db_item_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `db_category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
