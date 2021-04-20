drop database if exists ttschool;
create database `ttschool`;
use `ttschool`;

CREATE TABLE `school` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `year` INT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `group` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `room` VARCHAR(50) NOT NULL,
  `school_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`),
  KEY `room` (`room`),
  FOREIGN KEY (school_id) REFERENCES school (id) ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `trainee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(50) NOT NULL,
  `lastName` VARCHAR(50) NOT NULL,
  `rating` INT NOT NULL,
  `group_id` INT,
  PRIMARY KEY (`id`),
  KEY `firstName` (`firstName`),
  KEY `lastName` (`lastName`),
  FOREIGN KEY (group_id) REFERENCES `group` (id) ON DELETE SET NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE `subject` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL UNIQUE,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `subject_group` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `subject_id` INT NOT NULL,
  `group_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (subject_id) REFERENCES `subject` (id) ON DELETE CASCADE,
  FOREIGN KEY (group_id) REFERENCES `group` (id) ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `school` ADD UNIQUE(`name`,`year`);