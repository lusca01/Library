CREATE TABLE salas (
    idSala int NOT NULL,
    nomeSala varchar(12),
    tamanhoSala varchar(10),
    tipoSala varchar(20),
    PRIMARY KEY (idSala)
);

INSERT INTO salas (idSala,nomeSala,tamanhoSala,tipoSala)  VALUES 
('1', 'Sala 1','Pequena','Leitura'),
('2', 'Sala 2','Média','Leitura'),
('3', 'Sala 3','Média','Leitura'),
('4', 'Sala 4','Grande','Estudos');