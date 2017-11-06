CREATE SCHEMA `domino` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `domino`.`domino` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstNum` INT NULL,
  `secondNum` INT NULL,
  PRIMARY KEY (`id`));
CREATE TABLE `domino`.`market` (
  `name` VARCHAR(45) NOT NULL,
  `domino` INT NOT NULL,
  PRIMARY KEY (`name`, `domino`));
CREATE TABLE `domino`.`chain` (
  `variant` INT NOT NULL,
  `marketname` VARCHAR(45) NOT NULL,
  `domino` INT NOT NULL,
  PRIMARY KEY (`variant`, `marketname`));
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '0', '0');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '0', '1');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '0', '2');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '0', '3');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '0', '4');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '0', '5');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '0', '6');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '1', '1');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '1', '2');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '1', '3');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '1', '4');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '1', '5');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '1', '6');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '2', '2');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '2', '3');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '2', '4');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '2', '5');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '2', '6');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '3', '3');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '3', '4');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '3', '5');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '3', '6');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '4', '4');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '4', '5');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '4', '6');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '5', '5');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '5', '6');
INSERT INTO `domino`.`domino` (`id`, `firstNum`, `secondNum`) VALUES (null, '6', '6');