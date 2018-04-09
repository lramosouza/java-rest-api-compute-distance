-- TABLE CREATION
CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `latitude` double(9,6) unsigned NOT NULL,
  `longitude` double(9,6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `idx_city_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8

--LOAD

INSERT INTO `city` (`id`,`name`,`latitude`,`longitude`) VALUES (1,'Seattle',47.608013,-122.335167);
INSERT INTO `city` (`id`,`name`,`latitude`,`longitude`) VALUES (2,'Chicago',41.881832,-87.623177);
INSERT INTO `city` (`id`,`name`,`latitude`,`longitude`) VALUES (2,'Boston',42.361145,-71.057083);

-- Schema name = itau
-- Connection URL = jdbc:mysql://localhost:3306/itau
-- MYSQL credentials user=root pass=root