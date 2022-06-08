CREATE TABLE Clientes(
    idCliente int NOT NULL,
    nomeCliente varchar(25),
    docRg char(9),
	dataNascimento date,
    dataAfiliacao date,
	telefone varchar(50),
	email varchar(50),
	PRIMARY KEY(idCliente)
);


INSERT INTO Clientes (idCliente, nomeCliente,docRg,dataNascimento,dataAfiliacao,telefone,email)  VALUES
('1','José Silva','478963202','2000-12-05','2020-05-10','11 998536312','jose.silva@gmail.com'),
('2','Maria Andrade','265879632','1982-05-30','2021-12-15','11 974151123','maria.andrade@outlook.com'),
('3','Silvana Pereira','259874103','1999-06-15','2020-10-18','11 998411544','silvana.pereira@yahoo.com'),
('4','Natalia Ribeiro','987413057','1998-11-22','2021-03-02','11 998741154','natalia.ribeiro@hotmail.com');