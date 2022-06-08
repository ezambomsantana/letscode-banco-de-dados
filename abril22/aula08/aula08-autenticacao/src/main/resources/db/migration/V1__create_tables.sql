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