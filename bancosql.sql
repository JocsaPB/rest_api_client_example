CREATE DATABASE RESTAPIPROJECT;

USE RESTAPIPROJECT;

CREATE TABLE CLIENTE(
    IDCLIENTE INT PRIMARY KEY AUTO_INCREMENT,
	NOME VARCHAR(50) NOT NULL, 
    DTNASCIMENTO TIMESTAMP,
    SEXO CHAR(1),
    DHCRIACAO datetime NOT NULL,
    DHATUALIZACAO datetime NOT NULL,
    STATUSREGISTRO INT(1) NOT NULL
);

CREATE TABLE CONTATO(
	IDCONTATO INT PRIMARY KEY AUTO_INCREMENT,
    TIPOCONTATO INT(1) NOT NULL,
    CONTATO VARCHAR(40) NOT NULL,
    ID_CLIENTE INT NOT NULL,
    DHCRIACAO datetime NOT NULL,
    DHATUALIZACAO datetime NOT NULL,
    STATUSREGISTRO INT(1) NOT NULL,
    FOREIGN KEY(ID_CLIENTE) REFERENCES CLIENTE (IDCLIENTE)
);