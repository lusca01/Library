CREATE TABLE Eventos(
    idEvento int AUTO_INCREMENT NOT NULL,
    tipoEvento varchar(25),
    dataEvento date,
    representante char(30),
    participacao char(30),
    publicoAlvo varchar(15),
    PRIMARY KEY(idEvento)
);


INSERT INTO Eventos (tipoEvento,dataEvento,representante,participacao,publicoAlvo)  VALUES 
('Lançamento de livro', '2021-12-15','Roberto Silveira','CEO da Editora Livros SA','Todos'),
('Palestra', '2021-12-22','José Ferreira','Leonardo DiCaprio','Adulto'),
('Palestra', '2022-07-01','José Ferreira','Dono da Livraria','Adulto'),
('Outros', '2022-11-02','Roberto Silveira','Anitta','Todos');