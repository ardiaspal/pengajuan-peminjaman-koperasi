-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 01, 2018 at 05:27 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pbo2`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `AdminId` int(8) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `Role` enum('Ketua','Petugas','','') NOT NULL DEFAULT 'Petugas'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`AdminId`, `Username`, `Password`, `Role`) VALUES
(1, 'Compek', 'Compekiah', 'Ketua'),
(2, 'Ardi', 'Compek', 'Petugas'),
(7, 'joko', 'joko', 'Ketua'),
(8, 'romi', 'romi', 'Petugas');

-- --------------------------------------------------------

--
-- Table structure for table `anggota`
--

CREATE TABLE `anggota` (
  `anggota_id` int(8) NOT NULL,
  `AdminId` int(11) NOT NULL,
  `Nama` varchar(30) NOT NULL,
  `Alamat` varchar(50) NOT NULL,
  `Aktifitas_Sepeda` varchar(100) NOT NULL,
  `Angsuran` enum('3 Bulan','5 Bulan','1 taun','') NOT NULL,
  `Status_anggota` enum('Diterima','Diproses','Ditolak','non aktif') NOT NULL,
  `identitas` varchar(16) NOT NULL,
  `noHp` varchar(15) NOT NULL,
  `Tanggal` date NOT NULL,
  `peminjaman` enum('0','1','') NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `anggota`
--

INSERT INTO `anggota` (`anggota_id`, `AdminId`, `Nama`, `Alamat`, `Aktifitas_Sepeda`, `Angsuran`, `Status_anggota`, `identitas`, `noHp`, `Tanggal`, `peminjaman`) VALUES
(3, 8, 'lontong', 'situbondo', '2018', '1 taun', 'Diterima', '1323214', '23783', '2018-06-24', '0'),
(4, 8, 'jon', 'surabaya', '2018', '3 Bulan', 'Diterima', '3554345', '54354', '2018-06-24', '1'),
(5, 8, 'ros', 'situbondo', '2018', '5 Bulan', 'Diterima', '435435', '345435', '2018-06-24', '0'),
(6, 8, 'rendy', 'jember', '2018', '3 Bulan', 'Diterima', '4343242', '324342', '2018-06-24', '1'),
(8, 8, 'lontongngan', 'situbondo', '2018', '5 Bulan', 'Ditolak', '1323214', '23783', '2018-06-27', '0'),
(9, 8, 'mala', 'situbondo', '2018', '3 Bulan', 'Diterima', '4343', '45453', '2018-06-24', '1'),
(10, 8, 'jam', 'asembagus', '2018', '1 taun', 'Diterima', '34234', '345453', '2018-06-24', '1'),
(11, 8, 'meong', 'jember', '2018', '3 Bulan', 'Diterima', '34234', '234324', '2018-06-24', '1'),
(12, 8, 'jason', 'jakarta', '2018', '3 Bulan', 'Diterima', '34324', '3424324', '2018-06-24', '1'),
(13, 8, 'kipli', 'situbondo', '2014', '5 Bulan', 'Diterima', '54435', '4354534', '2018-06-24', '1'),
(14, 8, 'ajay', 'jember', '2019', '1 taun', 'Diterima', '32432523', '354353', '2018-06-24', '1'),
(15, 8, 'joko', 'asb', '2018', '5 Bulan', 'Diterima', '34332', '324234', '2018-06-24', '1'),
(16, 8, 'ardi', 'situbondo', '2017', '3 Bulan', 'Diterima', '234242', '8968968', '2018-06-24', '0');

-- --------------------------------------------------------

--
-- Table structure for table `calon_anggota`
--

CREATE TABLE `calon_anggota` (
  `calonAnggota_id` int(11) NOT NULL,
  `AdminId` int(11) NOT NULL,
  `Nama` varchar(50) NOT NULL,
  `Alamat` varchar(100) NOT NULL,
  `Tanggal` date NOT NULL,
  `identitas` varchar(16) NOT NULL,
  `noHp` varchar(15) NOT NULL,
  `Status_Canggota` enum('Diproses','Ditolak','Diterima','') NOT NULL,
  `peminjaman` enum('0','1','','') NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `calon_anggota`
--

INSERT INTO `calon_anggota` (`calonAnggota_id`, `AdminId`, `Nama`, `Alamat`, `Tanggal`, `identitas`, `noHp`, `Status_Canggota`, `peminjaman`) VALUES
(6, 8, 'leam', 'jember', '2018-06-14', '1321442', '0288232', 'Diproses', '1'),
(8, 8, 'lola', 'jember', '2018-06-15', '2131421', '31312312', 'Diproses', '1');

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

CREATE TABLE `pembayaran` (
  `pembayaran_id` int(11) NOT NULL,
  `PAnggota_id` int(11) NOT NULL,
  `PCanggota` int(11) NOT NULL,
  `tanggal_pembayaran` date NOT NULL,
  `jml_pembayaran` int(11) NOT NULL,
  `pembayaran_ke` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman_anggota`
