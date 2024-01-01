-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 31, 2023 at 09:57 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `amanahborneopark`
--

-- --------------------------------------------------------

--
-- Table structure for table `akun`
--

CREATE TABLE `akun` (
  `id` int(11) NOT NULL,
  `username` varchar(64) NOT NULL,
  `pass` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `akun`
--

INSERT INTO `akun` (`id`, `username`, `pass`) VALUES
(1, 'admin', 'admin'),
(2, 'admin1', 'admin1');

-- --------------------------------------------------------

--
-- Table structure for table `pengunjung`
--

CREATE TABLE `pengunjung` (
  `id` int(3) NOT NULL,
  `nama` varchar(64) NOT NULL,
  `jeniskelamin` varchar(64) NOT NULL,
  `umur` varchar(64) NOT NULL,
  `telepon` varchar(12) NOT NULL,
  `alamat` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pengunjung`
--

INSERT INTO `pengunjung` (`id`, `nama`, `jeniskelamin`, `umur`, `telepon`, `alamat`) VALUES
(1, 'Ridho', 'Laki-laki', '21', '0811223344', 'Martapura'),
(3, 'Aifaa', 'Perempuan', '20', '0821314151', 'Banjarmasin'),
(4, 'Iki', 'Laki-laki', '10', '0867563432', 'Mantaraman'),
(5, 'Azmi', 'Laki-laki', '7', '0822334455', 'Banjarbaru'),
(6, 'kiki', 'Laki-laki', '7', '382424', 'sdvsv'),
(7, 'Ridhooo', 'Laki-laki', '20', '34242', 'sdvsgg');

-- --------------------------------------------------------

--
-- Table structure for table `tiket`
--

CREATE TABLE `tiket` (
  `id_tiket` int(3) NOT NULL,
  `id` int(3) NOT NULL,
  `id_wahana` int(11) NOT NULL,
  `tanggal` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tiket`
--

INSERT INTO `tiket` (`id_tiket`, `id`, `id_wahana`, `tanggal`) VALUES
(1, 1, 1, '2023-12-31'),
(2, 3, 11, '2023-12-31'),
(3, 4, 14, '2023-12-31'),
(5, 5, 1, '2023-12-31'),
(6, 3, 5, '2023-12-30'),
(7, 1, 1, '2023-12-31'),
(8, 1, 6, '2023-12-31'),
(9, 1, 3, '2023-12-31'),
(10, 1, 9, '2023-12-31'),
(11, 1, 10, '2023-12-31'),
(13, 1, 5, '2023-12-31'),
(14, 5, 5, '2023-12-31'),
(15, 5, 12, '2023-12-31'),
(16, 1, 5, '2023-12-31'),
(17, 1, 4, '2023-12-31'),
(18, 3, 4, '2023-12-31');

-- --------------------------------------------------------

--
-- Table structure for table `wahana`
--

CREATE TABLE `wahana` (
  `id_wahana` int(3) NOT NULL,
  `namawahana` varchar(64) NOT NULL,
  `harga` varchar(64) NOT NULL,
  `jam_mulai` varchar(64) NOT NULL,
  `jam_selesai` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `wahana`
--

INSERT INTO `wahana` (`id_wahana`, `namawahana`, `harga`, `jam_mulai`, `jam_selesai`) VALUES
(1, 'Taman Kelinci', '15000', '09:00', '17:00'),
(2, 'Rumah Terbalik', '10000', '09:00', '17:00'),
(3, 'Refleksi Ikan', '10000', '10:00', '17:00'),
(4, 'Rumah Hantu', '20000', '10:00', '16:00'),
(5, 'ATV', '35000', '09:00', '16:00'),
(6, 'Feeding Fish', '10000', '08:00', '17:00'),
(7, 'Flying Fox', '30000', '10:00', '17:00'),
(8, 'Sepeda Udara', '25000', '10:00', '17:00'),
(9, 'Sekuter Listrik', '35000', '09:00', '17:00'),
(10, 'Hobbit & Witch House', '10000', '09:00', '17:00'),
(11, 'Gokart', '35000', '10:00', '17:00'),
(12, 'Sepeda Air', '25000', '10:00', '17:00'),
(13, 'Jembatan Goyang', '10000', '09:00', '17:00'),
(14, 'Petik Buah', '10000', '09:00', '16:00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `akun`
--
ALTER TABLE `akun`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pengunjung`
--
ALTER TABLE `pengunjung`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tiket`
--
ALTER TABLE `tiket`
  ADD PRIMARY KEY (`id_tiket`),
  ADD KEY `id` (`id`),
  ADD KEY `id_wahana` (`id_wahana`);

--
-- Indexes for table `wahana`
--
ALTER TABLE `wahana`
  ADD PRIMARY KEY (`id_wahana`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `akun`
--
ALTER TABLE `akun`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `pengunjung`
--
ALTER TABLE `pengunjung`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tiket`
--
ALTER TABLE `tiket`
  MODIFY `id_tiket` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `wahana`
--
ALTER TABLE `wahana`
  MODIFY `id_wahana` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tiket`
--
ALTER TABLE `tiket`
  ADD CONSTRAINT `tiket_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pengunjung` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tiket_ibfk_2` FOREIGN KEY (`id_wahana`) REFERENCES `wahana` (`id_wahana`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
