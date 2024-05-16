CREATE DATABASE lojainformatica;

USE lojainformatica;

CREATE TABLE computador(
idComputador int NOT NULL auto_increment PRIMARY KEY,
processador varchar(100) NOT NULL,
hd varchar(100) NOT NULL,
marca char(20)
);

DESCRIBE computador;

DELETE FROM computador
WHERE idComputador = 1;

SELECT * FROM computador;

UPDATE computador 
SET processador = 'Intel Core i5-13400',
 hd = 'Radeon RX580 8GB SSD 256+1tb' 
WHERE idComputador = 8;