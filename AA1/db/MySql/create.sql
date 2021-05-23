-- Talvez necessario criar uma table para arquivo PDF...
-- Inserir PDF na database?
-- Descricoes so varchar?
--
DROP DATABASE IF EXISTS SISTEMAEMPREGOS;

CREATE DATABASE SISTEMAEMPREGOS;

USE SISTEMAEMPREGOS;

-- Criando tables necessarias.
CREATE TABLE EMPRESA (
	ID BIGINT NOT NULL AUTO_INCREMENT,
	CNPJ BIGINT NOT NULL UNIQUE,
	NOME VARCHAR(256) NOT NULL,
	SENHA VARCHAR(64) NOT NULL,
	EMAIL VARCHAR(256) NOT NULL,
	DESCRICAO VARCHAR(256) NOT NULL,
	CIDADE VARCHAR(256) NOT NULL,
	PRIMARY KEY(ID)
);

CREATE TABLE PROFISSIONAL (
	ID BIGINT NOT NULL AUTO_INCREMENT,
	CPF BIGINT NOT NULL UNIQUE,
	NOME VARCHAR(256) NOT NULL,
	SENHA VARCHAR(64) NOT NULL,
	EMAIL VARCHAR(256) NOT NULL,
	TELEFONE VARCHAR(15),
	SEXO VARCHAR(9),
	NASCIMENTO DATE,
	PRIMARY KEY(ID)
);

CREATE TABLE USUARIO(
	ID BIGINT NOT NULL AUTO_INCREMENT,
	NOME VARCHAR(256) NOT NULL,
	LOGIN VARCHAR(20) NOT NULL UNIQUE,
	SENHA VARCHAR(64) NOT NULL,
	PAPEL VARCHAR(10),
	PRIMARY KEY (ID)
);

-- Necessario realizar o auto_increment nos beans.
CREATE TABLE VAGA (
	ID BIGINT NOT NULL,
	IDEMPRESA BIGINT NOT NULL,
	DESCRICAO VARCHAR(1000),
	REMUNERACAO FLOAT NOT NULL,
	DATALIMITE DATE NOT NULL,
	PRIMARY KEY(IDEMPRESA, ID),
	FOREIGN KEY (IDEMPRESA) REFERENCES EMPRESA(ID)
);

CREATE TABLE CANDIDATURA (
	IDVAGA BIGINT NOT NULL,
	IDEMPRESA BIGINT NOT NULL,
	IDPROFISSIONAL BIGINT NOT NULL,
	FOREIGN KEY(IDEMPRESA, IDVAGA) REFERENCES VAGA(IDEMPRESA, ID),
	FOREIGN KEY(IDPROFISSIONAL) REFERENCES PROFISSIONAL(ID),
	PRIMARY KEY(IDVAGA, IDEMPRESA, IDPROFISSIONAL)
);

CREATE TABLE FILE_CANDIDATURA (
	ID BIGINT NOT NULL AUTO_INCREMENT,
	FILENAME VARCHAR(256) NOT NULL,
	FILETYPE VARCHAR(256) NOT NULL,
	DATA MEDIUMBLOB NOT NULL,
	PRIMARY KEY(ID)
);

-- Inserindo elementos exemplo.
-- Profissionais
INSERT INTO PROFISSIONAL (CPF ,NOME ,SENHA ,EMAIL, TELEFONE ,SEXO, NASCIMENTO) VALUES(82128243068, "Roseval Junior", "rdmaljr@hotmail.com", "5539806798860", "123password", 'm', "2000-06-25");
INSERT INTO PROFISSIONAL (CPF ,NOME ,SENHA ,EMAIL, TELEFONE ,SEXO, NASCIMENTO) VALUES(44634097052, "Marcela Ribeiro", "rdmal@hotmail.com", "5577867134261","1234password", 'f', "1999-04-15");
INSERT INTO PROFISSIONAL (CPF ,NOME ,SENHA ,EMAIL, TELEFONE ,SEXO, NASCIMENTO) VALUES(35098455014, "José da SIlva", "roseval@estudante.ufscar.br", "5579768042305", "1245password", 'm', "1995-01-02");

-- Empresas
INSERT INTO EMPRESA (CNPJ ,NOME ,SENHA ,EMAIL, DESCRICAO ,CIDADE) VALUES(61189483000109, "MICROSOFT", "password123", "rdmaljr@hotmail.com", "Essa empresa vende windows", "São Carlos");
INSERT INTO EMPRESA (CNPJ ,NOME ,SENHA ,EMAIL, DESCRICAO ,CIDADE) VALUES(32989207000127, "PROGRAMAS .INC", "password1234", "rdmal@hotmail.com", "Essa empresa vende programas", "Bahia");

-- Usuarios
INSERT INTO USUARIO (NOME, LOGIN, SENHA, PAPEL) VALUES("administrador", "admin", "admin", "admin");
INSERT INTO USUARIO (NOME, LOGIN, SENHA, PAPEL) VALUES("usuario", "user", "user", "user");
-- Vagas
INSERT INTO VAGA (ID, IDEMPRESA, DESCRICAO, REMUNERACAO, DATALIMITE) VALUES(1, 1, "Vendedor de windows", 1000, "2020-06-02");
INSERT INTO VAGA (ID, IDEMPRESA, DESCRICAO, REMUNERACAO, DATALIMITE) VALUES(2, 2, "Vendedor de programas", 3000, "2020-06-10");

-- Candidaturas
INSERT INTO CANDIDATURA VALUES(1, 1, 1);
INSERT INTO CANDIDATURA VALUES(2, 2, 3);

-- PDFS (DEPOIS TESTAR).

-- Visualizando tables.
SELECT * FROM PROFISSIONAL;
SELECT * FROM EMPRESA;
SELECT * FROM USUARIO;
SELECT * FROM VAGA;
SELECT * FROM CANDIDATURA;
