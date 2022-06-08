CREATE TABLE parceria(
    idParceria int AUTO_INCREMENT NOT NULL,
    tipoParceria varchar(15),
    nomeParceria varchar(20),
    dataParceria DATE,
    fornece varchar(50),
    PRIMARY KEY (idParceria)
);


INSERT INTO parceria (tipoParceria,nomeParceria,dataParceria,fornece)  VALUES 
('Editora', 'Livros SA','2020-02-28','Livros'),
('Biblioteca', 'Biblioteca do Zé','2020-05-12','Livros'),
('Editora', 'Livros Bacanas','2020-07-05','Livros'),
('Biblioteca', 'Biblioteca Municipal','2020-09-27','Livros');