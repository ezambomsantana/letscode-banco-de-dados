# Aulas

1 - MySQL e SQL

2 - Conexão do banco de dados com Java

3 - MongoDB

# Banco de Dados

Armazenar e recuperar dados

Bancos de dados relacionais - Tabelas e Relacionamentos

Bancos de dados não relacionais - Documentos, grafos, ...

MySQL - Banco de dados relacional open source

## Links

https://www.mysql.com/

https://dbeaver.io/download/

https://hub.docker.com/_/mysql

https://dev.mysql.com/doc/refman/8.0/en/

## Problema

Precisamos criar um sistema para o gerenciamento de emprestimos, o sistema deve ter os dados dos clientes que pedem os emprestimos, os emprestimos e também as 
parcelas que compõem o emprestimo.

Os dados do cliente que devem ser salvos são o nome, cpf, salario e endereço.

Os emprestimos devem ter um ou mais cliente, e os dados que devem ser salvos são o valor total do emprestimo, a quantidade de parcelas e a data do empréstimo.

Os emprestimos possuem uma quantidade de parcelas, as parcelas devem conter um valor, o número sequencial, o status e a data de pagamento.

## DDL

Linguagem de definição de dados

### CREATE DATABASE

    CREATE DATABASE emprestimo;

### DROP DATABASE

    DROP DATABASE emprestimo;

### CREATE TABLE

    CREATE TABLE pessoa (
      id serial PRIMARY KEY,
      nome varchar(100) NOT NULL,
      cpf varchar(11) UNIQUE,
      salario float
    );

    CREATE TABLE endereco (
      id serial PRIMARY KEY,
      rua varchar(100),
      numero integer,
      cidade varchar(100),
      cep varchar(10),
      id_pessoa integer REFERENCES pessoa(id)
    );
  

### ALTER TABLE

    ALTER TABLE pessoa ADD COLUMN data_nascimento DATE;
    ALTER TABLE endereco DROP COLUMN numero;

### DROP TABLE

    DROP TABLE endereco;
    DROP TABLE pessoa;

## DML

Linguagem de manipulação de dados

### INSERT

    INSERT INTO pessoa VALUES(1, 'Eduardo', '123', 1000);
    INSERT INTO pessoa VALUES(2, 'Joao', '123', 1000);

    INSERT INTO pessoa(id, nome, cpf) VALUES(3, 'Maria', '234');
    INSERT INTO pessoa(nome, cpf) VALUES('Lucas', '456');
    INSERT INTO pessoa(cpf) VALUES('456');

### SELECT

    select *
    from pessoa
    where nome like 'Eduardo'

### UPDATE

    update pessoa
    set salario = 3000;

    update pessoa 
    set salario = 5000
    where nome like 'Eduardo';

### DELETE

    delete from pessoa
    where id = 7;

## Exercícios

1) Crie a tabela parcela, ela deve referenciar a tabela emprestimo, e deve ter as colunas descritas no problema.

2) Insira 3 emprestimos, cada um que possua pelo menos 5 parcelas.

3) Busque apenas os emprestimos que tenham mais de três parcelas.

4) Faça uma consulta que some todos os valores das parcelas de um emprestimo.

### DDL

        create table pessoa (
          id serial PRIMARY KEY,
          nome varchar(100) NOT NULL,
          cpf varchar(11) UNIQUE,
          salario float CHECK(salario > 0),
          endereco varchar(100) not null
        );

         create table emprestimo (
          id serial PRIMARY KEY,
          valor float NOT NULL,
          num_parcelas integer,
          data_emprestimo TIMESTAMP,
          status varchar(100) not null default 'ABERTO'
        );


        CREATE TABLE parcela (
          id serial PRIMARY KEY,
          valor float NOT NULL,
          num varchar(11) UNIQUE,
          status varchar(100) not null default 'ABERTO',
          data_pagamento TIMESTAMP,
          id_emprestimo integer references emprestimo(id)
        );

       create table pessoa_emprestimo(
            id_pessoa integer references pessoa(id),
            id_emprestimo integer references emprestimo(id),
            primary key(id_pessoa, id_emprestimo)
       );
    
      drop table pessoa_emprestimo ;
      drop table parcela;
      drop table pessoa ;
      drop table emprestimo;

