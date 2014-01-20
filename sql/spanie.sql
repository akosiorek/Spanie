-- MySQL dump 10.13  Distrib 5.5.34, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: HOTELE
-- ------------------------------------------------------
-- Server version	5.5.34-0ubuntu0.12.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `DLUZNICY`
--

DROP TABLE IF EXISTS `DLUZNICY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DLUZNICY` (
  `nr_dokumentu_klienta` varchar(20) NOT NULL,
  `login_uzytkownika` varchar(10) NOT NULL,
  `id_Hotelu` char(3) NOT NULL,
  `opis` varchar(50) NOT NULL,
  PRIMARY KEY (`nr_dokumentu_klienta`),
  KEY `id_Hotelu` (`id_Hotelu`),
  KEY `login_uzytkownika` (`login_uzytkownika`),
  CONSTRAINT `FK_Dluznicy_Hotel` FOREIGN KEY (`id_Hotelu`) REFERENCES `Hotel` (`id_Hotel`),
  CONSTRAINT `FK_Dluznicy_Klient` FOREIGN KEY (`nr_dokumentu_klienta`) REFERENCES `Klient` (`nr_dokumentu_klienta`),
  CONSTRAINT `FK_Dluznicy_UZYTKOWNIK` FOREIGN KEY (`login_uzytkownika`) REFERENCES `UZYTKOWNIK` (`login_uzytkownik`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DLUZNICY`
--

LOCK TABLES `DLUZNICY` WRITE;
/*!40000 ALTER TABLE `DLUZNICY` DISABLE KEYS */;
INSERT INTO `DLUZNICY` VALUES ('30921919ABC','s.swierk','WAS','Klient nie odwalal rezerwacji, nie zrealizowal pob');
/*!40000 ALTER TABLE `DLUZNICY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HISTORIA_POBYTOW`
--

DROP TABLE IF EXISTS `HISTORIA_POBYTOW`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HISTORIA_POBYTOW` (
  `nr_pobytu` int(11) NOT NULL,
  `nr_dokumentu_klienta` varchar(20) DEFAULT NULL,
  `nr_pokoju` int(11) NOT NULL,
  `data_rozpoczecia` date NOT NULL,
  `data_zakonczenia` date NOT NULL,
  `cena_pokoju` float NOT NULL,
  KEY `nr_dokumentu_klienta` (`nr_dokumentu_klienta`),
  KEY `nr_pobytu` (`nr_pobytu`),
  KEY `nr_pokoju` (`nr_pokoju`),
  CONSTRAINT `FK_HISTORIA_POBYTOW_Klient` FOREIGN KEY (`nr_dokumentu_klienta`) REFERENCES `Klient` (`nr_dokumentu_klienta`),
  CONSTRAINT `FK_HISTORIA_POBYTOW_POKOJE` FOREIGN KEY (`nr_pokoju`) REFERENCES `POKOJE` (`nr_pokoju`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HISTORIA_POBYTOW`
--

LOCK TABLES `HISTORIA_POBYTOW` WRITE;
/*!40000 ALTER TABLE `HISTORIA_POBYTOW` DISABLE KEYS */;
INSERT INTO `HISTORIA_POBYTOW` VALUES (11,'PL2020_o003',105,'2013-02-08','2013-04-05',150),(102,'90042000432',111,'2013-02-08','2013-04-05',150),(10,'30921919ABC',102,'2013-02-05','2013-04-05',120),(1,'30921919ABC',102,'2013-02-08','2013-04-05',120);
/*!40000 ALTER TABLE `HISTORIA_POBYTOW` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HISTORIA_REZERWACJI`
--

DROP TABLE IF EXISTS `HISTORIA_REZERWACJI`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HISTORIA_REZERWACJI` (
  `nr_rezerwacji` int(11) NOT NULL,
  `nr_dokumentu_klienta` varchar(20) NOT NULL,
  `data_rozpoczecia` date NOT NULL,
  `data_zakonczenia` date DEFAULT NULL,
  KEY `nr_dokumentu_klienta` (`nr_dokumentu_klienta`),
  KEY `nr_rezerwacji` (`nr_rezerwacji`),
  CONSTRAINT `FK_HISTORIA_REZERWACJI_Klient` FOREIGN KEY (`nr_dokumentu_klienta`) REFERENCES `Klient` (`nr_dokumentu_klienta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HISTORIA_REZERWACJI`
--

LOCK TABLES `HISTORIA_REZERWACJI` WRITE;
/*!40000 ALTER TABLE `HISTORIA_REZERWACJI` DISABLE KEYS */;
INSERT INTO `HISTORIA_REZERWACJI` VALUES (10,'30921919ABC','2013-02-09','2013-04-10');
/*!40000 ALTER TABLE `HISTORIA_REZERWACJI` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Hotel`
--

DROP TABLE IF EXISTS `Hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hotel` (
  `id_Hotel` char(3) NOT NULL,
  `il_pieter` int(11) NOT NULL,
  `il_pokoi_na_pietrze` int(11) NOT NULL,
  `super_user_login` varchar(10) NOT NULL,
  `super_user_haslo` varchar(10) NOT NULL,
  PRIMARY KEY (`id_Hotel`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Hotel`
--

LOCK TABLES `Hotel` WRITE;
/*!40000 ALTER TABLE `Hotel` DISABLE KEYS */;
INSERT INTO `Hotel` VALUES ('WAS',4,10,'super','sasasa');
/*!40000 ALTER TABLE `Hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Klient`
--

DROP TABLE IF EXISTS `Klient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Klient` (
  `nr_dokumentu_klienta` varchar(20) NOT NULL,
  `rodzaj_dokumentu` varchar(30) CHARACTER SET utf8 NOT NULL,
  `nazwisko` varchar(20) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`nr_dokumentu_klienta`),
  KEY `rodzaj_dokumentu` (`rodzaj_dokumentu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Klient`
--

LOCK TABLES `Klient` WRITE;
/*!40000 ALTER TABLE `Klient` DISABLE KEYS */;
INSERT INTO `Klient` VALUES ('20918jd34','paszport','Nowicki'),('30921919ABC','dowod_osobisty','Szymczak'),('7485739ghu','prawo_jazdy','Zieba'),('90042000432','paszport','Koziolek'),('fDFE239949','paszport','Daniluk'),('jdkslk900','paszport','Young'),('PL2020_o003','prawo_jazdy','Filipek'),('PL303_k40040','prawo_jazdy','Glowacki');
/*!40000 ALTER TABLE `Klient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POBYT`
--

DROP TABLE IF EXISTS `POBYT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `POBYT` (
  `nr_pobytu` int(11) NOT NULL AUTO_INCREMENT,
  `nr_dokumentu_klienta` varchar(20) NOT NULL,
  `data_rozpoczecia` date NOT NULL,
  `data_zakonczenia` date NOT NULL,
  `rabat` int(11) DEFAULT NULL,
  PRIMARY KEY (`nr_pobytu`),
  KEY `nr_dokumentu_klienta` (`nr_dokumentu_klienta`),
  CONSTRAINT `FK_POBYT_Klient` FOREIGN KEY (`nr_dokumentu_klienta`) REFERENCES `Klient` (`nr_dokumentu_klienta`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POBYT`
--

LOCK TABLES `POBYT` WRITE;
/*!40000 ALTER TABLE `POBYT` DISABLE KEYS */;
INSERT INTO `POBYT` VALUES (1,'90042000432','2014-01-16','2014-01-31',0),(2,'30921919ABC','2014-01-18','2014-02-01',0),(3,'PL2020_o003','2014-01-18','2014-01-22',0),(4,'jdkslk900','2014-01-19','2014-01-22',0),(5,'7485739ghu','2014-01-20','2014-03-03',0),(6,'PL303_k40040','2014-01-20','2014-03-03',0);
/*!40000 ALTER TABLE `POBYT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POBYT_POKOJE`
--

DROP TABLE IF EXISTS `POBYT_POKOJE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `POBYT_POKOJE` (
  `nr_pobytu` int(11) NOT NULL,
  `nr_pokoju` int(11) NOT NULL,
  `cena_pokoju` int(11) NOT NULL,
  KEY `nr_pobytu` (`nr_pobytu`),
  KEY `nr_pokoju` (`nr_pokoju`),
  CONSTRAINT `FK_POBYT_POKOJE_POBYT` FOREIGN KEY (`nr_pobytu`) REFERENCES `POBYT` (`nr_pobytu`),
  CONSTRAINT `FK_POBYT_POKOJE_POKOJE` FOREIGN KEY (`nr_pokoju`) REFERENCES `POKOJE` (`nr_pokoju`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POBYT_POKOJE`
--

LOCK TABLES `POBYT_POKOJE` WRITE;
/*!40000 ALTER TABLE `POBYT_POKOJE` DISABLE KEYS */;
INSERT INTO `POBYT_POKOJE` VALUES (1,104,120),(1,105,120),(2,109,100),(3,106,120),(4,111,140),(5,119,140),(5,120,135),(6,118,100);
/*!40000 ALTER TABLE `POBYT_POKOJE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POBYT_USLUGA_DODATKOWA`
--

DROP TABLE IF EXISTS `POBYT_USLUGA_DODATKOWA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `POBYT_USLUGA_DODATKOWA` (
  `nr_pobytu` int(11) NOT NULL,
  `nazwa_uslugi` varchar(10) NOT NULL,
  `data_rozpoczecia` date NOT NULL,
  `data_zakonczenia` date NOT NULL,
  `cena` float DEFAULT NULL,
  KEY `nr_pobytu` (`nr_pobytu`),
  KEY `nazwa_uslugi` (`nazwa_uslugi`),
  CONSTRAINT `FK_POBYT_USLUGA_DODATKOWA_POBYT` FOREIGN KEY (`nr_pobytu`) REFERENCES `POBYT` (`nr_pobytu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POBYT_USLUGA_DODATKOWA`
--

LOCK TABLES `POBYT_USLUGA_DODATKOWA` WRITE;
/*!40000 ALTER TABLE `POBYT_USLUGA_DODATKOWA` DISABLE KEYS */;
INSERT INTO `POBYT_USLUGA_DODATKOWA` VALUES (1,'obiad','2014-01-16','2014-01-31',20),(1,'obiad','2014-01-16','2014-01-31',20),(1,'obiad','2014-01-16','2014-01-31',20),(1,'obiad','2014-01-16','2014-01-31',20),(1,'sniadanie','2014-01-16','2014-01-31',20),(1,'sniadanie','2014-01-16','2014-01-31',20),(1,'sniadanie','2014-01-16','2014-01-31',20),(1,'sniadanie','2014-01-16','2014-01-31',20),(1,'kolacja','2014-01-16','2014-01-31',20),(1,'kolacja','2014-01-16','2014-01-31',20),(1,'kolacja','2014-01-16','2014-01-31',20),(1,'kolacja','2014-01-16','2014-01-31',20),(2,'pranie','2014-01-16','2014-01-31',5),(3,'pranie','2014-01-16','2014-01-20',5),(4,'auto-gr.B','2014-01-19','2014-01-22',130),(5,'auto-gr.A','2014-01-19','2014-01-22',100),(2,'kolacja','2014-01-18','2014-02-01',30),(2,'sniadanie','2014-01-18','2014-02-01',30),(2,'obiad','2014-01-18','2014-02-01',30),(3,'obiad','2014-01-16','2014-01-20',25),(3,'obiad','2014-01-16','2014-01-20',25),(3,'kolacja','2014-01-16','2014-01-20',25),(3,'kolacja','2014-01-16','2014-01-20',25);
/*!40000 ALTER TABLE `POBYT_USLUGA_DODATKOWA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POKOJE`
--

DROP TABLE IF EXISTS `POKOJE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `POKOJE` (
  `nr_pokoju` int(11) NOT NULL,
  `rodzaj_pokoju` varchar(5) NOT NULL,
  `stan` text NOT NULL,
  PRIMARY KEY (`nr_pokoju`),
  KEY `rodzaj_pokoju` (`rodzaj_pokoju`),
  CONSTRAINT `FK_POKOJE_TYP_POKOJU` FOREIGN KEY (`rodzaj_pokoju`) REFERENCES `TYP_POKOJU` (`rodzaj_pokoju`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POKOJE`
--

LOCK TABLES `POKOJE` WRITE;
/*!40000 ALTER TABLE `POKOJE` DISABLE KEYS */;
INSERT INTO `POKOJE` VALUES (101,'piatk','wolny'),(102,'piatk','wolny'),(103,'piatk','wolny'),(104,'dwojk','zajety'),(105,'dwojk','zajety'),(106,'exclu','zajety'),(107,'exclu','wolny'),(108,'exclu','wolny'),(109,'exclu','zajety'),(110,'para','wolny'),(111,'para','zajety'),(112,'para','wolny'),(113,'para','wolny'),(114,'dwojk','wolny'),(115,'trojk','wolny'),(116,'trojk','wolny'),(117,'trojk','wolny'),(118,'exclu','zajety'),(119,'para','zajety'),(120,'trojk','zajety');
/*!40000 ALTER TABLE `POKOJE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRAWO_DOSTEPU`
--

DROP TABLE IF EXISTS `PRAWO_DOSTEPU`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRAWO_DOSTEPU` (
  `id_prawa` int(11) NOT NULL,
  `funkcja` varchar(10) NOT NULL,
  PRIMARY KEY (`id_prawa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRAWO_DOSTEPU`
--

LOCK TABLES `PRAWO_DOSTEPU` WRITE;
/*!40000 ALTER TABLE `PRAWO_DOSTEPU` DISABLE KEYS */;
/*!40000 ALTER TABLE `PRAWO_DOSTEPU` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REZERWACJA`
--

DROP TABLE IF EXISTS `REZERWACJA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REZERWACJA` (
  `nr_rezerwacji` int(11) NOT NULL,
  `nr_dokumentu_klienta` varchar(20) NOT NULL,
  `data_rozpoczecia` date NOT NULL,
  `data_zakonczenia` date NOT NULL,
  `rabat` int(11) DEFAULT NULL,
  PRIMARY KEY (`nr_rezerwacji`),
  KEY `nr_dokumentu_klienta` (`nr_dokumentu_klienta`),
  CONSTRAINT `FK_REZERWACJA_Klient` FOREIGN KEY (`nr_dokumentu_klienta`) REFERENCES `Klient` (`nr_dokumentu_klienta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REZERWACJA`
--

LOCK TABLES `REZERWACJA` WRITE;
/*!40000 ALTER TABLE `REZERWACJA` DISABLE KEYS */;
INSERT INTO `REZERWACJA` VALUES (1,'30921919ABC','2014-05-12','2014-05-16',0),(2,'jdkslk900','2014-03-02','2014-06-02',0),(3,'7485739ghu','2014-03-01','2014-04-01',0),(4,'7485739ghu','2014-06-02','2014-07-02',0),(5,'7485739ghu','2014-01-24','2014-01-31',0),(12,'fDFE239949','2014-01-24','2014-02-04',0),(13,'20918jd34','2014-01-24','2014-02-04',0);
/*!40000 ALTER TABLE `REZERWACJA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REZERWACJA_POKOJE`
--

DROP TABLE IF EXISTS `REZERWACJA_POKOJE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REZERWACJA_POKOJE` (
  `nr_rezerwacji` int(11) NOT NULL,
  `nr_pokoju` int(11) NOT NULL,
  `cena_pokoju` int(11) NOT NULL,
  KEY `nr_pokoju` (`nr_pokoju`),
  KEY `nr_rezerwacji` (`nr_rezerwacji`),
  CONSTRAINT `FK_REZERWACJA_POKOJE_POKOJE` FOREIGN KEY (`nr_pokoju`) REFERENCES `POKOJE` (`nr_pokoju`),
  CONSTRAINT `FK_REZERWACJA_POKOJE_REZERWACJA` FOREIGN KEY (`nr_rezerwacji`) REFERENCES `REZERWACJA` (`nr_rezerwacji`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REZERWACJA_POKOJE`
--

LOCK TABLES `REZERWACJA_POKOJE` WRITE;
/*!40000 ALTER TABLE `REZERWACJA_POKOJE` DISABLE KEYS */;
INSERT INTO `REZERWACJA_POKOJE` VALUES (1,113,120),(2,107,150),(2,108,150),(3,101,125),(4,112,100),(5,116,135),(12,101,100),(13,102,100);
/*!40000 ALTER TABLE `REZERWACJA_POKOJE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RODZAJ_DOKUMENTU`
--

DROP TABLE IF EXISTS `RODZAJ_DOKUMENTU`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RODZAJ_DOKUMENTU` (
  `rodzaj_dokumentu` varchar(20) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RODZAJ_DOKUMENTU`
--

LOCK TABLES `RODZAJ_DOKUMENTU` WRITE;
/*!40000 ALTER TABLE `RODZAJ_DOKUMENTU` DISABLE KEYS */;
INSERT INTO `RODZAJ_DOKUMENTU` VALUES ('paszport'),('dowod_osobisty'),('prawo_jazdy');
/*!40000 ALTER TABLE `RODZAJ_DOKUMENTU` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SEZON`
--

DROP TABLE IF EXISTS `SEZON`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SEZON` (
  `nr_sezonu` varchar(10) NOT NULL,
  `data_rozpoczecia` date NOT NULL,
  `data_zakonczenia` date NOT NULL,
  PRIMARY KEY (`nr_sezonu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SEZON`
--

LOCK TABLES `SEZON` WRITE;
/*!40000 ALTER TABLE `SEZON` DISABLE KEYS */;
/*!40000 ALTER TABLE `SEZON` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SEZON_TYP_POKOJU`
--

DROP TABLE IF EXISTS `SEZON_TYP_POKOJU`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SEZON_TYP_POKOJU` (
  `rodzaj_pokoju` varchar(5) NOT NULL,
  `nr_sezonu` varchar(10) DEFAULT NULL,
  `cena_pokoju` float DEFAULT NULL,
  KEY `nr_sezonu` (`nr_sezonu`),
  KEY `rodzaj_pokoju` (`rodzaj_pokoju`),
  CONSTRAINT `FK_SEZON_TYP_POKOJU_SEZON` FOREIGN KEY (`nr_sezonu`) REFERENCES `SEZON` (`nr_sezonu`),
  CONSTRAINT `FK_SEZON_TYP_POKOJU_TYP_POKOJU` FOREIGN KEY (`rodzaj_pokoju`) REFERENCES `TYP_POKOJU` (`rodzaj_pokoju`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SEZON_TYP_POKOJU`
--

LOCK TABLES `SEZON_TYP_POKOJU` WRITE;
/*!40000 ALTER TABLE `SEZON_TYP_POKOJU` DISABLE KEYS */;
/*!40000 ALTER TABLE `SEZON_TYP_POKOJU` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TYP_POKOJU`
--

DROP TABLE IF EXISTS `TYP_POKOJU`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TYP_POKOJU` (
  `rodzaj_pokoju` varchar(5) NOT NULL,
  `ilosc_lozek` int(11) NOT NULL,
  `cena_pokoju` float NOT NULL,
  PRIMARY KEY (`rodzaj_pokoju`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TYP_POKOJU`
--

LOCK TABLES `TYP_POKOJU` WRITE;
/*!40000 ALTER TABLE `TYP_POKOJU` DISABLE KEYS */;
INSERT INTO `TYP_POKOJU` VALUES ('dwojk',2,140),('exclu',1,100),('para',2,150),('piatk',5,125),('trojk',3,13);
/*!40000 ALTER TABLE `TYP_POKOJU` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USLUGA_DODATKOWA`
--

DROP TABLE IF EXISTS `USLUGA_DODATKOWA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USLUGA_DODATKOWA` (
  `nazwa_uslugi` varchar(10) NOT NULL,
  `cena` float DEFAULT NULL,
  `opis` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USLUGA_DODATKOWA`
--

LOCK TABLES `USLUGA_DODATKOWA` WRITE;
/*!40000 ALTER TABLE `USLUGA_DODATKOWA` DISABLE KEYS */;
INSERT INTO `USLUGA_DODATKOWA` VALUES ('pranie',5,'Cena za jedna pralke. Pranie nalezy przyniesc do pralni.'),('obiad',NULL,'Cena podawana przy zlozeniu zamowienia w restauracji'),('sniadanie',NULL,'Cena podawana przy zlozeniu zamowienia w restauracji'),('auto-gr.A',100,'Cena za dobe. Auto 3 drzwiowe.'),('auto-gr.B',130,'Cena za dobe. Auto 5 drzwiowe.'),('kolacja',NULL,'Cena podawana przy zlozeniu zamowienia w restauracji');
/*!40000 ALTER TABLE `USLUGA_DODATKOWA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UZYTKOWNIK`
--

DROP TABLE IF EXISTS `UZYTKOWNIK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UZYTKOWNIK` (
  `nazwisko` varchar(10) NOT NULL,
  `login_uzytkownik` varchar(10) NOT NULL,
  `haslo` varchar(10) NOT NULL,
  PRIMARY KEY (`login_uzytkownik`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UZYTKOWNIK`
--

LOCK TABLES `UZYTKOWNIK` WRITE;
/*!40000 ALTER TABLE `UZYTKOWNIK` DISABLE KEYS */;
INSERT INTO `UZYTKOWNIK` VALUES ('Tokarczuk','g.tokarczu','32koL_ssa'),('Krzak','p.krzak','sosna'),('Swierk','s.swierk','szkola111');
/*!40000 ALTER TABLE `UZYTKOWNIK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UZYTKOWNIK_PRAWA`
--

DROP TABLE IF EXISTS `UZYTKOWNIK_PRAWA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UZYTKOWNIK_PRAWA` (
  `id_prawa` int(11) NOT NULL,
  `login_uzytkownika` varchar(10) NOT NULL,
  `id_Hotel` char(3) DEFAULT NULL,
  KEY `id_Hotel` (`id_Hotel`),
  KEY `id_prawa` (`id_prawa`),
  KEY `login_uzytkownika` (`login_uzytkownika`),
  CONSTRAINT `FK_UZYTKOWNIK_PRAWA_Hotel` FOREIGN KEY (`id_Hotel`) REFERENCES `Hotel` (`id_Hotel`),
  CONSTRAINT `FK_UZYTKOWNIK_PRAWA_PRAWO_DOSTEPU` FOREIGN KEY (`id_prawa`) REFERENCES `PRAWO_DOSTEPU` (`id_prawa`),
  CONSTRAINT `FK_UZYTKOWNIK_PRAWA_UZYTKOWNIK` FOREIGN KEY (`login_uzytkownika`) REFERENCES `UZYTKOWNIK` (`login_uzytkownik`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UZYTKOWNIK_PRAWA`
--

LOCK TABLES `UZYTKOWNIK_PRAWA` WRITE;
/*!40000 ALTER TABLE `UZYTKOWNIK_PRAWA` DISABLE KEYS */;
/*!40000 ALTER TABLE `UZYTKOWNIK_PRAWA` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-01-20 17:52:34
