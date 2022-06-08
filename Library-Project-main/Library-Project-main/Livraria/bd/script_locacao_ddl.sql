CREATE TABLE locacao(
    idLocacao int NOT NULL,
    nomeSala varchar(12),
    dataLocacao date,
    horaLocacao varchar(6),
    representanteLocacao varchar(20),
    PRIMARY KEY(idLocacao)
);

INSERT INTO locacao (idLocacao,nomeSala,dataLocacao,horaLocacao,representanteLocacao)  VALUES 
(1, 'Sala 1','2021-12-10','12:00','Roberto Silveira'),
(2, 'Sala 2','2021-12-11','13:00','José Ferreira'),
(3, 'Sala 3','2021-12-12','14:00','Vinicius Pedroso'),
(4, 'Sala 4','2021-12-13','15:00','Vinicius Pedroso');