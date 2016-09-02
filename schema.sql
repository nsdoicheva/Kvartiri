-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Profile_Picture`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Profile_Picture` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Profile_Picture` (
  `id` INT NOT NULL,
  `path_to_picture` VARCHAR(2083) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`User` ;

CREATE TABLE IF NOT EXISTS `mydb`.`User` (
  `id` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `phoneNumber` VARCHAR(15) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `e-mail` VARCHAR(50) NOT NULL,
  `isBroker` TINYINT(1) NOT NULL,
  `Profile_Picture_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `phone number_UNIQUE` (`phoneNumber` ASC),
  UNIQUE INDEX `e-mail_UNIQUE` (`e-mail` ASC),
  INDEX `fk_User_profile_picture1_idx` (`Profile_Picture_id` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  CONSTRAINT `fk_User_profile_picture1`
    FOREIGN KEY (`Profile_Picture_id`)
    REFERENCES `mydb`.`Profile_Picture` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Registered_User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Registered_User` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Registered_User` (
  `User_id` INT NOT NULL,
  INDEX `fk_Registered_User_User1_idx` (`User_id` ASC),
  PRIMARY KEY (`User_id`),
  UNIQUE INDEX `User_id_UNIQUE` (`User_id` ASC),
  CONSTRAINT `fk_Registered_User_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `mydb`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Admin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Admin` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Admin` (
  `User_id` INT NOT NULL,
  PRIMARY KEY (`User_id`),
  INDEX `fk_Admin_User1_idx` (`User_id` ASC),
  UNIQUE INDEX `User_id_UNIQUE` (`User_id` ASC),
  CONSTRAINT `fk_Admin_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `mydb`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Agency`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Agency` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Agency` (
  `User_id` INT NOT NULL,
  INDEX `fk_Agency_User1_idx` (`User_id` ASC),
  PRIMARY KEY (`User_id`),
  UNIQUE INDEX `User_id_UNIQUE` (`User_id` ASC),
  CONSTRAINT `fk_Agency_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `mydb`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Ad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Ad` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Ad` (
  `id` INT NOT NULL,
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
    REFERENCES `mydb`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Image` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Image` (
  `id` INT NOT NULL,
  `path_to_image` VARCHAR(45) NOT NULL,
  `Ad_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Image_Ad1_idx` (`Ad_id` ASC),
  CONSTRAINT `fk_Image_Ad1`
    FOREIGN KEY (`Ad_id`)
    REFERENCES `mydb`.`Ad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Message` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Message` (
  `id` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `content` TEXT NOT NULL,
  `User_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Message_User1_idx` (`User_id` ASC),
  CONSTRAINT `fk_Message_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `mydb`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`sent_message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`sent_message` ;

CREATE TABLE IF NOT EXISTS `mydb`.`sent_message` (
  `Message_id` INT NOT NULL,
  `sent_to_username` VARCHAR(45) NOT NULL,
  INDEX `fk_sent_message_Message1_idx` (`Message_id` ASC),
  PRIMARY KEY (`Message_id`),
  CONSTRAINT `fk_sent_message_Message1`
    FOREIGN KEY (`Message_id`)
    REFERENCES `mydb`.`Message` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`received_message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`received_message` ;

CREATE TABLE IF NOT EXISTS `mydb`.`received_message` (
  `Message_id` INT NOT NULL,
  `received_from_username` VARCHAR(45) NOT NULL,
  INDEX `fk_received_message_Message1_idx` (`Message_id` ASC),
  PRIMARY KEY (`Message_id`),
  CONSTRAINT `fk_received_message_Message1`
    FOREIGN KEY (`Message_id`)
    REFERENCES `mydb`.`Message` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`timestamps`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`timestamps` ;

CREATE TABLE IF NOT EXISTS `mydb`.`timestamps` (
  `create_time`  NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time`  NULL);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
