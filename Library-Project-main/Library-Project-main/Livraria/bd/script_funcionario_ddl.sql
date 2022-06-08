CREATE TABLE funcionario(
    idFuncionario int NOT NULL,
    nomeFuncionario varchar(25),
    funcaoFuncionario varchar(15),
    dataAdmissao DATE,
    salarioFuncionario double,
    PRIMARY KEY(idFuncionario)
);


INSERT INTO FUNCIONARIO (idFuncionario,nomeFuncionario,funcaoFuncionario,dataAdmissao,salarioFuncionario)  VALUES 
(1,'Carlos Silva','Caixa', '2020-01-28','1500'),
(2,'Roberto Silveira','Supervisor', '2020-01-20','3000'),
(3,'José Ferreira','Gerente', '2020-01-18','5000'),
(4,'Vinicius Pedroso','Atendente', '2020-01-22','2000');