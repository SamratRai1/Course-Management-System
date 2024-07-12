-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 12, 2022 at 12:20 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `management_course`
--

-- --------------------------------------------------------

--
-- Table structure for table `assigned`
--

CREATE TABLE `assigned` (
  `module_id` int(11) NOT NULL,
  `module_teacher` varchar(200) NOT NULL,
  `module_name` varchar(200) NOT NULL,
  `module_code` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `assigned`
--

INSERT INTO `assigned` (`module_id`, `module_teacher`, `module_name`, `module_code`) VALUES
(1, 'Bishal', 'Fundamentals of Computing', '4CS015'),
(2, 'Deepson', 'Fundamentals of Computing', '4CS015'),
(3, 'Rukesh', 'Fundamentals of Computing', '4CS015');

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `course_id` int(11) NOT NULL,
  `course_name` varchar(200) NOT NULL,
  `course_code` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`course_id`, `course_name`, `course_code`) VALUES
(2, 'Bachelors in Information Technology', 'BIT'),
(4, 'Bachelors in Information Management', 'BIBM');

-- --------------------------------------------------------

--
-- Table structure for table `enrolled`
--

CREATE TABLE `enrolled` (
  `enrolled_id` int(11) NOT NULL,
  `username` varchar(400) NOT NULL,
  `course_enrolled` varchar(400) NOT NULL,
  `level` int(11) NOT NULL,
  `modules` varchar(400) NOT NULL,
  `college_id` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `enrolled`
--

INSERT INTO `enrolled` (`enrolled_id`, `username`, `course_enrolled`, `level`, `modules`, `college_id`) VALUES
(1, 'Raymon', 'Bachelors in Information Technology', 1, 'Fundamentals of Computing', '2059640');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `user_id` int(11) NOT NULL,
  `username` varchar(200) NOT NULL,
  `password` varchar(20) NOT NULL,
  `indicator` varchar(20) NOT NULL,
  `college_id` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`user_id`, `username`, `password`, `indicator`, `college_id`) VALUES
(6, 'Admin', 'admin', 'Admin', 'null'),
(13, 'Raymon', '73-55-608', 'Student', '2059640'),
(14, 'Prabesh', 'prabesh123', 'Student', '2055670'),
(15, 'Sakshyam', 'sak123', 'Student', '2054650'),
(16, 'Bishal', 'bishal123', 'Teacher', '34534545');

-- --------------------------------------------------------

--
-- Table structure for table `marking`
--

CREATE TABLE `marking` (
  `marks_id` int(11) NOT NULL,
  `students_name` varchar(400) NOT NULL,
  `module` varchar(400) NOT NULL,
  `marks` int(11) NOT NULL,
  `course` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `marking`
--

INSERT INTO `marking` (`marks_id`, `students_name`, `module`, `marks`, `course`) VALUES
(7, 'Raymon', 'Fundamentals of Computing', 100, 'BIT'),
(8, 'Raymon', 'Intro ductory', 100, 'BIT'),
(9, 'Raymon', 'sdgsdg', 100, 'BIT'),
(10, 'Raymon', 'fgsdgsgds', 100, 'BIT'),
(12, 'Sakshyam', 'Intro ductory', 100, 'BIT'),
(13, 'Sakshyam', 'sdgsdg', 100, 'BIT'),
(14, 'Sakshyam', 'fgsdgsgds', 100, 'BIT'),
(15, 'Sakshyam', 'Fundamentals of computing', 100, 'BIT');

-- --------------------------------------------------------

--
-- Table structure for table `modules`
--

CREATE TABLE `modules` (
  `module_id` int(11) NOT NULL,
  `course_name` varchar(200) NOT NULL,
  `module_name` varchar(200) NOT NULL,
  `level` int(11) NOT NULL,
  `semester` int(11) NOT NULL,
  `module_code` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `modules`
--

INSERT INTO `modules` (`module_id`, `course_name`, `module_name`, `level`, `semester`, `module_code`) VALUES
(2, 'BIT', 'Fundamentals of Computing', 4, 1, '4CS015'),
(5, 'BIT', 'Intro ductory', 4, 1, '324dfgd'),
(6, 'BIT', 'sdgsdg', 4, 1, 'fgdfgdfg'),
(7, 'BIT', 'fgsdgsgds', 4, 1, 'sdgsdgsdg');

-- --------------------------------------------------------

--
-- Table structure for table `userinfo`
--

CREATE TABLE `userinfo` (
  `user_id` int(11) NOT NULL,
  `username` varchar(200) NOT NULL,
  `enrolled_course` varchar(200) NOT NULL,
  `semester` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `collegeId` varchar(200) NOT NULL,
  `indicator` varchar(200) NOT NULL,
  `addModules` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `userinfo`
--

INSERT INTO `userinfo` (`user_id`, `username`, `enrolled_course`, `semester`, `level`, `collegeId`, `indicator`, `addModules`) VALUES
(3, 'Raymon', 'BIT', 1, 4, '2059640', 'Student', 1),
(4, 'Prabesh', 'BIBM', 1, 4, '2055670', 'Student', 1),
(5, 'Sakshyam', 'BIT', 3, 5, '2054650', 'Student', 1),
(6, 'Bishal', '', 1, 4, '34534545', 'Teacher', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assigned`
--
ALTER TABLE `assigned`
  ADD PRIMARY KEY (`module_id`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`course_id`);

--
-- Indexes for table `enrolled`
--
ALTER TABLE `enrolled`
  ADD PRIMARY KEY (`enrolled_id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `marking`
--
ALTER TABLE `marking`
  ADD PRIMARY KEY (`marks_id`);

--
-- Indexes for table `modules`
--
ALTER TABLE `modules`
  ADD PRIMARY KEY (`module_id`);

--
-- Indexes for table `userinfo`
--
ALTER TABLE `userinfo`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assigned`
--
ALTER TABLE `assigned`
  MODIFY `module_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
  MODIFY `course_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `enrolled`
--
ALTER TABLE `enrolled`
  MODIFY `enrolled_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `marking`
--
ALTER TABLE `marking`
  MODIFY `marks_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `modules`
--
ALTER TABLE `modules`
  MODIFY `module_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `userinfo`
--
ALTER TABLE `userinfo`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
