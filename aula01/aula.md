# Banco de Dados

Armazenar e recuperar dados

Bancos de dados relacionais - Tabelas e Relacionamentos

Bancos de dados não relacionais - Documentos, grafos, ...

PostgreSQL - Banco de dados relacional open source

## Links

https://www.postgresql.org/

https://dbeaver.io/download/

https://www.elephantsql.com/

https://hub.docker.com/_/postgres

https://www.postgresql.org/docs/current/sql-commands.html

## Instalação com Docker

docker run --name postgres-2 -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres

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
    id integer PRIMARY KEY,
    nome varchar(100),
    cpf varchar(11),
    salario float
  );
  
  CREATE TABLE endereco (
    id integer PRIMARY KEY,
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

### SELECT

### UPDATE

### DELETE

## Exercícios

1) Crie a tabela parcela, ela deve referenciar a tabela emprestimo, e deve ter as colunas descritas no problema.

2) Insira 3 emprestimos, cada um que possua pelo menos 5 parcelas.

3) Busque apenas os emprestimos que tenham mais de três parcelas.

4) Faça uma consulta que some todos os valores das parcelas de um emprestimo.
