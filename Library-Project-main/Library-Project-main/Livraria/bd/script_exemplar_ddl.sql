CREATE TABLE exemplar(
    idexemplar int NOT NULL,
    titulo varchar(50),
    autor varchar(50),
    npaginas int,
    anopublicacao DATE,
    PRIMARY KEY (idexemplar)
);

INSERT INTO exemplar (idexemplar,titulo,autor,npaginas,anopublicacao)  VALUES 
(1,'O Seminário','Jacques Lacan',536,'2020-01-28'),
(2,'O Morro Dos Ventos Uivantes','Emily Bronte',496,'2020-01-20'),
(3,'Os cinco porquinhos','Agatha Christie',241,'2020-01-18'),
(4,'O consentimento','Vanessa Springora',157,'2020-01-22');