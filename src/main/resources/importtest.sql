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
  `id` INT NOT NULL AUTO_INCREMENT,
  `variant` INT NOT NULL,
  `marketname` VARCHAR(45) NOT NULL,
  `firstnum` INT NOT NULL,
  `secondnum` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk1_idx` (`marketname` ASC),
  CONSTRAINT `fk1`
  FOREIGN KEY (`marketname`)
  REFERENCES `domino`.`market` (`name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
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
INSERT INTO `domino`.`market` SELECT 'MyAllChains', id FROM `domino`.`domino` where id in (1,2,9,13,17,24);
INSERT INTO `domino`.`chain`
select null, 0, 'MyAllChains', 2, 5 from dual union all
select null, 0, 'MyAllChains', 5, 4 from dual union all
select null, 1, 'MyAllChains', 0, 0 from dual union all
select null, 1, 'MyAllChains', 0, 1 from dual union all
select null, 1, 'MyAllChains', 1, 6 from dual union all
select null, 2, 'MyAllChains', 5, 2 from dual union all
select null, 2, 'MyAllChains', 2, 1 from dual union all
select null, 2, 'MyAllChains', 1, 6 from dual union all
select null, 3, 'MyAllChains', 4, 5 from dual union all
select null, 3, 'MyAllChains', 5, 2 from dual union all
select null, 3, 'MyAllChains', 2, 1 from dual union all
select null, 3, 'MyAllChains', 1, 0 from dual union all
select null, 3, 'MyAllChains', 0, 0 from dual union all
select null, 4, 'MyAllChains', 6, 1 from dual union all
select null, 4, 'MyAllChains', 1, 2 from dual union all
select null, 4, 'MyAllChains', 2, 5 from dual union all
select null, 4, 'MyAllChains', 5, 4 from dual union all
select null, 5, 'MyAllChains', 2, 1 from dual union all
select null, 5, 'MyAllChains', 1, 0 from dual union all
select null, 5, 'MyAllChains', 0, 0 from dual union all
select null, 6, 'MyAllChains', 4, 5 from dual union all
select null, 6, 'MyAllChains', 5, 2 from dual union all
select null, 6, 'MyAllChains', 2, 1 from dual union all
select null, 6, 'MyAllChains', 1, 6 from dual union all
select null, 7, 'MyAllChains', 0, 0 from dual union all
select null, 7, 'MyAllChains', 0, 1 from dual union all
select null, 7, 'MyAllChains', 1, 2 from dual union all
select null, 7, 'MyAllChains', 2, 5 from dual union all
select null, 7, 'MyAllChains', 5, 4 from dual union all
select null, 8, 'MyAllChains', 0, 1 from dual union all
select null, 8, 'MyAllChains', 1, 6 from dual union all
select null, 9, 'MyAllChains', 2, 1 from dual union all
select null, 9, 'MyAllChains', 1, 6 from dual union all
select null, 10, 'MyAllChains', 1, 2 from dual union all
select null, 10, 'MyAllChains', 2, 5 from dual union all
select null, 10, 'MyAllChains', 5, 4 from dual union all
select null, 11, 'MyAllChains', 5, 2 from dual union all
select null, 11, 'MyAllChains', 2, 1 from dual union all
select null, 11, 'MyAllChains', 1, 0 from dual union all
select null, 11, 'MyAllChains', 0, 0 from dual union all
select null, 12, 'MyAllChains', 0, 1 from dual union all
select null, 12, 'MyAllChains', 1, 2 from dual union all
select null, 12, 'MyAllChains', 2, 5 from dual union all
select null, 12, 'MyAllChains', 5, 4 from dual union all
select null, 13, 'MyAllChains', 6, 1 from dual union all
select null, 13, 'MyAllChains', 1, 0 from dual union all
select null, 13, 'MyAllChains', 0, 0 from dual union all
select null, 14, 'MyAllChains', 1, 0 from dual union all
select null, 14, 'MyAllChains', 0, 0 from dual;
INSERT INTO `domino`.`market` SELECT 'MyLongestChains', id FROM `domino`.`domino` where id in (1,3,5,16,21);
INSERT INTO `domino`.`chain`
select null, 0, 'MyLongestChains', 2, 0 from dual union all
select null, 0, 'MyLongestChains', 0, 0 from dual union all
select null, 0, 'MyLongestChains', 0, 4 from dual union all
select null, 0, 'MyLongestChains', 4, 2 from dual union all
select null, 1, 'MyLongestChains', 4, 0 from dual union all
select null, 1, 'MyLongestChains', 0, 0 from dual union all
select null, 1, 'MyLongestChains', 0, 2 from dual union all
select null, 1, 'MyLongestChains', 2, 4 from dual union all
select null, 2, 'MyLongestChains', 0, 4 from dual union all
select null, 2, 'MyLongestChains', 4, 2 from dual union all
select null, 2, 'MyLongestChains', 2, 0 from dual union all
select null, 2, 'MyLongestChains', 0, 0 from dual union all
select null, 3, 'MyLongestChains', 0, 0 from dual union all
select null, 3, 'MyLongestChains', 0, 4 from dual union all
select null, 3, 'MyLongestChains', 4, 2 from dual union all
select null, 3, 'MyLongestChains', 2, 0 from dual union all
select null, 4, 'MyLongestChains', 0, 2 from dual union all
select null, 4, 'MyLongestChains', 2, 4 from dual union all
select null, 4, 'MyLongestChains', 4, 0 from dual union all
select null, 4, 'MyLongestChains', 0, 0 from dual union all
select null, 5, 'MyLongestChains', 0, 0 from dual union all
select null, 5, 'MyLongestChains', 0, 2 from dual union all
select null, 5, 'MyLongestChains', 2, 4 from dual union all
select null, 5, 'MyLongestChains', 4, 0 from dual union all
select null, 6, 'MyLongestChains', 2, 4 from dual union all
select null, 6, 'MyLongestChains', 4, 0 from dual union all
select null, 6, 'MyLongestChains', 0, 0 from dual union all
select null, 6, 'MyLongestChains', 0, 2 from dual union all
select null, 7, 'MyLongestChains', 4, 2 from dual union all
select null, 7, 'MyLongestChains', 2, 0 from dual union all
select null, 7, 'MyLongestChains', 0, 0 from dual union all
select null, 7, 'MyLongestChains', 0, 4 from dual;