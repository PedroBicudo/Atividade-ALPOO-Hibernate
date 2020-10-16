CREATE DATABASE HIBERNATE;

CREATE TABLE EDITORA (
	ID_EDITORA INT NOT NULL IDENTITY(1, 1),
	NOME VARCHAR(100) NOT NULL,

	CONSTRAINT PK_EDITORA PRIMARY KEY (ID_EDITORA)
);

CREATE TABLE LIVRO (
	ISBN INT NOT NULL,
	EDICAO INT NOT NULL,
	CUSTO NUMERIC(15, 2) NOT NULL,
	TITULO VARCHAR(100) NOT NULL

	CONSTRAINT PK_ISBN PRIMARY KEY(ISBN)
);

ALTER TABLE LIVRO 
	ADD IDFK_EDITORA INT NOT NULL,
	CONSTRAINT FK_EDITORA_LIVRO FOREIGN KEY(IDFK_EDITORA) REFERENCES EDITORA(ID_EDITORA)


CREATE TABLE AUTOR (
	ID_AUTOR INT NOT NULL IDENTITY(1,1),
	NOME VARCHAR(100) NOT NULL,

	CONSTRAINT PK_AUTOR PRIMARY KEY(ID_AUTOR)
);

CREATE TABLE EMAIL(
	ID_EMAIL INT NOT NULL,
	EMAIL VARCHAR(250) NOT NULL,

	CONSTRAINT PK_EMAIL PRIMARY KEY(ID_EMAIL)
);

ALTER TABLE EMAIL
	ADD IDFK_AUTOR INT NOT NULL,
	CONSTRAINT FK_AUTOR_EMAIL FOREIGN KEY(IDFK_AUTOR) REFERENCES AUTOR(ID_AUTOR)

CREATE TABLE AUTORIA_LIVRO (
	IDFK_AUTOR INT NOT NULL,
	IDFK_LIVRO INT NOT NULL,

	CONSTRAINT PK_AUTORIA_LIVRO PRIMARY KEY(IDFK_AUTOR, IDFK_LIVRO)
);

ALTER TABLE AUTORIA_LIVRO 
	ADD CONSTRAINT FK_AUTOR_AUTORIA_LIVRO FOREIGN KEY(IDFK_AUTOR) REFERENCES AUTOR(ID_AUTOR)

ALTER TABLE AUTORIA_LIVRO 
	ADD CONSTRAINT FK_LIVRO_AUTORIA_LIVRO FOREIGN KEY(IDFK_LIVRO) REFERENCES LIVRO(ISBN)


