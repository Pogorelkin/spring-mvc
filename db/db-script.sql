-- MySQL Script generated by MySQL Workbench
-- Tue Aug 20 17:17:51 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema springtaskDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema springtaskDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `springtaskDB` DEFAULT CHARACTER SET utf8 ;
USE `springtaskDB` ;

-- -----------------------------------------------------
-- Table `springtaskDB`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `springtaskDB`.`Users` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `id_UNIQUE` (`userId` ASC) VISIBLE,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `springtaskDB`.`Employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `springtaskDB`.`Employees` (
  `employeeId` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `idCardNumber` INT NOT NULL,
  PRIMARY KEY (`employeeId`),
  UNIQUE INDEX `id_UNIQUE` (`employeeId` ASC) VISIBLE,
  UNIQUE INDEX `idCardNumber_UNIQUE` (`idCardNumber` ASC) VISIBLE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `springtaskDB`.`Users`
-- -----------------------------------------------------
START TRANSACTION;
USE `springtaskDB`;
INSERT INTO `springtaskDB`.`Users` (`userId`, `login`, `password`) VALUES (1, 'Admin', 'Password');
INSERT INTO `springtaskDB`.`Users` (`userId`, `login`, `password`) VALUES (2, 'Vasya', '123');
INSERT INTO `springtaskDB`.`Users` (`userId`, `login`, `password`) VALUES (3, 'User', '123');

COMMIT;


-- -----------------------------------------------------
-- Data for table `springtaskDB`.`Employees`
-- -----------------------------------------------------
START TRANSACTION;
USE `springtaskDB`;
INSERT INTO `springtaskDB`.`Employees` (`employeeId`, `firstName`, `lastName`, `idCardNumber`) VALUES (1, 'Ivan', 'Ivanov', 111);
INSERT INTO `springtaskDB`.`Employees` (`employeeId`, `firstName`, `lastName`, `idCardNumber`) VALUES (2, 'Piotr', 'Petrov', 222);
INSERT INTO `springtaskDB`.`Employees` (`employeeId`, `firstName`, `lastName`, `idCardNumber`) VALUES (3, 'Vasily', 'Vasiliev', 333);

COMMIT;

