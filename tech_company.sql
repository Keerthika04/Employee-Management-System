-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 01, 2023 at 12:07 PM
-- Server version: 10.4.16-MariaDB
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tech_company`
--

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE `departments` (
  `ID` int(2) NOT NULL,
  `Department_Name` varchar(80) NOT NULL,
  `Department_Description` varchar(2500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `departments`
--

INSERT INTO `departments` (`ID`, `Department_Name`, `Department_Description`) VALUES
(1, 'IT department', 'IT department is responsible for managing and maintaining the company\'s technology infrastructure.'),
(2, 'Design department', 'Design department focuses on creating visually appealing and user-friendly designs for products, websites, marketing materials and etc.\r\n'),
(3, 'Business Development', 'Business Development department is responsible for identifying growth opportunities for the company.\r\n'),
(4, 'Marketing department', 'Marketing department is responsible for promoting the company\'s products or services to the target audience.\r\n'),
(5, 'Finance department', 'Finance department is in charge of managing the company\'s financial resources.');

-- --------------------------------------------------------

--
-- Table structure for table `designation`
--

CREATE TABLE `designation` (
  `ID` int(3) NOT NULL,
  `Designation_Name` varchar(80) NOT NULL,
  `Designation_Description` varchar(2500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `designation`
--

INSERT INTO `designation` (`ID`, `Designation_Name`, `Designation_Description`) VALUES
(1, 'Software Engineers', 'Software engineers are responsible for designing, developing and maintaining software applications.'),
(2, 'Systems Analysts', 'Systems analysts bridge the gap between business needs and technology solutions.'),
(3, 'Project Lead', 'Project leads are responsible for managing the team effectively, delegate tasks, provide feedback and support, and ensure that the team works together toward the project\'s success.'),
(4, 'Program Managers', 'Program managers are responsible for overseeing multiple projects that collectively contribute to a larger strategic goal.'),
(5, 'Graphic Designer', 'Creates visual content for various marketing materials and branding.\r\n'),
(6, 'Market Research Analyst', 'Gathers and analyzes market data to make informed decisions.'),
(7, 'Sales Manager', 'Leads and motivates the sales team to achieve revenue goals.'),
(8, 'Marketing Director', 'Oversees the marketing department\'s strategies and initiatives.'),
(9, 'Accountant', 'Manages financial records, reports and taxation.');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `ID` int(3) NOT NULL,
  `Department` int(2) NOT NULL,
  `Designation` int(2) NOT NULL,
  `EPF_No` varchar(6) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Email` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`ID`, `Department`, `Designation`, `EPF_No`, `Name`, `Email`) VALUES
(1, 1, 1, 'TC002', 'Jeyathashangan', 'jeyathashangan10@gmail.com'),
(2, 2, 5, 'TC003', 'Aysha Thikra', 'aysha6@gmail.com'),
(3, 5, 9, 'TC004', 'Keerthi Jeyan', 'keer5thi@gmail.com'),
(4, 3, 9, 'TC005', 'Sam Joe', 'samjoe@gmail.com'),
(5, 4, 8, 'TC006', 'Zainab Rubaith', 'zain@gmail.com'),
(6, 4, 6, 'TC007', 'Kabithbajan Jeyaharen', 'kabithbajan5@gmail.com'),
(7, 1, 3, 'TC008', 'Liru Mirukshan ', 'liri9@gmail.com'),
(8, 3, 7, 'TC009', 'Janani Naguleswaren', 'janu88@gmail.com'),
(9, 3, 7, 'TC010', 'Chrisela Christidas', 'chriselachris@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `hr_assistant`
--

CREATE TABLE `hr_assistant` (
  `ID` int(11) NOT NULL,
  `EPF_No` varchar(6) NOT NULL,
  `Name` varchar(80) NOT NULL,
  `Address` varchar(800) NOT NULL,
  `Username` varchar(40) NOT NULL,
  `Password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hr_assistant`
--

INSERT INTO `hr_assistant` (`ID`, `EPF_No`, `Name`, `Address`, `Username`, `Password`) VALUES
(1, 'TC001', 'Keerthika Jeyandran', '307 Trinco road, Batticaloa, Sri lanka', 'Keerthi6', 'KJ1234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `departments`
--
ALTER TABLE `departments`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Department_Name` (`Department_Name`);

--
-- Indexes for table `designation`
--
ALTER TABLE `designation`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Designation_Name` (`Designation_Name`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `EPF_No` (`EPF_No`),
  ADD KEY `Designation` (`Designation`),
  ADD KEY `Department` (`Department`,`Designation`,`EPF_No`) USING BTREE;

--
-- Indexes for table `hr_assistant`
--
ALTER TABLE `hr_assistant`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `EPF_No` (`EPF_No`),
  ADD UNIQUE KEY `Username` (`Username`),
  ADD UNIQUE KEY `EPF_No_2` (`EPF_No`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `departments`
--
ALTER TABLE `departments`
  MODIFY `ID` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `designation`
--
ALTER TABLE `designation`
  MODIFY `ID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `ID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `hr_assistant`
--
ALTER TABLE `hr_assistant`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`Department`) REFERENCES `departments` (`ID`),
  ADD CONSTRAINT `employees_ibfk_2` FOREIGN KEY (`Designation`) REFERENCES `designation` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
