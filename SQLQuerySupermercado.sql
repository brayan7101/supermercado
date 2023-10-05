CREATE DATABASE db_Supermercado;

USE db_Supermercado;

CREATE TABLE Rol
(
	idRol int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	rol VARCHAR NOT NULL
);

CREATE TABLE Usuario
(
	identification int PRIMARY KEY NOT NULL,
	nameUser VARCHAR(50) NOT NULL,
	date VARCHAR(10),
	idRol int NOT NULL,
	CONSTRAINT fk_Rol FOREIGN KEY (idRol) REFERENCES Rol (idRol),
);


INSERT INTO Rol ("rol") VALUES('Mayorista')
INSERT INTO Rol ("rol") VALUES('Normal')
INSERT INTO Rol ("rol") VALUES('VIP')