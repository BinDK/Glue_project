-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 27, 2021 at 11:26 AM
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
(1, '2021-03-26', 'Approved', 'CASH', 17.23, 3, 4);

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
  `bill_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `db_bill_detail`
--

INSERT INTO `db_bill_detail` (`bill_detail_id`, `item_id`, `store_price`, `item_unit`, `item_quantity`, `bill_id`) VALUES
(1, 2, 4.99, 'pcs', 10, 1);

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
  `item_price` double NOT NULL,
  `item_quantity` int(11) NOT NULL,
  `item_unit` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
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
(3, 'customer1', 123123, '');

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
(7, 'oiuh', 1231.2, 10, 122, 'pcs', 'null', 5);

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
(1, 'admin', 'admin', 'Administrator', 'ADMIN'),
(2, 'sm', 'sm123', 'Sale Manager', 'SM'),
(3, 'im123', 'im123', 'Inventory Manager', 'IM'),
(4, 'sale123', 'sale123', 'Saleman', 'sale');

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
  MODIFY `bill_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `db_bill_detail`
--
ALTER TABLE `db_bill_detail`
  MODIFY `bill_detail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `db_item`
--
ALTER TABLE `db_item`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `db_supplier`
--
ALTER TABLE `db_supplier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `db_user`
--
ALTER TABLE `db_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