--

CREATE TABLE `peminjaman_anggota` (
  `PAnggota_id` int(11) NOT NULL,
  `anggota_id` int(11) NOT NULL,
  `AdminId` int(11) NOT NULL,
  `status_peminjman` enum('diproses','diterima','ditolak','') NOT NULL,
  `jumlah_pinjaman` int(11) NOT NULL,
  `sisa_pengembalian` int(11) NOT NULL,
  `tanggal_peminjaman` date NOT NULL,
  `jangka` enum('3 bulan','12 bulan','','') NOT NULL,
  `catatan` text NOT NULL,
  `tipe` enum('anggota','','','') NOT NULL DEFAULT 'anggota',
  `tanggal_pembayaran` date DEFAULT NULL,
  `status_uang` enum('lunas','tidak lunas','','') NOT NULL DEFAULT 'tidak lunas',
  `bayar` int(11) NOT NULL,
  `denda` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `peminjaman_anggota`
--

INSERT INTO `peminjaman_anggota` (`PAnggota_id`, `anggota_id`, `AdminId`, `status_peminjman`, `jumlah_pinjaman`, `sisa_pengembalian`, `tanggal_peminjaman`, `jangka`, `catatan`, `tipe`, `tanggal_pembayaran`, `status_uang`, `bayar`, `denda`) VALUES
(11, 4, 8, 'ditolak', 70000, 70000, '2018-06-13', '3 bulan', 'minjmemmmmm', 'anggota', '2018-06-13', 'tidak lunas', 23333, 0),
(12, 9, 8, 'ditolak', 40000, 40000, '2018-06-04', '12 bulan', 'minjem cuy', 'anggota', '2018-06-04', 'tidak lunas', 3333, 0),
(14, 6, 8, 'diterima', 50000, 50000, '2018-06-06', '12 bulan', 'wkohdwildw', 'anggota', '2018-06-06', 'tidak lunas', 4166, 0),
(15, 12, 8, 'diterima', 50000, 50000, '2018-06-30', '12 bulan', 'belajar minjem uang', 'anggota', '2018-06-30', 'tidak lunas', 4166, 0),
(16, 13, 8, 'diterima', 300000, 275000, '2018-06-02', '12 bulan', '', 'anggota', '2018-07-29', 'tidak lunas', 25000, 3375),
(18, 15, 8, 'diproses', 300000, 300000, '2018-06-09', '3 bulan', 'minjem', 'anggota', '2018-06-09', 'tidak lunas', 100000, 0),
(19, 10, 8, 'diterima', 800000, 533334, '2018-06-16', '3 bulan', '', 'anggota', '2018-07-29', 'tidak lunas', 266666, 17333),
(20, 11, 8, 'diproses', 300000, 300000, '2018-06-02', '12 bulan', 'sldks', 'anggota', '2018-06-02', 'tidak lunas', 25000, 0);

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman_canggota`
--

CREATE TABLE `peminjaman_canggota` (
  `PCanggota` int(11) NOT NULL,
  `Canggota_id` int(11) NOT NULL,
  `AdminId` int(11) NOT NULL,
  `status_peminjman` enum('diproses','diterima','ditolak','') NOT NULL,
  `jumlah_pinjaman` int(11) NOT NULL,
  `sisa_pengembalian` int(11) NOT NULL,
  `tanggal_pinjaman` date NOT NULL,
  `jangka` enum('3 bulan','12 bulan','','') NOT NULL,
  `administrasi` enum('lunas','tidak lunas','','') NOT NULL,
  `jaminan` varchar(100) NOT NULL,
  `catatan` text NOT NULL,
  `tipe` enum('calon anggota','','','') NOT NULL DEFAULT 'calon anggota',
  `tanggal_pembayaran` date DEFAULT NULL,
  `status_uang` enum('lunas','tidak lunas','','') NOT NULL DEFAULT 'tidak lunas',
  `bayar` int(11) NOT NULL,
  `denda` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `peminjaman_canggota`
--

INSERT INTO `peminjaman_canggota` (`PCanggota`, `Canggota_id`, `AdminId`, `status_peminjman`, `jumlah_pinjaman`, `sisa_pengembalian`, `tanggal_pinjaman`, `jangka`, `administrasi`, `jaminan`, `catatan`, `tipe`, `tanggal_pembayaran`, `status_uang`, `bayar`, `denda`) VALUES
(8, 8, 8, 'diterima', 3000000, 2750000, '2018-06-16', '12 bulan', 'lunas', 'tumah', 'baru guys', 'calon anggota', '2018-07-22', 'tidak lunas', 250000, 7500),
(9, 6, 8, 'diterima', 80000, 53334, '2018-06-23', '3 bulan', 'lunas', 'ktp', '', 'calon anggota', '2018-08-02', 'tidak lunas', 26666, 1333);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`AdminId`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- Indexes for table `anggota`
--
ALTER TABLE `anggota`
  ADD PRIMARY KEY (`anggota_id`),
  ADD KEY `AdminId` (`AdminId`);

--
-- Indexes for table `calon_anggota`
--
ALTER TABLE `calon_anggota`
  ADD PRIMARY KEY (`calonAnggota_id`),
  ADD KEY `AdminId` (`AdminId`);

--
-- Indexes for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`pembayaran_id`),
  ADD UNIQUE KEY `PAnggota_id` (`PAnggota_id`),
  ADD UNIQUE KEY `PCanggota` (`PCanggota`);

--
-- Indexes for table `peminjaman_anggota`
--
ALTER TABLE `peminjaman_anggota`
  ADD PRIMARY KEY (`PAnggota_id`),
  ADD UNIQUE KEY `anggota_id` (`anggota_id`) USING BTREE,
  ADD KEY `peminjaman_anggota_ibfk_2` (`AdminId`);

--
-- Indexes for table `peminjaman_canggota`
--
ALTER TABLE `peminjaman_canggota`
  ADD PRIMARY KEY (`PCanggota`),
  ADD UNIQUE KEY `anggota_id` (`Canggota_id`),
  ADD KEY `AdminId` (`AdminId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `AdminId` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `anggota`
--
ALTER TABLE `anggota`
  MODIFY `anggota_id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `calon_anggota`
--
ALTER TABLE `calon_anggota`
  MODIFY `calonAnggota_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `pembayaran`
--
ALTER TABLE `pembayaran`
  MODIFY `pembayaran_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `peminjaman_anggota`
--
ALTER TABLE `peminjaman_anggota`
  MODIFY `PAnggota_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `peminjaman_canggota`
--
ALTER TABLE `peminjaman_canggota`
  MODIFY `PCanggota` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `anggota`
--
ALTER TABLE `anggota`
  ADD CONSTRAINT `anggota_ibfk_1` FOREIGN KEY (`AdminId`) REFERENCES `admin` (`AdminId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `calon_anggota`
--
ALTER TABLE `calon_anggota`
  ADD CONSTRAINT `calon_anggota_ibfk_1` FOREIGN KEY (`AdminId`) REFERENCES `admin` (`AdminId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD CONSTRAINT `pembayaran_ibfk_1` FOREIGN KEY (`PAnggota_id`) REFERENCES `peminjaman_anggota` (`PAnggota_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pembayaran_ibfk_2` FOREIGN KEY (`PCanggota`) REFERENCES `peminjaman_canggota` (`PCanggota`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `peminjaman_anggota`
--
ALTER TABLE `peminjaman_anggota`
  ADD CONSTRAINT `peminjaman_anggota_ibfk_1` FOREIGN KEY (`anggota_id`) REFERENCES `anggota` (`anggota_id`),
  ADD CONSTRAINT `peminjaman_anggota_ibfk_2` FOREIGN KEY (`AdminId`) REFERENCES `admin` (`AdminId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `peminjaman_canggota`
--
ALTER TABLE `peminjaman_canggota`
  ADD CONSTRAINT `peminjaman_canggota_ibfk_1` FOREIGN KEY (`Canggota_id`) REFERENCES `calon_anggota` (`calonAnggota_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `peminjaman_canggota_ibfk_2` FOREIGN KEY (`AdminId`) REFERENCES `admin` (`AdminId`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
