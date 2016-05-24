-- phpMyAdmin SQL Dump
-- version 4.3.8
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Apr 05, 2015 at 10:30 AM
-- Server version: 5.6.23
-- PHP Version: 5.6.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Table structure for table `block`
--

CREATE TABLE IF NOT EXISTS `block` (
  `pid`  INT(7)     NOT NULL,
  `blk`  VARCHAR(7) NOT NULL,
  `brcv` VARCHAR(7) DEFAULT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = 'Blocked Punts, Field Goal Attempts, etc.';

-- --------------------------------------------------------

--
-- Table structure for table `chart`
--

CREATE TABLE IF NOT EXISTS `chart` (
  `pid`  INT(7)     NOT NULL,
  `qba`  VARCHAR(5) DEFAULT NULL,
  `wo`   TINYINT(1) NOT NULL,
  `bf`   TINYINT(1) NOT NULL,
  `li`   TINYINT(1) NOT NULL,
  `brec` TINYINT(1) NOT NULL,
  `incd` VARCHAR(3) NOT NULL,
  `hur`  TINYINT(1) NOT NULL,
  `mbt`  TINYINT(1) NOT NULL,
  `yac`  INT(2)     NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = 'Data from our Play Charting Project';

-- --------------------------------------------------------

--
-- Table structure for table `conv`
--

CREATE TABLE IF NOT EXISTS `conv` (
  `pid`  INT(7)     NOT NULL,
  `type` VARCHAR(4) NOT NULL,
  `bc`   VARCHAR(7) DEFAULT NULL,
  `psr`  VARCHAR(7) DEFAULT NULL,
  `trg`  VARCHAR(7) DEFAULT NULL,
  `conv` TINYINT(1) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '2 Point Conversion Attempts (Y=Success, N=Fail)';

-- --------------------------------------------------------

--
-- Table structure for table `defense`
--

CREATE TABLE IF NOT EXISTS `defense` (
  `uid`    INT(6)        NOT NULL,
  `gid`    INT(5)        NOT NULL,
  `player` VARCHAR(7)    NOT NULL,
  `solo`   DECIMAL(3, 1) NOT NULL,
  `comb`   DECIMAL(3, 1) NOT NULL,
  `sck`    DECIMAL(2, 1) NOT NULL,
  `saf`    TINYINT(1)    NOT NULL,
  `blk`    TINYINT(1)    NOT NULL,
  `ints`   TINYINT(1)    NOT NULL,
  `pdef`   TINYINT(1)    NOT NULL,
  `frcv`   TINYINT(1)    NOT NULL,
  `forc`   TINYINT(1)    NOT NULL,
  `tdd`    TINYINT(1)    NOT NULL,
  `rety`   INT(3)        NOT NULL,
  `tdret`  TINYINT(1)    NOT NULL,
  `peny`   TINYINT(2)    NOT NULL,
  `snp`    TINYINT(2)    NOT NULL,
  `fp`     DECIMAL(4, 2) NOT NULL,
  `fp2`    DECIMAL(4, 2) NOT NULL,
  `game`   TINYINT(2)    NOT NULL,
  `seas`   TINYINT(2)    NOT NULL,
  `year`   INT(4)        NOT NULL,
  `team`   VARCHAR(3)    NOT NULL,
  `posd`   VARCHAR(8)    NOT NULL,
  `jnum`   TINYINT(2)    NOT NULL,
  `dcp`    TINYINT(1)    NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- Table structure for table `drive`
--

CREATE TABLE IF NOT EXISTS `drive` (
  `uid`   INT(6)     NOT NULL,
  `gid`   INT(7)     NOT NULL,
  `fpid`  INT(7)     NOT NULL,
  `tname` VARCHAR(3) NOT NULL,
  `drvn`  TINYINT(2) NOT NULL,
  `obt`   VARCHAR(4) DEFAULT NULL,
  `qtr`   TINYINT(1) NOT NULL,
  `min`   TINYINT(2) NOT NULL,
  `sec`   TINYINT(2) NOT NULL,
  `yfog`  TINYINT(2) NOT NULL,
  `plays` TINYINT(2) NOT NULL,
  `succ`  TINYINT(2) NOT NULL,
  `rfd`   TINYINT(2) NOT NULL,
  `pfd`   TINYINT(2) NOT NULL,
  `ofd`   TINYINT(2) NOT NULL,
  `ry`    INT(3)     NOT NULL,
  `ra`    TINYINT(2) NOT NULL,
  `py`    INT(3)     NOT NULL,
  `pa`    TINYINT(2) NOT NULL,
  `pc`    TINYINT(2) NOT NULL,
  `peyf`  TINYINT(2) NOT NULL,
  `peya`  TINYINT(2) NOT NULL,
  `net`   INT(3)     NOT NULL,
  `res`   VARCHAR(4) DEFAULT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- Table structure for table `fgxp`
--

CREATE TABLE IF NOT EXISTS `fgxp` (
  `pid`     INT(7)     NOT NULL,
  `fgxp`    VARCHAR(2) NOT NULL,
  `fkicker` VARCHAR(7) NOT NULL,
  `dist`    TINYINT(2) NOT NULL,
  `good`    TINYINT(1) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- Table structure for table `fumble`
--

CREATE TABLE IF NOT EXISTS `fumble` (
  `pid`  INT(7)     NOT NULL,
  `fum`  VARCHAR(7) NOT NULL,
  `frcv` VARCHAR(7) DEFAULT NULL,
  `fry`  INT(3)     NOT NULL,
  `forc` VARCHAR(7) DEFAULT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE IF NOT EXISTS `game` (
  `gid`  INT(5)        NOT NULL,
  `seas` INT(4)        NOT NULL,
  `wk`   TINYINT(2)    NOT NULL,
  `day`  VARCHAR(3)    NOT NULL,
  `v`    VARCHAR(3)    NOT NULL,
  `h`    VARCHAR(3)    NOT NULL,
  `stad` VARCHAR(45)   NOT NULL,
  `temp` VARCHAR(4)  DEFAULT NULL,
  `humd` VARCHAR(4)  DEFAULT NULL,
  `wspd` VARCHAR(4)  DEFAULT NULL,
  `wdir` VARCHAR(4)  DEFAULT NULL,
  `cond` VARCHAR(15) DEFAULT NULL,
  `surf` VARCHAR(30)   NOT NULL,
  `ou`   DECIMAL(3, 1) NOT NULL,
  `sprv` DECIMAL(3, 1) NOT NULL,
  `ptsv` TINYINT(2)    NOT NULL,
  `ptsh` TINYINT(2)    NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- Table structure for table `intercpt`
--

CREATE TABLE IF NOT EXISTS `intercpt` (
  `pid`  INT(7)     NOT NULL,
  `psr`  VARCHAR(7) NOT NULL,
  `ints` VARCHAR(7) NOT NULL,
  `iry`  TINYINT(3) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = 'Interceptions';

-- --------------------------------------------------------

--
-- Table structure for table `kicker`
--

CREATE TABLE IF NOT EXISTS `kicker` (
  `uid`    INT(6)        NOT NULL,
  `gid`    INT(5)        NOT NULL,
  `player` VARCHAR(7)    NOT NULL,
  `pat`    TINYINT(1)    NOT NULL,
  `fgs`    TINYINT(1)    NOT NULL,
  `fgm`    TINYINT(1)    NOT NULL,
  `fgl`    TINYINT(1)    NOT NULL,
  `fp`     DECIMAL(3, 1) NOT NULL,
  `game`   TINYINT(2)    NOT NULL,
  `seas`   TINYINT(2)    NOT NULL,
  `year`   INT(4)        NOT NULL,
  `team`   VARCHAR(3)    NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = 'FGS: 0 - 39 yds; FGM: 40 - 49 yds; FGL: 50+ yds';

-- --------------------------------------------------------

--
-- Table structure for table `koff`
--

CREATE TABLE IF NOT EXISTS `koff` (
  `pid`    INT(7)     NOT NULL,
  `kicker` VARCHAR(7) NOT NULL,
  `kgro`   TINYINT(2) NOT NULL,
  `knet`   TINYINT(2) NOT NULL,
  `ktb`    TINYINT(1) NOT NULL,
  `kr`     VARCHAR(7) DEFAULT NULL,
  `kry`    TINYINT(3) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- Table structure for table `offense`
--

CREATE TABLE IF NOT EXISTS `offense` (
  `uid`    INT(6)        NOT NULL,
  `gid`    INT(5)        NOT NULL,
  `player` VARCHAR(7)    NOT NULL,
  `pa`     TINYINT(2)    NOT NULL,
  `pc`     TINYINT(2)    NOT NULL,
  `py`     INT(3)        NOT NULL,
  `ints`   TINYINT(1)    NOT NULL,
  `tdp`    TINYINT(1)    NOT NULL,
  `ra`     TINYINT(2)    NOT NULL,
  `sra`    TINYINT(2)    NOT NULL,
  `ry`     INT(3)        NOT NULL,
  `tdr`    TINYINT(1)    NOT NULL,
  `trg`    TINYINT(2)    NOT NULL,
  `rec`    TINYINT(2)    NOT NULL,
  `recy`   INT(3)        NOT NULL,
  `tdrec`  TINYINT(1)    NOT NULL,
  `ret`    TINYINT(2)    NOT NULL,
  `rety`   INT(3)        NOT NULL,
  `tdret`  TINYINT(1)    NOT NULL,
  `fuml`   TINYINT(1)    NOT NULL,
  `peny`   TINYINT(2)    NOT NULL,
  `conv`   TINYINT(1)    NOT NULL,
  `snp`    TINYINT(2)    NOT NULL,
  `fp`     DECIMAL(4, 2) NOT NULL,
  `fp2`    DECIMAL(4, 2) NOT NULL,
  `fp3`    DECIMAL(4, 2) NOT NULL,
  `game`   TINYINT(2)    NOT NULL,
  `seas`   TINYINT(2)    NOT NULL,
  `year`   INT(4)        NOT NULL,
  `team`   VARCHAR(3)    NOT NULL,
  `posd`   VARCHAR(8)    NOT NULL,
  `jnum`   TINYINT(2)    NOT NULL,
  `dcp`    TINYINT(1)    NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- Table structure for table `oline`
--

CREATE TABLE IF NOT EXISTS `oline` (
  `olid` INT(5)     NOT NULL,
  `lt`   VARCHAR(7) NOT NULL,
  `lg`   VARCHAR(7) NOT NULL,
  `c`    VARCHAR(7) NOT NULL,
  `rg`   VARCHAR(7) NOT NULL,
  `rt`   VARCHAR(7) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- Table structure for table `pass`
--

CREATE TABLE IF NOT EXISTS `pass` (
  `pid`  INT(7)     NOT NULL,
  `psr`  VARCHAR(7) NOT NULL,
  `trg`  VARCHAR(7) DEFAULT NULL,
  `loc`  VARCHAR(2) NOT NULL,
  `yds`  TINYINT(3) NOT NULL,
  `comp` TINYINT(1) NOT NULL,
  `succ` TINYINT(1) NOT NULL,
  `spk`  TINYINT(1) NOT NULL,
  `dfb`  VARCHAR(7) DEFAULT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `penalty`
--

CREATE TABLE IF NOT EXISTS `penalty` (
  `uid`  INT(6)      NOT NULL,
  `pid`  INT(7)      NOT NULL,
  `ptm`  VARCHAR(3)  NOT NULL,
  `pen`  VARCHAR(7) DEFAULT NULL,
  `desc` VARCHAR(40) NOT NULL,
  `cat`  TINYINT(1)  NOT NULL,
  `pey`  TINYINT(2)  NOT NULL,
  `act`  VARCHAR(1)  NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- Table structure for table `play`
--

CREATE TABLE IF NOT EXISTS `play` (
  `gid`  INT(5)     NOT NULL,
  `pid`  INT(7)     NOT NULL,
  `off`  VARCHAR(3) NOT NULL,
  `def`  VARCHAR(3) NOT NULL,
  `type` VARCHAR(4) NOT NULL,
  `dseq` TINYINT(2) NOT NULL,
  `len`  TINYINT(2) NOT NULL,
  `qtr`  TINYINT(1) NOT NULL,
  `min`  TINYINT(2) NOT NULL,
  `sec`  TINYINT(2) NOT NULL,
  `ptso` TINYINT(2) NOT NULL,
  `ptsd` TINYINT(2) NOT NULL,
  `timo` TINYINT(2) NOT NULL,
  `timd` TINYINT(2) NOT NULL,
  `dwn`  TINYINT(1) NOT NULL,
  `ytg`  TINYINT(2) NOT NULL,
  `yfog` TINYINT(2) NOT NULL,
  `zone` TINYINT(1) NOT NULL,
  `fd`   TINYINT(1) NOT NULL,
  `sg`   TINYINT(1) NOT NULL,
  `nh`   TINYINT(1) NOT NULL,
  `pts`  TINYINT(1) NOT NULL,
  `tck`  TINYINT(1) NOT NULL,
  `sk`   TINYINT(1) NOT NULL,
  `pen`  TINYINT(1) NOT NULL,
  `ints` TINYINT(1) NOT NULL,
  `fum`  TINYINT(1) NOT NULL,
  `saf`  TINYINT(1) NOT NULL,
  `blk`  TINYINT(1) NOT NULL,
  `olid` INT(5)     NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- Table structure for table `player`
--

CREATE TABLE IF NOT EXISTS `player` (
  `player`   VARCHAR(7)    NOT NULL,
  `fname`    VARCHAR(20)   NOT NULL,
  `lname`    VARCHAR(25)   NOT NULL,
  `pname`    VARCHAR(25)   NOT NULL,
  `pos1`     VARCHAR(2)    NOT NULL,
  `pos2`     VARCHAR(2)  DEFAULT NULL,
  `height`   TINYINT(2)    NOT NULL,
  `weight`   INT(3)        NOT NULL,
  `yob`      INT(4)        NOT NULL,
  `forty`    DECIMAL(3, 2) NOT NULL,
  `bench`    TINYINT(2)    NOT NULL,
  `vertical` DECIMAL(3, 1) NOT NULL,
  `broad`    INT(3)        NOT NULL,
  `shuttle`  DECIMAL(3, 2) NOT NULL,
  `cone`     DECIMAL(3, 2) NOT NULL,
  `dpos`     INT(3)        NOT NULL,
  `col`      VARCHAR(35)   NOT NULL,
  `dv`       VARCHAR(35) DEFAULT NULL,
  `start`    INT(4)        NOT NULL,
  `cteam`    VARCHAR(3)    NOT NULL,
  `posd`     VARCHAR(8)    NOT NULL,
  `jnum`     TINYINT(2)    NOT NULL,
  `dcp`      TINYINT(1)    NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- Table structure for table `punt`
--

CREATE TABLE IF NOT EXISTS `punt` (
  `pid`    INT(7)     NOT NULL,
  `punter` VARCHAR(7) NOT NULL,
  `pgro`   TINYINT(2) NOT NULL,
  `pnet`   TINYINT(2) NOT NULL,
  `ptb`    TINYINT(1) NOT NULL,
  `pr`     VARCHAR(7) DEFAULT NULL,
  `pry`    TINYINT(3) NOT NULL,
  `pfc`    TINYINT(1) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- Table structure for table `redzone`
--

CREATE TABLE IF NOT EXISTS `redzone` (
  `uid`    INT(6)     NOT NULL,
  `gid`    INT(5)     NOT NULL,
  `player` VARCHAR(7) NOT NULL,
  `pa`     TINYINT(2) NOT NULL,
  `pc`     TINYINT(2) NOT NULL,
  `py`     INT(3)     NOT NULL,
  `ints`   TINYINT(1) NOT NULL,
  `ra`     TINYINT(2) NOT NULL,
  `sra`    TINYINT(2) NOT NULL,
  `ry`     INT(3)     NOT NULL,
  `trg`    TINYINT(2) NOT NULL,
  `rec`    TINYINT(2) NOT NULL,
  `recy`   INT(3)     NOT NULL,
  `fuml`   TINYINT(1) NOT NULL,
  `peny`   TINYINT(2) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- Table structure for table `rush`
--

CREATE TABLE IF NOT EXISTS `rush` (
  `pid`  INT(7)     NOT NULL,
  `bc`   VARCHAR(7) NOT NULL,
  `dir`  VARCHAR(2) NOT NULL,
  `yds`  TINYINT(3) NOT NULL,
  `succ` TINYINT(1) NOT NULL,
  `kne`  TINYINT(1) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- Table structure for table `sack`
--

CREATE TABLE IF NOT EXISTS `sack` (
  `uid`   INT(6)        NOT NULL,
  `pid`   INT(7)        NOT NULL,
  `qb`    VARCHAR(7)    NOT NULL,
  `sk`    VARCHAR(7)    NOT NULL,
  `value` DECIMAL(2, 1) NOT NULL,
  `ydsl`  INT(3)        NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- Table structure for table `safety`
--

CREATE TABLE IF NOT EXISTS `safety` (
  `pid` INT(7)     NOT NULL,
  `saf` VARCHAR(7) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tackle`
--

CREATE TABLE IF NOT EXISTS `tackle` (
  `uid`   INT(7)        NOT NULL,
  `pid`   INT(7)        NOT NULL,
  `tck`   VARCHAR(7)    NOT NULL,
  `value` DECIMAL(2, 1) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = 'Special teams tackles are not counted (ie, kickoffs, punts)';

-- --------------------------------------------------------

--
-- Table structure for table `td`
--

CREATE TABLE IF NOT EXISTS `td` (
  `pid`    INT(7)     NOT NULL,
  `qtr`    TINYINT(1) NOT NULL,
  `min`    TINYINT(2) NOT NULL,
  `sec`    TINYINT(2) NOT NULL,
  `dwn`    TINYINT(1) NOT NULL,
  `yds`    TINYINT(3) NOT NULL,
  `pts`    TINYINT(2) NOT NULL,
  `player` VARCHAR(7) DEFAULT NULL,
  `type`   VARCHAR(4) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- Table structure for table `team`
--

CREATE TABLE IF NOT EXISTS `team` (
  `tid`   INT(5)        NOT NULL,
  `gid`   INT(5)        NOT NULL,
  `tname` VARCHAR(3)    NOT NULL,
  `pts`   TINYINT(2)    NOT NULL,
  `q1p`   TINYINT(2)    NOT NULL,
  `q2p`   TINYINT(2)    NOT NULL,
  `q3p`   TINYINT(2)    NOT NULL,
  `q4p`   TINYINT(2)    NOT NULL,
  `rfd`   TINYINT(2)    NOT NULL,
  `pfd`   TINYINT(2)    NOT NULL,
  `ifd`   TINYINT(2)    NOT NULL,
  `ry`    INT(3)        NOT NULL,
  `ra`    TINYINT(2)    NOT NULL,
  `py`    INT(3)        NOT NULL,
  `pa`    TINYINT(2)    NOT NULL,
  `pc`    TINYINT(2)    NOT NULL,
  `sk`    TINYINT(2)    NOT NULL,
  `ints`  TINYINT(1)    NOT NULL,
  `fum`   TINYINT(1)    NOT NULL,
  `pu`    TINYINT(2)    NOT NULL,
  `gpy`   INT(3)        NOT NULL,
  `pr`    TINYINT(2)    NOT NULL,
  `pry`   INT(3)        NOT NULL,
  `kr`    TINYINT(2)    NOT NULL,
  `kry`   INT(3)        NOT NULL,
  `ir`    TINYINT(1)    NOT NULL,
  `iry`   INT(3)        NOT NULL,
  `pen`   INT(3)        NOT NULL,
  `top`   DECIMAL(3, 1) NOT NULL,
  `td`    TINYINT(1)    NOT NULL,
  `tdr`   TINYINT(1)    NOT NULL,
  `tdp`   TINYINT(1)    NOT NULL,
  `tdt`   TINYINT(1)    NOT NULL,
  `fgm`   TINYINT(1)    NOT NULL,
  `fgat`  TINYINT(2)    NOT NULL,
  `fgy`   INT(3)        NOT NULL,
  `rza`   TINYINT(2)    NOT NULL,
  `rzc`   TINYINT(1)    NOT NULL,
  `bry`   INT(3)        NOT NULL,
  `bpy`   INT(3)        NOT NULL,
  `srp`   TINYINT(2)    NOT NULL,
  `s1rp`  TINYINT(2)    NOT NULL,
  `s2rp`  TINYINT(2)    NOT NULL,
  `s3rp`  TINYINT(2)    NOT NULL,
  `spp`   TINYINT(2)    NOT NULL,
  `s1pp`  TINYINT(2)    NOT NULL,
  `s2pp`  TINYINT(2)    NOT NULL,
  `s3pp`  TINYINT(2)    NOT NULL,
  `lea`   TINYINT(2)    NOT NULL,
  `ley`   INT(3)        NOT NULL,
  `lta`   TINYINT(2)    NOT NULL,
  `lty`   INT(3)        NOT NULL,
  `lga`   TINYINT(2)    NOT NULL,
  `lgy`   INT(3)        NOT NULL,
  `mda`   TINYINT(2)    NOT NULL,
  `mdy`   INT(3)        NOT NULL,
  `rga`   TINYINT(2)    NOT NULL,
  `rgy`   INT(3)        NOT NULL,
  `rta`   TINYINT(2)    NOT NULL,
  `rty`   INT(3)        NOT NULL,
  `rea`   TINYINT(2)    NOT NULL,
  `rey`   INT(3)        NOT NULL,
  `r1a`   TINYINT(2)    NOT NULL,
  `r1y`   INT(3)        NOT NULL,
  `r2a`   TINYINT(2)    NOT NULL,
  `r2y`   INT(3)        NOT NULL,
  `r3a`   TINYINT(2)    NOT NULL,
  `r3y`   INT(3)        NOT NULL,
  `qba`   TINYINT(2)    NOT NULL,
  `qby`   INT(3)        NOT NULL,
  `sla`   TINYINT(2)    NOT NULL,
  `sly`   INT(3)        NOT NULL,
  `sma`   TINYINT(2)    NOT NULL,
  `smy`   INT(3)        NOT NULL,
  `sra`   TINYINT(2)    NOT NULL,
  `sry`   INT(3)        NOT NULL,
  `dla`   TINYINT(2)    NOT NULL,
  `dly`   INT(3)        NOT NULL,
  `dma`   TINYINT(2)    NOT NULL,
  `dmy`   INT(3)        NOT NULL,
  `dra`   TINYINT(2)    NOT NULL,
  `dry`   INT(3)        NOT NULL,
  `wr1a`  TINYINT(2)    NOT NULL,
  `wr1y`  INT(3)        NOT NULL,
  `wr3a`  TINYINT(2)    NOT NULL,
  `wr3y`  INT(3)        NOT NULL,
  `tea`   TINYINT(2)    NOT NULL,
  `tey`   INT(3)        NOT NULL,
  `rba`   TINYINT(2)    NOT NULL,
  `rby`   INT(3)        NOT NULL,
  `sga`   TINYINT(2)    NOT NULL,
  `sgy`   INT(3)        NOT NULL,
  `p1a`   TINYINT(2)    NOT NULL,
  `p1y`   INT(3)        NOT NULL,
  `p2a`   TINYINT(2)    NOT NULL,
  `p2y`   INT(3)        NOT NULL,
  `p3a`   TINYINT(2)    NOT NULL,
  `p3y`   INT(3)        NOT NULL,
  `spc`   TINYINT(2)    NOT NULL,
  `mpc`   TINYINT(2)    NOT NULL,
  `lpc`   TINYINT(2)    NOT NULL,
  `q1ra`  TINYINT(2)    NOT NULL,
  `q1ry`  INT(3)        NOT NULL,
  `q1pa`  TINYINT(2)    NOT NULL,
  `q1py`  INT(3)        NOT NULL,
  `lcra`  TINYINT(2)    NOT NULL,
  `lcry`  INT(3)        NOT NULL,
  `lcpa`  TINYINT(2)    NOT NULL,
  `lcpy`  INT(3)        NOT NULL,
  `rzra`  TINYINT(2)    NOT NULL,
  `rzry`  INT(3)        NOT NULL,
  `rzpa`  TINYINT(2)    NOT NULL,
  `rzpy`  INT(3)        NOT NULL,
  `sky`   INT(3)        NOT NULL,
  `lbs`   DECIMAL(3, 1) NOT NULL,
  `dbs`   DECIMAL(3, 1) NOT NULL,
  `sfpy`  INT(3)        NOT NULL,
  `drv`   TINYINT(2)    NOT NULL,
  `npy`   INT(3)        NOT NULL,
  `tb`    TINYINT(1)    NOT NULL,
  `i20`   TINYINT(1)    NOT NULL,
  `rtd`   TINYINT(1)    NOT NULL,
  `lnr`   DECIMAL(3, 1) NOT NULL,
  `lnp`   DECIMAL(3, 1) NOT NULL,
  `lbr`   DECIMAL(3, 1) NOT NULL,
  `lbp`   DECIMAL(3, 1) NOT NULL,
  `dbr`   DECIMAL(3, 1) NOT NULL,
  `dbp`   DECIMAL(3, 1) NOT NULL,
  `nha`   TINYINT(2)    NOT NULL,
  `s3a`   TINYINT(2)    NOT NULL,
  `s3c`   TINYINT(2)    NOT NULL,
  `l3a`   TINYINT(2)    NOT NULL,
  `l3c`   TINYINT(2)    NOT NULL,
  `stf`   TINYINT(2)    NOT NULL,
  `dp`    TINYINT(2)    NOT NULL,
  `fsp`   TINYINT(2)    NOT NULL,
  `ohp`   TINYINT(2)    NOT NULL,
  `pbep`  TINYINT(1)    NOT NULL,
  `dlp`   TINYINT(1)    NOT NULL,
  `dsp`   TINYINT(1)    NOT NULL,
  `dum`   TINYINT(1)    NOT NULL,
  `pfn`   TINYINT(1)    NOT NULL,
  `snpo`  TINYINT(2)    NOT NULL,
  `snpd`  TINYINT(2)    NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `block`
--
ALTER TABLE `block`
ADD UNIQUE KEY `pid` (`pid`);

--
-- Indexes for table `chart`
--
ALTER TABLE `chart`
ADD UNIQUE KEY `pid` (`pid`);

--
-- Indexes for table `conv`
--
ALTER TABLE `conv`
ADD UNIQUE KEY `pid` (`pid`);

--
-- Indexes for table `defense`
--
ALTER TABLE `defense`
ADD UNIQUE KEY `uid` (`uid`), ADD KEY `gid` (`gid`), ADD KEY `player` (`player`);

--
-- Indexes for table `drive`
--
ALTER TABLE `drive`
ADD UNIQUE KEY `uid` (`uid`), ADD KEY `gid` (`gid`), ADD KEY `fpid` (`fpid`), ADD KEY `tname` (`tname`);

--
-- Indexes for table `fgxp`
--
ALTER TABLE `fgxp`
ADD UNIQUE KEY `pid` (`pid`), ADD KEY `fgxp` (`fgxp`);

--
-- Indexes for table `fumble`
--
ALTER TABLE `fumble`
ADD UNIQUE KEY `pid` (`pid`), ADD KEY `fum` (`fum`);

--
-- Indexes for table `game`
--
ALTER TABLE `game`
ADD UNIQUE KEY `gid` (`gid`), ADD KEY `seas` (`seas`);

--
-- Indexes for table `intercpt`
--
ALTER TABLE `intercpt`
ADD UNIQUE KEY `pid` (`pid`), ADD KEY `psr` (`psr`), ADD KEY `ints` (`ints`);

--
-- Indexes for table `kicker`
--
ALTER TABLE `kicker`
ADD UNIQUE KEY `uid` (`uid`), ADD KEY `gid` (`gid`), ADD KEY `player` (`player`);

--
-- Indexes for table `koff`
--
ALTER TABLE `koff`
ADD UNIQUE KEY `pid` (`pid`), ADD KEY `kicker` (`kicker`);

--
-- Indexes for table `offense`
--
ALTER TABLE `offense`
ADD UNIQUE KEY `uid` (`uid`), ADD KEY `gid` (`gid`), ADD KEY `player` (`player`);

--
-- Indexes for table `oline`
--
ALTER TABLE `oline`
ADD UNIQUE KEY `olid` (`olid`);

--
-- Indexes for table `pass`
--
ALTER TABLE `pass`
ADD UNIQUE KEY `pid` (`pid`), ADD KEY `psr` (`psr`), ADD KEY `trg` (`trg`);

--
-- Indexes for table `penalty`
--
ALTER TABLE `penalty`
ADD UNIQUE KEY `uid` (`uid`), ADD KEY `pid` (`pid`);

--
-- Indexes for table `play`
--
ALTER TABLE `play`
ADD UNIQUE KEY `pid` (`pid`), ADD KEY `gid` (`gid`);

--
-- Indexes for table `player`
--
ALTER TABLE `player`
ADD UNIQUE KEY `player` (`player`), ADD KEY `fname` (`fname`), ADD KEY `lname` (`lname`);

--
-- Indexes for table `punt`
--
ALTER TABLE `punt`
ADD UNIQUE KEY `pid` (`pid`), ADD KEY `punter` (`punter`);

--
-- Indexes for table `redzone`
--
ALTER TABLE `redzone`
ADD UNIQUE KEY `uid` (`uid`), ADD KEY `gid` (`gid`), ADD KEY `player` (`player`);

--
-- Indexes for table `rush`
--
ALTER TABLE `rush`
ADD UNIQUE KEY `pid` (`pid`), ADD KEY `bc` (`bc`);

--
-- Indexes for table `sack`
--
ALTER TABLE `sack`
ADD UNIQUE KEY `uid` (`uid`), ADD KEY `pid` (`pid`), ADD KEY `qb` (`qb`), ADD KEY `sk` (`sk`);

--
-- Indexes for table `safety`
--
ALTER TABLE `safety`
ADD UNIQUE KEY `pid` (`pid`), ADD KEY `saf` (`saf`);

--
-- Indexes for table `tackle`
--
ALTER TABLE `tackle`
ADD UNIQUE KEY `uid` (`uid`), ADD KEY `pid` (`pid`), ADD KEY `tck` (`tck`);

--
-- Indexes for table `team`
--
ALTER TABLE `team`
ADD UNIQUE KEY `tid` (`tid`), ADD KEY `gid` (`gid`), ADD KEY `tname` (`tname`);

/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;

--
-- Indexes for table `play`
--
ALTER TABLE `td`
ADD UNIQUE KEY `pid` (`pid`), ADD KEY `player` (`player`);