CREATE DATABASE MovieTheatreDB;
USE MovieTheatreDB;

DROP TABLE IF EXISTS `cards`;

CREATE TABLE `cards` (
  `card_id` int NOT NULL AUTO_INCREMENT,
  `card_number` varchar(16) NOT NULL,
  `expiry_date` varchar(5) NOT NULL,
  `cvv` char(3) DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`card_id`)
);

DROP TABLE IF EXISTS `movies`;
CREATE TABLE `movies` (
  `movie_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `summary` varchar(512) NOT NULL,
  `length` int NOT NULL,
  `air_date` date DEFAULT NULL,
  PRIMARY KEY (`movie_id`)
) ;

DROP TABLE IF EXISTS `showtimes`;
CREATE TABLE `showtimes` (
  `showtime_id` int NOT NULL AUTO_INCREMENT,
  `movie_id` int NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`showtime_id`)
);

DROP TABLE IF EXISTS `seats`;
CREATE TABLE `seats` (
  `seat_id` int NOT NULL AUTO_INCREMENT,
  `row` char NOT NULL,
  `col` int NOT NULL,
  `theatre_id` int NOT NULL,
  `reserved` tinyint DEFAULT '1',
  PRIMARY KEY (`seat_id`)
);

DROP TABLE IF EXISTS `theatres`;
CREATE TABLE `theatres` (
  `theatre_id` int NOT NULL AUTO_INCREMENT,
  `number` int NOT NULL,
  `capacity` int NOT NULL DEFAULT '100',
  `seats_occupied` int DEFAULT '0',
  `movie_shows_id` int DEFAULT NULL,
  PRIMARY KEY (`theatre_id`)
);

DROP TABLE IF EXISTS `tickets`;
CREATE TABLE `tickets` (
  `ticket_id` int NOT NULL AUTO_INCREMENT,
  `user_email` varchar(128) NOT NULL,
  `seat_id` int NOT NULL,
  `purchased_date` date NOT NULL,
  `theatre_id` int NOT NULL,
  `ticket_price` varchar(5) NOT NULL,
  `showtime` date DEFAULT NULL,
  PRIMARY KEY (`ticket_id`)
);

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(128) NOT NULL,
  `registered` tinyint(1) DEFAULT '0',
  `password` varchar(16) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `annualFeeDue` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`user_id`)
);


LOCK TABLES `cards` WRITE;
INSERT INTO `cards` VALUES 
(1,'0123456789012345','01/20','123',1),
(2,'0123456789012345','01/21','234',2),
(3,'0123456789012345','01/23','345',3),
(4,'0123456789012345','01/24','456',4),
(5,'0123456789012345','01/25','567',5),
(6,'0123456789012345','01/26','678',6),
(7,'0123456789012345','01/27','789',7);
UNLOCK TABLES;

LOCK TABLES `movies` WRITE;
INSERT INTO `movies` (`name`, `summary`, `length`, `air_date`) VALUES
('Tangled', 'A spirited young woman with magical hair embarks on an adventure with a charming bandit.', 100, '2024-11-15'),
('Moana', 'A daring Polynesian teenager sails across the ocean to save her people with the help of a demigod.', 107, '2024-11-18'),
('How to Train Your Dragon', 'A young Viking befriends a dragon and challenges the traditions of his tribe.', 98, '2024-11-22'),
('Spider-Man: Into the Spider-Verse', 'A teenager becomes the Spider-Man of his dimension and teams up with other versions of Spider-Man from alternate realities.', 117, '2024-12-01'),
('Interstellar', 'A team of explorers ventures through a wormhole to ensure humanity’s survival.', 169, '2024-12-05'),
('Jurassic Park', 'A theme park with cloned dinosaurs goes wrong, leading to a thrilling adventure for its visitors.', 127, '2024-11-30'),
('Cars', 'A race car ends up in a small town where he learns valuable life lessons about friendship and community.', 117, '2024-12-10'),
('Need for Speed', 'A mechanic embarks on a high-speed cross-country race to get revenge on a wealthy business associate.', 132, '2024-12-12');
UNLOCK TABLES;

LOCK TABLES `showtimes` WRITE;
INSERT INTO `showtimes` (`movie_id`, `time`) VALUES
(1, '2024-11-15 19:00:00'),
(2, '2024-11-18 21:30:00'),
(3, '2024-11-22 18:00:00'),
(4, '2024-12-01 20:00:00'),
(5, '2024-12-05 17:00:00'),
(6, '2024-11-30 16:30:00'),
(7, '2024-12-10 19:15:00'),
(8, '2024-12-12 20:45:00');
UNLOCK TABLES;

LOCK TABLES `theatres` WRITE;
INSERT INTO `theatres` (`number`, `capacity`, `seats_occupied`, `movie_shows_id`) VALUES
(1, 100, 50, 1),
(2, 100, 30, 2),
(3, 100, 20, 3),
(4, 100, 70, 4),
(5, 100, 90, 5);
UNLOCK TABLES;

LOCK TABLES `tickets` WRITE;
INSERT INTO `tickets` (`user_email`, `seat_id`, `purchased_date`, `theatre_id`, `ticket_price`, `showtime`) VALUES
('john.doe@example.com', 1, '2024-11-15', 1, '12.99', '2024-11-15'),
('jane.smith@example.com', 2, '2024-11-18', 1, '12.99', '2024-11-18'),
('bob.jones@example.com', 3, '2024-11-22', 1, '12.99', '2024-11-22'),
('alice.williams@example.com', 4, '2024-12-01', 1, '16.99', '2024-12-01'),
('tom.brown@example.com', 5, '2024-12-05', 2, '18.99', '2024-12-05');
UNLOCK TABLES;

LOCK TABLES `users` WRITE;
INSERT INTO `users` (`email`, `registered`, `password`, `name`, `annualFeeDue`) VALUES
('john.doe@example.com', 1, 'password123', 'John Doe', 0),
('jane.smith@example.com', 1, 'password456', 'Jane Smith', 1),
('bob.jones@example.com', 1, 'password789', 'Bob Jones', 0),
('alice.williams@example.com', 1, 'password101', 'Alice Williams', 0),
('tom.brown@example.com', 1, 'password102', 'Tom Brown', 1);
('emma.johnson@example.com', 0, 'password112', 'Emma Johnson', 1),
('david.lee@example.com', 0, 'password113', 'David Lee', 0);
UNLOCK TABLES;

LOCK TABLES `seats` WRITE;
INSERT INTO `seats` (`row`, `col`, `number`, `theatre_id`, `reserved`) VALUES
-- Row A
('A', 1, 1, 1, 0),
('A', 2, 2, 1, 1),
('A', 3, 3, 1, 0),
('A', 4, 4, 1, 0),
('A', 5, 5, 1, 0),
('A', 6, 6, 1, 0),
('A', 7, 7, 1, 0),
('A', 8, 8, 1, 0),
('A', 9, 9, 1, 0),

-- Row B
('B', 1, 1, 1, 0),
('B', 2, 2, 1, 1),
('B', 3, 3, 1, 0),
('B', 4, 4, 1, 0),
('B', 5, 5, 1, 0),
('B', 6, 6, 1, 0),
('B', 7, 7, 1, 0),
('B', 8, 8, 1, 0),
('B', 9, 9, 1, 0),

-- Row C
('C', 1, 1, 1, 0),
('C', 2, 2, 1, 1),
('C', 3, 3, 1, 0),
('C', 4, 4, 1, 0),
('C', 5, 5, 1, 0),
('C', 6, 6, 1, 0),
('C', 7, 7, 1, 0),
('C', 8, 8, 1, 0),
('C', 9, 9, 1, 0),

-- Row D
('D', 1, 1, 1, 0),
('D', 2, 2, 1, 0),
('D', 3, 3, 1, 0),
('D', 4, 4, 1, 0),
('D', 5, 5, 1, 0),
('D', 6, 6, 1, 0),
('D', 7, 7, 1, 0),
('D', 8, 8, 1, 0),
('D', 9, 9, 1, 0),

-- Row E
('E', 1, 1, 1, 0),
('E', 2, 2, 1, 0),
('E', 3, 3, 1, 0),
('E', 4, 4, 1, 0),
('E', 5, 5, 1, 0),
('E', 6, 6, 1, 0),
('E', 7, 7, 1, 0),
('E', 8, 8, 1, 0),
('E', 9, 9, 1, 0),

-- Row F
('F', 1, 1, 1, 0),
('F', 2, 2, 1, 0),
('F', 3, 3, 1, 0),
('F', 4, 4, 1, 0),
('F', 5, 5, 1, 0),
('F', 6, 6, 1, 0),
('F', 7, 7, 1, 0),
('F', 8, 8, 1, 0),
('F', 9, 9, 1, 0);
UNLOCK TABLES;
