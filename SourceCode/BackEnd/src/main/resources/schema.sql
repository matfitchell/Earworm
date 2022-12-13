CREATE TABLE `users` (
  `ID` int NOT NULL,
  `userID` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `musicTaste` varchar(45) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `latitude` decimal(6,5) DEFAULT NULL,
  `longitude` decimal(6,5) DEFAULT NULL,
  PRIMARY KEY (`ID`)