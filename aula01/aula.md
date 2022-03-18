# Banco de Dados

## Links

https://www.postgresql.org/

https://dbeaver.com/

https://www.elephantsql.com/

https://hub.docker.com/_/postgres

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

### DROP DATABASE

### CREATE TABLE

### ALTER TABLE

### DROP TABLE

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
