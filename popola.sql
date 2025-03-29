-- Popolare la tabella account
INSERT INTO `account` (`idAccount`, `email`, `username`, `nome`, `cognome`, `data_nascita`, `password`, `numero_telefono`, `luogo_nascita`) VALUES
(1, 'mario.rossi@example.com', 'mario.rossi', 'Mario', 'Rossi', '1980-01-15', SHA1('password1'), 3331234567, 'Roma'),
(2, 'luigi.bianchi@example.com', 'luigi.bianchi', 'Luigi', 'Bianchi', '1990-02-25', SHA1('password2'), 3349876543, 'Milano'),
(3, 'anna.verdi@example.com', 'anna.verdi', 'Anna', 'Verdi', '1985-05-10', SHA1('password3'), 3455678901, 'Napoli');

-- Popolare la tabella paziente
INSERT INTO `paziente` (`idPaziente`, `idAccount`, `CF`) VALUES
(1, 1, 'RSSMRA80A01H501X'),
(2, 2, 'BNCLGU90B25F205P');

-- Popolare la tabella medico
INSERT INTO `medico` (`idMedico`, `genere`, `specializzazione`, `idAccount`) VALUES
(1, 'M', 'Cardiologia', 3),
(2, 'F', 'Pediatria', 2);

-- Popolare la tabella carriera
INSERT INTO `carriera` (`idCarriera`, `ruolo`, `data_inizio`, `data_fine`, `descrizione`, `idMedico`) VALUES
(1, 'Medico Generico', '2010-01-01', '2015-12-31', 'Esperienza in clinica generale', 1),
(2, 'Specialista Cardiologia', '2016-01-01', '2024-12-31', 'Lavoro in ospedale', 1);

-- Popolare la tabella appuntamento
INSERT INTO `appuntamento` (`idAppuntamento`, `data`, `n.civico`, `via`, `CAP`, `ora`, `idMedico`, `idPaziente`) VALUES
(1, '2024-12-25', '25', 'Via Roma', '84091', '10:00:00', 1, 1),
(2, '2024-12-26', '30', 'Via Milano', '80053', '14:30:00', 2, 2);

-- Popolare la tabella pagamento
INSERT INTO `pagamento` (`idPagamento`, `importo`, `data`, `tipo`, `causale`, `idAppuntamento`) VALUES
(1, 50.00, '2024-12-25', 'contanti', 'Visita specialistica', 1),
(2, 75.00, '2024-12-26', 'carta', 'Controllo pediatrico', 2);

-- Popolare la tabella cartella clinica
INSERT INTO `cartella_clinica` (`idCartella`, `procedure_diagnostiche`, `farmaci`, `storia_passata`, `trattamenti`, `osservazioni`, `idPaziente`) VALUES
(1, 'ECG', 'Aspirina', 'Ipertensione', 'Terapia antipertensiva', 'Buona risposta alla terapia', 1),
(2, 'Visita pediatrica', 'Ibuprofene', 'Febbre ricorrente', 'Antibiotici', 'Monitorare la febbre', 2);

-- Popolare la tabella accede
INSERT INTO `accede` (`idMedico`, `idCartella`) VALUES
(1, 1),
(2, 2);

-- Popolare la tabella test
INSERT INTO `test` (`idTest`, `esito`, `tipo`, `data`, `idMedico`, `idPaziente`, `idCartella`) VALUES
(1, 'Normale', 'ECG', '2024-12-20', 1, 1, 1),
(2, 'Negativo', 'Test influenza', '2024-12-22', 2, 2, 2);

-- Popolare la tabella servizio
INSERT INTO `servizio` (`idServizio`, `nome`, `descrizione`, `idMedico`) VALUES
(1, 'Cardiologia', 'Visite cardiologiche', 1),
(2, 'Pediatria', 'Cure pediatriche', 2);

-- Popolare la tabella lingua
INSERT INTO `lingua` (`idLingua`, `lingua`, `idServizio`) VALUES
(1, 'Italiano, Inglese', 1),
(2, 'Italiano, Francesce, Inglese, Spagnolo', 2);