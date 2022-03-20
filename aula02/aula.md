# Aula 02

## Exercício

   create table pessoa (
    id serial PRIMARY KEY,
    nome varchar(100) NOT NULL,
    cpf varchar(11) UNIQUE,
    salario float CHECK(salario > 0)
  );
  
   create table emprestimo (
    id serial PRIMARY KEY,
    valor varchar(100) NOT NULL,
    num_parcelas varchar(11) UNIQUE,
    data_emprestimo TIMESTAMP,
    status varchar(100)
  );
  
 
  CREATE TABLE parcela (
    id serial PRIMARY KEY,
    valor varchar(100) NOT NULL,
    num varchar(11) UNIQUE,
    status varchar(100) not null default 'ABERTO',
    data_patagamento TIMESTAMP,
    id_emprestimo integer references emprestimo(id)
  );
  
 create table pessoa_emprestimo(
 	id_pessoa integer references pessoa(id),
 	id_emprestimo integer references emprestimo(id),
 	primary key(id_pessoa, id_emprestimo)
 );

### DDL

### DML