### DML

      insert into pessoa(nome, c    pf, salario) values('Eduardo', '123', 5000);
      insert into emprestimo(valor, num_parcelas, data_emprestimo) values(10000, 10, '2022-01-01');

      insert into pessoa_emprestimo values(1, 1);

      insert into parcela(valor, num, id_emprestimo) values(2200,1,1);
      insert into parcela(valor, num, id_emprestimo) values(2200,2,1);
      insert into parcela(valor, num, id_emprestimo) values(2200,3,1);
      insert into parcela(valor, num, id_emprestimo) values(2200,4,1);
      insert into parcela(valor, num, id_emprestimo) values(2200,5,1);
     


      





    CREATE TABLE cliente (
        id integer PRIMARY KEY AUTO_INCREMENT,
        nome varchar(100) NOT NULL,
        cpf varchar(11) UNIQUE,
        salario float NOT NULL,
        endereco varchar(100)
    );

    CREATE TABLE emprestimo (
        id integer PRIMARY KEY AUTO_INCREMENT,
        valor_total float NOT NULL,
        quantidade_parcela integer NOT NULL,
        data_emprestimo TIMESTAMP
    );

    CREATE TABLE cliente_emprestimo (
        id_cliente integer,
        id_emprestimo integer,
        PRIMARY KEY(id_cliente, id_emprestimo),
        FOREIGN KEY ce_cliente(id_cliente) REFERENCES cliente(id),
        FOREIGN KEY ce_emprestimo(id_emprestimo) REFERENCES emprestimo(id)
    );

    CREATE TABLE parcela (
        id integer PRIMARY KEY AUTO_INCREMENT,
        valor float NOT NULL,
        data_pagamento TIMESTAMP,
        num_sequencial integer NOT NULL,
        id_emprestimo integer NOT NULL,
        FOREIGN KEY parcela_emprestimo(id_emprestimo) REFERENCES emprestimo(id)
    );



INSERT INTO cliente VALUES(1,'Eduardo', '123', 5000, 'Rua x')

INSERT INTO cliente(id, nome, cpf, salario) VALUES (2, 'Joao', '12345', 10000)

INSERT INTO cliente(id, nome, salario) VALUES (3, 'Luiz', 10000)


SELECT nome, cpf
FROM cliente
WHERE salario > 10000

SELECT MAX(salario), MIN(salario), AVG(salario), COUNT(salario), SUM(salario)
from cliente

ALTER TABLE cliente ADD COLUMN cidade VARCHAR(100)

UPDATE cliente set cidade = 'RJ' where id >= 4

SELECT cidade, AVG(salario), MAX(salario), COUNT(salario), SUM(salario)
from cliente
group by cidade

INSERT INTO emprestimo(valor_total, quantidade_parcela) VALUES (10000, 5)
INSERT INTO emprestimo(valor_total, quantidade_parcela) VALUES (50000, 5)
INSERT INTO emprestimo(valor_total, quantidade_parcela, data_emprestimo) VALUES (30000, 5, '2022-01-01)



INSERT INTO cliente_emprestimo VALUES(1, 1)
INSERT INTO cliente_emprestimo VALUES(2, 2)
INSERT INTO cliente_emprestimo VALUES(3, 2)
INSERT INTO cliente_emprestimo VALUES(4, 3)
INSERT INTO cliente_emprestimo VALUES(5, 3)



select c.cidade, SUM(e.valor_total), MIN(e.valor_total), MAX(e.valor_total), AVG(e.valor_total)
from cliente c
join cliente_emprestimo ce on c.id = ce.id_cliente
join emprestimo e on ce.id_emprestimo = e.id
group by c.cidade


select c.nome, c.salario, c.cidade, e.valor_total, e.data_emprestimo 
from cliente c
join cliente_emprestimo ce on c.id = ce.id_cliente
join emprestimo e on ce.id_emprestimo = e.id


update cliente set endereco = 'Rua xyz', salario = 10000 where cidade = 'SP' and salario = 5000


select e.id, e.valor_total, SUM(p.valor), COUNT(p.id)
from emprestimo e 
 join parcela p on e.id = p.id_emprestimo 
group by e.id

select e.id, e.valor_total, SUM(p.valor), COUNT(p.id)
from emprestimo e 
left join parcela p on e.id = p.id_emprestimo 
group by e.id

select e.id, e.valor_total, SUM(p.valor), COUNT(p.id)
from emprestimo e 
right join parcela p on e.id = p.id_emprestimo 
group by e.id

