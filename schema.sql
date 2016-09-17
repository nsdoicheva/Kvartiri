-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema project
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `project` ;

-- -----------------------------------------------------
-- Schema project
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `project` DEFAULT CHARACTER SET utf8 ;
USE `project` ;

-- -----------------------------------------------------
-- Table `project`.`Profile_Pictures`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project`.`Profile_Pictures` ;

CREATE TABLE IF NOT EXISTS `project`.`Profile_Pictures` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `path_to_picture` VARCHAR(2083) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project`.`Users` ;

CREATE TABLE IF NOT EXISTS `project`.`Users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `phoneNumber` VARCHAR(15) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `Profile_Picture_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `phone number_UNIQUE` (`phoneNumber` ASC),
  UNIQUE INDEX `e-mail_UNIQUE` (`email` ASC),
  INDEX `fk_User_profile_picture1_idx` (`Profile_Picture_id` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  CONSTRAINT `fk_User_profile_picture1`
    FOREIGN KEY (`Profile_Picture_id`)
    REFERENCES `project`.`Profile_Pictures` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project`.`Registered_Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project`.`Registered_Users` ;

CREATE TABLE IF NOT EXISTS `project`.`Registered_Users` (
  `User_id` INT NOT NULL,
  INDEX `fk_Registered_User_User1_idx` (`User_id` ASC),
  PRIMARY KEY (`User_id`),
  UNIQUE INDEX `User_id_UNIQUE` (`User_id` ASC),
  CONSTRAINT `fk_Registered_User_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `project`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project`.`Admin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project`.`Admin` ;

CREATE TABLE IF NOT EXISTS `project`.`Admin` (
  `User_id` INT NOT NULL,
  PRIMARY KEY (`User_id`),
  INDEX `fk_Admin_User1_idx` (`User_id` ASC),
  UNIQUE INDEX `User_id_UNIQUE` (`User_id` ASC),
  CONSTRAINT `fk_Admin_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `project`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project`.`Agencies`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project`.`Agencies` ;

CREATE TABLE IF NOT EXISTS `project`.`Agencies` (
  `User_id` INT NOT NULL,
  INDEX `fk_Agency_User1_idx` (`User_id` ASC),
  PRIMARY KEY (`User_id`),
  UNIQUE INDEX `User_id_UNIQUE` (`User_id` ASC),
  CONSTRAINT `fk_Agency_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `project`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project`.`Ads`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project`.`Ads` ;

CREATE TABLE IF NOT EXISTS `project`.`Ads` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `price` DOUBLE NOT NULL,
  `neighbourhood` VARCHAR(255) NOT NULL,
  `type` VARCHAR(7) NOT NULL,
  `square_meters` DOUBLE NOT NULL,
  `upload_date` DATE NOT NULL,
  `rooms` TINYINT(25) NOT NULL,
  `User_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_Ad_User1_idx` (`User_id` ASC),
  CONSTRAINT `fk_Ad_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `project`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project`.`Images`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project`.`Images` ;

CREATE TABLE IF NOT EXISTS `project`.`Images` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `path_to_image` VARCHAR(45) NOT NULL,
  `Ad_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Image_Ad1_idx` (`Ad_id` ASC),
  CONSTRAINT `fk_Image_Ad1`
    FOREIGN KEY (`Ad_id`)
    REFERENCES `project`.`Ads` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project`.`Messages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project`.`Messages` ;

CREATE TABLE IF NOT EXISTS `project`.`Messages` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `content` TEXT NOT NULL,
  `User_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Message_User1_idx` (`User_id` ASC),
  CONSTRAINT `fk_Message_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `project`.`Users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project`.`sent_message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project`.`sent_message` ;

CREATE TABLE IF NOT EXISTS `project`.`sent_message` (
  `Message_id` INT NOT NULL,
  `sent_to_username` VARCHAR(45) NOT NULL,
  INDEX `fk_sent_message_Message1_idx` (`Message_id` ASC),
  PRIMARY KEY (`Message_id`),
  CONSTRAINT `fk_sent_message_Message1`
    FOREIGN KEY (`Message_id`)
    REFERENCES `project`.`Messages` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project`.`received_message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project`.`received_message` ;

CREATE TABLE IF NOT EXISTS `project`.`received_message` (
  `Message_id` INT NOT NULL,
  `received_from_username` VARCHAR(45) NOT NULL,
  INDEX `fk_received_message_Message1_idx` (`Message_id` ASC),
  PRIMARY KEY (`Message_id`),
  CONSTRAINT `fk_received_message_Message1`
    FOREIGN KEY (`Message_id`)
    REFERENCES `project`.`Messages` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
