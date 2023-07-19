-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 19, 2023 lúc 11:46 AM
-- Phiên bản máy phục vụ: 10.4.21-MariaDB
-- Phiên bản PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `test`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `student`
--

CREATE TABLE `student` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(20) CHARACTER SET latin1 NOT NULL,
  `GENDER` varchar(6) CHARACTER SET latin1 NOT NULL,
  `COUNTRY` varchar(20) CHARACTER SET latin1 NOT NULL,
  `AGE` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `student`
--

INSERT INTO `student` (`ID`, `NAME`, `GENDER`, `COUNTRY`, `AGE`) VALUES
(1, 'NBVA', 'Male', 'Hoa Binh', 21),
(2, 'THMA', 'Female', 'Ha Noi', 20),
(3, 'Student', 'male', 'Thanh Hoa', 23),
(4, 'Bá Côi', 'Male', 'Thái Bình', 60),
(5, 'Student num 1', 'Male', 'Hòa Bình', 40),
(6, 'student num3', 'female', 'ha noi', 10),
(7, 'Student num2', 'male', 'hoa binh', 50),
(8, 'student num4', 'female', 'hoa binh', 20),
(9, 'huong', 'female', 'hb', 20),
(10, 'coi', 'male', 'thaibinh', 60),
(11, 'Viet Anh', 'Male', 'Hoa Binh', 21),
(12, 'Minh Anh', 'Female', 'Ha Noi', 20),
(13, '', 'name', 'Hòa Bình', 2),
(14, 'c', 'c', 'c', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `student`
--
ALTER TABLE `student`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
