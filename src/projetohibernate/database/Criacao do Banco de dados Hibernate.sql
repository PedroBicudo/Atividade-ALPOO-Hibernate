CREATE DATABASE HIBERNATE;

CREATE TABLE EDITORA (
	ID_EDITORA INT NOT NULL IDENTITY(1, 1),
	NOME VARCHAR(100) NOT NULL,

	CONSTRAINT PK_EDITORA PRIMARY KEY (ID_EDITORA)
);