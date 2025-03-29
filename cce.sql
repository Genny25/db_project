SET GLOBAL local_infile = 1;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET GLOBAL time_zone = '+00:00';
SET time_zone = '+00:00';

START TRANSACTION;

-- Creazione del database
CREATE DATABASE IF NOT EXISTS `cce`;
USE `cce`;

-- Struttura della tabella `account`
CREATE TABLE `account` (
  `idAccount` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(50) NOT NULL UNIQUE,
  `username` VARCHAR(20) NOT NULL UNIQUE,
  `nome` VARCHAR(30) NOT NULL,
  `cognome` VARCHAR(30) NOT NULL,
  `data_nascita` DATE NOT NULL,
  `password` VARCHAR(255) NOT NULL, 
  `numero_telefono` CHAR(10) NOT NULL, 
  `luogo_nascita` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`idAccount`)
);

-- Struttura della tabella `medico`
CREATE TABLE `medico` (
  `idMedico` INT(11) NOT NULL AUTO_INCREMENT,
  `genere` ENUM('M', 'F') NOT NULL,
  `specializzazione` VARCHAR(255) NOT NULL,
  `idAccount` INT(11) NOT NULL,
  PRIMARY KEY (`idMedico`),
  FOREIGN KEY (`idAccount`) REFERENCES `account` (`idAccount`) ON DELETE CASCADE
);

-- Struttura della tabella `paziente`
CREATE TABLE `paziente` (
  `idPaziente` INT(11) NOT NULL AUTO_INCREMENT,
  `idAccount` INT(11) NOT NULL,
  `CF` CHAR(16) NOT NULL UNIQUE,
  PRIMARY KEY (`idPaziente`),
  FOREIGN KEY (`idAccount`) REFERENCES `account` (`idAccount`) ON DELETE CASCADE
);

-- Struttura della tabella `cartella clinica`
CREATE TABLE `cartella_clinica` (
  `idCartella` INT(11) NOT NULL AUTO_INCREMENT,
  `procedure_diagnostiche` VARCHAR(255) NOT NULL,
  `farmaci` VARCHAR(255) NOT NULL,
  `storia_passata` VARCHAR(255) NOT NULL,
  `trattamenti` VARCHAR(255) NOT NULL,
  `osservazioni` VARCHAR(1000) NOT NULL,
  `idPaziente` INT(11) NOT NULL,
  PRIMARY KEY (`idCartella`),
  FOREIGN KEY (`idPaziente`) REFERENCES `paziente` (`idPaziente`) ON DELETE CASCADE
);

-- Struttura della tabella `accede`
CREATE TABLE `accede` (
  `idMedico` INT(11) NOT NULL,
  `idCartella` INT(11) NOT NULL,
  PRIMARY KEY (`idMedico`, `idCartella`),
  FOREIGN KEY (`idMedico`) REFERENCES `medico` (`idMedico`) ON DELETE CASCADE,
  FOREIGN KEY (`idCartella`) REFERENCES `cartella_clinica` (`idCartella`) ON DELETE CASCADE
);

-- Struttura della tabella `appuntamento`
CREATE TABLE `appuntamento` (
  `idAppuntamento` INT(11) NOT NULL AUTO_INCREMENT,
  `data` DATE NOT NULL,
  `n.civico` VARCHAR(3) NOT NULL,
  `via` VARCHAR(255) NOT NULL,
  `CAP` INT(5) NOT NULL,
  `ora` TIME NOT NULL,
  `idMedico` INT(11) NOT NULL,
  `idPaziente` INT(11) NOT NULL,
  PRIMARY KEY (`idAppuntamento`),
  FOREIGN KEY (`idMedico`) REFERENCES `medico` (`idMedico`) ON DELETE CASCADE,
  FOREIGN KEY (`idPaziente`) REFERENCES `paziente` (`idPaziente`) ON DELETE CASCADE
);

-- Struttura della tabella `carriera`
CREATE TABLE `carriera` (
  `idCarriera` INT(11) NOT NULL AUTO_INCREMENT,
  `ruolo` VARCHAR(255) NOT NULL,
  `data_inizio` DATE NOT NULL,
  `data_fine` DATE,
  `descrizione` VARCHAR(500) NOT NULL,
  `idMedico` INT(11) NOT NULL,
  PRIMARY KEY (`idCarriera`),
  FOREIGN KEY (`idMedico`) REFERENCES `medico` (`idMedico`) ON DELETE CASCADE
);

-- Struttura della tabella `servizio`
CREATE TABLE `servizio` (
  `idServizio` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `descrizione` VARCHAR(500) NOT NULL,
  `idMedico` INT(11) NOT NULL,
  PRIMARY KEY (`idServizio`),
  FOREIGN KEY (`idMedico`) REFERENCES `medico` (`idMedico`) ON DELETE CASCADE
);

-- Struttura della tabella `lingua`
CREATE TABLE `lingua` (
    `idLingua` INT(11) NOT NULL AUTO_INCREMENT,
    `lingua` VARCHAR(255) NOT NULL,
    `idServizio` INT(11) NOT NULL,  
    PRIMARY KEY (`idLingua`),
    FOREIGN KEY (`idServizio`) REFERENCES `servizio` (`idServizio`) ON DELETE CASCADE
);


-- Struttura della tabella `pagamento`
CREATE TABLE `pagamento` (
  `idPagamento` INT(11) NOT NULL AUTO_INCREMENT,
  `importo` DOUBLE NOT NULL,
  `data` DATE NOT NULL,
  `tipo` ENUM('contanti', 'carta') NOT NULL,
  `causale` VARCHAR(255) NOT NULL,
  `idAppuntamento` INT(11) NOT NULL,
  PRIMARY KEY (`idPagamento`),
  FOREIGN KEY (`idAppuntamento`) REFERENCES `appuntamento` (`idAppuntamento`) ON DELETE CASCADE
);

-- Struttura della tabella `test`
CREATE TABLE `test` (
  `idTest` INT(11) NOT NULL AUTO_INCREMENT,
  `esito` VARCHAR(255) NOT NULL,
  `tipo` VARCHAR(255) NOT NULL,
  `data` DATE NOT NULL,
  `idMedico` INT(11) NOT NULL,
  `idPaziente` INT(11) NOT NULL,
  `idCartella` INT(11) NOT NULL,
  PRIMARY KEY (`idTest`),
  FOREIGN KEY (`idMedico`) REFERENCES `medico` (`idMedico`) ON DELETE CASCADE,
  FOREIGN KEY (`idPaziente`) REFERENCES `paziente` (`idPaziente`) ON DELETE CASCADE,
  FOREIGN KEY (`idCartella`) REFERENCES `cartella_clinica` (`idCartella`) ON DELETE CASCADE
);

COMMIT;
